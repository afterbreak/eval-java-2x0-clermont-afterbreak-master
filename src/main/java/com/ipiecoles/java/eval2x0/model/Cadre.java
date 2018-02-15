package com.ipiecoles.java.eval2x0.model;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Cadre extends Employe {

    Double coefficient = 1D;

    public Cadre(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double coefficient) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.coefficient = coefficient;
    }

    public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Double coefficient) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.coefficient = coefficient;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cadre)) return false;
        if (!super.equals(o)) return false;
        Cadre cadre = (Cadre) o;
        return Objects.equals(getCoefficient(), cadre.getCoefficient());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getCoefficient());
    }

    @Override
    public String toString() {
        return "Cadre{" +
                "coefficient=" + coefficient +
                '}';
    }

    @Override
    public Double getPrimeAnnuelle() {
        return LocalDate.now().getYear() * 0.5 * coefficient;
    }

}
