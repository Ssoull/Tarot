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
	 * Repr�sente la vue de l'architecture MVC
	 */
	private Vue vue;
	/**
	 * Repr�sente le mod�le de l'architecture MVC
	 */
	private Modele modele;
	/**
	 * Repr�sente le controleur de l'architecture MVC
	 */
	private Controleur controleur;
	
	/**
	 * Initialise les �l�ments pour lancer l'application
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
