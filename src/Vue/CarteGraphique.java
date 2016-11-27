package Vue;

import Modele.Carte;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Cette classe permet de diff�rencier les cartes contenu dans la classe Mod�le en carte sous forme de bouton.
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
	 * Initialise le bouton correspondant � une carte.
	 * @param carte
	 */
	public CarteGraphique(Carte carte) {
		this.carte = carte;
		
		this.setVisible(true);
		this.setEnabled(false);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setIcon(new ImageIcon("img/cache.jpg"));
	}
	
	
	/**
	 * Revele la carte auparavant retournee, en remplacant son image de dos par son image de face.
	 */
	public void retourner() {
		setIcon(new ImageIcon(carte.getCheminImageCarte()));
	}
	
	
	/**
	 * Accesseur d'une carte correspondant � la carte gaphique. 
	 */
	public Carte getCarte() {
		return carte;
	}
}
