/*
 * TestJoueur.java                                28 avr. 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.joueur.test;

import othello.caracteristiques.Couleur;
import othello.joueur.Joueur;
import othello.plateau.Plateau;

/**
 * Tests unitaires de la classe Joueur
 *
 * @author David Simonin
 * @version 1.0
 */
public class TestJoueur {

    /**
     * test de joueur valide jeu de données pour les getters et le toString
     */
    public static final Joueur[] VALIDES = {
        new Joueur("CRAZYMat", Couleur.BLANC),
        new Joueur("Ghalhyus", Couleur.NOIR),
        new Joueur("Guepaz", Couleur.NOIR),
        new Joueur("Mausaille", Couleur.BLANC),
        new Joueur("moska", Couleur.BLANC)
    };

    /**
     * Taille du jeu de données VALIDES
     */
    public static final int TAILLE = VALIDES.length;

    /**
     * test du constructeur Joueur(String, int)
     */
    public static void testJoueurStringInt() {
        /* jeu de données pour les joueurs invalides */
        final String[] PSEUDOS_INVALIDES = {
            "moooooooooooooooooooooooosssssssssssssskaaaaaaaaaaa",
            "CRAZYMatGhalhyusGuepazMaussaillemoska",
            "GHALHYUSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS",
            "Guepaaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzz",
            "", ""
        };

        int test,         // nombre de test réussi
            jdd;  // indices des jeux de données

        /* affiche les tests de Joueur */
        System.out.println("Test du constructeur Joueur :\n"
                           + "-----------------------------");
        /* affiche un message de réussite pour les joueurs valides */
        System.out.println("Tous les tests sur des joueurs valides ont réussi");

        /* test des joueurs invalides */
        for (jdd = 0, test = 0; jdd < TAILLE
            && test == jdd; jdd++) {
            try {
                new Joueur(PSEUDOS_INVALIDES[jdd], Couleur.NOIR);
            } catch (Exception echec) {
                test++;
            }
        }

        /* affichage des résultats du test */
        if (test == jdd) {
            System.out.println("Tous les tests sur des joueurs invalides ont "
                + "réussi\n");
        } else {
            System.out.printf("Il y a eu un problème avec %s, il n'y a pas eu "
                    + "d'exception",
                PSEUDOS_INVALIDES[jdd - 1]);
        }
    }

    /**
     * test de la méthode getPseudo
     */
    public static void testGetPseudo() {
        /* jeu de données avec les valeurs attendues */
        final String[] PSEUDO = {
            "CRAZYMat",
            "Ghalhyus",
            "Guepaz",
            "Mausaille",
            "moska"
        };

        int jdd;  // indices des jeux de données

        /* affiche les tests de getPseudo */
        System.out.println("Test de la méthode getPseudo :\n"
            + "------------------------------");

        for (jdd = 0; jdd < TAILLE
            && VALIDES[jdd].getPseudo().equals(PSEUDO[jdd]);
             jdd++) {

        }

        if (jdd == TAILLE) {
            System.out.println("Tous les tests de getPseudo ont réussi\n");
        } else {
            System.out.printf("Il y a eu un problème avec %s, la valeur "
                    + "attendu été %s",
                VALIDES[jdd].getPseudo(),
                PSEUDO[jdd]);
        }
    }

    /**
     * test de la méthode getCouleur
     */
    public static void testGetCouleur() {
        /* jeu de données avec les valeurs attendues */
        final Couleur[] COULEUR = { Couleur.BLANC, Couleur.NOIR, Couleur.NOIR,
            Couleur.BLANC, Couleur.BLANC };

        int jdd;  // indices des jeux de données

        /* affiche les tests de getCouleur */
        System.out.println("Test de la méthode getCouleur :\n"
            + "-------------------------------");

        for (jdd = 0; jdd < TAILLE
            && VALIDES[jdd].getCouleur() == COULEUR[jdd];
             jdd++) {

        }

        if (jdd == TAILLE) {
            System.out.println("Tous les tests de getCouleur ont réussi\n");
        } else {
            System.out.printf("Il y a eu un problème avec %s, la couleur "
                    + "attendu était %s et nous avons obtenu %s",
                VALIDES[jdd].getPseudo(),
                COULEUR[jdd],
                VALIDES[jdd].getCouleur());
        }
    }

