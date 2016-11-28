package Modele;

import Tests.TarotException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

/**
 * Represente le modele de l'architecture MVC.
 * @author Gutierrez, Despret
 */
public class Modele extends Observable {
	/**
	 * Le nombre joueur dans une partie
	 */
	private final int NOMBRE_JOUEURS = 4;

	/**
	 * Le nombre de cartes pour la distribution.
	 */
	private final int NOMBRE_CARTE_POUR_DISTRIBUTION = 3;
	/**
	 * Le nombre de cartes minimum dans la main du joueur.
	 */
	private final int NOMBRE_CARTES_MINIMUM_PAR_JOUEUR = 18;

	/**
	 * Le nombre de carte dans le chien.
	 */
	private final int NOMBRE_CARTE_CHIEN = 6;

	/**
	 * Represente le paquet du jeu.
	 */
	private ArrayList<Carte> paquetDuJeu;

	/**
	 * Represente les mains des 4 joueurs.
	 */
	private ArrayList<ArrayList<Carte>> mainsDesJoueurs;

	/**
	 * Represente le paquet du Chien.
	 */
	private ArrayList<Carte> paquetDuChien;

	/**
	 * Initialise les elements du Modele.
	 */
	public Modele() {
		initialisationPaquet();

		try {
			initialisationMains();
		} catch (TarotException e) {
			e.message();
		}

		constructionJeuDeCarte();
	}

	/**
	 * Initialise les paquets.
	 */
	private void initialisationPaquet() {
		paquetDuJeu = new  ArrayList<Carte>();
		paquetDuChien = new ArrayList<Carte>();
	}

	/**
	 * Initialise les mains des joueurs.
	 */
	private void initialisationMains() throws TarotException {
		mainsDesJoueurs = new ArrayList<ArrayList<Carte>>(4);

		for(int nbrJoueur = 0; nbrJoueur < NOMBRE_JOUEURS; ++nbrJoueur) {
			mainsDesJoueurs.add(new ArrayList<Carte>());
		}

		if(mainsDesJoueurs.size() != 4) {
			throw new TarotException("Il y a " + mainsDesJoueurs.size() + " mains au lieu de 4");
		}
	}

	/**
	 * Construit le contenu du paquet de jeu.
	 */
	private void constructionJeuDeCarte() {
		try {
			constructionParCouleurDuJeu(14, CouleurCarte.Pique);
			constructionParCouleurDuJeu(14, CouleurCarte.Coeur);
			constructionParCouleurDuJeu(21, CouleurCarte.Atout);
			constructionParCouleurDuJeu(14, CouleurCarte.Carreau);
			constructionParCouleurDuJeu(14, CouleurCarte.Trefle);
		}
		catch(TarotException e) {
			e.message();
		}
		paquetDuJeu.add(new Carte(22, CouleurCarte.Excuse));

		Collections.shuffle(paquetDuJeu);
	}

	/**
	 * Construit une partie du paquet en fonction de la couleur et du nombre de carte de cette couleur. 
	 * @param nbrCartes
	 * @param couleurCarte
	 */
	private void constructionParCouleurDuJeu(int nbrCartes, CouleurCarte couleurCarte) throws TarotException {
		int nbCartesAjoutees = 0;
		int tailleInitiale = paquetDuJeu.size();

		for(int cptCartes = 1; cptCartes <= nbrCartes; ++cptCartes) {
			paquetDuJeu.add(new Carte(cptCartes, couleurCarte));
			++nbCartesAjoutees;
		}

		if(paquetDuJeu.size() != tailleInitiale + nbCartesAjoutees)
			throw new TarotException(" Il y a " + nbCartesAjoutees + " cartes [" + couleurCarte.toString() + "] ajoutees et non " + nbrCartes);
	}

	/**
	 * Permet de tirer 3 cartes par joueurs.
	 */
	public void tirerCartesPourLesJoueurs(int num_joueur) throws TarotException {

		for(int nbrCartesATirer = 0; nbrCartesATirer < NOMBRE_CARTE_POUR_DISTRIBUTION; ++nbrCartesATirer) {
			mainsDesJoueurs.get(num_joueur).add(tiragePremiereCarteDuPaquetDeJeu());
		}

		if(paquetDuChien.size() < NOMBRE_CARTE_CHIEN) {
			paquetDuChien.add(tiragePremiereCarteDuPaquetDeJeu());
		}

		if(num_joueur == 0) {
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Tire la premiere carte du paquet de jeu et la retourne.
	 * @return Carte
	 */
	private Carte tiragePremiereCarteDuPaquetDeJeu() {
		Carte tmpCarte = paquetDuJeu.get(0);
		paquetDuJeu.remove(0);

		return tmpCarte;
	}

	/**
	 * Accesseur du paquet de jeu.
	 * @return
	 */
	public ArrayList<Carte> getPaquetDuJeu() {
		return paquetDuJeu;
	}

	/**
	 * Accesseur de la main du Joueur.
	 * @return ArrayList<Carte>
	 */
	public ArrayList<Carte> getMainDuPremierJoueur() {
		return mainsDesJoueurs.get(0);
	}

	/**
	 * Accesseur du Chien.
	 * @return ArrayList<Carte>
	 */
	public ArrayList<Carte> getPaquetDuChien() {
		return paquetDuChien;
	}

	/**
	 * Accesseur du nombre de cartes minimum contenu dans une mains
	 * @return int
	 */
	public int getNombreCartesMinimumParJoueur() {
		return NOMBRE_CARTES_MINIMUM_PAR_JOUEUR;
	}

	/**
	 * Accesseur nombre de joueurs dans une partie.
	 * @return int
	 */
	public int getNombreJoueur() {
		return NOMBRE_JOUEURS;
	}
}


