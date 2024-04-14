package com.company;

import java.util.*;

public class Livre {

    //Déclaration des attributs d'instance
    private String titre;
    private String auteur;
    private int anneePublication;
    private String ISBN;

    //Déclaration du constructeur avec les paramètres
    public Livre(String titre, String auteur, int anneePublication, String ISBN) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.ISBN = ISBN;
    }

    // Getters et setters

    //pour titre
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getTitre() {
        return titre;
    }

    //Pour Auteur
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public String getAuteur() {
        return auteur;
    }

    //Pour Anne Publication
    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }
    public int getAnneePublication() {
        return anneePublication;
    }

    //Pour ISBN
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String getISBN() {
        return ISBN;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", anneePublication=" + anneePublication +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
