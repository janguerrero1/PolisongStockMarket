package com.polisong.services;

import com.polisong.dao.ProveedorDAO;
import com.polisong.dao.ViniloDAO;
import com.polisong.models.Vinilo;
import java.util.Scanner;

public class ProveedorService {
    private static Scanner scanner = new Scanner(System.in);
    private ProveedorDAO proveedorDAO = new ProveedorDAO();
    private ViniloDAO viniloDAO = new ViniloDAO();
    
    public void registrarProveedor(int idUsuario) {
        System.out.println("\n--- REGISTRO DE PROVEEDOR ---");
        
        System.out.print("Nombre de la tienda: ");
        String nombreTienda = scanner.nextLine();
        
        System.out.print("Correo de contacto: ");
        String correo = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        System.out.print("Cédula/RUT: ");
        int cedula = Integer.parseInt(scanner.nextLine());
        
        com.polisong.models.Proveedor proveedor = new com.polisong.models.Proveedor(idUsuario, nombreTienda, correo, telefono, cedula);
        boolean exito = proveedorDAO.insertarProveedor(proveedor);
        
        if (exito) {
            System.out.println("✅ Proveedor registrado exitosamente");
        } else {
            System.out.println("❌ Error al registrar proveedor");
        }
    }
    
    public void gestionarVinilos(int idUsuario) {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE VINILOS ---");
            System.out.println("1. Agregar nuevo vinilo");
            System.out.println("2. Ver mis vinilos");
            System.out.println("3. Actualizar inventario");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione: ");
            
            int opcion = Integer.parseInt(scanner.nextLine());
            
            switch (opcion) {
                case 1:
                    agregarVinilo(idUsuario);
                    break;
                case 2:
                    viniloDAO.obtenerVinilosPorProveedor(idUsuario);
                    break;
                case 3:
                    actualizarInventario(idUsuario);
                    break;
                case 4:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    
    private void agregarVinilo(int idProveedor) {
        System.out.println("\n--- NUEVO VINILO ---");
        
        System.out.print("Nombre del vinilo: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        
        System.out.print("Año: ");
        int anio = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Cantidad en inventario: ");
        int inventario = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Precio: ");
        float precio = Float.parseFloat(scanner.nextLine());
        
        Vinilo vinilo = new Vinilo(idProveedor, nombre, artista, anio, inventario, precio);
        boolean exito = viniloDAO.insertarVinilo(vinilo);
        
        if (exito) {
            System.out.println("✅ Vinilo agregado exitosamente");
        } else {
            System.out.println("❌ Error al agregar vinilo");
        }
    }
    
    private void actualizarInventario(int idProveedor) {
        System.out.print("ID del vinilo a actualizar: ");
        int idVinilo = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Nueva cantidad en inventario: ");
        int nuevoInventario = Integer.parseInt(scanner.nextLine());
        
        boolean exito = viniloDAO.actualizarInventario(idVinilo, nuevoInventario);
        
        if (exito) {
            System.out.println("✅ Inventario actualizado");
        } else {
            System.out.println("❌ Error al actualizar inventario");
        }
    }
    
    public void listarProveedores() {
        System.out.println("\n--- LISTA DE PROVEEDORES ---");
        proveedorDAO.obtenerTodosProveedores();
    }
}