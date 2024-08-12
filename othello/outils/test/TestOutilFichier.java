/*
 * TestOutilFichier.java                                16 mai 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.outils.test;

import othello.outils.OutilFichier;

import java.util.List;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.interfaceconsole.InterfaceConsole;
import othello.joueur.Ordinateur;
import othello.partie.Partie;

/** 
 * Tests unitaires de la classe OutilFichier
 * @author David Simonin
 */
public class TestOutilFichier {

    /**
     * Test de la méthode sauvegarder et de charger (test visuel)
     */
    public static void testSauvegardeCharger() {
        /* jeu de données pour le test de la sauvegarde et du chargement */
        Partie partie;
        
        List<Position> positionsDispo;
        
        final int NB_TOUR = 3;
        
        System.out.println("TEST : sauvegarder() et charger() (test visuel)\n"
                           + "-----------------------------------------------\n"
                           + "Jouer 3 coups, puis le programme sauvegardera et "
                           + "chargera automatiquement la partie en cours\n"
                           + "Veuillez vérifier que les deux parties "
                           + "corresponde bien :\n");
        
        System.out.println("Saisissez : TestSauvegarde");
        partie  = OutilFichier.charger();
        
        positionsDispo = partie.getDamier().getCasesJouables(partie
                                                             .getJoueur()[0]
                                                              .getCouleur());

        for (int tour = 0 ; tour < NB_TOUR ; tour++) {
            Couleur couleur = partie.getJoueur()[0].getCouleur();
            positionsDispo = partie.getDamier().getCasesJouables(couleur);

            InterfaceConsole.afficherPlateau(partie.getDamier(), positionsDispo);

            if (!(partie.getJoueur()[0] instanceof Ordinateur)
                    && partie.getJoueur()[0].getCouleur() == Couleur.NOIR
                    && partie.determinerTourJoueur() == Couleur.NOIR) {
                partie.tourJoueur(partie.getJoueur()[0], positionsDispo);
                partie.prochainTour();
            } else if (!(partie.getJoueur()[1] instanceof Ordinateur)
                    && partie.getJoueur()[1].getCouleur() == Couleur.BLANC
                    && partie.determinerTourJoueur() == Couleur.BLANC) {
                partie.tourJoueur(partie.getJoueur()[1], 
                                           positionsDispo);
                partie.prochainTour();
            }
            
            partie.tourOrdiFacile(partie.getJoueur()[partie
                                                     .chercherOrdinateur()]);
            partie.prochainTour();
        }
        
        /* test de la sauvegarde */
        InterfaceConsole.afficherPlateau(partie.getDamier(), positionsDispo);
        System.out.println("TEST : sauvegarder :\nSaisissez : TestCharge");
        OutilFichier.sauvegarder(partie);
        
        /* test de l'arrêt de la sauvegarde */
        System.out.println("\nTestez maintenant l'arrêt de la sauvegarde");
        OutilFichier.sauvegarder(partie);
        
        /* test de la charge */
        System.out.println("\nTEST : charger :\nSaisissez : TestCharge");
        partie = OutilFichier.charger();
        positionsDispo = partie.getDamier().getCasesJouables(partie
                                                             .getJoueur()[0]
                                                              .getCouleur());
        InterfaceConsole.afficherPlateau(partie.getDamier(), positionsDispo);
        
        /* test de l'arrêt de la charge */
        System.out.println("\nTestez maintenant l'arrêt de la charge");
        partie = OutilFichier.charger();
    }
    
    /**
     * Lance les tests unitaires
     * @param args non utilisés
     */
    public static void main(String[] args) {
        testSauvegardeCharger();
    }
}
