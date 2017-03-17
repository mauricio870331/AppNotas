/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author clopez
 */
public class Usuarios {

    private String id_Usuario;
    private String nombre;
    private String usuario;
    private String clave;
    private int tipo_usuario;
    

    public Usuarios(String id_Usuario, String nombre, String usuario, String clave, int tipo_usuario) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
        this.tipo_usuario = tipo_usuario;
    }

    Usuarios(String nombre, int tipo_usuario) {
        this.nombre = nombre;
        this.tipo_usuario = tipo_usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(String id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

}
