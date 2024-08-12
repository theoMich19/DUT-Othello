/*
 * Partie.java                                        8 mai 2021
 * IUT, info1 2020-2021, aucun droit
 */
package othello.partie;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.interfaceconsole.InterfaceConsole;
import othello.joueur.Joueur;
import othello.outils.OutilConversion;
import othello.outils.OutilSaisie;
import othello.pion.Pion;
import othello.plateau.Plateau;
import othello.outils.OutilFichier;
import othello.joueur.Ordinateur;

import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

import static othello.outils.OutilPartie.*;
import static othello.outils.OutilSaisie.*;

/**
 * Classe principale établissant toutes les caractéristiques d'une partie
 * d'Othello, c'est Ãƒ  dire : - Les joueurs (associés Ãƒ  son pseudo et
 * sa couleur)
 * - Le plateau - Les pions du plateau (dont leur couleur est associé avec
 * celle du joueur)
 * <p>
 * Chaque joueur peut jouer son tour et la partie se termine lorsqu'aucun pion
 * n'a la possibilité de jouer ou que le plateau est rempli
 * <p>
 *
 * @author Théo Michellon, David Simonin
 * @version 1.0
 */
public class Partie implements Serializable {

    /*
     * indication
     * Si saisie coordonnées valides passe joueur suivants
     * Si joueur passe son tour passe au joueurs suivant
     * Si saisie non valides redemande Ã  l'utilisateur
     */

    /** TODO commenter le rôle du champ (attribut, rôle associatif) */
    private static final long serialVersionUID = 1L;

    /**
     * Valeur Ã  entrer pour passer son tour
     */
    public static final String PARTIE_PASSER_TOUR = "1";
    /**
     * Valeur Ã  entrer pour sauvegarder la partie
     */
    public static final String PARTIE_SAUVEGARDER = "2";
    /**
     * Valeur Ã  entrer pour quitter la partie
     */
    public static final String PARTIE_QUITTER = "3";
    /**
     * Valeur Ã  entrer pour quitter le menu et le jeu
     */
    public static final String PARTIE_QUITTER_JEU = "4";

    /**
     * Message de saisie pour la sauvegarde et pour charger une sauvegarde
     */
    public static final String MESSAGE_SAISIE =
            "Saisisssez un nom de ficher inférieur à 15 caractères :";

    /**
     * Les joueurs de la partie
     */
    private final Joueur[] joueurs;

    /**
     * Le plateau de jeu
     */
    private final Plateau damier;

    /**
     * Un entier permettant de déterminer la fin d'une partie
     */
    private int passerTour;

    /**
     * Le numéro du tour
     */
    private int tour;

    /**
     * Créer une partie
     *
     * @param joueur Joueurs de la partie qui peuvent ÃƒÂªtre : un joueur et un
     *                ordinateur deux joueurs
     * @param damier  Plateau de jeu
     *
     * @throws IllegalArgumentException Si au moins l'une des références est
     *                                  null
     */
    public Partie(Joueur[] joueur, Plateau damier) {
        if (!isValide(joueur, damier)) {
            throw new IllegalArgumentException(
                    "Erreur la partie est incorrecte");
        }
        this.joueurs = Joueur.trier(joueur);
        this.damier = damier;
        tour = 0;
    }

    /**
     * Vérifie que les références ne sont pas null
     *
     * @param joueur Les joueurs de la partie
     * @param damier  Le plateau de jeu
     *
     * @return true si les références sont non null
     */
    public static boolean isValide(Joueur[] joueur, Plateau damier) {
        return joueur != null && damier != null && joueur.length == 2 &&
                !joueur[0].equals(joueur[1]);
    }

    /**
     * @return les joueurs en jeu
     */
    public Joueur[] getJoueur() {
        return joueurs;
    }

    /**
     * @return la plateau de jeu
     */
    public Plateau getDamier() {
        return damier;
    }

    /**
     * @return le numéro du tour
     */
    public int getTour() {
        return tour;
    }

    /**
     * Ajout +1 au tour actuelle
     */
    public void prochainTour() {
        tour++;
    }

    /**
     * méthode qui permet d'indiquer quel joueur doit jouer
     *
     * @return return 0 ou 1 (permettra de déterminé la couleur
     */
    public Couleur determinerTourJoueur() {
        return this.tour % 2 == 0 ? Couleur.NOIR : Couleur.BLANC;
    }

