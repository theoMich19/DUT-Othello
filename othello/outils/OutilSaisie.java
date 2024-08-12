/*
 * OutilSaisie.java              7 mai 2021
 * IUT Rodez, pas de droits
 */
package othello.outils;

import java.util.Scanner;

/**
 * Cette classe contient des méthodes outils pour effectuer des saisies : -
 * saisie d'un emplacement (coordonnée)
 *
 * @author Yohann May
 * @version 1.0
 */
public class OutilSaisie {

    /**
     * Objet Scanner pour effectuer des saisies
     */
    private static final Scanner clavier = new Scanner(System.in);


    /**
     * Méthode permettant de saisir une chaine de caractère
     * - A1 est égale à 1A
     * - Un entier entre 1 et 4
     *
     * @return une chaîne non vide composé d'une lettre et un chiffre
     */
    public static String lireChaine() {
        String chaineLue;
        char[] cara = new char[2];

        do {
            chaineLue = clavier.nextLine();
            if (chaineLue.length() == 1 && isDigit(chaineLue.charAt(0), 1, 4)) {
                return chaineLue;
            } else if (chaineLue.length() == 2) {
                cara[0] = chaineLue.toUpperCase().charAt(0);
                cara[1] = chaineLue.toUpperCase().charAt(1);

                if (isDigit(cara[0], 1, 8)) {
                    if (isLetter(cara[1])) {
                        return "" + cara[0] + cara[1];
                    }
                } else if (isLetter(cara[0])) {
                    if (isDigit(cara[1], 1, 8)) {
                        return "" + cara[1] + cara[0];
                    }
                }
            } else {
                chaineLue = ""; // Raz
            }
        } while (chaineLue.length() != 2);

        return chaineLue;
    }
    
    /**
     * Permet de saisir le pseudo d'un joueur, recommence si la saisie 
     * est sup�rieure � JOUEUR_TAILLE_MAX
     * @param message Message afficher � l'�cran lors de la saisie
     * @return La saisie de l'utilisateur
     */
    public static String saisie(String message) {
        /* Longueur maximale de la saisie */
        final int TAILLE_MAX = 15;
        
        String saisie;
        
        saisie = "";
        do { 
            System.out.print(message);
            saisie = clavier.nextLine();
            
            if (saisie.length() > TAILLE_MAX) {
                System.out.printf("Erreur la saisie doit �tre inf�rieur � %d "
                                  + "caract�res", TAILLE_MAX);
            }
        } while (saisie.length() > TAILLE_MAX);
        
        return saisie;
    }

    /**
     * Méthode permettant de continuer
     */
    public static void continuer() {
        System.out.println("Appuyer sur entrée pour continuer.");
        clavier.nextLine();
    }

    /**
     * TODO commenter la méthode
     *
     * @param nbMin nombre minimum autorisé à saisir
     * @param nbMax nombre maximum autorisé à saisir
     *
     * @return un nombre entré par l'utilisateur entre nbMin et nbMax
     */
    public static int lireNombre(int nbMin, int nbMax) {
        int chiffreLue = 0;

        do {
            if (clavier.hasNextInt()) {
                chiffreLue = clavier.nextInt();
            }
            clavier.nextLine();
        } while (chiffreLue < nbMin || nbMax < chiffreLue);

        return chiffreLue;
    }

    /**
     * Vérifie la validité du caractère entré en paramètre
     *
     * @param aVerifier caractère é vérifier
     * @param nbMin     le minimum de l'intervalle à vérifier
     * @param nbMax     le maximum de l'intervalle à vérifier
     *
     * @return true si le caractère est un nombre compris entre 1 et 8, false
     * sinon
     */
    public static boolean isDigit(char aVerifier, int nbMin, int nbMax) {
        return Character.isDigit(aVerifier)
            && (48 + nbMin) <= aVerifier && aVerifier <= (48 + nbMax);
    }

    /**
     * Vérifie la validité du caractère entré en paramètre
     *
     * @param aVerifier caractère é vérifier
     *
     * @return true si le caractère est une lettre compris entre A et H, false
     * sinon
     */
    public static boolean isLetter(char aVerifier) {
        return Character.isAlphabetic(aVerifier)
            && 65 <= aVerifier && aVerifier <= 72;
    }
}
