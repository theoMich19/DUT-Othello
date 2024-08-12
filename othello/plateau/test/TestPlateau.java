/*
 * TestPlateau.java                     9 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.plateau.test;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.interfaceconsole.InterfaceConsole;
import othello.pion.Pion;
import othello.plateau.Plateau;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests unitaires de la classe Plateau
 *
 * @author Maxime Moskalyk
 */
public class TestPlateau {

    /**
     * Le plateau utilisé comme référence de test
     */
    private static final Plateau p = new Plateau();

    /**
     * Test de la méthode remplirCase(Pion)
     */
    public static void testRemplirCase() {
        System.out.println("\ntestRemplirCase() :");
        while (p.getPions().size() < 30) {
            p.remplirCase(new Pion(new Position(
                (int) (Math.random() * Plateau.TAILLE_RANGEE),
                (int) (Math.random() * Plateau.TAILLE_RANGEE)),
                Couleur.values()[(int) (Math.random() * Couleur.values().length)]
            ));
        }
        InterfaceConsole.afficherPlateau(p, new ArrayList<>());
    }

    /**
     * Test de la méthode getPion(Position)
     */
    public static void testGetPion() {
        System.out.println("\ntestGetPion() :");

        System.out.println("Pion en 3, 5 : " + p.getPion(new Position(3, 5)));
        System.out.println("Pion en 1, 0 : " + p.getPion(new Position(1, 0)));
        System.out.println("Pion en 7, 7 : " + p.getPion(new Position(7, 7)));
        System.out.println("Pion en 0, 0 : " + p.getPion(new Position(0, 0)));
        System.out.println("Pion en 3, 4 : " + p.getPion(new Position(3, 4)));
        System.out.println("Pion en 5, 1 : " + p.getPion(new Position(5, 1)));
        System.out.println("Pion en 2, 6 : " + p.getPion(new Position(2, 6)));
        System.out.println("Pion en 1, 7 : " + p.getPion(new Position(1, 7)));

    }

    /**
     * Test de la méthode isCaseVide(Position)
     */
    public static void testIsCaseVide() {
        System.out.println("\ntestIsCaseVide() :");

        System.out.println("Case en 3, 5 : " + p.isCaseVide(new Position(3, 5)));
        System.out.println("Case en 1, 0 : " + p.isCaseVide(new Position(1, 0)));
        System.out.println("Case en 7, 7 : " + p.isCaseVide(new Position(7, 7)));
        System.out.println("Case en 0, 0 : " + p.isCaseVide(new Position(0, 0)));
        System.out.println("Case en 3, 4 : " + p.isCaseVide(new Position(3, 4)));
        System.out.println("Case en 5, 1 : " + p.isCaseVide(new Position(5, 1)));
        System.out.println("Case en 2, 6 : " + p.isCaseVide(new Position(2, 6)));
        System.out.println("Case en 1, 7 : " + p.isCaseVide(new Position(1, 7)));
    }

    /**
     * Test de la méthode isRempli()
     */
    public static void testIsRempli() {
        Plateau plateauRempli = new Plateau();
        for (Position pos : plateauRempli.getCasesVides()) {
            plateauRempli.remplirCase(new Pion(pos, Couleur.NOIR));
        }
        System.out.println("\ntestIsRempli() :");
        System.out.println("Plateau rempli : " + plateauRempli.isRempli());
        System.out.println("Plateau non rempli : " + p.isRempli());
    }

    /**
     * Test de la méthode getPions(Couleur) / getPions()
     */
    public static void testGetPions() {
        System.out.println("\ntestGetPions() :");
        System.out.println("\nPions de couleur noire :");
        for (Pion pion : p.getPions(Couleur.NOIR)) {
            System.out.println("Pion noir à : " + pion.getPosition());
        }
        System.out.println("\nPions de couleur blanche :");
        for (Pion pion : p.getPions(Couleur.BLANC)) {
            System.out.println("Pion noir à : " + pion.getPosition());
        }
    }

    /**
     * Test de la méthode getCasesVide()
     */
    public static void testGetCasesVide() {
        System.out.println("\ntestGetCasesVide() :");
        for (Position pos : p.getCasesVides()) {
            System.out.println("Case vide à : " + pos);
        }
    }

    /**
     * Test de l'encadrement d'un pion
     */
    public static void testDiagonalePion() {
        Plateau p = new Plateau();

        /* diagonale haut gauche */
        p.remplirCase(new Pion(new Position(2, 1), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(1, 0), Couleur.NOIR));

        /* haut */
        p.remplirCase(new Pion(new Position(3, 1), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(3, 0), Couleur.NOIR));

        /* diagonale haut droit */
        p.remplirCase(new Pion(new Position(4, 1), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(5, 0), Couleur.NOIR));

        /* droit */
        p.remplirCase(new Pion(new Position(4, 2), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(5, 2), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(6, 2), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(7, 2), Couleur.NOIR));

        /* diagonale bas droit */
        p.remplirCase(new Pion(new Position(4, 3), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(5, 4), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(6, 5), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(7, 6), Couleur.NOIR));

        /* bas */
        p.remplirCase(new Pion(new Position(3, 4), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(3, 5), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(3, 6), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(3, 7), Couleur.NOIR));

        /* diagonale bas gauche */
        p.remplirCase(new Pion(new Position(2, 3), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(1, 4), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(0, 5), Couleur.NOIR));

        /* gauche */
        p.remplirCase(new Pion(new Position(2, 2), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(1, 2), Couleur.BLANC));
        p.remplirCase(new Pion(new Position(0, 2), Couleur.NOIR));

        /* pions placés alétoirement */
        //for (int i = 0; i < 50; i++) {
        //    p.remplirCase(new Pion(new Position((int) (Math.random() * 8),
        //        (int) (Math.random() * 8)), Couleur.NOIR));
        //    p.remplirCase(new Pion(new Position((int) (Math.random() * 8),
        //        (int) (Math.random() * 8)), Couleur.BLANC));
        //}
        /* pions à analyser */
        Pion pion = new Pion(new Position(3, 2), Couleur.NOIR);
        p.remplirCase(pion);

        List<Position> pos = new ArrayList<>();
        InterfaceConsole.afficherPlateau(p, pos);

        for (Pion ennemie : pion.chercherAdversairesAutour(p)) {
            pos.add(ennemie.getPosition());
        }
        InterfaceConsole.afficherPlateau(p, pos);

    }

    /**
     * Exécution des tests unitaires
     *
     * @param args non utilisé
     */
    public static void main(String[] args) {
        testRemplirCase();
        testGetPion();
        testIsCaseVide();
        testIsRempli();
        testGetPions();
        testGetCasesVide();
        testDiagonalePion();
    }
}
