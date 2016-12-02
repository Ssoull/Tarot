package Vue;

import Modele.Modele;
import Controleur.Controleur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Represente la vue de l'architecture MVC
 * @author Despret, Gutierrez
 */

public class Vue extends JFrame implements Observer{
	/**
	 * Represente le modele de l'architecture MVC.
	 */
	private Modele modele;
	/**
	 * Represente le controleur de l'architecture MVC.
	 */
	private Controleur controleur;


	/**
	 * Panneau contenant les boutons du jeu.
	 */
	private PanneauBoutonsDuJeu panneauBoutonsJeu;

	/**
	 * Panneau contenant la main du Joueur.
	 */
	private PanneauMainDuJoueur panneauMainDuJoueur;
	
	/**
	 * Panneau contant le Chien.
	 */
	private PanneauDuChien panneauDuChien;
	

	/**
	 * Initialise les composant de la vue.
	 * @param modele
	 * @param controleur
	 */
	public Vue(Modele modele, Controleur controleur) {
		this.modele = modele;

		this.controleur = controleur;

		initialisationFenetre();

		initialisationElements();

		this.validate();
	}


	/**
	 * Initialise la fenetre d'affichage.
	 */
	private void initialisationFenetre() {
		this.setLayout(new BorderLayout());
		this.setExtendedState(this.MAXIMIZED_BOTH); // Permet de mettre en plein ecran
		Dimension dimensionEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dimensionEcran);
		this.setTitle("Tarot S3 (Jules Despret, Pablo Gutierrez)");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
	}

	/**
	 * Initialisation de la mains du Joueur et et du Chien.
	 */
	private void initialisationElements() {
		initialisationElementsMainDuJoueur();
		initialisationElementsChien();
		initialisationElementsPanneauBoutons();
	}

	/**
	 * Initialisation de la mains du Joueur.
	 */
	private void initialisationElementsMainDuJoueur() {
		panneauMainDuJoueur = new PanneauMainDuJoueur(this);
		
		this.getContentPane().add(panneauMainDuJoueur, BorderLayout.EAST);
	}

	/**
	 * Initialisation du Chien.
	 */
	private void initialisationElementsChien() {
		panneauDuChien = new PanneauDuChien(this);
		
		this.getContentPane().add(panneauDuChien, BorderLayout.WEST);
	}
	
	
	/**
	 * Initialise le panneau contenant les boutons du jeu
	 */
	private void initialisationElementsPanneauBoutons() {
		panneauBoutonsJeu = new PanneauBoutonsDuJeu(modele, controleur, panneauMainDuJoueur);
		
		this.getContentPane().add(panneauBoutonsJeu, BorderLayout.NORTH); 
	}

	/**
	 * Permet de mettre a jour la Vue (appelee par la classe Modele).
	 */
	@Override
	public void update(Observable obs, Object obj) {
		if(panneauBoutonsJeu.getBoutonDistribuer().isEnabled()) {
			panneauMainDuJoueur.actualisationCartesDuJoueurPourAffichageLorsDeLaDistribution(modele, panneauBoutonsJeu);
			panneauDuChien.actualisationCartesDuChienPourAffichageLorsDeLaDistribution(modele);
		}
		else if(!panneauBoutonsJeu.getBoutonRetournerToutesLesCartesDuJoueur().isEnabled() && !modele.getMainsDuJoueurPourAffichageTrie()) {
			panneauMainDuJoueur.actualisationCartesDuJoueurPouraffichageLorsDuTrie(modele);
		}

		this.validate(); // Re-actualise les composants de la fenetre (JPanel)
	}
}
