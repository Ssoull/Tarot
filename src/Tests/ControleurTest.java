package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Controleur.*;
import Modele.*;

/**
 * Classe utilisee pour les tests unitaires de la partie Controleur du projet Tarot.
 * @author jdespret
 *
 */
public class ControleurTest {
	private Controleur controleur;
	private Modele modele;
	
	/**
	 * Permet de reinitialiser le Modele et le Controleur utilises a chaque test.
	 */
	private void reconstruire() {
		modele = new Modele();
		controleur = new Controleur(modele);
	}
	
	/**
	 * Teste si le controleur demande correctement au modele de tirer une carte de la main du joueur 1
	 * et de l'inserer dans l'ecart.
	 */
	@Test
	public void testAjoutCarteDansEcart() {
		reconstruire();
		
		Carte test = new Carte(13, TypeCarte.Pique);
		modele.ajoutCarteDansMainJoueur(test);
		assertTrue(modele.getMainDuPremierJoueur().size() == 1 && modele.getPaquetEcart().isEmpty());
		
		try {
			controleur.ajoutCarteDansEcart(test.getCheminImageCarte());
		} catch (TarotException e) {
			e.message();
		}
		assertTrue(modele.getMainDuPremierJoueur().isEmpty() && modele.getPaquetEcart().size() == 1);
	}
	
	/**
	 * Teste si le Controleur est capable de recuperer toutes les cartes de l'ecart
	 * et de les inserer dans le chien sans encombres.
	 */
	@Test
	public void testRecupererEcartDansChien() {
		reconstruire();
		
		for(int i=0 ; i<modele.getNombreCartesEcartMax() ; ++i)
			modele.ajoutCarteEcart(new Carte(2, TypeCarte.Pique));
		
		assertTrue(modele.getPaquetEcart().size() == modele.getNombreCartesEcartMax() 
				&& modele.getPaquetDuChien().isEmpty());
		
		try {
			controleur.recupererEcartDansChien();
		} catch (TarotException e) {
			e.message();
		}
		assertTrue(modele.getPaquetEcart().isEmpty() 
				&& modele.getPaquetDuChien().size() == modele.getNombreCartesChienMax());
	}
	
	/**
	 * Teste si le Controleur est capable de vider le chien dans le Modele.
	 */
	@Test
	public void testViderPaquetDuChien() {
		reconstruire();
		
		for(int i=0 ; i<modele.getNombreCartesChienMax() ; ++i) {
			try {
				modele.tirerCartesPourLesJoueurs(0);
			} catch (TarotException e) {
				e.message();
			}
		}
		assertTrue(modele.getPaquetDuChien().size() == modele.getNombreCartesChienMax());
		
		try {
			controleur.viderPaquetDuChien();
		} catch (TarotException e) {
			e.message();
		}
		assertTrue(modele.getPaquetDuChien().isEmpty());
	}
	
}
