package Modele;


/**
 * Elément constituant les mains, le paquet de jeu et le Chien.
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
	 * Chemin d'accès à l'image de la carte.
	 */
	private String cheminImageCarte;
	
	
	/**
	 * Initialise une carte.
	 * @param valeurCarte
	 * @param couleurCarte
	 */
	public Carte(int valeurCarte, CouleurCarte couleurCarte) {
		this.valeurCarte = valeurCarte;
		this.couleurCarte = couleurCarte;
		
		cheminImageCarte = "img/";
		
		switch(this.couleurCarte)
		{
		case Atout:
			cheminImageCarte += this.valeurCarte;
			break;
		case Excuse:
			cheminImageCarte += "excuse";
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
		return cheminImageCarte;
	}
}
