/*
 * TestOutilConversion.java              7 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.outils.test;

import static othello.outils.OutilConversion.convertirSaisiePosition;
import static othello.outils.OutilSaisie.lireChaine;

/**
 * Tests unitaires de la classe OutilConversion
 *
 * @author Yohann May
 */
public class TestOutilConversion {

    /**
     * Tests unitaires de convertirSaisiePosition()
     */
    public static void testConvertirSaisiePosition() {
        String positionUtilisateur;

        System.out.print("Saisie une position : ");
        positionUtilisateur = lireChaine();

        System.out.println(positionUtilisateur);
        System.out.println(convertirSaisiePosition(positionUtilisateur));
    }

    /**
     * Lancement des méthodes de tests
     *
     * @param args unused
     */
    public static void main(String[] args) {
        testConvertirSaisiePosition();
    }

}
