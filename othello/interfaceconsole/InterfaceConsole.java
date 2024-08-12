/*
 * InterfaceConsole.java                                7 mai 2021
 * IUT info1 2020-2021, pas de copyright pas de droit d'auteur
 */
package othello.interfaceconsole;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.joueur.Joueur;
import othello.joueur.Ordinateur;
import othello.outils.OutilConversion;
import othello.plateau.Plateau;
import othello.outils.OutilSaisie;
import othello.partie.Partie;

import static othello.joueur.Ordinateur.NIVEAUX;

import java.util.List;

/**
 * Gère l'interface de l'application Othello
 *
 * @author David Simonin
 * @version 1.0
 */
public class InterfaceConsole {

    /**
     * Interface menu principal
     */
    public static final String MENU_PRINCIPAL =
        """
            -----------------------------
            |          Othello          |
            -----------------------------

            1) Nouvelle partie
            2) Charger partie
            3) Quitter le jeu
        """;

    /**
     * Texte d'aide pour le joueur
     */
    public static final String COMMANDES_JOUEUR =
        """
            1) Passer le tour
            2) Sauvegarder la partie
            3) Quitter la partie
            4) Quitter le jeu
            Pour placer pion, entrez son indice de ligne
            suivi de son indice de colonne
        """;
    
    /**
     * Mode de jeux
     */
    public static final String MODES_DE_JEUX = 
            """
                1) Jouer contre l'ordinateur
                2) Jouer avec un autre joueur
            """;
    
    /**
     * Message pour la saisie du joueur
     */
    public static final String MESSAGE_SAISIE_JOUEUR = 
            "Saisissez un pseudo inf�rieur � 15 caract�res :";
    
    /**
     * Message pour le choix de la difficult�
     */
    public final static String CHOIX_DIFFICULTE = 
            """
               Veuillez choisir un mode de difficult� : 
               1) Facile
               2) Normale
               3) Difficile
               4) Retourner au menu principale
            """;
    
    /**
     * Diff�rent niveaux de difficult�
     */
    public final static String[] DIFFICULTES = {
            "FACILE", "NORMAL", "DIFFICILE"
    };

    /**
     * Affiche le menu principal du jeu Othello
     */
    public static void afficherMenuPrincipal() {
        System.out.println(MENU_PRINCIPAL);
    }
    
    /**
     * Affiche et g�re le choix du mode de jeu
     * @return La partie cr��e
     */
    public static Partie choixModesDeJeux() {
        Partie partie;
        AfficherModesDeJeux();
        partie = gererModesDeJeux();
        return partie;
    }

    /**
     * G�re le choix du mode de jeu
     * @return La r�f�rence de la partie cr��e
     */
    public static Partie gererModesDeJeux() {
        Partie partie;
        
        partie = null;
        if (OutilSaisie.lireNombre(1, 2) == 1) {
            partie = choixDifficulte();
        } else {
            partie = creerPartieJoueurContreJoueur();
        }
        
        return partie;
    }
    
    /**
     * G�re le choix du mode de difficult�
     * @return la r�f�rence de la partie cr��e
     */
    public static Partie choixDifficulte() {
        Partie partie;
        
        int saisie;
        
        partie = null;
        afficherDifficultees();
        saisie = OutilSaisie.lireNombre(1, 4);
        if (1 <= saisie && saisie <= 3) {
            partie = creerPartieOrdinateur(NIVEAUX[saisie]);
        } 
        
        return partie;
    }
    
    /**
     * Affiche les diff�rentes options de saisie pour le choix de 
     * la difficult�e
     */
    public static void afficherDifficultees() {
        System.out.println(CHOIX_DIFFICULTE);
    }
    
    /**
     * Cr�er une partie contre un ordinateur
     * @param difficulte Niveau de difficulte de l'ordinateur
     * @return La r�f�rence de la partie cr��e
     */
    public static Partie creerPartieOrdinateur(String difficulte) {
        Plateau damier;
        
        Partie partie;
        
        Couleur couleurJoueur = Couleur.random();
        
        Joueur[] joueurs =  {
                new Joueur(OutilSaisie.saisie(MESSAGE_SAISIE_JOUEUR),
                           couleurJoueur),                                  
                new Ordinateur(couleurJoueur.opposee(), difficulte)
        };
        
        damier = new Plateau();
        
        partie = new Partie(joueurs, damier);
        
        return partie;
    }
    
    /**
     * Cr�er une partie entre 2 joueurs
     * @return La r�f�rence de la partie cr��e
     */
    public static Partie creerPartieJoueurContreJoueur() {
        Couleur couleurJoueur1 = Couleur.random();

        Joueur[] joueurs =  {
                new Joueur(OutilSaisie.saisie(MESSAGE_SAISIE_JOUEUR), 
                           couleurJoueur1),
                new Joueur(OutilSaisie.saisie(MESSAGE_SAISIE_JOUEUR), 
                        couleurJoueur1.opposee())
        };

        Plateau damier = new Plateau();

        Partie JoueurcontreJoueur = new Partie(joueurs, damier);

        return JoueurcontreJoueur;
    }
    

    /**
     * Affiche les diff�rents mode de jeu
     */
    public static void AfficherModesDeJeux() {
        System.out.println(MODES_DE_JEUX);
    }

    /**
     * Affiche les commandes disponibles pour le joueur
     */
    public static void afficherCommandesJoueur() {
        System.out.println(COMMANDES_JOUEUR);
    }

    /**
     * Affiche le tour du joueur qui doit jouer
     * @param couleur Couleur du joueur qui doit jouer
     */
    public static void afficherJoueurAJouer(Couleur couleur) {
        System.out.printf("C'est au tour de %s de jouer : ", couleur);
    }

    /**
     * Affiche le score du joueur
     * @param pseudo Pseudo du joueur � qui ont va afficher le score
     */
    public static void afficherScore(Joueur pseudo) {
        System.out.printf("Le score de %s est de %d points\n",
            pseudo.getPseudo(), pseudo.getScore(new Plateau()));
    }

    /**
     * Affiche le plateau de jeu et les pions dessus
     *
     * @param plateau       Plateau à afficher
     * @param caseAModifier les cases où une icône différente sera utilisé
     */
    public static void afficherPlateau(Plateau plateau,
                                       List<Position> caseAModifier) {
        Position position;
        String icone;
        StringBuilder damier = new StringBuilder("  ");

        for (int i = 1; i <= Plateau.TAILLE_RANGEE; i++) {
            damier.append(" ").append(i) // numéro des colonnes
                .append(" ");
        }
        damier.append("\n");

        for (int x = 0; x < Plateau.TAILLE_RANGEE; ++x) {
            damier.append(OutilConversion.COLONNE_INDEXES[x])
                .append(" "); // lettres des lignes
            for (int y = 0; y < Plateau.TAILLE_RANGEE; ++y) {
                // on inverse le x et le y pour corriger la visualisation
                // de l'ordre du plateau sur la console
                position = new Position(y, x);
                if (caseAModifier.contains(position)) {
                    icone = "-";
                } else if (plateau.isCaseVide(position)) {
                    icone = " ";
                } else {
                    icone = plateau.getPion(position).toString();
                }
                damier.append(" ").append(icone) // l'icone du pion/case
                    .append(" ");
            }
            damier.append("\n");
        }

        System.out.println(damier);
    }
}