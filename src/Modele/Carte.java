package Modele;


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
