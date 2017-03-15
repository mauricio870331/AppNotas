/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author clopez
 */
public class UsuariosDao {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    String sql;
    ResultSet rs;

    //Listar
    public Usuarios getUsuario(String usuario, String pass) throws SQLException {
        Usuarios u = null;
        try {
            sql = "select nombre, tipo_usuario from usuarios where usuario = ? and clave = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, usuario);
            pstm.setString(2, pass);
            rs = pstm.executeQuery();
            if (rs.next()) {
                u = new Usuarios(rs.getString(1), rs.getInt(2));
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return u;
    }

    public ArrayList<Usuarios> getUsuarioList(int tipo) throws SQLException {
        ArrayList<Usuarios> l = new ArrayList();
        try {
            if (tipo > 0) {
                sql = "select * from usuarios where tipo_usuario = ?";
            } else {
                sql = "select * from usuarios";
            }
            pstm = cn.prepareCall(sql);
            if (tipo > 0) {
                pstm.setInt(1, tipo);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                l.add(new Usuarios(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return l;
    }

    //Listar
    public String guardarUsuario(Usuarios u) throws SQLException {
        String msn = "Error Al Crear El Usuario";
        try {
            sql = "insert into usuarios values (?,?,?,?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, u.getId_Usuario());
            pstm.setString(2, u.getNombre());
            pstm.setString(3, u.getUsuario());
            pstm.setString(4, u.getClave());
            pstm.setInt(5, u.getTipo_usuario());
            pstm.executeUpdate();
            msn = "Usuario Creado Con Exito";
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return msn;
    }

}
