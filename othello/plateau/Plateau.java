/*
 * Plateau.java                     27 Avril 2021
 * IUT Rodez, pas de droits
 */
package othello.plateau;

import othello.caracteristiques.Couleur;
import othello.caracteristiques.Position;
import othello.pion.Pion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe définissant le plateau de jeu constitué de lignes, de colonnes et de
 * cases qui peuvent être vides ou remplis
 *
 * @author Maxime Moskalyk
 * @version 1.0
 */
public class Plateau implements Serializable {

    /**
     * Taille max du plateau (lignes et colonnes)
     */
    public static final int TAILLE_RANGEE = 8;

    /**
     * Le damier composé de case pouvant comporter un pion
     * Damier[colonne][ligne] soit [x][y]
     */
    private final Pion[][] damier = new Pion[TAILLE_RANGEE][TAILLE_RANGEE];

    /**
     * Initialise le plateau
     */
    public Plateau() {
        initialiseDefaut();
    }

    /**
     * Vérifie si il y a dépassement sur les abscisses et/ou ordonnées
     *
     * @param position coordonnées en X et Y
     *
     * @return vrai si les positions indiqués sont valides
     */
    public static boolean isValide(Position position) {
        return position.x() < 0 || position.y() < 0 ||
                position.x() >= Plateau.TAILLE_RANGEE ||
                position.y() >= Plateau.TAILLE_RANGEE;
    }

    /**
     * Ajout des pions par défaut au centre du plateau
     */
    public void initialiseDefaut() {
        remplirCase(new Pion(new Position(3, 4), Couleur.NOIR));
        remplirCase(new Pion(new Position(4, 3), Couleur.NOIR));

        remplirCase(new Pion(new Position(3, 3), Couleur.BLANC));
        remplirCase(new Pion(new Position(4, 4), Couleur.BLANC));
    }

    /**
     * Remplie une case par un pion à une position donnée
     *
     * @param pion le pion à poser
     *
     * @throws IndexOutOfBoundsException si il y a un dépassement sur la taille
     *                                   du plateau
     */
    public void remplirCase(Pion pion) {
        if (isValide(pion.getPosition())) {
            throw new IndexOutOfBoundsException("Dépassement sur un pion : " +
                pion.getPosition());
        }
        damier[pion.getPosition().x()][pion.getPosition().y()] = pion;
    }

    /**
     * Retourne un pion à une certaine position donnée
     *
     * @param position la position en abscisse et ordonnée
     *
     * @return le pion trouvé dans le damier
     */
    public Pion getPion(Position position) {
        return damier[position.x()][position.y()];
    }

    /**
     * Vérifie si une case est vide à une certaine position donnée
     *
     * @param position la position de la case à vérifier
     *
     * @return vrai si la case n'a aucun pion positionné dessus
     */
    public boolean isCaseVide(Position position) {
        return getPion(position) == null;
    }

    /**
     * Vérifie si le plateau est remplie
     *
     * @return vrai si le plateau ne comporte plus de cases vide
     */
    public boolean isRempli() {
        return getCasesVides().isEmpty();
    }

    /**
     * Retourne tout les pions du plateau de la couleur indiquée
     *
     * @param couleur couleur des pions souhaités
     *
     * @return tout les pions de la couleur indiquée
     */
    public List<Pion> getPions(Couleur couleur) {
        List<Pion> pions = new ArrayList<>();
        for (Pion[] pionsDamier : getDamier()) {
            for (Pion pion : pionsDamier) {
                if (pion != null && couleur == pion.getCouleur()) {
                    pions.add(pion);
                }
            }
        }
        return pions;
    }

    /**
     * Retourne tout les pions visibles sur le plateau
     *
     * @return tout les pions
     */
    public List<Pion> getPions() {
        List<Pion> pions = new ArrayList<>(getPions(Couleur.BLANC));
        pions.addAll(getPions(Couleur.NOIR));

        return pions;
    }

    /**
     * @return la damier contenant toutes les cases
     */
    public Pion[][] getDamier() {
        return damier;
    }

    /**
     * Affiche toutes les cases vides du plateau
     *
     * @return tout les cases vide
     */
    public List<Position> getCasesVides() {
        List<Position> vides = new ArrayList<>();
        for (int x = 0; x < TAILLE_RANGEE; x++) {
            for (int y = 0; y < TAILLE_RANGEE; y++) {
                Position position = new Position(x, y);
                if (isCaseVide(position)) {
                    vides.add(position);
                }
            }
        }
        return vides;
    }

    /**
     * Méthode permettant de déterminer chaque position ou l'on peut placer un
     * pion à chaque tour.
     *
     * @param couleur couleur du joueur qui joue son tour
     *
     * @return une liste contenant toutes les positions possibles de jouer
     */
    public List<Position> getCasesJouables(Couleur couleur) {
        List<Position> positionJouables = new ArrayList<>();

        /* On simule un pion sur chaque case vide du plateau et on
         * analyse si des pions adverses sont encadrables. Si c'est
         * le cas, on enregistre cette case vide comme "jouable"
         *
         * TODO éviter d'analyser chaque case vide mais
         *  	plutôt analyser autour de chaque pion adverse
         */
        for (Position positionVide : getCasesVides()) {
            Pion pion = new Pion(positionVide, couleur);

            if (!pion.chercherAdversairesAutour(this).isEmpty()) {
                positionJouables.add(positionVide);
            }
        }

        return positionJouables;
    }

    /**
     * Vérifier si la position entrée par le joueur est possible
     *
     * @param position           choix du pion de l'utilisateur
     * @param positionsPossibles différentes positions possible de placer un
     *                           pion
     *
     * @return true si le choix de l'utilisateur = position possible, false
     * sinon
     */
    public boolean isJouable(Position position,
                             List<Position> positionsPossibles) {
        return positionsPossibles.contains(position);
    }

    /**
     * Méthode permettant de placer un pion en position X, Y de la couleur du
     * joueur qui joue si la position qu'il renseigne est valide.
     *
     * @param pion le pion à vérifier puis à placer sur le plateau
     *
     * @return true si le pion a bien était placé, false sinon
     */
    public boolean placerPion(Pion pion) {
        if (isJouable(pion.getPosition(),
            getCasesJouables(pion.getCouleur()))) {

            remplirCase(pion);
            for (Pion ennemi : pion.chercherAdversairesAutour(this)) {
                remplirCase(new Pion(ennemi.getPosition(),
                    pion.getCouleur()));
            }
            return true;
        }
        return false;
    }

    /* non javadoc
     * @see java.lang.Object#equals(Object o)
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Plateau &&
            Arrays.deepEquals(damier, ((Plateau) o).getDamier());
    }
}
