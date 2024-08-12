/*
 * Joueur.java                                28 avr. 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.joueur;

import othello.caracteristiques.Couleur;
import othello.plateau.Plateau;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe représentant un joueur
 *
 * @author David Simonin
 * @version 1.0
 */
public class Joueur implements Serializable {

    /** TODO commenter le rôle du champ (attribut, rôle associatif) */
    private static final long serialVersionUID = 1L;

    /**
     * Taille maximale du pseudo du joueur
     */
    private static final int PSEUDO_TAILLE_MAX = 25;

    /**
     * Pseudo du joueur
     */
    private String pseudo;

    /**
     * Couleur des pions du joueur 'x' pour noir et 'o' pour blanc
     */
    private Couleur couleur;

    /**
     * Score du joueur
     */
    private int score;

    /**
     * initialise le joueur si les données sont correctes
     *
     * @param pseudo  pseudo du joueur
     * @param couleur couleur des pions du joueur
     *
     * @throws IllegalArgumentException si le nom du joueur dépasse les 25
     *                                  caractère ou si la chaîne est vide ou si
     *                                  la couleur n'existe pas
     */
    public Joueur(String pseudo, Couleur couleur) {
        if (!isValide(pseudo, couleur)) {
            throw new IllegalArgumentException("Erreur, le pseudo ou la "
                + "couleur est incorrecte");
        }
        this.pseudo = pseudo;
        this.couleur = couleur;
    }

    /**
     * Vérifie que le pseudo et la couleur sont bien valide
     *
     * @param pseudo  pseudo du joueur
     * @param couleur couleur des pions du joueur
     *
     * @return vrai si la taille du pseudo est inférieur Ã  25 caractères et si
     * la chaîne n'est pas vide et si la couleur n'existe pas
     */
    private static boolean isValide(String pseudo, Couleur couleur) {
        return pseudo.length() < PSEUDO_TAILLE_MAX && pseudo.length() != 0
            && Arrays.asList(Couleur.values()).contains(couleur);
    }

    /**
     * @return le pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @return la couleur
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Retourne le score dépendant du nombre de pions de même couleur
     * sur le plateau indiqué en paramètre
     *
     * @param plateau le plateau Ã  analyser les pions
     *
     * @return le score en rapport au nombre de pions sur le plateau
     */
    public int getScore(Plateau plateau) {
        return plateau.getPions(couleur).size();
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getPseudo();
    }

    /* non javadoc
     * @see java.lang.Object#equals(Object o)
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Joueur &&
            pseudo.equals(((Joueur) o).getPseudo());
    }
    
    /**
     * Ordre les joueurs par leur couleur, les noirs en premier
     * @param aTrier Joueurs à ordonner en fonction de leur couleur
     * @return La référence du tableau trié
     */
    public static Joueur[] trier(Joueur[] aTrier) {
        Joueur sauvegarde;  // sauvegarde le joueur qui se fait remplacer
        
        int tri;  // indice de tri
        
        tri = 0;
        for (int indice = 0 ; indice < aTrier.length ; indice++) {
            sauvegarde = aTrier[indice];
            tri = indice;
            while ( tri > 0 && aTrier[tri - 1].getCouleur().getIcone() 
                               < sauvegarde.getCouleur().getIcone() ) {
                    aTrier[tri] = aTrier[tri-1];
                    tri --;
            }
             aTrier[tri] = sauvegarde;
        }
        
        return aTrier;
    }
}