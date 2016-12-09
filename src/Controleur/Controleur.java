/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 */

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
	 * Indique si la main du joueur et le chien doivent etre actualises lors de la distribution.
	 */
	private boolean notificationActualisationMainsDuJoueurEtDuChienLorsDeLaDistribution;
	/**
	 * Indique si la main du joueur est triee.
	 */
	private boolean notificationActualisationMainsDuJoueurPourAffichagePourTrie;
	/**
	 * Indique si le chien doit s'actualiser si il ne doit plus apparaitre.
	 */
	private boolean notificationActualisationSuppressionDuChien;
	/**
	 * Indique si l'ecart doit etre actualise.
	 */
	private boolean notificationActualisationEcart;

	
	
	/**
	 * Constructeur parametre du Controleur.
	 * @param modele
	 */
	public Controleur(Modele modele) {
		this.modele = modele;
		
		notificationActualisationMainsDuJoueurEtDuChienLorsDeLaDistribution = false;
		notificationActualisationMainsDuJoueurPourAffichagePourTrie = false;
		notificationActualisationEcart = false;
		notificationActualisationSuppressionDuChien = false;
	}

	/**
	 * Distribue les cartes aux joueurs.
	 * @return TRUE si on peut continuer a distribuer, FALSE sinon.
	 */
	public boolean distribuerCartes() {
		if(modele.getPaquetDuJeu().size() > 0) {
			notificationActualisationMainsDuJoueurEtDuChienLorsDeLaDistribution = true;
			
			for(int numJoueur = 0; numJoueur < modele.getNombreJoueur(); ++numJoueur) {
				try {
					modele.tirerCartesPourLesJoueurs(numJoueur);
				} catch (TarotException e) {
					e.message();
				}
			}
			
			//return true;
		}
		//else {
			//return false;
		//}
		
		// Retourne vrai si le paquet a encore des cartes et faux sinon.
		// Nous effectuons ce test ici plutot que dans le if au-dessus car cela oblige a l'utilisateur de re-cliquer inutilement sur le bouton distribuer.
		// Pour le voir par vous meme, vous pouvez commenter le if..else.. juste en dessous et decommenter les ligne juste au-dessus. 
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
		// On ne refait pas de trie si l'affichage du precedent tri ne s'est pas fait.
		if(!notificationActualisationMainsDuJoueurPourAffichagePourTrie) {
			notificationActualisationMainsDuJoueurPourAffichagePourTrie = true;
			
			modele.trierMainJoueur();
		}
	}
	
	
	/**
	 * Ajoute une carte dans l'ecart, avec le chemin pour acceder a son image en parametre.
	 * @param chemin_carte
	 * @throws TarotException
	 */
	public void ajoutCarteDansEcartAPartirDuCheminDeLaCarte(String chemin_carte) throws TarotException {
		Carte carteTmp = null;
		
		for(Carte carte : modele.getMainDuPremierJoueur()) {
			if(carte.getCheminImageCarte().equals(chemin_carte) && modele.getPaquetEcart().size() != 6) {
				carteTmp = carte;
			}
		}
		
		if(carteTmp != null) {
			notificationActualisationEcart = true;
			
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
			notificationActualisationSuppressionDuChien = true;
			
			modele.viderChien();
			
			if(!modele.getPaquetDuChien().isEmpty())
				throw new TarotException(this, "Le chien ne s'est pas correctement vide, il reste " + modele.getPaquetDuChien().size() + " cartes");
		}
	}
		
	
	/**
	 * Accesseur pour savoir si la mains du joueur dans la vue doit actualiser la mains du joueur a cause d'une distribution.
	 * @return boolean
	 */
	public boolean getNotificationActualisationMainsDuJoueurLorsDeLaDistribution() {
		return notificationActualisationMainsDuJoueurEtDuChienLorsDeLaDistribution;
	}

	
	/**
	 * Accesseur pour savoir si la mains du joueur dans la vue est trie ou non.
	 * @return boolean
	 */
	public boolean getNotificationActualisationMainsDuJoueurPourAffichagePourTrie() {
		return notificationActualisationMainsDuJoueurPourAffichagePourTrie;
	}
	
	
	/**
	 * Accesseur pour savoir si l'ecart doit s'actualiser ou non.
	 * @return boolean
	 */
	public boolean getNotificationActualisationEcart() {
		return notificationActualisationEcart;
	}

	

	public boolean getNotificationActualisationSuppressionDuChien() {
		return notificationActualisationSuppressionDuChien;
	}

	
	
	public void actualisationSuppressionDuChienFinie() {
		this.notificationActualisationSuppressionDuChien = false;
	}

	
	/**
	 * Methode pour dire que l'actualisation de la distribution sur la main du joueur et du chien c'est faite.
	 */
	public void actualisationMainsDuJoueurEtDuChienLorsDeLaDistributionFinie() {
		this.notificationActualisationMainsDuJoueurEtDuChienLorsDeLaDistribution = false;
	}
	
	
	/**
	 * Methode utilise pour dire que le mains du joueur de la vue est trie.
	 */
	public void actualisationMainsDuJoueurPourAffichagePourTrieFinie() {
		notificationActualisationMainsDuJoueurPourAffichagePourTrie = false;
	}
	
	
	/**
	 * Methode pour dire que l'actualisation de l'ecart c'est faite.
	 */
	public void actualisationEcartFinie() {
		this.notificationActualisationEcart = false;
	}
}
