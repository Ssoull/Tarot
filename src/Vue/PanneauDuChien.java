package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	 * Liste des cartes du Chien.
	 */
	private ArrayList<CarteGraphique> cartesDuChienPourAffichage;
	
	/**
	 * Initialise le panneau du Chien et tout son contenus
	 * @param fenetre_affichage
	 */
	public PanneauDuChien(JFrame fenetre_affichage) {
		texteDuChien = new JLabel("Chien :");

		this.setBackground(Color.RED);
		
		Dimension tmpDimension = new Dimension(fenetre_affichage.getWidth(), fenetre_affichage.getHeight());
		this.setPreferredSize(tmpDimension);
		this.setMaximumSize(tmpDimension);

		this.add(texteDuChien, BorderLayout.NORTH);

		cartesDuChienPourAffichage = new ArrayList<CarteGraphique>();
	}
	
	
	/**
	 * Actualise l'affichage du Chien.
	 */
	public void actualisationCartesDuChienPourAffichage(Modele modele) {
		int tailleDuChien = modele.getPaquetDuChien().size();

		cartesDuChienPourAffichage.add(new CarteGraphique(modele.getPaquetDuChien().get(tailleDuChien - 1)));
		this.add(cartesDuChienPourAffichage.get(cartesDuChienPourAffichage.size() - 1), BorderLayout.CENTER);
	}
}
