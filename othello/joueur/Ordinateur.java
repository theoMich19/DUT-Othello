/*
 * Ordinateur.java                                17 mai 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.joueur;

import othello.caracteristiques.Couleur;

/** 
 * Ordinateur avec trois niveaux de difficultés, facile, normal et difficile
 * @author Info1
 *
 */
public class Ordinateur extends Joueur {

    /**
     * Niveau de difficultés possible de l'ordinateur
     */
    public static final String[] NIVEAUX = {
          "FACILE",
          "NORMAL",
          "DIFFICILE"
    };
    
    /**
     * Niveau de difficulté de l'ordinateur
     */
    private String difficulte;
    
    /**
     * Créer un joueur de type ordinateur
     * @param pseudo Pseudo de l'ordinateur
     * @param couleur Couleur de l'ordinateur
     * @param difficulte Difficulté de la couleur
     */
    public Ordinateur(Couleur couleur, String difficulte) {
        super("ordinateur", couleur);
        if (!isValide(difficulte)) {
            throw new IllegalArgumentException("Erreur, la diffculté n'est "
                                               + "pas valide");
        }
        
        this.difficulte = difficulte;
    }
    
    /**
     * Vérifie si le niveau de difficulté est valide
     * @param aVerifier chaine de caractère à vérifier
     * @return true si la difficulté est valide,
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
     * Vérifie si la difficulté en paramètre est bien celle de l'ordinateur
     * @param aVerifier Difficulté à vérifier
     * @return True si aVerifier correspond à la difficulté de l'ordinateur
     */
    public static boolean isDifficulte(Joueur ordinateur, String aVerifier) {
        return ordinateur instanceof Ordinateur 
               && ((Ordinateur)(ordinateur)).getDifficulte().equals(aVerifier);
    }
    
    /**
     * @return La difficulté
     */
    public String getDifficulte() {
        return difficulte;
    }
}
