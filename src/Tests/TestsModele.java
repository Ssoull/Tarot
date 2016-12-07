package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Modele.*;

public class TestsModele {
	private Modele modele;

	/**
	 * Teste si, au depart, le paquet de cartes contient bien l'integralite des 78 cartes requises.
	 */
	@Test
	public void testPaquetJeuPleinAuDepart() {
		modele = new Modele(); 
		assertTrue(modele.getPaquetDuJeu().size() == modele.getNombreCartesTotalTarot());
	}

	/**
	 * Teste que l'ajout d'une carte dans la main du premier joueur se deroule bien.
	 */
	@Test
	public void testAjoutCarteDansMainPremierJoueur() {
		modele = new Modele(); 
		assertTrue(modele.getMainDuPremierJoueur().size() == 0);
		modele.ajoutCarteDansMainJoueur(new Carte(10, TypeCarte.Coeur));
		assertTrue(modele.getMainDuPremierJoueur().size() == 1);
	}

	/**
	 * Teste si le modele reussit a retirer une carte du paquet pour la placer dans la main du premier joueur.
	 * Il doit manquer 4 cartes dans le paquet : 3 distribuees au joueur 1, 1 dans le chien.
	 */
	@Test
	public void testTirerCartePourJoueur1() {
		modele = new Modele();

		assertTrue(modele.getMainDuPremierJoueur().size() == 0 && modele.getPaquetDuJeu().size() == modele.getNombreCartesTotalTarot());
		try {
			modele.tirerCartesPourLesJoueurs(0);
		} catch (TarotException e) {
			System.err.print("TEST_");
			e.message();
		}
		assertTrue(modele.getMainDuPremierJoueur().size() == modele.getNombreCartesPourDistribution() 
				&& modele.getPaquetDuJeu().size() == modele.getNombreCartesTotalTarot() - modele.getNombreCartesPourDistribution() - 1);
	}

	/**
	 * Teste si le modele reussit a retirer une carte du paquet pour la placer dans la main d'un joueur different du premier, ici le second.
	 * Il doit manquer 3 cartes dans le paquet, les 3 distribuees au joueur 2. Le chien n'est pas affecte.
	 */
	@Test
	public void testTirerCartePourJoueur2() {
		modele = new Modele();

		assertTrue(modele.getPaquetDuJeu().size() == modele.getNombreCartesTotalTarot());
		try {
			modele.tirerCartesPourLesJoueurs(1);
		} catch (TarotException e) {
			System.err.print("TEST_");
			e.message();
		}
		assertTrue(modele.getPaquetDuJeu().size() == modele.getNombreCartesTotalTarot() - modele.getNombreCartesPourDistribution());
	}
	
	@Test
	public void testTriMainJoueur() {
		modele = new Modele();
		
		Carte test = new Carte(3, TypeCarte.Pique);
		modele.ajoutCarteDansMainJoueur(new Carte(7, TypeCarte.Coeur));
		modele.ajoutCarteDansMainJoueur(new Carte(4, TypeCarte.Carreau));
		modele.ajoutCarteDansMainJoueur(test);
		int indexInitialPique = modele.getMainDuPremierJoueur().indexOf(test);
		
		modele.trierMainJoueur();
		assert(indexInitialPique != modele.getMainDuPremierJoueur().indexOf(test));
	}
}
