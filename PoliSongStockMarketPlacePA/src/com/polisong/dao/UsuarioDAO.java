package com.polisong.dao;

import com.polisong.models.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static int nextId = 1;
    
    static {
        // Datos de ejemplo con saldo 0
        usuarios.add(new Usuario("Juan Pérez", "juan@email.com", "123", "usuario", 123456));
        usuarios.add(new Usuario("María García", "maria@email.com", "123", "proveedor", 654321));
        usuarios.add(new Usuario("Admin", "admin@email.com", "admin", "admin", 999999));
        
        // Asignar IDs
        for (int i = 0; i < usuarios.size(); i++) {
            usuarios.get(i).setIdUsuario(i + 1);
        }
        nextId = usuarios.size() + 1;
    }
    
    public boolean insertarUsuario(Usuario usuario) {
        try {
            usuario.setIdUsuario(nextId++);
            usuarios.add(usuario);
            System.out.println("✅ Usuario agregado: " + usuario.getNombre());
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public void obtenerUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }
    
    public Usuario buscarUsuarioPorCredenciales(String correo, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }
    
    public boolean actualizarSaldo(int idUsuario, float nuevoSaldo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                usuario.setSaldo(nuevoSaldo);
                System.out.println("✅ Saldo actualizado para: " + usuario.getNombre());
                return true;
            }
        }
        System.out.println("❌ Usuario no encontrado");
        return false;
    }
    
    public Usuario buscarUsuarioPorId(int idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }
}