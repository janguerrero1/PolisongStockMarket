// Proveedor.java
package com.polisong.models;

public class Proveedor {
    private int idProveedor;
    private int idUsuario;
    private String nombreTienda;
    private String correo;
    private String telefono;
    private int cedula;
    
    public Proveedor(int idUsuario, String nombreTienda, String correo, String telefono, int cedula) {
        this.idUsuario = idUsuario;
        this.nombreTienda = nombreTienda;
        this.correo = correo;
        this.telefono = telefono;
        this.cedula = cedula;
    }
    
    // Getters y Setters
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNombreTienda() { return nombreTienda; }
    public void setNombreTienda(String nombreTienda) { this.nombreTienda = nombreTienda; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public int getCedula() { return cedula; }
    public void setCedula(int cedula) { this.cedula = cedula; }
}