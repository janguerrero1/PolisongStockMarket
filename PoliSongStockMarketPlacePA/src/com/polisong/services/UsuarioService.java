package com.polisong.services;

import com.polisong.dao.UsuarioDAO;
import com.polisong.dao.ProveedorDAO;
import com.polisong.models.Usuario;
import com.polisong.models.Proveedor;
import java.util.Scanner;

public class UsuarioService {
    private static Scanner scanner = new Scanner(System.in);
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ProveedorDAO proveedorDAO = new ProveedorDAO();
    
    public void registrarUsuario() {
        System.out.println("\n--- REGISTRAR NUEVO USUARIO ---");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        
        System.out.print("Rol (usuario/proveedor): ");
        String rol = scanner.nextLine();
        
        // QUITADO: Saldo inicial
        // System.out.print("Saldo inicial: ");
        // float saldo = Float.parseFloat(scanner.nextLine());
        
        System.out.print("Cédula: ");
        int cedula = Integer.parseInt(scanner.nextLine());
        
        // Usuario con saldo inicial 0
        Usuario usuario = new Usuario(nombre, correo, contrasena, rol, cedula);
        boolean exito = usuarioDAO.insertarUsuario(usuario);
        
        if (exito) {
            System.out.println("✅ Usuario registrado exitosamente");
            
            if ("proveedor".equals(rol)) {
                registrarProveedor(usuario);
            }
        } else {
            System.out.println("❌ Error al registrar usuario");
        }
    }
    
    private void registrarProveedor(Usuario usuario) {
        System.out.println("\n--- REGISTRO ADICIONAL PARA PROVEEDOR ---");
        
        System.out.print("Nombre de la tienda: ");
        String nombreTienda = scanner.nextLine();
        
        System.out.print("Correo de contacto: ");
        String correo = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        System.out.print("Cédula/RUT: ");
        int cedula = Integer.parseInt(scanner.nextLine());
        
        Proveedor proveedor = new Proveedor(usuario.getIdUsuario(), nombreTienda, correo, telefono, cedula);
        boolean exito = proveedorDAO.insertarProveedor(proveedor);
        
        if (exito) {
            System.out.println("✅ Proveedor registrado exitosamente");
        } else {
            System.out.println("❌ Error al registrar proveedor");
        }
    }
    
    public Usuario iniciarSesion() {
        System.out.println("\n--- INICIAR SESIÓN ---");
        
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        
        Usuario usuario = usuarioDAO.buscarUsuarioPorCredenciales(correo, contrasena);
        
        if (usuario != null) {
            System.out.println("✅ ¡Bienvenido " + usuario.getNombre() + "!");
            return usuario;
        } else {
            System.out.println("❌ Credenciales incorrectas");
            return null;
        }
    }
    
    public void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        usuarioDAO.obtenerUsuarios();
    }
    
    public void actualizarSaldoUsuario() {
        System.out.println("\n--- ACTUALIZAR SALDO ---");
        
        System.out.print("ID del usuario: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Nuevo saldo: ");
        float nuevoSaldo = Float.parseFloat(scanner.nextLine());
        
        boolean exito = usuarioDAO.actualizarSaldo(idUsuario, nuevoSaldo);
        
        if (exito) {
            System.out.println("✅ Saldo actualizado exitosamente");
        } else {
            System.out.println("❌ Error al actualizar saldo");
        }
    }
    
    public void buscarUsuarioPorId() {
        System.out.println("\n--- BUSCAR USUARIO POR ID ---");
        System.out.print("ID del usuario: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());
        
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            System.out.println("✅ Usuario encontrado:");
            System.out.println(usuario);
        } else {
            System.out.println("❌ Usuario no encontrado");
        }
    }
}