    /**
     * test de la méthode getScore
     */
    public static void testGetScore() {
        /* jeu de données avec les valeurs attendues */
        final int[] SCORE = { 2, 2, 2, 2, 2 };

        int jdd;  // indices des jeux de données

        /* affiche les tests de getScore */
        System.out.println("Test de la méthode getScore :\n"
            + "-----------------------------");

        for (jdd = 0; jdd < TAILLE
            && VALIDES[jdd].getScore(new Plateau()) == SCORE[jdd];
             jdd++) {

        }

        if (jdd == TAILLE) {
            System.out.println("Tous les tests de getScore ont réussi\n");
        } else {
            System.out.printf("Il y a eu un problème avec %s, la valeur "
                    + "attendu été %d et nous avons obtenu %d",
                VALIDES[jdd].getPseudo(),
                SCORE[jdd],
                VALIDES[jdd].getScore(new Plateau()));
        }
    }

    /**
     * test de la méthode toString
     */
    public static void testToString() {
        /* jeu de données avec les valeurs attendues */
        final String[] ATTENDUS = {
            "CRAZYMat", "Ghalhyus", "Guepaz", "Mausaille", "moska"
        };

        int jdd;  // indices des jeux de données

        /* affiche les tests de toString */
        System.out.println("Test de la méthode toString :\n"
                           + "-----------------------------");

        for (jdd = 0; jdd < TAILLE
            && VALIDES[jdd].toString().equals(ATTENDUS[jdd]);
             jdd++) {

        }

        if (jdd == TAILLE) {
            System.out.println("Tous les tests de toString ont réussi\n");
        } else {
            System.out.printf("Il y a eu un problème avec %s, la valeur "
                              + "attendu été      %s",
                              VALIDES[jdd].toString(),ATTENDUS[jdd]);
        }
    }
    
    /**
     * Test de la méthode equals
     */
    public static void testEquals() {
        /* jeu de donnée copie de VALIDES */
        final Joueur[] COPIE_VALIDES = {
            new Joueur("CRAZYMat", Couleur.BLANC),
            new Joueur("Ghalhyus", Couleur.NOIR),
            new Joueur("Guepaz", Couleur.NOIR),
            new Joueur("Mausaille", Couleur.BLANC),
            new Joueur("moska", Couleur.BLANC)    
        };
        
        int jdd;  // jeu de donnée
        
        /* affiche les tests de equals */
        System.out.println("Test de la méthode equals :\n"
                           + "---------------------------");
        
        for(jdd = 0 ; jdd < TAILLE && VALIDES[jdd].equals(COPIE_VALIDES[jdd]) ; 
            jdd++);  // corps vide
        
        if (jdd == TAILLE) {
            System.out.println("Tous les tests de equals ont réussi\n");
        } else {
            System.out.printf("Il y a eu un problème avec %s, la valeur "
                              + "attendu été      %s\n",
                              VALIDES[jdd].toString(),
                              COPIE_VALIDES[jdd].toString());
        }
    }
    
    /**
     * Test de la méthode trier
     */
    public static void testTrier() {
        /* jeu de donnée copie de VALIDES */
        final Joueur[] COPIE_VALIDES = {
            new Joueur("CRAZYMat", Couleur.BLANC),
            new Joueur("Ghalhyus", Couleur.NOIR),
            new Joueur("Guepaz", Couleur.NOIR),
            new Joueur("Mausaille", Couleur.BLANC),
            new Joueur("moska", Couleur.BLANC)    
        };
        
        final Joueur[] A_TRIER =  Joueur.trier(COPIE_VALIDES);
        
        int jdd;  // jeu de donnée
        
        /* affiche le test de trier */
        System.out.println("Test de la méthode trier :\n"
                           + "--------------------------");
        
        for (jdd = 0 ; jdd < TAILLE && COPIE_VALIDES[jdd].equals(A_TRIER[jdd]) ;
             jdd++ );  // corps vide
            
        if (jdd == TAILLE) {
            System.out.println("Tous les tests de trier ont réussi");
        } else {
            System.out.printf("Il y a eu un problème avec %s, la valeur "
                              + "attendu été      %s\n",
                              COPIE_VALIDES[jdd].toString(),
                              A_TRIER[jdd].toString());
        }
    }

    /**
     * Lance les tests unitaires
     *
     * @param args non utilisé
     */
    public static void main(String[] args) {
        testJoueurStringInt();
        testGetPseudo();
        testGetCouleur();
        testGetScore();
        testToString();
        testEquals();
        testTrier();
    }
}
