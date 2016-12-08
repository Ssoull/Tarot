/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 */

package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur;
import Modele.Carte;
import Modele.Modele;
import Tests.TarotException;

/**
 * Panneau contenant tous les elements graphique pour afficher le chien.
 * @author Despret
 */
public class PanneauDuChien extends JPanel {
	/**
	 * Texte du Chien.
	 */
	private JLabel texteDuChien;
	/**
	 * Panneau positionnant correctement les cartes.
	 */
	private JPanel placementCartesDuChien;
	/**
	 * Liste des cartes du Chien.
	 */
	private ArrayList<CarteGraphique> cartesDuChienPourAffichage;


	/**
	 * Initialise le panneau du Chien et tout son contenus.
	 * @param fenetre_affichage
	 */
	public PanneauDuChien(JFrame fenetre_affichage) {
		initialisationPanneauDuChien(fenetre_affichage);
		initialisationTexteDuChien();
		initialisationPlacementCartesDuChien();

		cartesDuChienPourAffichage = new ArrayList<CarteGraphique>(6);
	}


	/**
	 * Initialise le panneau general du chien afin de pouvoir accepeter les elements qui le composent.
	 * @param fenetre_affichage
	 */
	private void initialisationPanneauDuChien(JFrame fenetre_affichage) {
		this.setLayout(new BorderLayout());

		Dimension tmpDimension = new Dimension(fenetre_affichage.getWidth()/2, fenetre_affichage.getHeight()/2);
		this.setPreferredSize(tmpDimension);
		this.setMaximumSize(tmpDimension);
	}


	/**
	 * Initialise le texte pour identifier l'endroit du chien.
	 */
	private void initialisationTexteDuChien() {
		texteDuChien = new JLabel("Chien :");
		texteDuChien.setFont(new Font("Arial", Font.PLAIN, 30));

		this.add(texteDuChien, BorderLayout.NORTH);
	}


	/**
	 * Initialise le panneau mettant correctement les cartes du chien.
	 */
	private void initialisationPlacementCartesDuChien() {
		placementCartesDuChien = new JPanel(new FlowLayout());

		this.add(placementCartesDuChien);
	}


	/**
	 * Actualise l'affichage du Chien.
	 */
	public void actualisationCartesDuChienPourAffichageLorsDeLaDistribution(Modele modele) {
		int tailleDuChien = modele.getPaquetDuChien().size();

		cartesDuChienPourAffichage.add(new CarteGraphique(modele.getPaquetDuChien().get(tailleDuChien - 1)));
		placementCartesDuChien.add(cartesDuChienPourAffichage.get(cartesDuChienPourAffichage.size() - 1), BorderLayout.CENTER);
	}


	/**
	 * Methode permettant de vider le chien dans le paquet du joueur.
	 * @param modele
	 */
	public void viderPaquetDuChienDansLaMainDuJoueur(Controleur controleur, Modele modele) {
		for(CarteGraphique carteGraphique : cartesDuChienPourAffichage) {
			modele.ajoutCarteDansMainJoueur(carteGraphique.getCarte());
		}

		cartesDuChienPourAffichage.clear();
		placementCartesDuChien.removeAll();

		try {
			controleur.viderPaquetDuChien(); //On vide le chien (au niveau stockage 
		} catch(TarotException e) {
			e.message();
		}
		controleur.trierMainJoueur(); //On trie la main du joueur (dans le modele) et re-affiche la bonne main du joueur grace au modele Observer/Observable.
	}


	/**
	 * Methode permettant de recuperer le contenu de l'ecart dans le Chien.
	 * @param modele
	 */
	public void recupererPaquetEcartDansPaquetDuChien(Modele modele) {
		for(Carte carte : modele.getPaquetDuChien()) {
			cartesDuChienPourAffichage.add(new CarteGraphique(carte));
			cartesDuChienPourAffichage.get(cartesDuChienPourAffichage.size() - 1).retourner();

			placementCartesDuChien.add(cartesDuChienPourAffichage.get(cartesDuChienPourAffichage.size() - 1));
		}
	}


	/**
	 * Accesseur au paquet du chien.
	 * @return ArrayList<CarteGraphique>
	 */
	public ArrayList<CarteGraphique> getCartesDuChienPourAffichage() {
		return cartesDuChienPourAffichage;
	}
}
