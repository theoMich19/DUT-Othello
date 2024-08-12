/*
 * MÃ©thodes pour gÃ©rer les fichiers de l'application "gestion des horaires de
 * bus"
 */
package othello.outils;

import static othello.outils.OutilSaisie.saisie;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import othello.partie.Partie;

/**
 * Créer et charge des sauvegardes du jeu Othello
 *
 * @author INFO1
 * @version 1.0
 */
public class OutilFichier {
    
    /**
     * Message de saisie pour la sauvegarde et pour charger une sauvegarde
     */
    public static final String MESSAGE_SAISIE =
            "Saisisssez un nom de ficher inférieur à 15 caractères ou quitter "
            + "la saisie en entrant STOP :";
    
    /**
     * Sauvegarde la partie
     * @param partie Partie à sauvegarder
     */
    public static void sauvegarder(Partie partie) {
        String saisie;

        saisie = saisie(MESSAGE_SAISIE);
        if (!saisie.equals("STOP")) {
            try {
                FileOutputStream aSauvegarder =  new FileOutputStream(saisie);
                ObjectOutputStream sauvegarde =
                        new ObjectOutputStream(aSauvegarder);
                sauvegarde.writeObject(partie);
                sauvegarde.flush();
                sauvegarde.close();
                System.out.println("Sauvegarde réussi");
            } catch (Exception erreur) {
                System.out.println(erreur);
            }
        }
    }
    
    /**
     * Charge une partie
     * @return La partie chargée
     */
    public static Partie charger()  {
        Partie partie;
        String saisie;
        
        partie = null;
        saisie = saisie(MESSAGE_SAISIE);
        if (!saisie.equals("STOP")) {
            try {
                FileInputStream aCharger =  new FileInputStream(saisie);
                ObjectInputStream sauvegarde = new ObjectInputStream(aCharger);
                partie = (Partie)sauvegarde.readObject();
            } catch (Exception erreur) {
                // corps vide
            }
        }
        return partie;
    }
}