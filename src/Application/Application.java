/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 * 
 * Message des programmeurs : 
 * Si il n'y a pas d'accent au niveau des commentaires, c'est parce qu'en essayant de l'envoyer,
 * L'IDE mettait les accents sous forme de méta caractères. En esperant que cela ne nuise pas a la lecture.
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
