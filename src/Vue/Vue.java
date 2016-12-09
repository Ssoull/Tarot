/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 */

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
	 * Panneau Contenant l'ecart et le chien
	 */
	private PanneauDuChienEtEcart panneauDuChienEtEcart;

	
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
		
		this.validate(); //Permet l'affichage des boutons sans evenement de l'acteur.
	}


	/**
	 * Initialise la fenetre d'affichage.
	 */
	private void initialisationFenetre() {
		this.setLayout(new BorderLayout());
		
		Dimension dimensionEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // Permet de récupérer la taille de l'ecran.
		this.setSize(dimensionEcran.width, dimensionEcran.height - 50);
		
		this.setTitle("Tarot S3B (Jules Despret, Pablo Gutierrez)");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	
	/**
	 * Initialisation de la mains du Joueur et et du Chien.
	 */
	private void initialisationElements() {
		initialisationElementsMainDuJoueur();
		initialisationElementsChienEtEcart();
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
	 * Initialisation du Chien et de l'Ecart.
	 */
	private void initialisationElementsChienEtEcart() {
		panneauDuChienEtEcart = new PanneauDuChienEtEcart(this, controleur);
		this.add(panneauDuChienEtEcart, BorderLayout.WEST);
	}
	
	
	/**
	 * Initialise le panneau contenant les boutons du jeu
	 */
	private void initialisationElementsPanneauBoutons() {
		panneauBoutonsJeu = new PanneauBoutonsDuJeu(this, modele, controleur, panneauMainDuJoueur, panneauDuChienEtEcart.getPanneauDuChien());
		
		this.getContentPane().add(panneauBoutonsJeu, BorderLayout.NORTH); 
	}

	
	/**
	 * Permet de mettre a jour la Vue (appelee par la classe Modele).
	 */
	@Override
	public void update(Observable obs, Object obj) {
		if(controleur.getNotificationActualisationMainsDuJoueurLorsDeLaDistribution()) {
			panneauMainDuJoueur.actualisationCartesDuJoueurPourAffichageLorsDeLaDistribution(controleur, modele, panneauBoutonsJeu, panneauDuChienEtEcart.getPanneauEcart());
			panneauDuChienEtEcart.getPanneauDuChien().actualisationCartesDuChienPourAffichageLorsDeLaDistribution(modele);
			
			controleur.actualisationMainsDuJoueurEtDuChienLorsDeLaDistributionFinie();
		}
		
		if(controleur.getNotificationActualisationMainsDuJoueurPourAffichagePourTrie()) {
			panneauMainDuJoueur.actualisationCartesDuJoueurPouraffichageLorsDuTrie(modele, panneauBoutonsJeu);
			
			controleur.actualisationMainsDuJoueurPourAffichagePourTrieFinie();
		}
		
		if(controleur.getNotificationActualisationEcart()) {
			panneauDuChienEtEcart.getPanneauEcart().actualisationPaquetEcart(controleur, modele, panneauBoutonsJeu, panneauDuChienEtEcart.getPanneauDuChien(), panneauMainDuJoueur);
			
			controleur.actualisationEcartFinie();
		}
		
		if(controleur.getNotificationActualisationSuppressionDuChien()) {
			panneauDuChienEtEcart.getPanneauDuChien().suppressionDuChien();
			
			controleur.actualisationSuppressionDuChienFinie();
		}
		
		this.validate(); // Re-actualise les composants de la fenetre (JPanel)
		this.repaint();
	}
}