    /**
     * Détermine si la partie est jouable en regardant si le plateau est
     * complet ou si les 2 joueurs n'ont plus de mouvement possible
     *
     * @return vrai si la partie est encore jouable
     */
    public boolean isJouable() {
        int pionsJouables = 0;
        for (Pion p : damier.getPions()) {
            pionsJouables += p.chercherAdversairesAutour(damier).size();
        }

        return !damier.isRempli() || pionsJouables == 0;
    }

    /**
     * Méthodes qui détermine si c'est au joueur suivant de jouer ou non en
     * analysant la saisie du joueur actuel si le joueur saisie :
     * <ul>
     *     <li>des coordonnées valides XY le pion est posé
     *     (entrez son indice de ligne(une lettre)
     *     suivi de son indice de colonne(un chiffre))</li>
     *     <li>'1' :  le joueur passe sont tour</li>
     *     <li>Alors c'est Ãƒ  l'autre joueur de jouer</li>
     * </ul>
     * si le joueur saisie :
     * <ul> euh ca fonctionne ca ? #demande Theo
     *     <li> des coordonnées invalides </li>
     *     <li> un raccourcis de commandes invalides</li>
     *     Alors on lui demande Ãƒ  nouveau ce qu'il veut faire</li>
     * </ul>
     * Si :
     * <ul>
     *     <li> les deux joueurs passent leurs tours ou</li>
     *     <li> le nombre de score et arrivé au maximum</li>
     *     <li> toutes les cases sont remplies </li>
     *     <li>La partie s'arrete </li>
     * </ul>
     * @return la derni�re commande de l'utilisateur
     */
    public int jouer()  {
        String saisie;  // la saisie de l'utilisateur

        passerTour = 0;

        saisie = "";
        while (passerTour < 2 && isJouable() && !saisie.equals("3")
                && !saisie.equals("4")) {
            Couleur couleur = joueurs[tour % 2].getCouleur();
            List<Position> positionsDispo = damier.getCasesJouables(couleur);

            InterfaceConsole.afficherPlateau(damier, positionsDispo);

            if (!(joueurs[0] instanceof Ordinateur)
                    && joueurs[0].getCouleur() == Couleur.NOIR
                    && determinerTourJoueur() == Couleur.NOIR) {
                saisie = tourJoueur(joueurs[0], positionsDispo);
                prochainTour();
            } else if (!(joueurs[1] instanceof Ordinateur)
                    && joueurs[1].getCouleur() == Couleur.BLANC
                    && determinerTourJoueur() == Couleur.BLANC) {
                saisie = tourJoueur(joueurs[1], positionsDispo);
                prochainTour();
            }

            if (Ordinateur.isDifficulte(joueurs[chercherOrdinateur()],
                    "FACILE")) {
                tourOrdiFacile(joueurs[chercherOrdinateur()]);
                prochainTour();
            } else if (Ordinateur.isDifficulte(joueurs[chercherOrdinateur()],
                    "NORMAL")) {
                tourOrdiNormal(joueurs[chercherOrdinateur()]);
                prochainTour();
            } else if (Ordinateur.isDifficulte(joueurs[chercherOrdinateur()],
                    "DIFFICILE")) {
                // TODO tourDifficile
            }
        }

        if (!saisie.equals("4")) {
            System.out.println("\n\n\tFIN DE LA PARTIE");
        }

        // TODO afficherScore() / méthode pour afficher le score final
        // InterfaceConsole.afficherScore()

        return saisie.charAt(0);
    }

    /**
     * TODO commenter le rÃ´le de la méthode (commande ou requÃªte ?)
     */
    public void tourOrdiNormal(Joueur joueur) {
        List<Position> positionsDispo = damier.getCasesJouables(joueur.getCouleur());
        int indice;

        if (positionsDispo.size() == 0) {
            passerTour++;
        } else {
            passerTour = 0;
            indice = obtenirMaximum(positionsDispo, joueur.getCouleur(), damier);
            damier.placerPion(new Pion(positionsDispo.get(indice), joueur.getCouleur()));
            System.out.println("L'ordinateur joue en : " + positionsDispo.get(indice));
        }

    }

    /**
     * Méthode de niveau facile pour un ordinateur
     */
    public void tourOrdiFacile(Joueur joueur) {
        List<Position> positionsDispo = damier.getCasesJouables(joueur.getCouleur());
        Position positionRandom;

        if (positionsDispo.size() == 0) {
            passerTour++;
        } else  {
            passerTour = 0;
            positionRandom = positionsDispo.get(getRandomPosition(positionsDispo));

            damier.placerPion(new Pion(positionRandom, joueur.getCouleur()));
            System.out.println("L'ordinateur joue en : " + positionRandom);
        }
    }

