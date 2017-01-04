/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.domaine;

import java.io.Serializable;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author adil
 */

public class messageBean implements Serializable {

    private boolean etat = true;
    private boolean invetat = false;
    protected static String mess_modif_true = "Modification effectuée avec succès";
    protected static String mess_insert_true = "Ajout effectué avec succès";
    protected static String mess_op_false = "Opération échouée";
    protected static String mess_del_true = "Suppression effectuée avec succès";
    protected String style_message;
    
    
private MapModel simpleModel;  
  
    private Marker marker;  
    public void chageretat() {
        this.setInvetat(this.isEtat());
        this.setEtat(!this.isEtat());
    }
//getters and setters

    /**
     * @return the etat
     */
    public boolean isEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    /**
     * @return the invetat
     */
    public boolean isInvetat() {
        return invetat;
    }

    /**
     * @param invetat the invetat to set
     */
    public void setInvetat(boolean invetat) {
        this.invetat = invetat;
    }
    public void MapBean() {  
        simpleModel = new DefaultMapModel();  
          
        //Shared coordinates  
        LatLng coord1 = new LatLng(28.986959, -10.057376);  
        
          
        //Basic marker  
        simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));  
         
    } 
   
    
}
