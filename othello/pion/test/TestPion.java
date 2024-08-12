/*
 * TestPion.java                     27 Avril 2021
 * IUT Rodez, pas de droits
 */
package othello.pion.test;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.interfaceconsole.InterfaceConsole;
import othello.outils.OutilConversion;
import othello.pion.Pion;
import othello.plateau.Plateau;

import java.util.ArrayList;
import java.util.List;

import static othello.outils.OutilSaisie.lireChaine;

/**
 * Classe permettant de réaliser les tests unitaires de la classe ActionPion
 *
 * @author Yohann May
 * @version 1.0
 */
public class TestPion {

    /**
     * Tests unitaires de placer()
     */
    public static void testPlacer() {
        System.out.println("Exécution testPlacerPion()...");
        Plateau p = new Plateau();
        Position position;

        do {
            System.out.print("Saisir une position : ");
            position = OutilConversion.convertirSaisiePosition(lireChaine());
        } while (!p.placerPion(new Pion(position, Couleur.NOIR)));

        InterfaceConsole.afficherPlateau(p, new ArrayList<>());
    }

    /**
     * Tests unitaires de getPositionsPossibles()
     */
    public static void testGetPositionsPossibles() {
        System.out.println("Exécution testGetPositionPossible()...");
        Plateau p = new Plateau();
        Couleur couleur = Couleur.NOIR;

        System.out.println("Test Positions noir : ");
        for (Position position : p.getCasesJouables(couleur)) {
            System.out.println("X : " + position.x() + " Y : " + position.y());
        }
        InterfaceConsole.afficherPlateau(p, p.getCasesJouables(couleur));

        System.out.println("Test Positions blanc : ");
        couleur = Couleur.BLANC;
        for (Position position : p.getCasesJouables(couleur)) {
            System.out.println("X : " + position.x() + " Y : " + position.y());
        }
        InterfaceConsole.afficherPlateau(p, p.getCasesJouables(couleur));

    }

    /**
     * Tests unitaires de isJouable()
     */
    public static void testIsJouable() {
        Plateau p = new Plateau();
        Couleur couleur = Couleur.NOIR;
        List<Position> positionPossibles = p.getCasesJouables(couleur);
        Position positionUtilisateur;

        do {
            System.out.print("Saisir une position : ");
            positionUtilisateur = OutilConversion.convertirSaisiePosition(lireChaine());
            System.out.println(p.isJouable(positionUtilisateur,
                positionPossibles));
        } while (!p.isJouable(positionUtilisateur, positionPossibles));

    }

    /**
     * Lancement des méthodes de tests
     *
     * @param args unused
     */
    public static void main(String[] args) {
        testGetPositionsPossibles();
        testIsJouable();
        testPlacer();
    }
}