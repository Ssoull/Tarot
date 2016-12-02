package Vue;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controleur.Controleur;
import Modele.Modele;

public class PanneauBoutonsDuJeu extends JPanel{
	/**
	 * Bouton pour la distribution.
	 */
	private JButton boutonDistribuer;
	/**
	 * Bouton pour retourner les cartes du joueur.
	 */
	private JButton boutonRetournerToutesLesCartesDuJoueurEtTrier;
	
	/**
	 * Initialise le panneau et ces elements le composant.
	 * @param controleur
	 * @param panneau_main_joueur
	 */
	public PanneauBoutonsDuJeu(Modele modele, Controleur controleur, PanneauMainDuJoueur panneau_main_joueur) {
		initialisationBoutonDistribuer(controleur, panneau_main_joueur);
		initialisationBoutonRetournerToutesLesCartesDuJoueurEtTrier(modele, panneau_main_joueur);
	}

	/**
	 * Initialise le bouton distribuer.
	 */
	private void initialisationBoutonDistribuer(Controleur controleur, PanneauMainDuJoueur panneau_main_joueur) {
		boutonDistribuer = new JButton("Distribuer");
		boutonDistribuer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonDistribuer.setVisible(true);
		boutonDistribuer.setEnabled(true);

		boutonDistribuer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource() == boutonDistribuer) {
					if(!controleur.distribuerCartes()) {
						boutonDistribuer.setEnabled(false); // Si on ne peut plus distribuer, on desactive le bouton
						boutonRetournerToutesLesCartesDuJoueurEtTrier.setEnabled(true);
						
						for(CarteGraphique carteMainJoueur : panneau_main_joueur.getCartesJoueurPourAffichage()) {
							carteMainJoueur.setEnabled(true);
						}
					}
				}
			}
		});

		this.add(boutonDistribuer);
	}

	/**
	 * Initialise le bouton retournant toutes les cartes du joueur.
	 * @param panneau_main_joueur
	 */
	private void initialisationBoutonRetournerToutesLesCartesDuJoueurEtTrier(Modele modele, PanneauMainDuJoueur panneau_main_joueur) {
		boutonRetournerToutesLesCartesDuJoueurEtTrier = new JButton("Retourner ses cartes et les trier");
		boutonRetournerToutesLesCartesDuJoueurEtTrier.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonRetournerToutesLesCartesDuJoueurEtTrier.setVisible(true);
		boutonRetournerToutesLesCartesDuJoueurEtTrier.setEnabled(false);

		boutonRetournerToutesLesCartesDuJoueurEtTrier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource() == boutonRetournerToutesLesCartesDuJoueurEtTrier) {
					boutonRetournerToutesLesCartesDuJoueurEtTrier.setEnabled(false);
					
					modele.trierMainJoueur();
				}
			}
		});

		this.add(boutonRetournerToutesLesCartesDuJoueurEtTrier);
	}
	
	
	/**
	 * Accesseur au bouton distribuer.
	 * @return JButton
	 */
	public JButton getBoutonDistribuer() {
		return boutonDistribuer;
	}
	
	/**
	 * Accesseur du bouton retournant toutes les cartes.
	 * @return JButton
	 */
	public JButton getBoutonRetournerToutesLesCartesDuJoueur() {
		return boutonRetournerToutesLesCartesDuJoueurEtTrier;
	}
}