    /**
     * GÃ¨re le tour d'un joueur
     * @param joueur qui joue son tour
     * @param positionsDispo positions jouables par le joueur
     * @return La saisie de l'utilisateur
     */
    public String tourJoueur(Joueur joueur,  List<Position> positionsDispo) {
        Position position = new Position(0, 0);
        String saisie;

        InterfaceConsole.afficherCommandesJoueur();
        InterfaceConsole.afficherJoueurAJouer(determinerTourJoueur());

        do {
            saisie = OutilSaisie.lireChaine();

            if (saisie.length() == 2) {
                position = OutilConversion.convertirSaisiePosition(saisie);
                saisiePosition(position, positionsDispo, saisie, joueur);
            } else if (saisie.length() == 1) {
                saisieCommande(saisie);
            }
        } while (saisie.length() == 2 && !damier.isJouable(position,
                positionsDispo));

        return saisie;
    }

    /**
     * Gérer le placement d'un pion lorsqu'un joueur entre une position
     * @param position position saisie par l'utilisateur (convertie)
     * @param positionsDispo position disponibles sur le damier (oÃ¹ on peut
     *                       jouer)
     * @param saisie saisie de l'utilisateur
     * @param joueur joueur qui joue son tour
     */
    public void saisiePosition(Position position,
                               List<Position> positionsDispo,
                               String saisie,
                               Joueur joueur) {

        Couleur couleur = joueur.getCouleur();

        if (!damier.isJouable(position, positionsDispo)) {
            System.out.println("Saisie incorrect, veuillez " +
                    "vérifier que vous retournez bien un pion sinon " +
                    "passez votre tour :");
        } else {
            System.out.printf("Le joueur %s joue en %s\n\n", couleur, saisie);
            damier.placerPion(new Pion(position, couleur));
            passerTour = 0;
        }
    }


    /**
     * Gérer la saisie de l'utilisateur quand la saisie est de taille 1
     * (commande)
     * @param saisie saisie de l'utilisateur
     */
    public void saisieCommande(String saisie) {
        /* objet Scanner pour la saise */
        Scanner clavier = new Scanner(System.in);

        switch (saisie) {
            case PARTIE_PASSER_TOUR -> {
                System.out.println("Vous passez votre tour.\n\n");
                passerTour++;
            }
            case PARTIE_SAUVEGARDER -> {
                OutilFichier.sauvegarder(this);
                System.out.println("Vous sauvegardez la partie\n\n");
            }
            case PARTIE_QUITTER -> System.out.println("Vous quittez la partie");
            // TODO coder la méthode
            case PARTIE_QUITTER_JEU -> System.out
                    .println("Vous quittez le jeu");
            // TODO coder la méthode
        }
        clavier.nextLine();
    }


    /**
     * Méhtode de niveau facile pour un ordinateur
     * @return
     *
     */
    public int jouerOrdi() {
        Couleur couleur = joueurs[0].getCouleur();
        List<Position> positionsDispo = damier.getCasesJouables(couleur);

        InterfaceConsole.afficherPlateau(damier, positionsDispo);
        if (positionsDispo.size() == 0) {
            return 1; // Passer tour
        }
        damier.placerPion(new Pion(positionsDispo
                .get(getRandom(positionsDispo)),couleur ));
        return 0;
    }

    /**
     * Permet d'obtenir un int random
     * @param positionsDispo
     * @return en entier aléatoire selon une liste entré en argument
     */
    public static int getRandom(List<Position> positionsDispo ) {
        return (int) (Math.random() * positionsDispo.size());
    }

    /**
     * Vérifie que la partie est contre l'ordinateur
     * @return True si la partie se joue contre un ordinateur
     */
    public boolean estContreOrdinateur() {
        return this.getJoueur()[this.chercherOrdinateur()]
                .getPseudo().startsWith("ordinateur");
    }

    /**
     * Vérifie que l'ordinateur est en facile
     * @return true si l'ordinateur est en facile
     */
    public boolean estFacile() {
        return true;  // TODO bouchon
    }

    /**
     * @return la place de l'ordinateur dans le tableau
     */
    public int chercherOrdinateur() {
        return this.getJoueur()[0] instanceof Ordinateur
                ? 0 : 1;
    }
}