/*
 * TestInterfaceConsole.java                                7 mai 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.interfaceconsole.test;

import othello.caracteristiques.Couleur;
import othello.interfaceconsole.InterfaceConsole;
import othello.joueur.Joueur;

/**
 * Tests unitaires de la classe TestInterfaceConsole
 *
 * @author David Simonin
 */
public class TestInterfaceConsole {

    /**
     * jeu de donnée avec des joueurs
     */
    public static final Joueur[] JOUEURS = {
        new Joueur("CRAZYMat", Couleur.BLANC),
        new Joueur("Ghalhyus", Couleur.NOIR),
        new Joueur("Guepaz", Couleur.NOIR),
        new Joueur("Mausaille", Couleur.BLANC),
        new Joueur("moska", Couleur.BLANC)
    };

    /**
     * Test visuel de la méthode afficherMenuPrincipal
     */
    public static void testAfficherMenuPrincipal() {
        System.out.println("""
            TEST : afficheMenuPrincipal() (test visuel)
            -------------------------------------------
            Assurez vous que le menu s'affiche correctement :
            """);
        InterfaceConsole.afficherMenuPrincipal();
    }
    
    /**
     * Test visuel des m�thodes : 
     * <ul>
     *     <li>choixModesDeJeux</li>
     *     <li>gererModesDeJeu</li>
     *     <li>choixDifficulte</li>
     *     <li>afficherDifficultees</li>
     *     
     */

    /**
     * Test visuel de la méthode afficherCommandesJoueur
     */
    public static void testAfficherCommandesJoueur() {
        System.out.println("""

            TEST : afficheCommandesJoueur() (test visuel)
            -------------------------------------------------
            Assurez vous que les commandes s'affiche correctement :
            """);
        InterfaceConsole.afficherCommandesJoueur();
    }

    /**
     * Test visuel de la méthode afficherPlateau
     */
    public static void testAfficherPlateau() {
        System.out.println("""

            TEST : affichePlateau() (test visuel)
            -----------------------------------------
            Assurez vous que le plateau s'affiche correctement :
            """);
        InterfaceConsole.afficherCommandesJoueur();
    }

    /**
     * Test de la méthode afficherJoueurAJouer
     */
    public static void testAfficherJoueurAJouer() {
        System.out.println("""

            TEST : afficherJoueurAJouer() (test visuel)
            --------------------------------------------
            Assurez vous que le texte s'affiche correctement pour
            chaque joueur:
            """);

        for (Joueur joueur : JOUEURS) {
            InterfaceConsole.afficherJoueurAJouer(joueur.getCouleur());
        }
    }

    /**
     * test de la méthode afficherScore
     */
    public static void testAfficherScore() {
        System.out.println("""

            TEST : afficherScore() (test visuel)
            --------------------------------------------
            Assurez vous que le score s'affiche correctement pour chaque joueur:
            """);

        for (Joueur joueur : JOUEURS) {
            InterfaceConsole.afficherScore(joueur);
        }
    }

    /**
     * Lance les tests unitaires
     *
     * @param args non utilisé
     */
    public static void main(String[] args) {
        testAfficherMenuPrincipal();
        testAfficherCommandesJoueur();
        // testAfficherPlateau();
        testAfficherJoueurAJouer();
        testAfficherScore();
    }
}
