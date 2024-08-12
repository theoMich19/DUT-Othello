/*
 * OutilConversion.java              7 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.outils;

import othello.caracteristiques.Position;

/**
 * Classe permettant la conversion de différentes valeurs
 *
 * @author Yohann May
 * @version 1.0
 */
public class OutilConversion {

    /**
     * Les indexes correspondant au numéro aux colonnes
     */
    public static final char[] COLONNE_INDEXES = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'
    };

    /**
     * Méthode permettant de convertir la saisie d'un utilisateur en un tableau
     * d'entier contenant deux indices
     * <p>
     * Exemple : A1 -> Position(1, 1) B5 -> Position(2, 5)
     *
     * @param saisieUtilisateur la saisie de l'utilisateur sous la forme A1
     *
     * @return un tableau contenant 2 entiers
     */
    public static Position convertirSaisiePosition(String saisieUtilisateur) {
        int x, y;

        x = 0;
        try {
            x = Integer.parseInt(String.valueOf(saisieUtilisateur.charAt(0))) - 1;
        } catch (NumberFormatException ignored) {
            // ignoré
        }
        for (y = 0; y < COLONNE_INDEXES.length; y++) {
            if (COLONNE_INDEXES[y] == saisieUtilisateur.charAt(1)) {
                break;
            }
        }
        return new Position(x, y);
    }
}