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
import Modele.Modele;
import Tests.TarotException;

/**
 * Classe contenant tous les elements graphique de l'ecart.
 * @author Gutierrez
 */
public class PanneauEcart extends JPanel {
	/**
	 * Represente le texte indiquant l'ecart.
	 */
	private JLabel texteEcart;
	/**
	 * Panneau permettant le positionnement des cartes composant l'ecart.
	 */
	private JPanel placementDesCartesDeEcart;
	/**
	 * Liste de CarteGraphique representant les cartes a afficher pour l'ecart.
	 */
	private ArrayList<CarteGraphique> cartesComposantEcart;
	
	
	/**
	 * Contructeur du panneau de l'ecart.
	 * @param fenetre_affichage
	 * @param controleur
	 */
	public PanneauEcart(JFrame fenetre_affichage, Controleur controleur) {
		initialisationPanneauEcart(fenetre_affichage);
		initialisationTexteEcart();
		initialisationPlacementCartesDeEcart();

		cartesComposantEcart = new ArrayList<CarteGraphique>(6);
		placementDesCartesDeEcart.setTransferHandler(new ImporterDonnees(controleur));
	}
	
	/**
	 * Initialise le panneau contenant l'ecart.
	 * @param fenetre_affichage
	 */
	private void initialisationPanneauEcart(JFrame fenetre_affichage) {
		this.setLayout(new BorderLayout());

		Dimension tmpDimension = new Dimension(fenetre_affichage.getWidth()/2, fenetre_affichage.getHeight()/2);
		this.setPreferredSize(tmpDimension);
		this.setMaximumSize(tmpDimension);
	}
	

	/**
	 * Initialise le texte pour identifier l'endroit de l'ecart.
	 */
	private void initialisationTexteEcart() {
		texteEcart = new JLabel("Ecart :");
		texteEcart.setFont(new Font("Arial", Font.PLAIN, 30));

		this.add(texteEcart, BorderLayout.NORTH);
	}

	
	/**
	 * Initialise le panneau mettant correctement les cartes de l'ecart.
	 */
	private void initialisationPlacementCartesDeEcart() {
		placementDesCartesDeEcart = new JPanel(new FlowLayout());

		this.add(placementDesCartesDeEcart);
	}


	/**
	 * Methode actualisant le paquet de l'ecart.
	 * @param controleur
	 * @param modele
	 * @param panneau_bouton
	 * @param panneau_chien
	 * @param panneau_main_joueur
	 */
	public void actualisationPaquetEcart(Controleur controleur, Modele modele, PanneauBoutonsDuJeu panneau_bouton, PanneauDuChien panneau_chien, PanneauMainDuJoueur panneau_main_joueur) {

		int tailleEcart = modele.getPaquetEcart().size();
		
		CarteGraphique carteGraphiqueTmp = panneau_main_joueur.trouverCarteDansLaMainDuJoueur(modele.getPaquetEcart().get(tailleEcart - 1));
			
		if(carteGraphiqueTmp != null) {
			cartesComposantEcart.add(carteGraphiqueTmp);
			panneau_main_joueur.actualisationCartesJoueurPourAffichageLorsDeLaPriseOuDeLaGarde(carteGraphiqueTmp);
			placementDesCartesDeEcart.add(cartesComposantEcart.get(cartesComposantEcart.size() - 1), BorderLayout.CENTER);
		}
		
		try {
			if(modele.ecartPlein()) {
				actualisationDesPaquetEnFonctionDuPaquetEcart(controleur, modele, panneau_bouton, panneau_chien, panneau_main_joueur);
				
				cartesComposantEcart.clear();
				placementDesCartesDeEcart.removeAll();
			}
		} catch (TarotException e) {
			e.message();
		}
	}
	
	
	/**
	 * Methode actualisant tout les paquets (Joueur, Chien et Ecart) si l'ecart est remplie a son maximum (c-a-d 6).
	 * @param controleur
	 * @param modele
	 * @param panneau_bouton
	 * @param panneau_chien
	 * @param panneau_main_joueur
	 */
	private void actualisationDesPaquetEnFonctionDuPaquetEcart(Controleur controleur, Modele modele, PanneauBoutonsDuJeu panneau_bouton, PanneauDuChien panneau_chien, PanneauMainDuJoueur panneau_main_joueur) {
		try {
			controleur.recupererEcartDansChien(); // Ceci concerne les cartes contenus dans le modele.
		} catch (TarotException e) {
			e.message();
		}
		
		panneau_chien.recupererPaquetEcartDansPaquetDuChien(modele); // Ceci concerne les cartes contenus dans la vue.
		
		for(CarteGraphique carteGraphique : panneau_main_joueur.getCartesJoueurPourAffichage()) {
			carteGraphique.setEnabled(true);
			
			carteGraphique.setTransferHandler(null); // On enleve les exportations de donnees avec le Glisser/Deposer sur les boutons.
			
			placementDesCartesDeEcart.setTransferHandler(null); // On ne peut plus deposer de cartes dans l'ecart.
		}
			
		for(CarteGraphique carteGraphique : panneau_chien.getCartesDuChienPourAffichage()) {
			carteGraphique.setEnabled(false);
		}
	}
}
