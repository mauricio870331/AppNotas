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
public class MateriasDao {

    Connection cn = Conexion.getConexion();
    PreparedStatement pstm;
    String sql;
    ResultSet rs;

    //Listar
//    public Usuarios getUsuario(String usuario, String pass) throws SQLException {
//        Usuarios u = null;
//        try {
//            sql = "select nombre, tipo_usuario from usuarios where usuario = ? and clave = ?";
//            pstm = cn.prepareStatement(sql);
//            pstm.setString(1, usuario);
//            pstm.setString(2, pass);
//            rs = pstm.executeQuery();
//            if (rs.next()) {
//                u = new Usuarios(rs.getString(1), rs.getInt(2));
//            }
//        } catch (Exception e) {
//            System.out.println("error " + e);
//        }
//        return u;
//    }

    public ArrayList<Materias> getMateriasList(String materia) throws SQLException {
        ArrayList<Materias> m = new ArrayList();
        try {
            if (!materia.equals("")) {
                sql = "select * from materias where materia = ?";
            } else {
                sql = "select * from materias";
            }
            pstm = cn.prepareStatement(sql);
            if (!materia.equals("")) {
                pstm.setString(1, materia);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                m.add(new Materias(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return m;
    }

    //Listar
    public String guardarMateria(Materias m) throws SQLException {
        String msn = "Error Al Crear la materia";
        try {
            sql = "insert into materias (materia) values (?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, m.getMateria());         
            pstm.executeUpdate();
            msn = "Materia Creada Con Exito";
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return msn;
    }

}
