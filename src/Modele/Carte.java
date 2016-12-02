package Modele;

import java.util.Comparator;

/**
 * Element constituant les mains, le paquet de jeu et le Chien.
 * @author Despret
 */
public class Carte {
	/**
	 * Valeur de la carte.
	 */
	private int valeurCarte;
	/**
	 * Couleur de la carte.
	 */
	private CouleurCarte couleurCarte;
	/**
	 * Chemin d'acces a l'image de la carte.
	 */
	private String cheminImageCarte;
	/**
	 * Determine si la carte est retournee.
	 */
	private boolean retourner;

	/**
	 * Initialise une carte.
	 * @param valeurCarte
	 * @param couleurCarte
	 */
	public Carte(int valeurCarte, CouleurCarte couleurCarte) {
		this.valeurCarte = valeurCarte;
		this.couleurCarte = couleurCarte;
		retourner = false;

		cheminImageCarte = "img/";

		switch(this.couleurCarte)
		{
		case Atout:
			cheminImageCarte += this.valeurCarte;
			break;
		case Excuse:
			cheminImageCarte += "Excuse";
			break;
		default:
			cheminImageCarte += this.valeurCarte + this.couleurCarte.toString();
		}
		cheminImageCarte += ".jpg";
	}
	
	
	public static Comparator<Carte> comparateurCartes = new Comparator<Carte>() {

		/**
		 * Compare deux cartes en calculant pour chacune une valeur de
		 * comparaison.
		 * 
		 * @return La valeur de la premiere carte moins celle de la deuxieme.
		 */
		public int compare(Carte carte1, Carte carte2) {
			int valeur1 = valeurDeComparaisonCalculee(carte1);
			int valeur2 = valeurDeComparaisonCalculee(carte2);

			return valeur1 - valeur2;
		}

		/**
		 * 
		 * @param c : la carte dont on veut calculer la valeur de comparaison.
		 * @return la valeur de comparaison calculee.
		 */
		private int valeurDeComparaisonCalculee(Carte c) {
			int valeur = c.getValeur();
			switch (c.getCouleur()) {
			case Pique:
				valeur += 0;
				break;
			case Coeur:
				valeur += 100;
				break;
			case Excuse:
				valeur += 200;
				break;
			case Atout:
				valeur += 300;
				break;
			case Carreau:
				valeur += 400;
				break;
			case Trefle:
				valeur += 500;
				break;
			}
			return valeur;
		}

	};
	
	
	/**
	 * @return L'entier representant la valeur de la carte
	 */
	public int getValeur() {
		return valeurCarte;
	}

	/**
	 * @return La couleur de la carte (enumeration).
	 */
	public CouleurCarte getCouleur() {
		return couleurCarte;
	}
	

	/**
	 * Accesseur du chemin de l'image de la carte.
	 * @return String
	 */
	public String getCheminImageCarte() {
		retourner = true;
		return cheminImageCarte;
	}

	/**
	 * Accesseur pour savoir si la carte est retournee.
	 * @return boolean
	 */
	public boolean estRetournee() {
		return retourner;
	}
}
