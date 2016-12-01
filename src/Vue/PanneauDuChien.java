package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Modele;

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


		cartesDuChienPourAffichage = new ArrayList<CarteGraphique>();
	}

	/**
	 * Initialise le panneau general du chien afin de pouvoir accepeter les elements qui le composent.
	 * @param fenetre_affichage
	 */
	private void initialisationPanneauDuChien(JFrame fenetre_affichage) {
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());
		Dimension tmpDimension = new Dimension(fenetre_affichage.getWidth(), fenetre_affichage.getHeight());
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
	 * Initialise le panneau plaçant correctement les cartes du chien.
	 */
	private void initialisationPlacementCartesDuChien() {
		placementCartesDuChien = new JPanel(new FlowLayout());
		placementCartesDuChien.setBackground(Color.RED);
		
		this.add(placementCartesDuChien);
	}
	
	/**
	 * Actualise l'affichage du Chien.
	 */
	public void actualisationCartesDuChienPourAffichage(Modele modele) {
		int tailleDuChien = modele.getPaquetDuChien().size();

		cartesDuChienPourAffichage.add(new CarteGraphique(modele.getPaquetDuChien().get(tailleDuChien - 1)));
		placementCartesDuChien.add(cartesDuChienPourAffichage.get(cartesDuChienPourAffichage.size() - 1), BorderLayout.CENTER);
	}
}
