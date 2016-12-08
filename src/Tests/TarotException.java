/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 */

package Tests;

/**
 * Classe gerant les exceptions dans le projet Tarot.
 * Se construit a partir d'une message et affiche un avertissement sur la sortie d'erreur en cas de levee d'exception.
 * @author Despret
 */
public class TarotException extends Exception {
	/**
	 * Message qui sera envoye sur la sortie d'erreur en cas de levee d'exception.
	 */
	private String message;
	
	public TarotException(Object o, String message) {
		this.message = o.getClass().toString() + " :\nERREUR -> " + message;
	}
	
	/**
	 * Ecrit le message enregistre sur la sortie d'erreur.
	 */
	public void message() {
		System.err.println(message);
	}
}