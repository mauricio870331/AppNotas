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
public class GetAsignarMaterias {

    public static AsignarMaterias am = null;

    public static AsignarMaterias getAsignarMaterias() {
        try {
            if (am == null) {
                am = new AsignarMaterias(null, true);
                System.out.println("mu fue instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return am;
    }

}
