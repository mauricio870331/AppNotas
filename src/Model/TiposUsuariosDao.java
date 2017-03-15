/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author clopez
 */
public class TiposUsuariosDao {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    String sql;
    ResultSet rs;

    //Listar
    public ArrayList<TiposUsuarios> getTiposUsuarios() throws SQLException {
        ArrayList<TiposUsuarios> u = new ArrayList();
        try {
            sql = "select * from tipos_usuarios";
            pstm = cn.prepareStatement(sql);           
            rs = pstm.executeQuery();
            u.add(new TiposUsuarios(0, "-- Seleccione --"));
            while (rs.next()) {
                u.add(new TiposUsuarios(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return u;
    }

//    public ArrayList<Usuarios> getUsuarioList(int tipo) throws SQLException {
//        ArrayList<Usuarios> l = new ArrayList();
//        try {
//            if (tipo > 0) {
//                sql = "select * from usuarios where tipo_usuario = ?";
//            } else {
//                sql = "select * from usuarios";
//            }
//            pstm = cn.prepareCall(sql);
//            if (tipo > 0) {
//                pstm.setInt(1, tipo);
//            }
//            rs = pstm.executeQuery();
//            while (rs.next()) {
//                l.add(new Usuarios(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
//            }
//        } catch (Exception e) {
//            System.out.println("error " + e);
//        }
//        return l;
//    }
}
