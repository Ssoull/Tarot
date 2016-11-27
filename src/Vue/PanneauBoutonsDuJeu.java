package Vue;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controleur.Controleur;

public class PanneauBoutonsDuJeu extends JPanel{
	/**
	 * Bouton pour la distribution.
	 */
	private JButton boutonDistribuer;
	/**
	 * Bouton pour retourner les cartes du joueur.
	 */
	private JButton boutonRetournerToutesLesCartesDuJoueur;
	
	/**
	 * Initialise le panneau et ces éléments le composant
	 * @param controleur
	 * @param panneau_main_joueur
	 */
	public PanneauBoutonsDuJeu(Controleur controleur, PanneauMainDuJoueur panneau_main_joueur) {
		initialisationBoutonDistribuer(controleur, panneau_main_joueur);
		initialisationBoutonRetournerToutesLesCartesDuJoueur(panneau_main_joueur);
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
						boutonRetournerToutesLesCartesDuJoueur.setEnabled(true);
						
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
	 * Initialise le bouton retournant toutes les cartes du joueur
	 * @param panneau_main_joueur
	 */
	private void initialisationBoutonRetournerToutesLesCartesDuJoueur(PanneauMainDuJoueur panneau_main_joueur) {
		boutonRetournerToutesLesCartesDuJoueur = new JButton("Retourner ses cartes");
		boutonRetournerToutesLesCartesDuJoueur.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonRetournerToutesLesCartesDuJoueur.setVisible(true);
		boutonRetournerToutesLesCartesDuJoueur.setEnabled(false);

		boutonRetournerToutesLesCartesDuJoueur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource() == boutonRetournerToutesLesCartesDuJoueur) {
					boutonRetournerToutesLesCartesDuJoueur.setEnabled(false);
					
					for(CarteGraphique carteMainJoueur : panneau_main_joueur.getCartesJoueurPourAffichage()) {
						carteMainJoueur.retourner();
					}
				}
			}
		});

		this.add(boutonRetournerToutesLesCartesDuJoueur);
	}
	
	/**
	 * Accesseur du bouton retournant toutes les cartes.
	 * @return JButton
	 */
	public JButton getBoutonRetournerToutesLesCartesDuJoueur() {
		return boutonRetournerToutesLesCartesDuJoueur;
	}
}
