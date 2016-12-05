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
