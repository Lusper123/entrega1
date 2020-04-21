/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;

import model.Member;

/**
 *
 * @author choco
 */
public class Auth {

    private static Member user = null;

    public static Member user() {
        return user;
    }

    public static void login(Member u) {

        if (u == null || u.checkLogin(u.getLogin())) {
            user = u;
           
        }

    }
    
    
}
