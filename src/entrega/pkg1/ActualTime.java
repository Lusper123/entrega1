/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;

import java.time.LocalTime;

/**
 *
 * @author choco
 */
public class ActualTime {
    private static LocalTime localTime;
    
    public static void setActualTime(LocalTime localTime){
         ActualTime.localTime = localTime;
    }
    
    public static LocalTime get() {
        return localTime;
    }
}

