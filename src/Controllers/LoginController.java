/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Usuarios;
import Model.UsuariosDao;
import Vistas.GetLogin;
import Vistas.GetPrincipal;
import Vistas.Login;
import Vistas.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author clopez
 */
public class LoginController implements ActionListener {

    Login lg;
    Principal pr;
    UsuariosDao usuarioDao = null;
    DefaultTableModel modelo;

    public LoginController() {
        lg = GetLogin.getLogin();
        pr = GetPrincipal.getPrincipal();
        lg.btnIngresar.addActionListener(this);
        pr.btnListUsuarios.addActionListener(this);
        ocultarPaneles();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lg.btnIngresar) {
            usuarioDao = new UsuariosDao();
            try {
                Usuarios u = usuarioDao.getUsuario(lg.txtUser.getText(), lg.txtPass.getText());
                if (u != null) {
                    switch (u.getTipo_usuario()) {
                        case 1:
                            pr.mnuUsuarios.setVisible(true);
                            break;
                        case 2:
                            pr.mnuUsuarios.setVisible(false);
                            break;
                        case 3:
                            pr.mnuUsuarios.setVisible(false);
                            break;
                    }
                    pr.setTitle("Módulo Principal | Usuario: "+u.getNombre());
                    lg.dispose();
                    pr.setLocationRelativeTo(null);
                    pr.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o Clave Incorrecta");
                }
            } catch (SQLException ex) {
                System.out.println("error = " + ex);
            }
        }

        if (e.getSource() == pr.btnListUsuarios) {
            try {
                cargarTablaUsuarios(0);
                 pr.pn1.setVisible(false);
                pr.pnListusers.setVisible(true);
            } catch (SQLException ex) {
                System.out.println("error --" + ex);
            }
        }
    }

    public void ocultarPaneles() {
        pr.pnListusers.setVisible(false);
        pr.pn1.setVisible(true);
    }

    private void cargarTablaUsuarios(int tipousuario) throws SQLException {
        String Titulos[] = {"Documento", "Nombre", "tipo"};
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) { //para evitar que las celdas sean editables
                return column == 0;
            }
        };
        Object[] columna = new Object[3];
        usuarioDao = new UsuariosDao();
        Iterator<Usuarios> nombreIterator = usuarioDao.getUsuarioList(tipousuario).iterator();
        while (nombreIterator.hasNext()) {
            Usuarios u = nombreIterator.next();
            columna[0] = u.getId_Usuario();
            columna[1] = u.getNombre();
            columna[2] = u.getTipo_usuario();
            modelo.addRow(columna);
        }
        pr.tblusuarios.setModel(modelo);
        TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
        pr.tblusuarios.setRowSorter(ordenar);
        pr.tblusuarios.setModel(modelo);
    }

}
