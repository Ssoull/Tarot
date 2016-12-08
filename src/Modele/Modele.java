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
	 * Nombre de cartes dans un jeu de Tarot.
	 */
	private final int NOMBRE_CARTES_TOTAL_TAROT = 78;
	/**
	 * Le nombre de cartes a donner a un joueur lors d'une distribution.
	 */
	private final int NOMBRE_CARTES_POUR_DISTRIBUTION = 3;
	/**
	 * Le nombre de cartes minimum dans la main du joueur.
	 */
	private final int NOMBRE_CARTES_MINIMUM_PAR_JOUEUR = 18;
	/**
	 * Le nombre de carte dans le chien.
	 */
	private final int NOMBRE_CARTES_CHIEN = 6;
	/**
	 * Le nombre maximum de cartes dans l'ecart.
	 */
	private final int NOMBRE_CARTES_ECART = 6;
	/**
	 * Indique si la mains du joueur est triee.
	 */
	private boolean notificationMainsDuJoueurPourAffichagePourTrie;
	
	
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
	 * Represente l'ecart.
	 */
	private ArrayList<Carte> paquetEcart;
	
	
	/**
	 * Initialise les elements du Modele.
	 */
	public Modele() {
		initialisationPaquet();

		try {
			initialisationMains();
			constructionJeuDeCarte();
		} catch (TarotException e) {
			e.message();
		}
		
		notificationMainsDuJoueurPourAffichagePourTrie = false;
	}

	
	/**
	 * Initialise les paquets.
	 */
	private void initialisationPaquet() {
		paquetDuJeu = new  ArrayList<Carte>();
		paquetDuChien = new ArrayList<Carte>(NOMBRE_CARTES_CHIEN);
		paquetEcart = new ArrayList<Carte>(NOMBRE_CARTES_ECART);
	}

	
	/**
	 * Initialise les mains des joueurs.
	 */
	private void initialisationMains() throws TarotException {
		mainsDesJoueurs = new ArrayList<ArrayList<Carte>>(NOMBRE_JOUEURS);

		for(int nbrJoueur = 0; nbrJoueur < NOMBRE_JOUEURS; ++nbrJoueur) {
			mainsDesJoueurs.add(new ArrayList<Carte>());
		}

		if(mainsDesJoueurs.size() != NOMBRE_JOUEURS) {
			throw new TarotException(this, "Il y a " + mainsDesJoueurs.size() + " mains au lieu de " + NOMBRE_JOUEURS);
		}
	}

	
	/**
	 * Construit le contenu du paquet de jeu.
	 */
	private void constructionJeuDeCarte() throws TarotException {
		try {
			constructionParCouleurDuJeu(14, TypeCarte.Pique);
			constructionParCouleurDuJeu(14, TypeCarte.Coeur);
			constructionParCouleurDuJeu(21, TypeCarte.Atout);
			constructionParCouleurDuJeu(14, TypeCarte.Carreau);
			constructionParCouleurDuJeu(14, TypeCarte.Trefle);
		}
		catch(TarotException e) {
			e.message();
		}
		paquetDuJeu.add(new Carte(22, TypeCarte.Excuse));
		
		if(paquetDuJeu.size() != NOMBRE_CARTES_TOTAL_TAROT)
			throw new TarotException(this, "A l'initialisation, le paquet de cartes contient " + paquetDuJeu.size() + " cartes et non " + NOMBRE_CARTES_TOTAL_TAROT);

		Collections.shuffle(paquetDuJeu);
	}

	
	/**
	 * Construit une partie du paquet en fonction de la couleur et du nombre de carte de cette couleur. 
	 * @param nbrCartes
	 * @param typeCarte
	 */
	private void constructionParCouleurDuJeu(int nbrCartes, TypeCarte typeCarte) throws TarotException {
		int nbCartesAjoutees = 0;
		int tailleInitiale = paquetDuJeu.size();

		for(int cptCartes = 1; cptCartes <= nbrCartes; ++cptCartes) {
			paquetDuJeu.add(new Carte(cptCartes, typeCarte));
			++nbCartesAjoutees;
		}

		if(paquetDuJeu.size() != tailleInitiale + nbCartesAjoutees)
			throw new TarotException(this, "Il y a " + nbCartesAjoutees + " cartes [" + typeCarte.toString() + "] ajoutees et non " + nbrCartes);
	}

	
	/**
	 * Permet de tirer 3 cartes pour les joueurs.
	 */
	public void tirerCartesPourLesJoueurs(int num_joueur) throws TarotException {
		int nbCartesInitialDansMainJoueur = mainsDesJoueurs.get(num_joueur).size();
		for(int nbrCartesATirer = 0; nbrCartesATirer < NOMBRE_CARTES_POUR_DISTRIBUTION; ++nbrCartesATirer) {
			mainsDesJoueurs.get(num_joueur).add(tiragePremiereCarteDuPaquetDeJeu());
		}
		
		if(mainsDesJoueurs.get(num_joueur).size() != nbCartesInitialDansMainJoueur + NOMBRE_CARTES_POUR_DISTRIBUTION) {
			int nbCartesAjoutees = mainsDesJoueurs.get(num_joueur).size() - nbCartesInitialDansMainJoueur;
			throw new TarotException(this, "Lors du tour du joueur " + num_joueur + ", seulement " + nbCartesAjoutees
					+ " cartes ont ete ajoutees et non " + NOMBRE_CARTES_POUR_DISTRIBUTION);
		}

		if(num_joueur == 0) {
			if(paquetDuChien.size() < NOMBRE_CARTES_CHIEN) {
				paquetDuChien.add(tiragePremiereCarteDuPaquetDeJeu());
			}
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
	 * Trie la main du joueur 1 en classant les cartes par couleur et valeur.
	 */
	public void trierMainJoueur() {
		Collections.sort(mainsDesJoueurs.get(0), Carte.comparateurCartes);
		
		notificationMainsDuJoueurPourAffichagePourTrie = true;
		
		this.setChanged();
		this.notifyObservers();
	}

	
	/**
	 * Ajoute une carte en parametre dans l'ecart.
	 * Cette carte vient de la main du joueur, on doit donc l'enlever.
	 * @param carte
	 */
	public void ajoutCarteEcart(Carte carte) {
		paquetEcart.add(carte);
		mainsDesJoueurs.get(0).remove(carte);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	
	/**
	 * Ajoute une carte en parametre dans la main du joueur.
	 * @param carte
	 */
	public void ajoutCarteDansMainJoueur(Carte carte) {
		mainsDesJoueurs.get(0).add(carte);
	}
	
	
	/**
	 * Verifie si l'ecart est plein.
	 * @return
	 */
	public boolean ecartPlein() throws TarotException {
		if(paquetEcart.size() > 6)
			throw new TarotException(this, "L'ecart contient trop de cartes [" + paquetEcart.size() + "]");
		return paquetEcart.size() == 6;
	}
	
	
	/**
	 * Methode vidant le chien.
	 */
	public void viderChien() {
		paquetDuChien.clear();
	}
	
	
	/**
	 * Methode remplissant le chien a partir de l'ecart.
	 */
	public void recupererCartesEcartDansChien() {
		for(Carte carte : paquetEcart) {
			paquetDuChien.add(carte);
		}
		
		paquetEcart.clear();
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
	 * Accesseur de l'ecart.
	 * @return ArrayList<Carte>
	 */
	public ArrayList<Carte> getPaquetEcart() {
		return paquetEcart;
	}

	
	/**
	 * Accesseur du nombre de cartes minimum contenu dans une mains.
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
	
	/**
	 * @return Le nombre de cartes au total dans un jeu de Tarot -> 78
	 */
	public int getNombreCartesTotalTarot() {
		return NOMBRE_CARTES_TOTAL_TAROT;
	}
	
	/**
	 * @return Le nombre de cartes a donner a un joueur lors d'une distribution -> 3
	 */
	public int getNombreCartesPourDistribution() {
		return NOMBRE_CARTES_POUR_DISTRIBUTION;
	}
	
	/**
	 * @return Le nombre de cartes maximum dans l'ecart -> 6.
	 */
	public int getNombreCartesEcartMax() {
		return NOMBRE_CARTES_ECART;
	}
	
	/**
	 * @return Le nombre de cartes maximum dans le chien -> 6.
	 */
	public int getNombreCartesChienMax() {
		return NOMBRE_CARTES_CHIEN;
	}
	
	/**
	 * Accesseur pour savoir si la mains du joueur dans la vue est trie ou non.
	 * @return boolean
	 */
	public boolean getNotificationMainsDuJoueurPourAffichagePourTrie() {
		return notificationMainsDuJoueurPourAffichagePourTrie;
	}

	
	/**
	 * Mutateur utilise pour dire que le mains du joueur de la vue est trie.
	 * @param trie
	 */
	public void setNotifiactionMainsDuJoueurPourAffichagePourTrie(boolean trie) {
		notificationMainsDuJoueurPourAffichagePourTrie = trie;
	}
}


