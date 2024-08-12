/*
 * Direction.java                     9 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.caracteristiques;

/**
 * Directions possible à analyser des côtés d'un pion
 *
 * @author Maxime Moskalyk
 * @version 1.0
 */
public enum Direction {
    /**
     * Diagonale allant vers le haut à gauche
     */
    DIAGONALE_HAUT_GAUCHE,
    /**
     * Horizontale allant vers le haut
     */
    HAUT,
    /**
     * Diagonale allant vers le haut à droite
     */
    DIAGONALE_HAUT_DROITE,
    /**
     * Verticale allant vers la droite
     */
    DROITE,
    /**
     * Diagonale allant vers le bas à droite
     */
    DIAGONALE_BAS_DROITE,
    /**
     * Horizontale allant vers le bas
     */
    BAS,
    /**
     * Diagonale allant vers le bas à gauche
     */
    DIAGONALE_BAS_GAUCHE,
    /**
     * Verticale allant vers la gauche
     */
    GAUCHE;

    /**
     * @return si l'abscisse doit être vérifié négativement ou pas
     */
    public boolean isXNegatif() {
        return this.toString().contains("GAUCHE");
    }

    /**
     * @return si l'ordonnée doit être vérifié négativement ou pas
     */
    public boolean isYNegatif() {
        return this.toString().contains("HAUT");
    }

    /**
     * @return si l'abscisse reste constante
     */
    public boolean isXConstante() {
        return this == HAUT || this == BAS;
    }

    /**
     * @return si l'ordonnée reste constante
     */
    public boolean isYConstante() {
        return this == DROITE || this == GAUCHE;
    }
}
