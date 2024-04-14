package com.company;
import java.util.*;
import com.company.Utilisateur; // Import de la classe Bibliotheque depuis le même package
import com.company.Livre; // Import de la classe Bibliotheque depuis le même package

public class Bibliotheque {
    private ArrayList<Livre> listeLivres; // Liste des livres disponibles dans la bibliothèque
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs; // Map des livres empruntés par chaque utilisateur : chaque clé est un objet Utilisateur et chaque valeur est une liste d'objets Livre empruntés par cet utilisateur.
    private  List<Utilisateur> utilisateurs = new ArrayList<>();


    public void menu(){

        System.out.println("<---------------------------------------------------------------------------------------------->");
        System.out.println("                    Bienvenu dans votre menu principal vous pouvez:                             ");
        System.out.println("                       1- Effectuer la gestion des livres                                       ");
        System.out.println("                       2- Effectuer la gestion des emprunts                                     ");
        System.out.println("                       3- Effectuer la gestion des utilisateurs                                 ");
        System.out.println("                       4-            Quitter                                                    ");
        System.out.println("<---------------------------------------------------------------------------------------------->");

        int choix;
        Scanner sc = new Scanner(System.in);

        choix= sc.nextInt();


        switch (choix) {
            case 1:
                gestionDesLivres();
                break;
            case 2:
                gestionDesEmprunts();
                break;
            case 3:
                gestionDesUtilisateurs();
                break;

                case 4:
                    break;
            default:
                System.out.println("Choix indisponible");
        }
    }

    public void gestionDesLivres(){

        System.out.println("<---------------------------------------------------------------------------------------------->");
        System.out.println("                  Bienvenu dans votre menu de gestions des livres                               ");
        System.out.println("                      1- Ajouter un livre                                                       ");
        System.out.println("                      2- Supprimer un livre                                                     ");
        System.out.println("                      3- Rechercher un livre                                                    ");
        System.out.println("                      4- Retourner au menu principal                                            ");
        System.out.println("<---------------------------------------------------------------------------------------------->");

        int choix;
        Scanner sc = new Scanner(System.in);

        choix= sc.nextInt();


        switch (choix) {
            case 1:
                ajouterLivre();
                break;
            case 2:
                supprimerLivre();
                break;
            case 3:
                Scanner Sc = new Scanner(System.in);
                System.out.println("Entrer le critère de recherche: ISBN, nom auteur ou titre ");
                String critere = Sc.nextLine();
                Sc.nextLine();
                rechercherLivre(critere);
                break;
            case 4:
                menu();
            default:
                System.out.println("Choix indisponible");
        }
        gestionDesLivres();

    }

    public void gestionDesEmprunts(){

        System.out.println("<---------------------------------------------------------------------------------------------->");
        System.out.println("                     Bienvenu dans votre menu de gestions des emprunts                          ");
        System.out.println("                          1- Enregistrer un emprunts.                                           ");
        System.out.println("                          2- Enregistrer un retour.                                             ");
        System.out.println("                          3- Afficher les livres empruntés par un utilsateur donné              ");
        System.out.println("            4- Limiter le nombre de livres qu'un utilisateur peut emprunter simultanément.      ");
        System.out.println("                          5- Menu Principal.                                                    ");
        System.out.println("<---------------------------------------------------------------------------------------------->");


        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();


        switch (choix) {
            case 1:
                emprunterLivre();
                break;
            case 2:
                retournerLivre();
                break;
            case 3:
                afficherLivresEmpruntes();
                break;
            case 4:
                limiterEmprunts();
                break;
            case 5:
                menu();
                break;
            default:
                System.out.println("Choix indisponible");
        }
        gestionDesEmprunts();
    }

    public void gestionDesUtilisateurs(){

        System.out.println("<---------------------------------------------------------------------------------------------->");
        System.out.println("                   Bienvenu dans votre menu de gestions des utilisateurs                        ");
        System.out.println("                    1- Enregistrer de nouveaux utilisateurs                                     ");
        System.out.println("                    2- Vérifier l'éligibilité des utilisateurs à emprunter des livres           ");
        System.out.println("                    3- Consulter la liste des emprunts par utilisateur                          ");
        System.out.println("                    4- Menu principal                                                           ");
        System.out.println("<---------------------------------------------------------------------------------------------->");


        Scanner sc = new Scanner(System.in);

        int choix = sc.nextInt();


        switch (choix) {
            case 1:
                enregistrerUtilisateur();
                break;
            case 2:
                verifierEligibilite();
                break;
            case 3:
                afficherLivresEmpruntes();
            case 4:
                menu();
            default:
                System.out.println("Choix indisponible");
        }
    gestionDesUtilisateurs();
    }

