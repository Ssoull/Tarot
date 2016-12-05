package Controleur;

import Modele.Carte;
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
	
	private boolean recommencerPartie;

	/**
	 * Constructeur parametre du Controleur.
	 * @param modele
	 */
	public Controleur(Modele modele) {
		this.modele = modele;
		recommencerPartie = false;
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

	public void trierMainJoueur() {
		modele.trierMainJoueur();
	}
	
	public void ajoutCarteDansEcart(String chemin_carte) {
		Carte carteTmp = null;
		
		for(Carte carte : modele.getMainDuPremierJoueur()) {
			if(carte.getCheminImageCarte().equals(chemin_carte) && modele.getPaquetEcart().size() != 6) {
				carteTmp = carte;
			}
		}
		
		if(carteTmp != null) {
			modele.ajoutCarteEcart(carteTmp);
		}
	}

	public boolean getRecommencerPartie() {
		return recommencerPartie;
	}

	public void setRecommencerPartie(boolean recommencerPartie) {
		this.recommencerPartie = recommencerPartie;
	}

	public void viderPaquetDuChien() {
		
	}

	public void viderPaquetDuChienEtRecupererEcartDansChien() {
		if(modele.getPaquetEcart().size() == 6)	{
			modele.viderChien();
			modele.recupererCartesEcartDansChien();
		}
	}
}
