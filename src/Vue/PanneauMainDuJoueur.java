package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Modele;

public class PanneauMainDuJoueur extends JPanel {
	/**
	 * Texte de la main du Joueur.
	 */
	private JLabel texteMainDuJoueur;
	/**
	 * Liste de toute les cartes de la main du Joueur.
	 */
	private ArrayList<CarteGraphique> cartesJoueurPourAffichage;
	
	/**
	 * Initialise le panneau de la main du joueur et son contenus
	 */
	public PanneauMainDuJoueur(JFrame fenetre_affichage) {
		texteMainDuJoueur = new JLabel("Main du Joueur :");
		
		this.setBackground(Color.BLUE);
		Dimension tmpDimension = new Dimension(fenetre_affichage.getWidth() - (fenetre_affichage.getWidth()/2 - 150), fenetre_affichage.getHeight());
		this.setPreferredSize(new Dimension(tmpDimension));
		this.setMaximumSize(new Dimension(tmpDimension));

		this.add(texteMainDuJoueur, BorderLayout.NORTH);

		cartesJoueurPourAffichage = new ArrayList<CarteGraphique>();
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

			this.add(cartesJoueurPourAffichage.get(cartesJoueurPourAffichage.size() - 1), BorderLayout.CENTER);

		}
	}
	
	
	public boolean toutesLesCartesSontRetournees() {
		boolean verification = true;
		
		for(CarteGraphique carteMainJoueur : this.getCartesJoueurPourAffichage()) {
			if(!carteMainJoueur.getCarte().getCarteRetounees()) {
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
