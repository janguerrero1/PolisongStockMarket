package com.polisong.dao;

import com.polisong.models.Proveedor;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    private static List<Proveedor> proveedores = new ArrayList<>();
    private static int nextId = 1;
    
    static {
        // Datos de ejemplo
        proveedores.add(new Proveedor(2, "Vinilos Clásicos", "ventas@vinilosclasicos.com", "555-1234", 123456));
        
        for (int i = 0; i < proveedores.size(); i++) {
            proveedores.get(i).setIdProveedor(i + 1);
        }
        nextId = proveedores.size() + 1;
    }
    
    public boolean insertarProveedor(Proveedor proveedor) {
        try {
            proveedor.setIdProveedor(nextId++);
            proveedores.add(proveedor);
            System.out.println("✅ Proveedor agregado: " + proveedor.getNombreTienda());
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }
    
    public Proveedor obtenerProveedorPorUsuario(int idUsuario) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getIdUsuario() == idUsuario) {
                return proveedor;
            }
        }
        return null;
    }
    
    public void obtenerTodosProveedores() {
        System.out.println("\n--- LISTA DE PROVEEDORES ---");
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados");
        } else {
            for (Proveedor proveedor : proveedores) {
                System.out.println(proveedor);
            }
        }
    }
    
    public boolean actualizarTelefonoProveedor(int idProveedor, String nuevoTelefono) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getIdProveedor() == idProveedor) {
                proveedor.setTelefono(nuevoTelefono);
                System.out.println("✅ Teléfono actualizado para: " + proveedor.getNombreTienda());
                return true;
            }
        }
        System.out.println("❌ Proveedor no encontrado");
        return false;
    }
    
    public boolean actualizarCorreoProveedor(int idProveedor, String nuevoCorreo) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getIdProveedor() == idProveedor) {
                proveedor.setCorreo(nuevoCorreo);
                System.out.println("✅ Correo actualizado para: " + proveedor.getNombreTienda());
                return true;
            }
        }
        System.out.println("❌ Proveedor no encontrado");
        return false;
    }
}