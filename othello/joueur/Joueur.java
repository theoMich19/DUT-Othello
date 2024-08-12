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
 * Classe repr�sentant un joueur
 *
 * @author David Simonin
 * @version 1.0
 */
public class Joueur implements Serializable {

    /** TODO commenter le r�le du champ (attribut, r�le associatif) */
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
     * initialise le joueur si les donn�es sont correctes
     *
     * @param pseudo  pseudo du joueur
     * @param couleur couleur des pions du joueur
     *
     * @throws IllegalArgumentException si le nom du joueur d�passe les 25
     *                                  caract�re ou si la cha�ne est vide ou si
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
     * V�rifie que le pseudo et la couleur sont bien valide
     *
     * @param pseudo  pseudo du joueur
     * @param couleur couleur des pions du joueur
     *
     * @return vrai si la taille du pseudo est inf�rieur à 25 caract�res et si
     * la cha�ne n'est pas vide et si la couleur n'existe pas
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
     * Retourne le score d�pendant du nombre de pions de m�me couleur
     * sur le plateau indiqu� en param�tre
     *
     * @param plateau le plateau à analyser les pions
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
     * @param aTrier Joueurs � ordonner en fonction de leur couleur
     * @return La r�f�rence du tableau tri�
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