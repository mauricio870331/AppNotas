package Model;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    private static Connection con = null;
    public static String TipoCon = "";

    //patron singleton
    public static Connection getConexion() {
        try {
            if (con == null) {
                String url = "jdbc:mysql://127.0.0.1:3306/notas?autoReconnect=true";
                String pwd = "";
                String usr = "root";
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);
                //con = DriverManager.getConnection("jdbc:mysql://localhost/codigo_proyectos", "codigo_proyectos", "P}3LdHI^+WMV");
                con = DriverManager.getConnection(url, usr, pwd);
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }

    public static Connection closeConexion() {
        con = null;
        return con;
    }
}
