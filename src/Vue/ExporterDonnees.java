package Vue;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 * Classe permettant d'exporter des donnees. En liens avec la classe ImporterDonnees.java
 * @author Gutierrez
 */
public class ExporterDonnees extends TransferHandler {
	/**
	 * Déclaration et initialisation du type de donnees que l'on peut transmettre.
	 */
    public static final DataFlavor DONNEES_TRANSMISSIBLE = DataFlavor.stringFlavor;
    /**
     * Chemin de la carte que l'on exporte.
     */
    private String chemin_carte;
    
    
    /**
     * Constructeur prenant en paramètre le chemin de la carte qu'on veut pouvoir "déplacer".
     * @param chemin_carte
     */
    public ExporterDonnees(String chemin_carte) {
        this.chemin_carte = chemin_carte;
    }
    
    
    /**
     * Retourne le chemin de la carte.
     * @return String
     */
    public String getCheminCarte() {
        return chemin_carte;
    }
    
    
    /**
     * Redefinit une methode de TransferHandLer. 
     * On choisit le type d'action que que fera le "glisse/deposse". Ici c'est une copie.
     */
    @Override
    public int getSourceActions(JComponent composant) {
        return DnDConstants.ACTION_COPY;
    }
    
    
    /**
     * Redefinit une methode de TransferHandLer. 
     * Cette methode permet de creer un objet representant les donnees a transferer.  
     */
    @Override
    protected Transferable createTransferable(JComponent composant) {
        Transferable transfert = new StringSelection(getCheminCarte());
        return transfert;
    }
}
