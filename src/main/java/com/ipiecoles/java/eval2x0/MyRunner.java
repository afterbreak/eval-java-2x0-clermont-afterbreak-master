package com.ipiecoles.java.eval2x0;

import com.ipiecoles.java.eval2x0.exceptions.TechnicienException;
import com.ipiecoles.java.eval2x0.model.*;
import com.ipiecoles.java.eval2x0.service.CadreService;
import com.ipiecoles.java.eval2x0.service.EmployeService;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {

    public static final String REGEX_MATRICULE = "[TMCX][0-9]{5}";
    public static final String REGEX_DATE = "^(0?[1-9]|[12][0-9]|3[01])[/](0?[1-9]|1[0-2])[/](19[5-9][0-9]|20[0-4][0-9]|2050)$";
    private static final String REGEX_OUI_NON = "^[ON]$";

    @Autowired
    private EmployeService employeService;
    @Autowired
    private CadreService cadreService;

    /**
     * Méthode principale de lancement de l'application en mode console
     */
    public void run(String... strings) throws Exception {
        System.out.println("Bienvenue dans le système de gestion du personnel...");

        afficheMenuPrincipalLitChoix();
    }

    /**
     * Affichage du menu principal
     */
    private void afficheMenuPrincipalLitChoix() throws TechnicienException {
        //Affichage du menu principal
        System.out.println("=====================================");
        System.out.println("1 - Lister tous les employés");
        System.out.println("2 - Ajouter un employé");
        System.out.println("3 - Supprimer un employé");
        System.out.println("4 - Augmenter le salaire d'un employé");
        System.out.println("5 - Rechercher un employés");
        System.out.println("6 - Rechercher les employés gagnant plus qu'un certain salaire");
        System.out.println("7 - Rechercher les cadres dont le coefficient est compris dans un intervalle");
        System.out.println("0 - Quitter");
        System.out.println("=====================================");
        switch (litEntier(0,7)){
            case 1:
                afficheListeEmploye();
                break;
            case 2:
                afficheMenuAjouterEmploye();
                break;
            case 3:
                afficheMenuSupprimerEmploye();
                break;
            case 4:
                afficheMenuAugmenterEmploye();
                break;
            case 5:
                afficheMenuRechercherEmploye();
                break;
            case 6:
                afficheMenuRechercherSalaireEmploye();
                break;
            case 7:
                afficheMenuRechercherCadreCoefficient();
                break;
            case 0:
                return;

        }

        //On rappelle le menu principal lorsqu'on sort d'un sous-menu
        afficheMenuPrincipalLitChoix();
    }

    private void afficheMenuRechercherCadreCoefficient() throws TechnicienException {
        System.out.println("=====================================");
        System.out.println("Recherche des cadres dont le coefficient est dans l'intervalle de 2 coefficients: ");
        System.out.println("Saisissez le coeff de la borne inferieure : ");
        Double coeff1 = litDouble(0D,5D);
        System.out.println("Saisissez le coeff de la borne supèrieure : ");
        Double coeff2 = litDouble(0D,5D);
        List<Cadre> cadreList = cadreService.findByCoefficientBetween(coeff1, coeff2);
        if (coeff1 == coeff2 ){
            System.out.println("les coeff sont identiques");
            afficheMenuPrincipalLitChoix();
        }
        else if(cadreList.size() == 0){
            System.out.println("Il n'y a personne");
        }
        else{
            System.out.println(cadreList);
        }
    }

    /**
     *
     * afficheMenuRechercherSalaireEmploye permet de recchercher les employés en fonction de leur salaire. Retourne les employes avec un salaire inferieur ala valeur saisie
     */
    private void afficheMenuRechercherSalaireEmploye() throws TechnicienException {
        System.out.println("=====================================");
        System.out.println("Recherche des employés gagnant moins de : ");
        List<Employe> employe = employeService.findBySalaire(litDouble(0.0,10000.0));
        System.out.println(employe);
        afficheMenuPrincipalLitChoix();
    }

    /**
     * afficheMenuRechercherEmploye permet de rechercher les employe en fonction du nom , prenom ou matricule
     */
    private void afficheMenuRechercherEmploye() {
        System.out.println("=====================================");
        System.out.println("Recherche d'un employé par nom, prénom ou par matricule : ");
        List<Employe> employe = employeService.findByNomOrPrenom(litString());
        System.out.println("Veuillez ressaisir votre recherche pour confirmation : ");
        Employe employe1 = employeService.findByMatricule(litString());

        if (!(employe.isEmpty())) {
            System.out.println(employe);
        }
        else if (employe1 != null) {
            System.out.println(employe1);
        }
        else {
            System.out.println("Saisie incorrect ou employe inexistant !");
        }

    }

    /**
     *
     * afficheMenuAugmenterEmploye augmente le salaire du pourcentage voulu sur un employe selectionné par matricule
     */
    private void afficheMenuAugmenterEmploye() throws TechnicienException {
        System.out.println("=====================================");
        System.out.println("Augmenter l'employé de matricule : ");
        Employe employe = employeService.findByMatricule(litString());
        employe.augmenterSalaire(litDouble(0.0,100.0));
        employeService.saveEmploye(employe);
        afficheMenuPrincipalLitChoix();
    }

    /**
     * afficheMenuSupprimerEmploye suprime un employe en ecrivant son matricule
     *
     */
    private void afficheMenuSupprimerEmploye() throws TechnicienException {
        System.out.println("=====================================");
        System.out.println("Suppression de l'employé de matricule : ");
        Employe employe = employeService.findByMatricule(litString());
        if (employe.getMatricule().substring(0,1).equals("M")){
            System.out.println("Vous ne pouvez pas supprimer un manager avec une equipe de technicien");
        }
        else {
            employeService.deleteEmploye(employe.getId());
        }
        afficheMenuPrincipalLitChoix();
    }


    /**
     *
     * afficheMenuAjouterEmploye permet de créer un employe
     */
    private void afficheMenuAjouterEmploye() throws TechnicienException {
        String nom="";
        String prenom="";
        String matricule="";
        LocalDate dateEmpbauche;
        Double salaire=0d;
        Integer grade=0;
        Double caAnnuel=0d;
        Integer performance;
        System.out.println("=====================================");
        System.out.println("Ajout d'un employé : ");
        System.out.println("Veuillez saisir le matricule du nouvel employe : ");
        matricule = litString();

        if(matricule.substring(0,1).equals("C")) {
            System.out.println("Veuillez saisir le nom du nouveau commercial : ");
            nom = litString();
            System.out.println("Veuillez saisir le prenom du nouveau commercial : ");
            prenom=litString();
            dateEmpbauche = LocalDate.now();
            System.out.println("Veuillez saisir le salaire du nouveau commercial : ");
            salaire = litDouble(500,5000);
            System.out.println("Veuillez saisir le caAnnuel du nouveau commercial : ");
            caAnnuel = litDouble(100000,1000000);
            System.out.println("Veuillez saisir la perfomance du nouveau commercial : ");
            performance = litEntier(0,100);
            Commercial e;
            e = new Commercial(nom,prenom,matricule,dateEmpbauche,salaire,caAnnuel,performance);
            employeService.creerEmploye(e);
        }
        else if (matricule.substring(0,1).equals("T")) {
            System.out.println("Veuillez saisir le nom du nouveau technicien : ");
            nom = litString();
            System.out.println("Veuillez saisir le prenom du nouveau technicien : ");
            prenom=litString();
            dateEmpbauche = LocalDate.now();
            System.out.println("Veuillez saisir le salaire du nouveau technicien : ");
            salaire = litDouble(500,5000);
            System.out.println("Veuillez saisir le grade du nouveau technicien : ");
            litEntier(1,5);
            Technicien e;
            e = new Technicien(nom, prenom, matricule, dateEmpbauche, salaire, grade);
            employeService.creerEmploye(e);
        }
        else if (matricule.substring(0,1).equals("M")) {
            System.out.println("Veuillez saisir le nom du nouveau manager : ");
            nom = litString();
            System.out.println("Veuillez saisir le prenom du nouveau manager : ");
            prenom=litString();
            dateEmpbauche = LocalDate.now();
            System.out.println("Veuillez saisir le salaire du nouveau manager : ");
            salaire = litDouble(500,5000);
            Manager e;
            e = new Manager( nom, prenom, matricule, dateEmpbauche, salaire);
            employeService.creerEmploye(e);
        }
        else {
            System.out.println("Le matricule n'est pas valide");
        }


    }

    /**
     * afficheListeEmploye permet de paginer la lise des employe
     */
    private void afficheListeEmploye() {
        System.out.println("Liste des employés : ");
        affichePageEmploye(0);
    }

    private void affichePageEmploye(int page) {
        Page<Employe> employePage = employeService.findAllEmployes(page, 10, "matricule", "ASC");
        employePage.getContent().forEach(this::afficheEmploye);
        if(employePage.hasPrevious()){
            System.out.print("Précédent (P) ");
        }
        System.out.print(" | Sortir (X) | ");
        if(employePage.hasNext()){
            System.out.print("Suivant (S) ");
        }
        System.out.println("");
        String saisie = litString();
        if(saisie.equals("P")){
            affichePageEmploye(page - 1);
        } else if(saisie.equals("S")){
            affichePageEmploye(page + 1);
        }
    }

    /**
     * Méthode qui appelle le ToString de l'employé passé en paramètre
     * @param employe
     */
    private void afficheEmploye(Employe employe) {
        if(employe != null){
            System.out.println(employe);
        }
    }

    /**
     * Méthode qui parse une chaine de caractère en date au format DD/MM/YYYY
     * @param s chaine a parser
     * @return la date
     */
    private LocalDate parseDate(String s) {
        return LocalDate.parse(s, DateTimeFormat.forPattern("DD/MM/YYYY"));
    }

    /**
     * Lit un double entre min et max. Attention la valeur saisie doit comporter une virgule même s'il n'y a pas de décimales
     * @param min
     * @param max
     * @return la valeur saisie sous forme de Double
     */
    private Double litDouble(double min, double max){
        System.out.print("Entrer un nombre à virgule entre " + min + " et " + max + " (le séparateur des décimales doit être une virgule) : ");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextDouble()){
            double saisie = scanner.nextDouble();
            if(saisie >= min && saisie <= max){
                return saisie;
            }
        }
        //On retourne null si l'utilisateur n'a rien saisi
        return 0d;
    }

    /**
     * Lit n'importe quelle chaîne de caractère
     * @return la chaîne saisie
     */
    private String litString() {
        return litString(null);
    }

    private String litString(String regex) {
        System.out.print("Entrer une chaîne de caractère : ");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String saisie = scanner.next();
            if(regex == null || saisie.matches(regex)){
                return saisie;
            }
        }
        //On retourne null si l'utilisateur n'a rien saisi
        return "";
    }

    /**
     * Méthode permettant la lecture d'un entier compris entre min et max
     *
     * @param min Borne inférieure de l'intervalle de l'entier (inclus)
     * @param max Borne supérieure de l'intervalle de l'entier (inclus)
     * @return l'entier saisi par l'utilisateur, à condition qu'il soit valide
     */
    private int litEntier(int min, int max){
        System.out.print("Entrer un entier entre " + min + " et " + max + " : ");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            int saisie = scanner.nextInt();
            if(saisie <= max && saisie >= min) {
                //Saisie OK, on retourne l'entier
                return saisie;
            } else {
                System.out.println("Merci d'entrer un entier entre " + min + " et " + max + " : ");
            }
        }
        //On ne sortira du while que lorsque l'utilisateur aura saisi
        //un entier correct
        return 0;
    }

    public java.sql.Connection initConnection(){
        String url = "jdbc:mysql://localhost:3306/entreprise";
        String user = "root";
        String pwd = "fred271.";

        java.sql.Connection connexion = null;
        System.out.println("connexion??");

        try {
            connexion = java.sql.DriverManager.getConnection(url, user, pwd);
        } catch ( java.sql.SQLException e ) {
            //Problème de connexion à la base !
            System.out.println("probleme de connexion");
        }
        System.out.println("connect");
        return connexion;
    }
    //////connexion a la base faite, on recupere pas ce qu'il faut
    //////voir la fonction affichePageEmploye
}
