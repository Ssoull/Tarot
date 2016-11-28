package Vue;

import Modele.Modele;
import Controleur.Controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Repr�sente la vue de l'architecture MVC
 * @author Despret, Gutierrez
 */

public class Vue extends JFrame implements Observer{
	/**
	 * Taille de la fenetre.
	 */
	private final Dimension TAILLE_FENETRE = new Dimension(800,600);

	/**
	 * Repr�sente le mod�le de l'architecture MVC.
	 */
	private Modele modele;
	/**
	 * Repr�sente le controleur de l'architecture MVC.
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
	 * Initialise la fen�tre d'affichage.
	 */
	private void initialisationFenetre() {
		this.setLocationRelativeTo(null);
		this.setTitle("Tarot S3 (Jules Despret, Pablo Gutierrez)");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(this.MAXIMIZED_BOTH); // Permet de mettre en plein ecran
		this.setResizable(false);
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
		this.getContentPane().add(panneauMainDuJoueur, BorderLayout.WEST);
	}

	/**
	 * Initialisation du Chien.
	 */
	private void initialisationElementsChien() {
		panneauDuChien = new PanneauDuChien(this);
		this.getContentPane().add(panneauDuChien, BorderLayout.CENTER);
	}
	
	
	/**
	 * Initialise le panneau contenant les boutons du jeu
	 */
	private void initialisationElementsPanneauBoutons() {
		panneauBoutonsJeu = new PanneauBoutonsDuJeu(controleur, panneauMainDuJoueur);
		this.getContentPane().add(panneauBoutonsJeu, BorderLayout.NORTH); 
	}

	/**
	 * Permet de mettre a jour la Vue (appelee par la classe Modele).
	 */
	@Override
	public void update(Observable obs, Object obj) {
		panneauMainDuJoueur.actualisationCartesDuJoueurPourAffichage(modele, panneauBoutonsJeu);
		panneauDuChien.actualisationCartesDuChienPourAffichage(modele);

		this.validate(); // Re-actualise les composants de la fenetre (JPanel)
	}

}
