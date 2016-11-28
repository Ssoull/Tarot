package Controleur;

import Modele.Modele;
import Tests.TarotException;

/**
 * Represente le controleur de l'architecture MVC. 
 * @author Gutierrez, Despret
 *
 */
public class Controleur {	
	/**
	 * Represente le Modele de l'architecture MVC.
	 */
	private Modele modele;

	/**
	 * Constructeur parametre du Controleur.
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
				try {
					modele.tirerCartesPourLesJoueurs(numJoueur);
				} catch (TarotException e) {
					e.message();
				}
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
