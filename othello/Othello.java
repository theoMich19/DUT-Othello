package othello;

import othello.partie.Partie;
import othello.interfaceconsole.InterfaceConsole;
import othello.outils.OutilFichier;

import static othello.outils.OutilSaisie.lireNombre;

/**
 * Classe principale de l'exécution de la partie d'Othello
 *
 * @author Théo Michellon, Maxime Moskalyk, Yohann May David Simonin, Simon
 * Launay
 * @version 1.0
 */
public class Othello {

    /**
     * Méthode lançant une partie d'Othello en initialisant les 2 joueurs et le
     * plateau
     *
     * @param args non utilisé
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) {

        Partie partie;
        
        int saisie,
            derniereCommande;  // derni�re commande saisie par le joueur lors 
                               // de sa partie
        
        partie = null;
        saisie = 0;
        derniereCommande = 0;
        do {
            InterfaceConsole.afficherMenuPrincipal();
            saisie = lireNombre(1, 3);
            switch (saisie) {
            case 1 -> {
                partie = InterfaceConsole.choixModesDeJeux();
                if (partie != null)  {
                    derniereCommande = partie.jouer();
                }
            }
            case 2 -> {
                try {
                    partie = OutilFichier.charger();
                    derniereCommande = partie.jouer();
                } catch (Exception erreur) {
                    System.out.println("Erreur le nom du fichier de sauvegarde "
                                       + "n'existe pas\n");
                }
            }
            case 3 -> System.out.println("Vous quittez le jeu");
            }
        } while (saisie != 3 && derniereCommande != '4');
    }
}
