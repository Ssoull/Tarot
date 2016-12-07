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
	public void testAjoutCarteDansMainJoueur() {
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
	
	/**
	 * Teste si le tri des cartes dans la main du joueur fonctionne correctement.
	 * On teste avec 5 cartes : l'ordre attendu doit être respecté :
	 *  1°Pique ;
	 *  2°Coeur ;
	 *  3°Atout ;
	 *  4°Carreau ;
	 *  5°Trefle ;
	 */
	@Test
	public void testTriMainJoueur() {
		modele = new Modele();
		
		Carte testCoeur = new Carte(7, TypeCarte.Coeur);
		Carte testTrefle = new Carte(5, TypeCarte.Trefle);
		Carte testCarreau = new Carte(4, TypeCarte.Carreau);
		Carte testPique = new Carte(3, TypeCarte.Pique);
		Carte testAtout = new Carte(19, TypeCarte.Atout);
		
		
		modele.ajoutCarteDansMainJoueur(testCoeur);
		modele.ajoutCarteDansMainJoueur(testTrefle);
		modele.ajoutCarteDansMainJoueur(testCarreau);
		modele.ajoutCarteDansMainJoueur(testPique);
		modele.ajoutCarteDansMainJoueur(testAtout);
		
		modele.trierMainJoueur();
		
		assertTrue(modele.getMainDuPremierJoueur().indexOf(testPique) == 0);
		assertTrue(modele.getMainDuPremierJoueur().indexOf(testCoeur) == 1);
		assertTrue(modele.getMainDuPremierJoueur().indexOf(testAtout) == 2);
		assertTrue(modele.getMainDuPremierJoueur().indexOf(testCarreau) == 3);
		assertTrue(modele.getMainDuPremierJoueur().indexOf(testTrefle) == 4);
	}
	
	/**
	 * Durant ce test, on ajoute une carte dans la main du joueur, 
	 * puis on appelle une methode pour la retirer et l'ajouter dans l'ecart.
	 */
	@Test
	public void testAjoutCarteEcart() {
		modele = new Modele();
		
		Carte test = new Carte(4, TypeCarte.Pique);
		modele.ajoutCarteDansMainJoueur(test);
		
		assertTrue(modele.getPaquetEcart().size() == 0);
		assertTrue(modele.getMainDuPremierJoueur().size() == 1);
		
		modele.ajoutCarteEcart(test);
		
		assertTrue(modele.getPaquetEcart().size() == 1);
		assertTrue(modele.getMainDuPremierJoueur().size() == 0);
	}
	
	/**
	 * Teste si le modele est capable de determiner si l'ecart est plein ou non.
	 */
	@Test
	public void testEcartPlein() {
		modele = new Modele();
		
		try {
			assertTrue(!modele.ecartPlein());
		} catch (TarotException e) {
			System.out.print("TEST_");
			e.message();
		}
		
		for(int i=0 ; i<modele.getNombreCartesEcart() ; i++)
			modele.ajoutCarteEcart(new Carte(0, TypeCarte.Atout));
		
		try {
			assertTrue(modele.ecartPlein());
		} catch (TarotException e) {
			System.out.print("TEST_");
			e.message();
		}
	}
}
