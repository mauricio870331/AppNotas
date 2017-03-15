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
public class GetModalUsers {

    public static ModalUsers mu = null;

    public static ModalUsers getModalUsers() {
        try {
            if (mu == null) {
                mu = new ModalUsers(null, true);
                System.out.println("mu fue instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return mu;
    }

}
