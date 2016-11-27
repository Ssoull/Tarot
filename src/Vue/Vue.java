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
 * Représente la vue de l'architecture MVC
 * @author Despret, Gutierrez
 */

public class Vue extends JFrame implements Observer{
	/**
	 * Taille de la fenetre.
	 */
	private final Dimension TAILLE_FENETRE = new Dimension(800,600);

	/**
	 * Représente le modèle de l'architecture MVC.
	 */
	private Modele modele;
	/**
	 * Représente le controleur de l'architecture MVC.
	 */
	private Controleur controleur;


	/**
	 * Panneau contenant les boutons du jeu.
	 */
	private JPanel panneauBoutonJeu;
	/**
	 * Bouton pour la distribution.
	 */
	private JButton boutonDistribuer;


	/**
	 * Panneau contenant la main du Joueur.
	 */
	private JPanel panneauMainDuJoueur;
	/**
	 * Texte de la main du Joueur.
	 */
	private JLabel texteMainDuJoueur;
	/**
	 * Liste de toute les cartes de la main du Joueur.
	 */
	private ArrayList<CarteGraphique> cartesJoueurPourAffichage;

	/**
	 * Panneau contant le Chien.
	 */
	private JPanel panneauDuChien;
	/**
	 * Texte du Chien.
	 */
	private JLabel texteDuChien;
	/**
	 * Liste des cartes du Chien.
	 */
	private ArrayList<CarteGraphique> cartesDuChienPourAffichage;

	/**
	 * Initialise les composant de la vue.
	 * @param modele
	 * @param controleur
	 */
	public Vue(Modele modele, Controleur controleur) {
		this.modele = modele;

		this.controleur = controleur;

		initialisationFenetre();

		panneauBoutonJeu = new JPanel();
		initialisationBoutonDistribuer();
		this.getContentPane().add(panneauBoutonJeu, BorderLayout.NORTH); 

		initialisationElements();

		this.validate();
	}


	/**
	 * Initialise la fenêtre d'affichage.
	 */
	private void initialisationFenetre() {
		this.setLocationRelativeTo(null);
		this.setTitle("Tarot S3 (Jules Despret, Pablo Gutierrez)");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(this.MAXIMIZED_BOTH); // Permet de mettre en plein écran
		this.setVisible(true);
	}

	/**
	 * Initialise le bouton distribuer.
	 */
	private void initialisationBoutonDistribuer() {
		boutonDistribuer = new JButton("Distribuer");
		boutonDistribuer.setLocation(150,250);
		boutonDistribuer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonDistribuer.setVisible(true);
		boutonDistribuer.setEnabled(true);

		boutonDistribuer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource() == boutonDistribuer) {
					if(!controleur.distribuerCartes()) {
						boutonDistribuer.setEnabled(false); // Si on ne peut plus distribuer, on desactive le bouton

						for(CarteGraphique carteMainJoueur : cartesJoueurPourAffichage) {
							carteMainJoueur.setEnabled(true);
						}
					}
				}
			}
		});

		panneauBoutonJeu.add(boutonDistribuer);
	}


	/**
	 * Initialisation de la mains du Joueur et et du Chien.
	 */
	private void initialisationElements() {
		initialisationElementsMainDuJoueur();
		initialisationElementsChien();
	}


	/**
	 * Initialisation de la mains du Joueur.
	 */
	private void initialisationElementsMainDuJoueur() {
		texteMainDuJoueur = new JLabel("Main du Joueur :");

		panneauMainDuJoueur = new JPanel();
		panneauMainDuJoueur.setBackground(Color.BLUE);
		Dimension tmpDimension = new Dimension(this.getWidth() - (this.getWidth()/2 - 150), this.getHeight());
		panneauMainDuJoueur.setPreferredSize(new Dimension(tmpDimension));
		panneauMainDuJoueur.setMaximumSize(new Dimension(tmpDimension));

		panneauMainDuJoueur.add(texteMainDuJoueur, BorderLayout.NORTH);

		this.getContentPane().add(panneauMainDuJoueur, BorderLayout.WEST);

		cartesJoueurPourAffichage = new ArrayList<CarteGraphique>();
	}


	/**
	 * Initialisation du Chien.
	 */
	private void initialisationElementsChien() {
		texteDuChien = new JLabel("Chien :");

		panneauDuChien = new JPanel();
		panneauDuChien.setBackground(Color.RED);
		panneauDuChien.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		panneauDuChien.setMaximumSize(new Dimension(this.getWidth(), this.getHeight()));

		panneauDuChien.add(texteDuChien, BorderLayout.NORTH);

		this.getContentPane().add(panneauDuChien, BorderLayout.CENTER);

		cartesDuChienPourAffichage = new ArrayList<CarteGraphique>();
	}


	/**
	 * Actualise l'affichage de la main du Joueur.
	 */
	private void actualisationCartesDuJoueurPourAffichage() {
		int tailleDesCartesDuJoueurPourAffichage = cartesJoueurPourAffichage.size();

		for(int nbrCartePourAjout = 0; nbrCartePourAjout <= 2; ++nbrCartePourAjout) {
			cartesJoueurPourAffichage.add(new CarteGraphique(modele.getMainDuPremierJoueur().get(tailleDesCartesDuJoueurPourAffichage + nbrCartePourAjout)));
			CarteGraphique carteJoueurPourAffichageCourant = cartesJoueurPourAffichage.get(cartesJoueurPourAffichage.size() - 1);

			carteJoueurPourAffichageCourant.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					carteJoueurPourAffichageCourant.retourner();
				}
			});

			panneauMainDuJoueur.add(cartesJoueurPourAffichage.get(cartesJoueurPourAffichage.size() - 1), BorderLayout.CENTER);

		}
		int i = 0;
		int j = i;
	}


	/**
	 * Actualise l'affichage du Chien.
	 */
	private void actualisationCartesDuChienPourAffichage() {
		int tailleDuChien = modele.getPaquetDuChien().size();

		cartesDuChienPourAffichage.add(new CarteGraphique(modele.getPaquetDuChien().get(tailleDuChien - 1)));
		panneauDuChien.add(cartesDuChienPourAffichage.get(cartesDuChienPourAffichage.size() - 1), BorderLayout.CENTER);
	}


	/**
	 * Permet de mettre à jour la vue (appelé par la classe Modèle).
	 */
	@Override
	public void update(Observable obs, Object obj) {
		actualisationCartesDuJoueurPourAffichage();
		actualisationCartesDuChienPourAffichage();

		this.validate(); // Ré-actualise les composants de la fenêtre (JPanel)
	}

}
