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
     * couleur noire avec l'ic�ne associ�e
     */
    NOIR('x'),
    /**
     * couleur blanche avec l'ic�ne associ�e
     */
    BLANC('o');

    /**
     * l'ic�ne de la couleur
     */
    public char icone;

    /**
     * @return al�atoirement une couleur
     */
    public static Couleur random() {
        return values()[(int) (Math.random() * values().length)];
    }

    /**
     * Initialise l'ic�ne des couleurs pour la console
     *
     * @param icone l'ic�ne
     */
    Couleur(char icone) {
        this.icone = icone;
    }

    /**
     * @return l'ic�ne de la couleur
     */
    public char getIcone() {
        return icone;
    }

    /**
     * D�termine la couleur oppos�e
     *
     * @return La couleur oppos�e
     */
    public Couleur opposee() {
        return this == NOIR ? BLANC : NOIR;
    }
}