# eval-java-2x0-clermont
Eval Java 2X0 Clermont

Rappel : pour ouvrir le projet dans IntelliJ, ouvrir le fichier pom.xml et ouvrir ensuite en tant que projet.

Le code fourni doit évidemment compiler... Le fond et la forme seront évalués donc n'hésitez-pas à mettre des commentaires. Il est nécessaire de gérer les cas 
ou les méthodes ne renvoient pas de résultat (recherche par matricule par exemple) et de prévenir l'utilisateur. Avant de coder une fonctionnalité, 
commencez par regarder les méthodes déjà présentes. Bon courage ! N'hésitez-pas à vous entraider (sans abus...) et à me contacter en cas de difficultés.

// - Ajouter au mapping de la classe `Employe` les attributs `tempsPartiel` de type `Boolean` et `sexe` de type `String` avec leurs getter et setters.
// - Modifier si besoin les méthodes `toString`, `hashCode` et `equals`
// - Ajouter un constructeur prenant en compte ces nouveaux attributs

- Compléter la classe `MyRunner` pour gérer les différents menus et fonctionnalités. Un certain nombre de fonctions sont à votre disposition dans
la classe. A faire dans l'ordre que vous voulez : 
//- Afficher de manière paginée la liste des employés. On affichera (en utilisant `toString` de `Employe`) des pages de 10 employés, ordonnés selon leur matricule croissant. Une // fois la page affichée, il est possible
//      - de passer à la page suivante, s'il y en a une, en tappant "S" (pour suivant)
//      - de revenir à la page précédente, s'il y en a une avec "P" (pour précédent)
//      - de revenir au menu principal avec "X" ou n'importe quelle autre saisie
//  - Ajouter un employé en remplissant l'ensemble des champs (sauf le manager du Technicien, et l'équipe du manager). En fonction du matricule, on créera la bonne entité et on // demandera évidemment les champs spécifiques à chaque entité (grade pour Technicien...)
//  - Supprimer un employé en renseignant son matricule
//  - Augmenter un employé en renseignant son matricule
//  - Rechercher un employé par matricule, ou par nom ou prénom sans prendre en compte la casse. S'aider des méthodes déjà présentes dans `EmployeService`
//  - Rechercher les employés gagnant moins qu'un salaire qui sera saisi par l'utilisateur
  
//- Ajouter une entité `Cadre` héritant de `Employe` avec l'attribut suivant : 
//  - `coefficient` de type `Double` avec une valeur par défaut à `1d`.
//- Ajouter getters, setters, constructeurs, `toString`, `equals` et `hashCode`
//- Redéfinir `getPrimeAnnuelle` pour que la méthode renvoie la prime annuelle de base (dans la classe `Entreprise`) multipliée par le coefficient du cadre.
- Compléter `MyRunner` pour pouvoir effectuer une recherche sur les cadres uniquement renvoyant la liste des cadres dont le coefficient est entre deux valeurs saisies par l'utilisateur. Ajouter un repository et un service pour gérer l'entité `Cadre`.
