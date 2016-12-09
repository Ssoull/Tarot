/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 * 
 * Message des programmeurs : 
 * Si il n'y a pas d'accent au niveau des commentaires, c'est parce qu'en essayant de l'envoyer,
 * L'IDE mettait les accents sous forme de méta caractères. En esperant que cela ne nuise pas a la lecture.
 * 
 * Le code a ete realise a partir de l'architecture MVC, Observer/Observable. Il a aussi ete realise afin que si on decide
 * de changer de bibliotheque graphique le modele et le controleur ne soit pas impacte.
 * 
 * Les boutons lorsqu'ils sont desactive montre qu'on ne peut pas interagir avec.
 * A la fin tout les boutons de la mains du joueur sont active pour simboliser que la partie commence.
 * 
 * Gutierrez Pablo :
 * "Nous avons choisi Swing car nous aimons la difficulte, avec ce projet nous avons compris que la reciproque n'est pas vrai..."
 * 
 * Despret Jules :
 * "Une initiation pertinente à l'architecture MVC"
 */

package Application;

import Modele.Modele;
import Controleur.Controleur;
import Vue.Vue;


/**
 * Permet de lancer l'application
 * @author Despret
 */
public class Application {
	/**
	 * Represente la vue de l'architecture MVC
	 */
	private Vue vue;
	/**
	 * Represente le modele de l'architecture MVC
	 */
	private Modele modele;
	/**
	 * Represente le controleur de l'architecture MVC
	 */
	private Controleur controleur;
	
	/**
	 * Initialise les elements pour lancer l'application
	 */
	public Application() {
		modele = new Modele();
		controleur = new Controleur(modele);
		vue = new Vue(modele, controleur);
		
		modele.addObserver(vue);
	}


	/**
	 * Lance l'application
	 * @param args
	 */
	public static void main(String[] args){
		
		new Application();

	}
}
