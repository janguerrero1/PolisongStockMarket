// Vinilo.java
package com.polisong.models;

public class Vinilo {
    private int idVinilo;
    private int idProducto;
    private int idProveedor;
    private String nombre;
    private String artista;
    private int anio;
    private int inventario;
    private float precio;
    
    public Vinilo(int idProveedor, String nombre, String artista, int anio, int inventario, float precio) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.artista = artista;
        this.anio = anio;
        this.inventario = inventario;
        this.precio = precio;
    }
    
    // Getters y Setters
    public int getIdVinilo() { return idVinilo; }
    public void setIdVinilo(int idVinilo) { this.idVinilo = idVinilo; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public int getInventario() { return inventario; }
    public void setInventario(int inventario) { this.inventario = inventario; }
    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }
}