package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.*;

import Modele.Modele;

public class PanneauMainDuJoueur extends JPanel {
	/**
	 * Texte de la main du Joueur.
	 */
	private JLabel texteMainDuJoueur;
	/**
	 * Panneau positionnant correctement les cartes de la mains du premier Joueur.
	 */
	private JPanel placementCartesJoueur;
	/**
	 * Liste de toute les cartes de la main du Joueur.
	 */
	private ArrayList<CarteGraphique> cartesJoueurPourAffichage;
	
	
	
	/**
	 * Initialise le panneau de la main du joueur et son contenus.
	 */
	public PanneauMainDuJoueur(JFrame fenetre_affichage) {
		
		initialisationPanneauGeneral(fenetre_affichage);
		initialisationTexteMainDuJoueur();
		initialisationPlacementCartesJoueur();
		
		cartesJoueurPourAffichage = new ArrayList<CarteGraphique>();
	}
	
	
	/**
	 * Initialise le panneau general de la mains du Joueur afin de pouvoir accepeter les elements qui le composent.
	 * @param fenetre_affichage
	 */
	private void initialisationPanneauGeneral(JFrame fenetre_affichage) {
		this.setBackground(Color.BLUE);
		this.setLayout(new BorderLayout());
		Dimension tmpDimension = new Dimension(fenetre_affichage.getWidth() - (fenetre_affichage.getWidth()/2 - 150), fenetre_affichage.getHeight());
		this.setPreferredSize(new Dimension(tmpDimension));
		this.setMaximumSize(new Dimension(tmpDimension));
	}
	
	
	/**
	 * Initialise le texte servant à identifier la main du Joueur.
	 */
	private void initialisationTexteMainDuJoueur() {
		texteMainDuJoueur = new JLabel("Main du Joueur :");
		texteMainDuJoueur.setFont(new Font("Arial", Font.PLAIN, 30));
		
		this.add(texteMainDuJoueur, BorderLayout.NORTH);
	}
	
	
	/**
	 * Initialise le placement des cartes de la mains du Joueur.
	 */
	private void initialisationPlacementCartesJoueur() {
		placementCartesJoueur = new JPanel(new FlowLayout());
		placementCartesJoueur.setBackground(Color.BLUE);
		
		this.add(placementCartesJoueur, BorderLayout.CENTER);
	}
	
	
	/**
	 * Actualise l'affichage de la main du Joueur.
	 */
	public void actualisationCartesDuJoueurPourAffichage(Modele modele, PanneauBoutonsDuJeu panneau_boutons) {
		int tailleDesCartesDuJoueurPourAffichage = cartesJoueurPourAffichage.size();

		for(int nbrCartePourAjout = 0; nbrCartePourAjout <= 2; ++nbrCartePourAjout) {
			cartesJoueurPourAffichage.add(new CarteGraphique(modele.getMainDuPremierJoueur().get(tailleDesCartesDuJoueurPourAffichage + nbrCartePourAjout)));
			
			CarteGraphique carteJoueurPourAffichageCourant = cartesJoueurPourAffichage.get(cartesJoueurPourAffichage.size() - 1);
			PanneauMainDuJoueur panneauTemporaire = this;
			
			carteJoueurPourAffichageCourant.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					carteJoueurPourAffichageCourant.retourner();
					
					if(panneauTemporaire.toutesLesCartesSontRetournees()) {
						panneau_boutons.getBoutonRetournerToutesLesCartesDuJoueur().setEnabled(false);
					}
				}
			});

			placementCartesJoueur.add(cartesJoueurPourAffichage.get(cartesJoueurPourAffichage.size() - 1));

		}
	}
	
	/**
	 * Verifie si toutes les cartes sont retournees.
	 * @return boolean
	 */
	public boolean toutesLesCartesSontRetournees() {
		boolean verification = true;
		
		for(CarteGraphique carteMainJoueur : this.getCartesJoueurPourAffichage()) {
			if(!carteMainJoueur.getCarte().estRetournee()) {
				verification = false;
			}
		}
		
		return verification;
	}
	
	/**
	 * Accesseur pour les cartes du joueur a afficher
	 * @return ArrayList<CarteGraphique>
	 */
	public ArrayList<CarteGraphique> getCartesJoueurPourAffichage() {
		return cartesJoueurPourAffichage;
	}
}
