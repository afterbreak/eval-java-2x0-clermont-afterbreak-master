package com.ipiecoles.java.eval2x0.exceptions;

import com.ipiecoles.java.eval2x0.model.Technicien;

public class TechnicienException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -46465298479125228L;

    public static final String GRADE = "Le grade doit être compris entre 1 et 5 : ";

    public TechnicienException(String message, Technicien technicien, Object valeurIncorrecte) {
        super(message + valeurIncorrecte + ", technicien : " + technicien.toString());
        System.out.println(this.getMessage());
    }
}

