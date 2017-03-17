/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Materias;
import Model.MateriasDao;
import Model.TiposUsuarios;
import Model.TiposUsuariosDao;
import Model.Usuarios;
import Model.UsuariosDao;
import Vistas.AsignarMaterias;
import Vistas.GetAsignarMaterias;
import Vistas.GetLogin;
import Vistas.GetModalMaterias;
import Vistas.GetModalUsers;
import Vistas.GetPrincipal;
import Vistas.Login;
import Vistas.ModalMaterias;
import Vistas.ModalUsers;
import Vistas.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class PrincipalController implements ActionListener {

    Login lg;
    Principal pr;
    ModalUsers mu;
    ModalMaterias mm;
    AsignarMaterias am;
    UsuariosDao usuarioDao = null;
    MateriasDao materiasDao = null;
    DefaultTableModel modelo;
    TiposUsuariosDao tiposUserDao;
    int tipoUserLog = 0;
    ArrayList<Object>
    public PrincipalController() {
        lg = GetLogin.getLogin();
        pr = GetPrincipal.getPrincipal();
        mu = GetModalUsers.getModalUsers();
        mm = GetModalMaterias.getModalUsers();
        am = GetAsignarMaterias.getAsignarMaterias();
        lg.btnIngresar.addActionListener(this);
        pr.btnListUsuarios.addActionListener(this);
        pr.btnListMaterias.addActionListener(this);
        pr.btnNuevoUsuario.addActionListener(this);
        pr.btnNuevaMateria.addActionListener(this);
        mu.btnGuardar.addActionListener(this);
        mm.btnGuardar.addActionListener(this);
        pr.seleccionar.addActionListener(this);
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
                            pr.mnuUsuarios.setVisible(true);
                            pr.btnNuevoUsuario.setVisible(false);
                            tipoUserLog = u.getTipo_usuario();
                            break;
                        case 3:
                            pr.mnuUsuarios.setVisible(false);
                            break;
                    }
                    pr.setTitle("Módulo Principal | Usuario: " + u.getNombre());
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
                if (tipoUserLog == 2) {
                    cargarTablaUsuarios(3);
                } else {
                    cargarTablaUsuarios(0);
                }

                pr.pn1.setVisible(false);
                pr.pnListMaterias.setVisible(false);
                pr.pnListusers.setVisible(true);
            } catch (SQLException ex) {
                System.out.println("error --" + ex);
            }
        }

        if (e.getSource() == pr.btnListMaterias) {
            try {
                cargarTablaMaterias("");
                pr.pn1.setVisible(false);
                pr.pnListusers.setVisible(false);
                pr.pnListMaterias.setVisible(true);
            } catch (SQLException ex) {
                System.out.println("error --" + ex);
            }
        }

        if (e.getSource() == pr.btnNuevoUsuario) {
            try {
                cargarTiposDeusuarios();
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
            mu.setLocationRelativeTo(null);
            mu.setVisible(true);
        }

        if (e.getSource() == pr.btnNuevaMateria) {
            mm.setLocationRelativeTo(null);
            mm.setVisible(true);
        }

        if (e.getSource() == mu.btnGuardar) {
            try {
                //pendiente validar
                String id_Usuario = mu.txtDoc.getText();
                String nombre = mu.txtNombre.getText();
                String usuario = mu.txtUser.getText();
                String clave = mu.txtClave.getText();
                TiposUsuarios t = (TiposUsuarios) mu.cboTipo.getSelectedItem();
                crearusuarios(new Usuarios(id_Usuario, nombre, usuario, clave, t.getId()));
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == mm.btnGuardar) {
            try {
                //pendiente validar
                String materia = mm.txtMateria.getText();
                crearMaterias(new Materias(materia));
            } catch (SQLException ex) {
                System.out.println("error " + ex);
            }
        }

        if (e.getSource() == pr.seleccionar) {
            int fila = pr.tblusuarios.getSelectedRow();
            if (fila >= 0) {
                try {
                    cargarMaterias("");                  
                    am.documento.setText(pr.tblusuarios.getValueAt(fila, 0).toString());
                    am.setLocationRelativeTo(null);
                    am.setVisible(true);
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
            }
        }
        
        
        if (e.getSource() == am.seleccionar) {
            int fila = am.tblMaterias.getSelectedRow();
            if (fila >= 0) {
                try {
                    
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Debes seleccionar un registro!");
            }
        }

    }

    public void ocultarPaneles() {
        pr.pnListusers.setVisible(false);
        pr.pnListMaterias.setVisible(false);
        pr.pn1.setVisible(true);
    }

    private void cargarTablaUsuarios(int tipousuario) throws SQLException {
        String Titulos[] = {"Documento", "Nombre"};
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
//            columna[2] = u.getTipo_usuario();
            modelo.addRow(columna);
        }
        pr.tblusuarios.setModel(modelo);
        TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
        pr.tblusuarios.setRowSorter(ordenar);
        pr.tblusuarios.setModel(modelo);
    }

    //cargar tabla materias
    private void cargarTablaMaterias(String materia) throws SQLException {
        String Titulos[] = {"id", "Nombre"};
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) { //para evitar que las celdas sean editables
                return column == 0;
            }
        };
        Object[] columna = new Object[2];
        materiasDao = new MateriasDao();
        Iterator<Materias> nombreIterator = materiasDao.getMateriasList(materia).iterator();
        while (nombreIterator.hasNext()) {
            Materias m = nombreIterator.next();
            columna[0] = m.getId();
            columna[1] = m.getMateria();
            modelo.addRow(columna);
        }
        pr.tblMaterias.setModel(modelo);
        TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
        pr.tblMaterias.setRowSorter(ordenar);
        pr.tblMaterias.setModel(modelo);
    }

    //Metodo que carga el combo de tipos de usuarios en el modal crear usuaios
    private void cargarTiposDeusuarios() throws SQLException {
        mu.cboTipo.removeAllItems();
        tiposUserDao = new TiposUsuariosDao();
        Iterator<TiposUsuarios> itr = tiposUserDao.getTiposUsuarios().iterator();
        while (itr.hasNext()) {
            TiposUsuarios t = itr.next();
            mu.cboTipo.addItem(t);
        }
        tiposUserDao = null;
    }

    public void crearusuarios(Usuarios u) throws SQLException {
        usuarioDao = new UsuariosDao();
        JOptionPane.showMessageDialog(null, usuarioDao.guardarUsuario(u));
        cargarTablaUsuarios(0);
    }

    private void crearMaterias(Materias m) throws SQLException {
        materiasDao = new MateriasDao();
        JOptionPane.showMessageDialog(null, materiasDao.guardarMateria(m));
        mm.txtMateria.setText("");
        mm.dispose();
        cargarTablaMaterias("");
    }

    private void cargarMaterias(String materia) throws SQLException {
        String Titulos[] = {"id", "Nombre"};
        modelo = new DefaultTableModel(null, Titulos) {
            @Override
            public boolean isCellEditable(int row, int column) { //para evitar que las celdas sean editables
                return column == 0;
            }
        };
        Object[] columna = new Object[2];
        materiasDao = new MateriasDao();
        Iterator<Materias> nombreIterator = materiasDao.getMateriasList(materia).iterator();
        while (nombreIterator.hasNext()) {
            Materias m = nombreIterator.next();
            columna[0] = m.getId();
            columna[1] = m.getMateria();
            modelo.addRow(columna);
        }
        am.tblMaterias.setModel(modelo);
        TableRowSorter<TableModel> ordenar = new TableRowSorter<>(modelo);
        am.tblMaterias.setRowSorter(ordenar);
        am.tblMaterias.getColumnModel().getColumn(0).setMaxWidth(0);
        am.tblMaterias.getColumnModel().getColumn(0).setMinWidth(0);
        am.tblMaterias.getColumnModel().getColumn(0).setPreferredWidth(0);
        am.tblMaterias.setModel(modelo);
        materiasDao = null;
    }

}
