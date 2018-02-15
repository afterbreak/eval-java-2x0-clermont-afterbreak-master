package com.ipiecoles.java.eval2x0.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nom;
	
	private String prenom;

	private String matricule;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateEmbauche;
	
	private Double salaire = Entreprise.SALAIRE_BASE;

	private Boolean tempsPartiel;

	private String sexe;
	
	public Employe() {
		
	}
	
	public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
		this.dateEmbauche = dateEmbauche;
		this.salaire = salaire;
		}

	/*constructeur avec les nouveaux attributs*/

	public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe) {
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
		this.dateEmbauche = dateEmbauche;
		this.salaire = salaire;
		this.tempsPartiel = tempsPartiel;
		this.sexe = sexe;
	}

	public final Integer getNombreAnneeAnciennete() {
		return LocalDate.now().getYear() - dateEmbauche.getYear();
	}
	
	public Integer getNbConges() {
		return Entreprise.NB_CONGES_BASE;
	}
	
	public abstract Double getPrimeAnnuelle();

	public void augmenterSalaire(Double pourcentage) {
		this.salaire = this.getSalaire() * (1 + pourcentage);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the dateEmbauche
	 */
	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	/**
	 * @param dateEmbauche the dateEmbauche to set
	 */
	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	/**
	 *
	 * @return the tempsPartiel
	 */
	public Boolean getTempsPartiel() {																					/*getter and setter add*/
		return tempsPartiel;
	}

	/**
	 *
	 * @param tempsPartiel the Boolean tempsPartiel to set
	 */

	public void setTempsPartiel(Boolean tempsPartiel) {
		this.tempsPartiel = tempsPartiel;
	}

	/**
	 *
	 * @return the sexe
	 */

	public String getSexe() {
		return sexe;
	}

	/**
	 *
	 * @param sexe the sexe to set
	 */

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	/**
	 * @return the salaire
	 */
	public Double getSalaire() {
		return salaire;
	}
	
	/**
	 * @param salaire the salaire to set
	 */
	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Employe{");
		sb.append("nom='").append(nom).append('\'');
		sb.append(", prenom='").append(prenom).append('\'');
		sb.append(", matricule='").append(matricule).append('\'');
		sb.append(", dateEmbauche=").append(dateEmbauche);
		sb.append(", salaire=").append(salaire);
		sb.append(", tempsPartiel=").append(tempsPartiel);
		sb.append(", sexe=").append(sexe);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Employe)) return false;
		return Objects.equals(this.hashCode(), o.hashCode());
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, prenom, matricule, dateEmbauche, salaire,tempsPartiel ,sexe);
	}

}
