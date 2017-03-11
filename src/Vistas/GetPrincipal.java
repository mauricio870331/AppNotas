/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

/**
 *
 * @author clopez
 */
public class GetPrincipal {

    public static Principal pr = null;

    public static Principal getPrincipal() {
        try {
            if (pr == null) {
                pr = new Principal();
                System.out.println("pr fue instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return pr;
    }

}
