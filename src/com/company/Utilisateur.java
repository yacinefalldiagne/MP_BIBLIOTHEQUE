package com.company;
import java.util.*;
import com.company.Livre; // Import de la classe Bibliotheque depuis le même package



public class Utilisateur {
    private String nom;
    private String prenom;
    private int numeroIdentification;
    private ArrayList<Livre> livresEmpruntes;
    private boolean cotisationAJour;
    private int limiteEmprunts; // Limite du nombre de livres empruntés simultanément

    //Constructeur de l'utilsateur
    public Utilisateur(String nom, String prenom, int numeroIdentification,int limiteEmprunts) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroIdentification = numeroIdentification;
        this.livresEmpruntes = new ArrayList<>(); //Initialisation d'une liste d'emprunts vide
        this.limiteEmprunts = limiteEmprunts;
    }

    // Méthode pour emprunter un livre
    public void emprunterLivre(Livre livre) {
        livresEmpruntes.add(livre);
    }

    // Méthode pour retourner un livre
    public void retournerLivre(Livre livre) {
        livresEmpruntes.remove(livre);
    }

    // Méthode pour afficher les livres empruntés par l'utilisateur
    public void afficherLivresEmpruntes() {
        System.out.println("Livres empruntés par " +prenom+ " "+ nom + ":");
        for (Livre livre : livresEmpruntes) {
            System.out.println(livre.toString());
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLivresEmpruntes(ArrayList<Livre> livresEmpruntes) {
        this.livresEmpruntes = livresEmpruntes;
    }

    public void setNumeroIdentification(int numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }

    public void setLimiteEmprunts(int limiteEmprunts) {
        this.limiteEmprunts = limiteEmprunts;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return  this.prenom;
    }

    public int getNumeroIdentification(){
        return  this.numeroIdentification;
    }

    public void getLimiteEmprunts(int limiteEmprunts) {
        this.limiteEmprunts = limiteEmprunts;
    }

    public boolean getCotisationAJour() {
        return cotisationAJour;
    }

    public ArrayList<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public boolean peutEmprunter() {
        return livresEmpruntes.size() < limiteEmprunts; //retourne true si le nombre d'emprunts est inférieur au nombre de possibilités pour emprunter le livre
    }

    // Méthode pour vérifier l'éligibilité de l'utilisateur à emprunter un livre
    public boolean estEligible() {
        return this.cotisationAJour;
    }

    public void payerCotisation() {
        // Logique de paiement de la cotisation
        this.cotisationAJour = true;
        System.out.println("La cotisation a été payée avec succès.");
    }

    // Méthode pour mettre à jour l'état des cotisations
    public void setCotisationAJour(boolean etat) {
        this.cotisationAJour = etat;
    }
}