    //Constructeur de la bibliothèque
    public Bibliotheque() {
        this.listeLivres = new ArrayList<>();
        this.empruntsUtilisateurs = new HashMap<>();
    }

    /*Fonctions pour un livre
     Méthode pour ajouter un livre à la bibliothèque*/
    public void ajouterLivre() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ajout d'un nouveau livre :");
        System.out.println("Entrez le titre du livre :");
        String titre = scanner.nextLine();
        System.out.println("Entrez l'auteur du livre :");
        String auteur = scanner.nextLine();
        System.out.println("Entrez l'année de publication du livre :");
        int anneePublication = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne restante après la saisie de l'année
        System.out.println("Entrez le numéro ISBN du livre :");
        String isbn = scanner.nextLine();

        Livre livre = new Livre(titre, auteur, anneePublication, isbn);
        listeLivres.add(livre);

        System.out.println("Livre ajouté avec succès !");
    }

    // Méthode pour supprimer un livre de la bibliothèque
    public void supprimerLivre() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Suppression d'un livre :");
        System.out.println("Entrez le titre ou le numéro ISBN du livre à supprimer :");
        String critereSuppression = sc.nextLine();

        Livre livreASupprimer = rechercherLivre(critereSuppression);
        if (livreASupprimer != null) {
            listeLivres.remove(livreASupprimer);
            System.out.println("Livre supprimé avec succès !");
        } else {
            System.out.println("Aucun livre trouvé avec le titre ou l'ISBN spécifié.");
        }
    }

    // Méthode pour rechercher un livre par titre, auteur ou ISBN
    public Livre rechercherLivre(String critere) {
        /*Scanner Sc = new Scanner(System.in);
        System.out.println("Entrer le critère de recherche: ISBN, nom auteur ou titre ");
        String critere = Sc.nextLine();
        Sc.nextLine();
        */
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(critere) || livre.getAuteur().equalsIgnoreCase(critere) || livre.getISBN().equalsIgnoreCase(critere)) {
                System.out.println("Livre trouvé :"+ livre.toString());

                return livre;
            }
            else  {
                System.out.println("Livre introuvable :");

            }
        }
        return null;
    }

    /* Fonctions pour emprunter
    Méthode pour enregistrer l'emprunt d'un livre par un utilisateur*/
    public void emprunterLivre() {
        Scanner scanner = new Scanner(System.in);

        // Saisie des informations de l'utilisateur
        System.out.println("Entrez le nom de l'utilisateur :");
        String nomUtilisateur = scanner.nextLine();
        System.out.println("Entrez le numéro d'identification de l'utilisateur :");
        int numeroIdentification = scanner.nextInt();

        // Recherche de l'utilisateur dans votre système
        Utilisateur utilisateur = searchUtilisateur(numeroIdentification);

        if (utilisateur == null || !utilisateur.getNom().equals(nomUtilisateur)) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }

        // Saisie des informations du livre
        scanner.nextLine(); // Pour consommer la nouvelle ligne restante après la saisie de l'identifiant
        System.out.println("Entrez le titre du livre :");
        String titreLivre = scanner.nextLine();
        System.out.println("Entrez l'auteur du livre :");
        String auteurLivre = scanner.nextLine();

        // Recherche du livre dans votre système
        Livre livre = rechercherLivre(titreLivre, auteurLivre);


        if (livre == null) {
            System.out.println("Livre non trouvé.");
            return;
        }

        // Vérification de l'éligibilité de l'utilisateur et des cotisations
        if (utilisateur.getCotisationAJour()) {
            // Vérification si l'utilisateur a atteint sa limite d'emprunts
            if (utilisateur.peutEmprunter()) {
                empruntsUtilisateurs.get(utilisateur).add(livre); // Ajout du livre aux emprunts de la bibliotheque
                 utilisateur.emprunterLivre(livre);
                System.out.println("Livre emprunté avec succès.");
            } else {
                System.out.println("Limite d'emprunts atteinte. Vous ne pouvez pas emprunter un autre livre.");
            }
        } else {
            System.out.println("Impossible d'emprunter. Veuillez régler vos cotisations. Voulez-vous les regler? (Oui/Non)");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("Oui")) {
                utilisateur.payerCotisation();
                emprunterLivre();
            }
            else if (reponse.equalsIgnoreCase("Non")) {
                utilisateur.setCotisationAJour(false);
            }


        }
    }

    public Livre rechercherLivre(String titre, String auteur) {
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(titre) && livre.getAuteur().equalsIgnoreCase(auteur)) {
                return livre;
            }
        }
        return null; // Livre non trouvé
    }

    // Méthode pour enregistrer le retour d'un livre par un utilisateur
    public void retournerLivre() {

        Scanner scanner = new Scanner(System.in);

        // Saisie des informations de l'utilisateur
        System.out.println("Entrez le nom de l'utilisateur :");
        String nomUtilisateur = scanner.nextLine();
        System.out.println("Entrez le numéro d'identification de l'utilisateur :");
        int numeroIdentification = scanner.nextInt();

        // Recherche de l'utilisateur dans votre système
        Utilisateur utilisateur = searchUtilisateur(numeroIdentification);

        if (utilisateur == null || !utilisateur.getNom().equals(nomUtilisateur)) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }

        // Saisie des informations du livre
        scanner.nextLine(); // Pour consommer la nouvelle ligne restante après la saisie de l'identifiant
        System.out.println("Entrez le titre du livre :");
        String titreLivre = scanner.nextLine();
        System.out.println("Entrez l'auteur du livre :");
        String auteurLivre = scanner.nextLine();

        // Recherche du livre dans votre système
        Livre livre = rechercherLivre(titreLivre, auteurLivre);

        if (livre == null) {
            System.out.println("Livre non trouvé.");
            return;
        }

        empruntsUtilisateurs.get(utilisateur).remove(livre);// Ici, empruntsUtilisateurs.get(utilisateur) récupère la liste des livres empruntés par l'utilisateur à partir de la carte empruntsUtilisateurs, puis la méthode remove() supprime le livre de  cette liste.
        utilisateur.retournerLivre(livre);
    }

    //Fonction pour limiter un utilisateur sur son nombre d'emprunts
    public void limiterEmprunts() {
        Scanner sc = new Scanner(System.in);

        // Demander à l'utilisateur de saisir son numéro d'identification ou un identifiant unique pour sélectionner l'utilisateur
        System.out.println("Entrez le numéro d'identification de l'utilisateur pour lequel vous souhaitez définir la limite d'emprunts :");
        int numeroIdentification = sc.nextInt();

        // Trouver l'utilisateur correspondant à partir de votre système (vous devez avoir une méthode pour cela)
        Utilisateur utilisateur = searchUtilisateur(numeroIdentification);

        // Vérifier si l'utilisateur a été trouvé
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }

        // Demander à l'administrateur de saisir la nouvelle limite d'emprunts
        System.out.println("Entrer le nombre maximum d'emprunts autorisé pour l'utilisateur " + utilisateur.getNom() + " :");
        int limite = sc.nextInt();

        // Vérifier si la limite est valide
        if (limite <= 0) {
            System.out.println("La limite d'emprunts doit être supérieure à zéro.");
            return;
        }

        // Définir la nouvelle limite d'emprunts pour cet utilisateur
        utilisateur.setLimiteEmprunts(limite);
        System.out.println("La limite d'emprunts pour l'utilisateur " + utilisateur.getNom() + " a été définie à : " + limite);
    }

    // Méthode pour trouver un utilisateur par son numéro d'identification
    public Utilisateur searchUtilisateur(int numeroIdentification) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getNumeroIdentification() == numeroIdentification) {
                return utilisateur;
            }
        }
        return null; // Utilisateur non trouvé
    }

    // Méthode pour afficher les statistiques de la bibliothèque
    public void afficherStatistiques() {
        int totalLivres = listeLivres.size(); //Ici on stocke tous les livres présents dans la bibliothéque
        int totalEmprunts = 0; //Compteur qui va stocker le nombre de livres empruntés
        for (ArrayList<Livre> emprunts : empruntsUtilisateurs.values()) {
            totalEmprunts += emprunts.size(); //On ajoute donc ici le nombre des livres empruntés
        }
        System.out.println("Nombre total de livres : " + totalLivres);
        System.out.println("Nombre total d'exemplaires empruntés : " + totalEmprunts);
    }

    // Méthode pour afficher les livres disponibles dans la bibliothèque
    public void afficherLivres() {
        if (listeLivres.isEmpty()) {
            System.out.println("La bibliothèque est vide.");
        } else {
            System.out.println("Livres disponibles dans la bibliothèque :");
            for (Livre livre : listeLivres) {
                System.out.println(livre.getTitre() + " par " + livre.getAuteur());
            }
        }
    }


    // Méthode pour afficher les livres empruntés par un utilisateur donné
    public void afficherLivresEmpruntes() {
        Scanner scanner = new Scanner(System.in);

        // Saisie des informations de l'utilisateur
        System.out.println("Entrez le nom de l'utilisateur :");
        String nomUtilisateur = scanner.nextLine();
        System.out.println("Entrez le numéro d'identification de l'utilisateur :");
        int numeroIdentification = scanner.nextInt();

        // Recherche de l'utilisateur dans votre système
        Utilisateur utilisateur = searchUtilisateur(numeroIdentification);

        if (utilisateur == null || !utilisateur.getNom().equals(nomUtilisateur)) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }

        System.out.println(utilisateur.getNom() + " a emprunté les livres suivants :");
        for (Livre livre : utilisateur.getLivresEmpruntes()) {
            System.out.println("- " + livre);
        }
        System.out.println();
    }
    /*Fonctions pour les utilisateurs
     Méthode pour enregistrer un nouvel utilisateur*/

    public void enregistrerUtilisateur() {
        Scanner scanner = new Scanner(System.in);

        // Saisie des informations de l'utilisateur
        System.out.println("Entrez le nom de l'utilisateur :");
        String nom = scanner.nextLine();
        System.out.println("Entrez le prénom de l'utilisateur :");
        String prenom = scanner.nextLine();
        System.out.println("Entrez le numéro d'identification de l'utilisateur :");
        int numeroIdentification = scanner.nextInt();
        System.out.println("Entrez la limite d'emprunts de l'utilisateur :");
        int limiteEmprunts = scanner.nextInt();
        scanner.nextLine();

        // Création de l'utilisateur avec les informations saisies
        Utilisateur utilisateur = new Utilisateur(nom, prenom, numeroIdentification, limiteEmprunts);

        System.out.println("Voulez-vous payer votre cotisation ? (Oui/Non)");
        String reponse = scanner.nextLine();
        if (reponse.equalsIgnoreCase("Oui")) {
            utilisateur.payerCotisation();
        } else if (reponse.equalsIgnoreCase("Non")) {
            utilisateur.setCotisationAJour(false);
        }

        // Ajout de l'utilisateur à la liste des utilisateurs
        utilisateurs.add(utilisateur);

        // Initialise la liste des livres empruntés pour cet utilisateur dans la HashMap empruntsUtilisateurs
        empruntsUtilisateurs.put(utilisateur, new ArrayList<>());


        System.out.println("Utilisateur enregistré avec succès.");
    }


    // Méthode pour vérifier l'éligibilité d'un utilisateur à emprunter un livre
    public void verifierEligibilite() {
        System.out.println(" Veuillez saisir le numéro d'identification de l'utilisateur:");
        Scanner sc = new Scanner(System.in);
        int numeroIdentification = sc.nextInt();
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getNumeroIdentification() == numeroIdentification) {
                if (utilisateur.estEligible()) {
                    System.out.println(utilisateur.getNom() + " " + utilisateur.getPrenom() + " est éligible");
                } else {
                    System.out.println(utilisateur.getNom() + " " + utilisateur.getPrenom() + " n'est pas éligible");
                }
            }
            else
                System.out.println("Utilisateur introuvable");
        }

    }
}



