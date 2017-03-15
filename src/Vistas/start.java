/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controllers.PrincipalController;

/**
 *
 * @author clopez
 */
public class start {

    public static Login lg = null;

    public static void main(String[] args) {
        lg = GetLogin.getLogin();
        lg.setLocationRelativeTo(null);
        lg.setVisible(true);
        PrincipalController lc = new PrincipalController();
    }
}
