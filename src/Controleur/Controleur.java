package Controleur;

import Modele.Modele;

/**
 * Repr�sente le controleur de l'architecture MVC. 
 * @author Gutierrez, Despret
 *
 */
public class Controleur {	
	/**
	 * Repr�sente le mod�le de l'architecture MVC.
	 */
	private Modele modele;

	/**
	 * Constructeur param�tr� de Controleur.
	 * @param modele
	 */
	public Controleur(Modele modele) {
		this.modele = modele;
	}

	/**
	 * Distribue les cartes aux joueurs.
	 * @return
	 */
	public boolean distribuerCartes() {
		if(modele.getPaquetDuJeu().size() > 0) {
			for(int numJoueur = 0; numJoueur < modele.getNombreJoueur(); ++numJoueur) {
				modele.tirerCartesPourLesJoueurs(numJoueur);
			}
		}
		
		if(modele.getPaquetDuJeu().size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
