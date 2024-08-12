/*
 * Ordinateur.java                                17 mai 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.joueur;

import othello.caracteristiques.Couleur;

/** 
 * Ordinateur avec trois niveaux de difficult�s, facile, normal et difficile
 * @author Info1
 *
 */
public class Ordinateur extends Joueur {

    /**
     * Niveau de difficult�s possible de l'ordinateur
     */
    public static final String[] NIVEAUX = {
          "FACILE",
          "NORMAL",
          "DIFFICILE"
    };
    
    /**
     * Niveau de difficult� de l'ordinateur
     */
    private String difficulte;
    
    /**
     * Cr�er un joueur de type ordinateur
     * @param pseudo Pseudo de l'ordinateur
     * @param couleur Couleur de l'ordinateur
     * @param difficulte Difficult� de la couleur
     */
    public Ordinateur(Couleur couleur, String difficulte) {
        super("ordinateur", couleur);
        if (!isValide(difficulte)) {
            throw new IllegalArgumentException("Erreur, la diffcult� n'est "
                                               + "pas valide");
        }
        
        this.difficulte = difficulte;
    }
    
    /**
     * V�rifie si le niveau de difficult� est valide
     * @param aVerifier chaine de caract�re � v�rifier
     * @return true si la difficult� est valide,
     *         false sinon
     */
    public boolean isValide(String aVerifier) {
        boolean valide = false;
        for (String verification : NIVEAUX) {
            if (aVerifier.equals(verification)) {
                valide = true;
            }
        }
        return valide;
    }
    
    /***
     * V�rifie si la difficult� en param�tre est bien celle de l'ordinateur
     * @param aVerifier Difficult� � v�rifier
     * @return True si aVerifier correspond � la difficult� de l'ordinateur
     */
    public static boolean isDifficulte(Joueur ordinateur, String aVerifier) {
        return ordinateur instanceof Ordinateur 
               && ((Ordinateur)(ordinateur)).getDifficulte().equals(aVerifier);
    }
    
    /**
     * @return La difficult�
     */
    public String getDifficulte() {
        return difficulte;
    }
}
