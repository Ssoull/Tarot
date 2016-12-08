/*
 * Un code realise par Jules Despret et Pablo Gutierrez.
 */

package Vue;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JPanel;
import javax.swing.TransferHandler;

import Controleur.Controleur;

/**
 * Classe permettant d'importer des donnees dans un JPanel. En liens avec la classe ExporterDonnees.java
 * @author Gutierrez
 */
public class ImporterDonnees extends TransferHandler {
	/**
	 * Déclaration et initialisation des donnees que l'on peut transmettre.
	 */
    public static final DataFlavor DONNEES_TRANSMISSIBLE = DataFlavor.stringFlavor;
    /**
     * Représente le Controleur de l'architecture MVC.
     */
    private Controleur controleur;
    
    
    /**
     * Contructeur prenant en paramètre le controleur de l'application en cours.
     * @param controleur
     */
    public ImporterDonnees(Controleur controleur) {
    	this.controleur = controleur;
    }
    
    
    /**
     * Redefinit une methode de TransferHandLer. 
     * Cette methode permet de vérifier si les donnees qu'on envoit peuvent etre importer.
     */
    @Override
    public boolean canImport(TransferHandler.TransferSupport donnees_supportees) {
        return donnees_supportees.isDataFlavorSupported(DONNEES_TRANSMISSIBLE);
    }
    
    
    /**
     * Redefinit une methode de TransferHandLer. 
     * Cette methode verifie si on peut importer les donnees et si c'est le cas le fait.
     */
    @Override
    public boolean importData(TransferHandler.TransferSupport donnees_supportees) {
        boolean importAvecSucces = false;
        if (canImport(donnees_supportees)) {
            try {
                Transferable transfert = donnees_supportees.getTransferable();
                Object chemin_carte = transfert.getTransferData(DONNEES_TRANSMISSIBLE);
                if (chemin_carte instanceof String) {
                    Component composant = donnees_supportees.getComponent();
                    if (composant instanceof JPanel) {
                    	controleur.ajoutCarteDansEcartAPartirDuCheminDeLaCarte(chemin_carte.toString());
                    	importAvecSucces = true;
                    }
                }
            } catch (Exception exception) {
            	exception.printStackTrace();
            }
        }
        return importAvecSucces;
    }
}
