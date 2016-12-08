/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 */

package Vue;

import Modele.Carte;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Cette classe permet de differencier les cartes contenu dans la classe Modele en carte sous forme de bouton.
 * @author Gutierrez
 */

public class CarteGraphique extends JButton {
	/**
	 * Taille de l'image des cartes.
	 */
	final Dimension TAILLE_CARTE = new Dimension(100, 177);
	/**
	 * Carte correspondant au bouton.
	 */
	private Carte carte;
	
	/**
	 * Initialise le bouton correspondant a une carte.
	 * @param carte
	 */
	public CarteGraphique(Carte carte) {
		this.carte = carte;
		
		this.setVisible(true);
		this.setEnabled(false);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		if(this.carte != null) {
			this.setIcon(new ImageIcon("img/cache.jpg"));
		}
	}
	
	/**
	 * Revele la carte auparavant retournee, en remplacant son image de dos par son image de face.
	 */
	public void retourner() {
		carte.setRetourner(true);
		setIcon(new ImageIcon(carte.getCheminImageCarte()));
	}
	
	/**
	 * Renvoie la carte logique correspondant a cette carte graphique. 
	 */
	public Carte getCarte() {
		return carte;
	}

	
	/**
	 * Mutateur pour changer la carte.
	 * @param carte
	 */
	public void setCarte(Carte carte) {
		this.carte.setTypeCarte(carte.getType());
		this.carte.setCheminImageCarte(carte.getCheminImageCarte());
		this.carte.setValeurCarte(carte.getValeur());
		this.carte.setRetourner(carte.estRetournee());
		
		setIcon(new ImageIcon(this.carte.getCheminImageCarte()));
	}
}
