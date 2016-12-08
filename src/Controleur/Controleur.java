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

	/**
	 * Constructeur parametre du Controleur.
	 * @param modele
	 */
	public Controleur(Modele modele) {
		this.modele = modele;
	}

	/**
	 * Distribue les cartes aux joueurs.
	 * @return TRUE si on peut continuer a distribuer, FALSE sinon.
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
	
	
	/**
	 * Trie la main du joueur.
	 */
	public void trierMainJoueur() {
		// On ne refait pas de trie si l'affichage du precedent tri ne s'est pas fait
		if(!modele.getNotificationMainsDuJoueurPourAffichagePourTrie()) {
			modele.trierMainJoueur();
		}
	}
	
	
	/**
	 * Ajoute une carte dans l'ecart, avec le chemin pour acceder a son image en parametre.
	 * @param chemin_carte
	 * @throws TarotException
	 */
	public void ajoutCarteDansEcart(String chemin_carte) throws TarotException {
		Carte carteTmp = null;
		
		for(Carte carte : modele.getMainDuPremierJoueur()) {
			if(carte.getCheminImageCarte().equals(chemin_carte) && modele.getPaquetEcart().size() != 6) {
				carteTmp = carte;
			}
		}
		
		if(carteTmp != null) {
			modele.ajoutCarteEcart(carteTmp);
		}
		else
			throw new TarotException(this, "Impossible de retrouver la carte a ajouter dans l'ecart");
	}
	
	
	/**
	 * Recupere les cartes de l'ecart pour le mettre dans le chien.
	 * @throws TarotException
	 */
	public void recupererEcartDansChien() throws TarotException {
		if(modele.getPaquetEcart().size() == 6)	{
			modele.recupererCartesEcartDansChien();
		}
		
		if(modele.getPaquetDuChien().size() != 6)
			throw new TarotException(this, "Il y a " + modele.getPaquetDuChien().size() + " cartes et non 6 dans le chien apres recuperation de l'ecart");
	}
	
	/**
	 * Vider le paquet du chien.
	 * @throws TarotException
	 */
	public void viderPaquetDuChien() throws TarotException {
		if(!modele.getPaquetDuChien().isEmpty()) {
			modele.viderChien();
			
			if(!modele.getPaquetDuChien().isEmpty())
				throw new TarotException(this, "Le chien ne s'est pas correctement vide, il reste " + modele.getPaquetDuChien().size() + " cartes");
		}
	}
}
