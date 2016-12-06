package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Modele.*;
import Vue.*;
import Controleur.*;

/**
 * Classe regroupant l'integralite des tests unitaires du projet Tarot. Utilise la bibliotheque JUnit.
 * @author jdespret
 *
 */
public class TestsUnitairesTarot {
	private Modele modele;
	private Controleur controleur;
	private Vue vue;

	/**
	 * Methode utiliser pour reinitialiser l'architecture du jeu,
	 *  en recreant les elements du MVC.
	 */
	private void reconstituerArchitecture() {
		modele = new Modele();
		controleur = new Controleur(modele);
		vue = new Vue(modele, controleur);

		modele.addObserver(vue);
	}

	/**
	 * Teste si, au depart, le paquet de cartes contient bien l'integralite des 78 cartes requises.
	 */
	@Test
	public void testPaquetJeuPleinAuDepart() {
		reconstituerArchitecture(); 
		assertTrue(modele.getPaquetDuJeu().size() == modele.getNombreCartesTotalTarot());
	}

	/**
	 * Teste que l'ajout d'une carte dans la main du premier joueur se deroule bien.
	 */
	@Test
	public void testAjoutCarteDansMainPremierJoueur() {
		reconstituerArchitecture();
		assertTrue(modele.getMainDuPremierJoueur().size() == 0);
		modele.ajoutCarteDansMainJoueur(new Carte(10, TypeCarte.Coeur));
		assertTrue(modele.getMainDuPremierJoueur().size() == 1);
	}
}
