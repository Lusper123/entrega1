/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;

import model.Court;

/**
 *
 * @author choco
 */
public class ActualCourt {
    private static Court court;
    
    public static void setActualCourt(Court court){
         ActualCourt.court = court;
    }
    
    public static Court get() {
        return court;
    }
}
