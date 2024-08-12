/*
 * Position.java                     9 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.caracteristiques;

import java.io.Serializable;

/**
 * Instance de classe définissant les coordonnées en 2 dimensions sur les
 * abscisses (x) et ordonnées (y)
 *
 * @author Maxime Moskalyk
 * @version 1.0
 */
public record Position(int x, int y) implements Serializable {
    // méthodes x() et y() déjà initiées
}
