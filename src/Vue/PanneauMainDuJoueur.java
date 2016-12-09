/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 */

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

import Controleur.Controleur;
import Modele.Carte;
import Modele.TypeCarte;
import Modele.Modele;

/**
 * Classe contenant tous les elements de la main graphique du joueur.
 * @author Despret
 */
public class PanneauMainDuJoueur extends JPanel {
	/**
	 * Texte de la main du Joueur.
	 */
	private JLabel texteMainDuJoueur;
	/**
	 * Panneau positionnant correctement les cartes de la mains du premier
	 * Joueur.
	 */
	private JPanel placementCartesJoueur;
	/**
	 * Liste de toute les cartes de la main du Joueur.
	 */
	private ArrayList<CarteGraphique> cartesJoueurPourAffichage;
	/**
	 * Active ou désactive le MouseMotionListener des cartes graphique.
	 */
	
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
	 * Initialise le panneau general de la mains du Joueur afin de pouvoir
	 * accepeter les elements qui le composent.
	 * 
	 * @param fenetre_affichage
	 */
	private void initialisationPanneauGeneral(JFrame fenetre_affichage) {
		this.setLayout(new BorderLayout());

		Dimension tmpDimension = new Dimension(fenetre_affichage.getWidth() / 2, fenetre_affichage.getHeight());
		this.setPreferredSize(tmpDimension);
		this.setMaximumSize(tmpDimension);
	}

	
	/**
	 * Initialise le texte servant a identifier la main du Joueur.
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

		this.add(placementCartesJoueur);
	}
	

	/**
	 * Actualise l'affichage de la main du Joueur.
	 */
	public void actualisationCartesDuJoueurPourAffichageLorsDeLaDistribution(Controleur controleur, Modele modele, PanneauBoutonsDuJeu panneau_boutons, PanneauEcart panneau_ecart) {
		int tailleDesCartesDuJoueurPourAffichage = cartesJoueurPourAffichage.size();

		for(int nbrCartePourAjout = 0; nbrCartePourAjout <= 2; ++nbrCartePourAjout) {
			cartesJoueurPourAffichage.add(new CarteGraphique(modele.getMainDuPremierJoueur().get(tailleDesCartesDuJoueurPourAffichage + nbrCartePourAjout)));

			CarteGraphique carteJoueurPourAffichageCourant = cartesJoueurPourAffichage.get(cartesJoueurPourAffichage.size() - 1);

			PanneauMainDuJoueur panneauTemporaire = this;

			carteJoueurPourAffichageCourant.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(panneau_boutons.getBoutonRetournerToutesLesCartesDuJoueur().isEnabled()) {
						carteJoueurPourAffichageCourant.retourner();
						carteJoueurPourAffichageCourant.setEnabled(false);
					}
						
					if(panneauTemporaire.toutesLesCartesSontRetournees()) {
						panneau_boutons.actionBoutonRetournerEtTrierCarteDuJoueur(controleur);
					}
				}
			});

			placementCartesJoueur.add(cartesJoueurPourAffichage.get(cartesJoueurPourAffichage.size() - 1));

		}
	}

	
	/**
	 * Actualise les cartes une fois que le modï¿½le les tries.
	 * 
	 * @param modele
	 */
	public void actualisationCartesDuJoueurPouraffichageLorsDuTrie(Modele modele, PanneauBoutonsDuJeu panneau_bouton) {
		int cpt = 0;

		int cptAtout = 0;
		boolean lePetitSecPresent = false;
		boolean excusePresent = false;

		cartesJoueurPourAffichage.clear();
		placementCartesJoueur.removeAll();

		for (Carte carte : modele.getMainDuPremierJoueur()) {
			cartesJoueurPourAffichage.add(new CarteGraphique(carte));
			cartesJoueurPourAffichage.get(cpt).retourner();
			cartesJoueurPourAffichage.get(cpt).setEnabled(false);

			placementCartesJoueur.add(cartesJoueurPourAffichage.get(cpt));

			if(carte.getValeur() == 1 && carte.getType() == TypeCarte.Atout) {
				lePetitSecPresent = true;
			}
			else if(carte.getValeur() == 22 && carte.getType() == TypeCarte.Excuse) {
				excusePresent = true;
			}
			else if(carte.getValeur() != 1 && carte.getType() == TypeCarte.Atout){
				cptAtout++;
			}

			cpt++;
		}
		
		//Verifie si le petit est sec.
		if(cptAtout == 0 && lePetitSecPresent && !excusePresent) {
			panneau_bouton.getBoutonPourLaPrise().setEnabled(false);
			panneau_bouton.getBoutonPourLaGarde().setEnabled(false);
			panneau_bouton.getBoutonPourLaGardeSansLeChien().setEnabled(false);
			panneau_bouton.getBoutonPourLaGardeContreLeChien().setEnabled(false);
		}
	}

	
	/**
	 * Verifie si toutes les cartes sont retournees.
	 * 
	 * @return boolean
	 */
	public boolean toutesLesCartesSontRetournees() {
		boolean verification = true;

		for (CarteGraphique carteMainJoueur : this.getCartesJoueurPourAffichage()) {
			if (!carteMainJoueur.getCarte().estRetournee()) {
				verification = false;
			}
		}

		return verification;
	}

	
	/**
	 * Methode prennant en parametre une carte et retourne son equivalant en carte graphique si elle existe dans la main du joueur.
	 * @param carte
	 * @return CarteGraphique
	 */
	public CarteGraphique trouverCarteDansLaMainDuJoueur(Carte carte) {
		CarteGraphique carteGraphiqueTmp = null;

		for(CarteGraphique carteGraphique : cartesJoueurPourAffichage) {
			if(carteGraphique.getCarte().equals(carte)) {
				carteGraphiqueTmp = carteGraphique;
			}
		}

		return carteGraphiqueTmp;
	}
	
	
	/**
	 * Methode actualisant toutes les cartes du joueur lors de la Prise ou de la Garde.
	 * @param carteGraphique
	 */
	public void actualisationCartesJoueurPourAffichageLorsDeLaPriseOuDeLaGarde(CarteGraphique carteGraphique) {
		cartesJoueurPourAffichage.remove(carteGraphique);
		placementCartesJoueur.remove(carteGraphique);

		placementCartesJoueur.setBackground(placementCartesJoueur.getBackground());
	}


	/**
	 * Accesseur pour les cartes du joueur a afficher5
	 * @return ArrayList<CarteGraphique>
	 */
	public ArrayList<CarteGraphique> getCartesJoueurPourAffichage() {
		return cartesJoueurPourAffichage;
	}
}
