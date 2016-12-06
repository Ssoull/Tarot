package Vue;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import Controleur.Controleur;
import Modele.TypeCarte;
import Modele.Modele;


/**
 * Classe permettant de créer et paramétrer les boutons du jeu.
 * @author Despret
 */
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
	 * Bouton permettant au joueur d'effectuer une Prise.
	 */
	private JButton boutonPourLaPrise;
	/**
	 * Bouton permettant au jouant d'effectuer une Garde.
	 */
	private JButton boutonPourLaGarde;
	/**
	 * Bouton permettant au joueur d'effectuer une Garde sans le Chien.
	 */
	private JButton boutonPourLaGardeSansLeChien;
	/**
	 * Bouton permettant au joueur d'effectuer une Garde contre le Chien.
	 */
	private JButton boutonPourLaGardeContreLeChien;

	
	/**
	 * Initialise le panneau et ces elements le composant.
	 * @param controleur
	 * @param panneau_main_joueur
	 */
	public PanneauBoutonsDuJeu(Modele modele, Controleur controleur, PanneauMainDuJoueur panneau_main_joueur, PanneauDuChien panneau_chien) {
		initialisationBoutonDistribuer(controleur, panneau_main_joueur);
		initialisationBoutonRetournerToutesLesCartesDuJoueurEtTrier(controleur, panneau_main_joueur);
		
		boutonPourLaPrise = new JButton("Prise");
		boutonPourLaGarde = new JButton("Garde");
		initialisationBoutonPourLaPriseEtLaGarde(controleur, modele, boutonPourLaPrise, panneau_main_joueur, panneau_chien);
		initialisationBoutonPourLaPriseEtLaGarde(controleur, modele, boutonPourLaGarde, panneau_main_joueur, panneau_chien);
		
		boutonPourLaGardeSansLeChien = new JButton("Garde sans le chien");
		boutonPourLaGardeContreLeChien = new JButton("Garde contre le chien");
		initialisationBoutonPourLaGardeSansLeChienEtPourLaGardeContreLeChien(boutonPourLaGardeSansLeChien, panneau_main_joueur);
		initialisationBoutonPourLaGardeSansLeChienEtPourLaGardeContreLeChien(boutonPourLaGardeContreLeChien, panneau_main_joueur);
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
	 * Initialise le bouton retournant et triant toutes les cartes du joueur.
	 * Les cartes sont retourner dans le update de la vue.
	 * @param panneau_main_joueur
	 */
	private void initialisationBoutonRetournerToutesLesCartesDuJoueurEtTrier(Controleur controleur, PanneauMainDuJoueur panneau_main_joueur) {
		boutonRetournerToutesLesCartesDuJoueurEtTrier = new JButton("Retourner ses cartes et les trier");
		boutonRetournerToutesLesCartesDuJoueurEtTrier.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonRetournerToutesLesCartesDuJoueurEtTrier.setVisible(true);
		boutonRetournerToutesLesCartesDuJoueurEtTrier.setEnabled(false);

		boutonRetournerToutesLesCartesDuJoueurEtTrier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource() == boutonRetournerToutesLesCartesDuJoueurEtTrier) {
					actionBoutonRetournerEtTrierCarteDuJoueur(controleur);
				}
			}
		});

		this.add(boutonRetournerToutesLesCartesDuJoueurEtTrier);
	}
	
	
	/**
	 * Initialise le bouton permettant de faire une Prise et la Garde
	 * @param panneau_main_joueur
	 * @param panneau_chien
	 */
	private void initialisationBoutonPourLaPriseEtLaGarde(Controleur controleur, Modele modele, JButton bouton, PanneauMainDuJoueur panneau_main_joueur, PanneauDuChien panneau_chien) {
		bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bouton.setVisible(true);
		bouton.setEnabled(false);
		
		bouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource() == bouton) {
					actionBoutonPriseEtGarde(controleur, modele, panneau_main_joueur, panneau_chien);
				}
			}
		});
		
		this.add(bouton);
	}
	
	
	/**
	 * Initialise les boutons pour la Garde sans et contre le Chien.
	 * @param bouton
	 * @param panneau_main_joueur
	 */
	private void initialisationBoutonPourLaGardeSansLeChienEtPourLaGardeContreLeChien(JButton bouton, PanneauMainDuJoueur panneau_main_joueur) {
		bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bouton.setVisible(true);
		bouton.setEnabled(false);
		
		bouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource() == bouton) {
					actionBoutonGardeSansLeChienEtGardeContreLeChien(panneau_main_joueur);
				}
			}
		});
		
		this.add(bouton);
	}
	
	
	/**
	 * Methode representant les actions que va faire le bouton Retourner et trier lorsque le joueur va cliquer dessus.
	 * @param modele
	 */
	public void actionBoutonRetournerEtTrierCarteDuJoueur(Controleur controleur) {
		boutonRetournerToutesLesCartesDuJoueurEtTrier.setEnabled(false);
		
		boutonPourLaPrise.setEnabled(true);
		boutonPourLaGarde.setEnabled(true);
		boutonPourLaGardeSansLeChien.setEnabled(true);
		boutonPourLaGardeContreLeChien.setEnabled(true);
		
		controleur.trierMainJoueur();
	}
	
	
	/**
	 * Methode representant l'action que va faire le bouton Prise lorsque le joueur va cliquer dessus.
	 * @param panneau_main_joueur
	 * @param panneau_chien
	 */
	public void actionBoutonPriseEtGarde(Controleur controleur, Modele modele, PanneauMainDuJoueur panneau_main_joueur, PanneauDuChien panneau_chien) {
		boutonPourLaPrise.setEnabled(false);
		boutonPourLaGarde.setEnabled(false);
		boutonPourLaGardeSansLeChien.setEnabled(false);
		boutonPourLaGardeContreLeChien.setEnabled(false);
		
		
		panneau_chien.viderPaquetDuChienDansLaMainDuJoueur(controleur, modele); //On Recupere les cartes du chien dans la mains du joueur (au niveau stockage dans le modele) et on vide le chien.
		
		for(CarteGraphique carteGraphique : panneau_chien.getCartesDuChienPourAffichage()) {
			carteGraphique.retourner();
		}
		
		for(CarteGraphique carteGraphique : panneau_main_joueur.getCartesJoueurPourAffichage()) {
			//Ici le 14 représente les rois, le 1 l'atout 1 et le 21 l'atout 21, ces cartes ne peuvent pas etre dans l'Ecart.
			if((carteGraphique.getCarte().getValeur() == 14 && carteGraphique.getCarte().getType() != TypeCarte.Atout) || 
				carteGraphique.getCarte().getType() == TypeCarte.Atout || carteGraphique.getCarte().getType() == TypeCarte.Excuse)	{
				carteGraphique.setEnabled(false);
			}
			else {
				carteGraphique.setEnabled(true);
				
				carteGraphique.setTransferHandler(new ExporterDonnees(carteGraphique.getCarte().getCheminImageCarte()));

				carteGraphique.addMouseMotionListener(new MouseAdapter() {
	                @Override
	                public void mouseDragged(MouseEvent e) {
	                    JButton button = (JButton) e.getSource();
	                    TransferHandler handle = button.getTransferHandler();
	                    handle.exportAsDrag(button, e, TransferHandler.COPY);
	                }
	            });
			}
		}
	}
	
	
	/**
	 * Représente l'action des boutons de la Garde sans et contre le Chien.
	 * @param panneau_main_joueur
	 */
	private void actionBoutonGardeSansLeChienEtGardeContreLeChien(PanneauMainDuJoueur panneau_main_joueur) {
		boutonPourLaPrise.setEnabled(false);
		boutonPourLaGarde.setEnabled(false);
		boutonPourLaGardeSansLeChien.setEnabled(false);
		boutonPourLaGardeContreLeChien.setEnabled(false);
		
		for(CarteGraphique carteGraphique : panneau_main_joueur.getCartesJoueurPourAffichage()) {
			carteGraphique.setEnabled(true);
		}
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

	/**
	 * Accesseur du bouton de la Prise.
	 * @return JButton
	 */
	public JButton getBoutonPourLaPrise() {
		return boutonPourLaPrise;
	}
	
	/**
	 * Accesseur du bouton de la Garde.
	 * @return JButton
	 */
	public JButton getBoutonPourLaGarde() {
		return boutonPourLaGarde;
	}
	
	/**
	 * Accesseur du bouton de la Garde sans le Chien.
	 * @return JButton
	 */
	public JButton getBoutonPourLaGardeSansLeChien() {
		return boutonPourLaGardeSansLeChien;
	}
	
	/**
	 * Accesseur du bouton de la Garde contre le Chien.
	 * @return JButton
	 */
	public JButton getBoutonPourLaGardeContreLeChien() {
		return boutonPourLaGardeContreLeChien;
	}
}
