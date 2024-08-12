/*
 * Couleur.java                     9 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.caracteristiques;

/**
 * Valeur de la couleur durant une partie
 *
 * @author Maxime Moskalyk
 * @version 1.0
 */
public enum Couleur {
    /**
     * couleur noire avec l'icône associée
     */
    NOIR('x'),
    /**
     * couleur blanche avec l'icône associée
     */
    BLANC('o');

    /**
     * l'icône de la couleur
     */
    public char icone;

    /**
     * @return aléatoirement une couleur
     */
    public static Couleur random() {
        return values()[(int) (Math.random() * values().length)];
    }

    /**
     * Initialise l'icône des couleurs pour la console
     *
     * @param icone l'icône
     */
    Couleur(char icone) {
        this.icone = icone;
    }

    /**
     * @return l'icône de la couleur
     */
    public char getIcone() {
        return icone;
    }

    /**
     * Détermine la couleur opposée
     *
     * @return La couleur opposée
     */
    public Couleur opposee() {
        return this == NOIR ? BLANC : NOIR;
    }
}