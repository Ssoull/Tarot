/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 */

package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleur.Controleur;

/**
 * Classe contenant tous les elements graphique pour afficher l'ecart et le chien.
 * @author Despret
 */
public class PanneauDuChienEtEcart extends JPanel{
	/**
	 * Represente le panneau du chien.
	 */
	private PanneauDuChien panneauDuChien;
	/**
	 * Represente le panneau de l'ecart.
	 */
	private PanneauEcart panneauEcart;

	
	/**
	 * Constructeur parametres.
	 * Initialise tous les elements pour afficher le chien et l'ecart. 
	 * @param fenetre_affichage
	 * @param controleur
	 */
	public PanneauDuChienEtEcart(JFrame fenetre_affichage, Controleur controleur) {
		initialisationPanneauDuChienEtEcart(fenetre_affichage);
		initialisationDesPanneauxRestants(fenetre_affichage, controleur);
	}
	
	
	/**
	 * Initialise le panneau contenant le chien et l'ecart.
	 * @param fenetre_affichage
	 */
	private void initialisationPanneauDuChienEtEcart(JFrame fenetre_affichage) {
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());

		Dimension tmpDimension = new Dimension(fenetre_affichage.getWidth()/2, fenetre_affichage.getHeight());
		this.setPreferredSize(tmpDimension);
		this.setMaximumSize(tmpDimension);
	}
	
	
	/**
	 * Initialise les panneaux contenant respectivement l'ecart et le chien.
	 * @param fenetre_affichage
	 * @param controleur
	 */
	private void initialisationDesPanneauxRestants(JFrame fenetre_affichage, Controleur controleur) {
		panneauEcart = new PanneauEcart(fenetre_affichage, controleur);
		this.add(panneauEcart, BorderLayout.SOUTH);
		
		panneauDuChien = new PanneauDuChien(fenetre_affichage);
		this.add(panneauDuChien, BorderLayout.NORTH);
	}

	
	/**
	 * Accesseur du panneau du chien.
	 * @return PanneauDuChien
	 */
	public PanneauDuChien getPanneauDuChien() {
		return panneauDuChien;
	}

	/**
	 * Accesseur du panneau de l'ecart.
	 * @return PanneauEcart
	 */
	public PanneauEcart getPanneauEcart() {
		return panneauEcart;
	}
}
