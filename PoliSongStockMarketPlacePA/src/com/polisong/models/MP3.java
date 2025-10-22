// MP3.java
package com.polisong.models;

public class MP3 {
    private int idMp3;
    private int idProducto;
    private String nombre;
    private String duracion;
    private float tamano;
    private int calidad;
    private String genero;
    private float precio;
    
    public MP3(String nombre, String duracion, float tamano, int calidad, String genero, float precio) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.tamano = tamano;
        this.calidad = calidad;
        this.genero = genero;
        this.precio = precio;
    }
    
    // Getters y Setters
    public int getIdMp3() { return idMp3; }
    public void setIdMp3(int idMp3) { this.idMp3 = idMp3; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
    public float getTamano() { return tamano; }
    public void setTamano(float tamano) { this.tamano = tamano; }
    public int getCalidad() { return calidad; }
    public void setCalidad(int calidad) { this.calidad = calidad; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }
}