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
	private TypeCarte typeCarte;
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
	 * @param typeCarte
	 */
	public Carte(int valeurCarte, TypeCarte typeCarte) {
		this.valeurCarte = valeurCarte;
		this.typeCarte = typeCarte;
		retourner = false;

		cheminImageCarte = "img/";

		switch(this.typeCarte)
		{
		case Atout:
			cheminImageCarte += this.valeurCarte;
			break;
		case Excuse:
			cheminImageCarte += "Excuse";
			break;
		default:
			cheminImageCarte += this.valeurCarte + this.typeCarte.toString();
		}
		cheminImageCarte += ".jpg";
	}
	
	
	/**
	 * Methode permetant de comparer les cartes et de les reordonnees entres elles.
	 */
	public static Comparator<Carte> comparateurCartes = new Comparator<Carte>() {

		/**
		 * Compare deux cartes en calculant pour chacune une valeur de
		 * comparaison.
		 * @return int
		 */
		public int compare(Carte carte1, Carte carte2) {
			int valeur1 = valeurDeComparaisonCalculee(carte1);
			int valeur2 = valeurDeComparaisonCalculee(carte2);

			return valeur1 - valeur2;
		}

		/**
		 * Retourne la valeur de la carte place en parametre apres comparaison de sa couleur.
		 * @param carte
		 * @return int
		 */
		private int valeurDeComparaisonCalculee(Carte carte) {
			int valeur = carte.getValeur();
			switch (carte.getType()) {
			case Pique:
				valeur += 0;
				break;
			case Coeur:
				valeur += 100;
				break;
			case Atout:
				valeur += 200;
				break;
			case Excuse:
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
	 * Accesseur de la valeur de la carte.
	 * @return int
	 */
	public int getValeur() {
		return valeurCarte;
	}

	
	/**
	 * Accesseur du type de la carte.
	 * @return TypeCarte
	 */
	public TypeCarte getType() {
		return typeCarte;
	}
	

	/**
	 * Accesseur du chemin de l'image de la carte.
	 * @return String
	 */
	public String getCheminImageCarte() {
		return cheminImageCarte;
	}

	
	/**
	 * Accesseur pour savoir si la carte est retournee.
	 * @return boolean
	 */
	public boolean estRetournee() {
		return retourner;
	}

	
	/**
	 * Mutateur de la valeur de la carte.
	 * @param valeurCarte
	 */
	public void setValeurCarte(int valeurCarte) {
		this.valeurCarte = valeurCarte;
	}

	
	/**
	 * Mutateur du type de la carte.
	 * @param typeCarte
	 */
	public void setTypeCarte(TypeCarte typeCarte) {
		this.typeCarte = typeCarte;
	}
	
	
	/**
	 * Mutateur du boolean indiquant si la carte est retournee.
	 * @param retourner
	 */
	public void setRetourner(boolean retourner) {
		this.retourner = retourner;
	}

	
	/**
	 * Mutateur du chemin de la carte.
	 * @param cheminImageCarte
	 */
	public void setCheminImageCarte(String cheminImageCarte) {
		this.cheminImageCarte = cheminImageCarte;
	}
}
