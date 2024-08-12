/*
 * TestPartie.java                                9 mai 2021
 * 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.partie.test;

import othello.caracteristiques.Couleur;
import othello.joueur.Joueur;
import othello.partie.Partie;
import othello.plateau.Plateau;

/**
 * Tests unitaires de la classe Partie
 *
 * @author David Simonin
 * @version 1.0
 */
public class TestPartie {

    /**
     * jeu d'essai avec un plateau initial
     */
    public static Plateau damier = new Plateau();
    static Joueur[] joueurs = {
        new Joueur("Ghalhyus", Couleur.NOIR),
        new Joueur("CRAZYMat", Couleur.BLANC)
    };
    /**
     * test du constructeur Partie Jeu de données pour les prochains test
     */
    public final static Partie VALIDE = new Partie(joueurs, damier);

    /**
     * Test du constructeur Partie avec des valeurs invalides
     */
    public static void testPartieJoueursPlateau() {
        final Joueur[][] JOUEURS_INVALIDES = {
            null,
            { new Joueur("Jean", Couleur.NOIR),
                new Joueur("CRAZYMat", Couleur.BLANC) },
            null,
            { new Joueur("Ghalhyus", Couleur.NOIR),
                new Joueur("Mausaille", Couleur.NOIR) },
            { new Joueur("moska", Couleur.BLANC),
                new Joueur("Guepaz", Couleur.BLANC) }
        };

        final Plateau[] DAMIERS_INVALIDES = {
            new Plateau(),
            null,
            null,
            new Plateau(),
            new Plateau()
        };

        final int TAILLE = JOUEURS_INVALIDES.length;

        int test;

        System.out.print("""
            TEST : Partie(Joueur[], Plateau) (test visuel)
            ----------------------------------------------
            """);
        /* test avec des valeurs invalides */
        test = 0;
        for (int jdd = 0; jdd < TAILLE && jdd == test; jdd++) {
            try {
                new Partie(JOUEURS_INVALIDES[jdd], DAMIERS_INVALIDES[jdd]);
            } catch (IllegalArgumentException echec) {
                test++;
            }
        }

        if (test == TAILLE) {
            System.out.println("Tous les tests du constructeurs Partie "
                + "ont réussi\n");
        } else {
            System.out.println("Erreur, l'un des tests a échoué\n");
        }
    }

    /**
     * Test getJoueur
     */
    public static void testGetJoueurs() {
        /* jeu de donnée */
        final Joueur[] COPIE_JOUEUR = {
            new Joueur("Ghalhyus", Couleur.NOIR),
            new Joueur("CRAZYMat", Couleur.BLANC)
        };

        final int TAILLE = VALIDE.getJoueur().length;

        int jdd;

        System.out.print("""
            TEST : getJoueurs() (test automatique)
            -------------------------------------
            """);

        for (jdd = 0; jdd < TAILLE && COPIE_JOUEUR[jdd]
            .equals(VALIDE.getJoueur()[jdd]);
             jdd++)
            ;  // corps vide

        if (jdd == TAILLE) {
            System.out.println("Le test de getJoueur a réussi\n");
        } else {
            System.out.println("Erreur, le test a échoué\n");
        }

    }

    /**
     * Test getDamier
     */
    public static void testGetDamier() {
        /* jeu de donnée */
        final Plateau COPIE_DAMIER = new Plateau();

        System.out.print("""
            TEST : getDamier() (test automatique)
            -------------------------------------
            """);

        if (COPIE_DAMIER.equals(VALIDE.getDamier())) {
            System.out.println("Le test de getDamier a réussi\n");
        } else {
            System.out.println("Erreur, le test a échoué\n");
        }
    }

    /**
     * Test getTour
     */
    public static void testGetTour() {
        System.out.print("""
            TEST : getTour() (test automatique)
            -----------------------------------
            """);

        if (0 == VALIDE.getTour()) {
            System.out.println("Le test de getTour a réussi\n");
        } else {
            System.out.println("Erreur, le test a échoué\n");
        }
    }

    /**
     * Test prochainTour()
     */
    public static void testProchainTour() {
        System.out.print("""
            TEST : prochainTour() (test automatique)
            ----------------------------------------
            """);

        VALIDE.prochainTour();
        if (VALIDE.getTour() == 1) {
            System.out.println("Le test de prochainTour a réussi\n");
        } else {
            System.out.println("Erreur, le test a échoué\n");
        }
    }

    /**
     * Test determinerTourJoueur()
     */
    public static void testDeterminerTourJoueur() {
        /* jeu de donnée */
        final Partie COPIE_VALIDE = new Partie(joueurs, damier);

        System.out.print("""
            TEST : determinerTourJoueur() (test automatique)
            ------------------------------------------------
            """);

        if (VALIDE.determinerTourJoueur() == Couleur.BLANC
            && COPIE_VALIDE.determinerTourJoueur() == Couleur.NOIR) {
            System.out.println("Le test de determinerTourJoueur a réussi\n");
        } else {
            System.out.println("Erreur, le test a échoué\n");
        }
    }

    /**
     * test de la méthode LancerPartie
     */
    public static void testJouer() {
        System.out.println("""
            TEST : afficherJoueurAJouer() (test visuel)
            --------------------------------------------
            Assurez vous de tester chaque possibilité
            """);
        VALIDE.jouer();
    }

    /**
     * Lance les tests unitaires
     *
     * @param args non utilisé
     */
    public static void main(String[] args) {
        testPartieJoueursPlateau();
        testGetJoueurs();
        testGetDamier();
        testGetTour();
        testProchainTour();
        testDeterminerTourJoueur();
        testJouer();
    }
}