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
public class GetLogin {

    public static Login lg = null;

    public static Login getLogin() {
        try {
            if (lg == null) {
                lg = new Login();
                System.out.println("pr fue instanceado");
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
        return lg;
    }

}
