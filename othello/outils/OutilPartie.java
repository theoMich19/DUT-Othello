/*
 * Randomizer.java              13 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.outils;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.pion.Pion;
import othello.plateau.Plateau;

import java.util.ArrayList;
import java.util.List;

/**
 * Outil de gestion pour les diff�rents param�tres de la partie
 * @author Info1
 *
 */
public class OutilPartie {

    /**
     * Permet d'obtenir un entier random
     * @param positions liste sur laquelle obtenir un random
     * @return en entier aléatoire selon une liste entré en argument
     */
    public static int getRandomPosition(List<Position> positions) {
        return (int) (Math.random() * positions.size());
    }

    /**
     * Permet d'obtenir un entier random
     * @param liste liste sur laquelle obtenir un random
     * @return en entier aléatoire selon une liste entré en argument
     */
    public static int getRandomInteger(List<Integer> liste ) {
        int random;

        do {
            random = (int) (Math.random() * liste.size());
        } while (random >= liste.size());

        return random;
    }

    /**
     * Obtenir l'indice ou l'on peut retourner le maximum de pion
     * @param positionsDispo position jouables
     * @param couleur couleur du joueur
     * @param damier plateau du jeux
     * @return l'indice ayant la valeur maximum
     */
    public static int obtenirMaximum(List<Position> positionsDispo,
                                     Couleur couleur, Plateau damier) {
        List<Integer> indiceMax = new ArrayList<>();
        int maximum = 0;
        int indice = 0;

        for (Position p : positionsDispo) {
            Pion pion = new Pion(p, couleur);
            if (pion.chercherAdversairesAutour(damier).size() >= maximum) {
                // Maximum prend la valeur du nombre de pion mangeable
                maximum = pion.chercherAdversairesAutour(damier).size();
                if (pion.chercherAdversairesAutour(damier).size() == maximum) {
                    indiceMax.add(indice);
                } else if (pion.chercherAdversairesAutour(damier)
                           .size() > maximum) {
                    indiceMax = new ArrayList<>();
                    indiceMax.add(indice);
                }
            }
            System.out.println(indice);
            indice++;
        }
        if (indiceMax.size() > 1) {
            indice = indiceMax.get(getRandomInteger(indiceMax));
        }

        return indice;
    }

}
