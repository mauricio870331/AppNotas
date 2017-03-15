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
public class GetModalMaterias {

    public static ModalMaterias mm = null;

    public static ModalMaterias getModalUsers() {
        try {
            if (mm == null) {
                mm = new ModalMaterias(null, true);
                System.out.println("mu fue instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return mm;
    }

}
