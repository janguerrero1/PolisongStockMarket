package com.polisong.main;

import com.polisong.services.UsuarioService;
import com.polisong.services.ProveedorService;
import com.polisong.services.CatalogoService;
import com.polisong.services.PedidoService;
import com.polisong.config.SupabaseConnection;
import com.polisong.models.Usuario;
import java.util.Scanner;

public class SistemaMusica {
    private static Scanner scanner = new Scanner(System.in);
    private static UsuarioService usuarioService = new UsuarioService();
    private static ProveedorService proveedorService = new ProveedorService();
    private static CatalogoService catalogoService = new CatalogoService();
    private static PedidoService pedidoService = new PedidoService();
    private static Usuario usuarioLogueado = null;
    
    public static void main(String[] args) {
        System.out.println("🎵 POLISONG STOCK MARKETPLACE 🎵");
        System.out.println("=================================");
        System.out.println(SupabaseConnection.getConnectionStatus());
        
        boolean salir = false;
        
        while (!salir) {
            if (usuarioLogueado == null) {
                mostrarMenuNoAutenticado();
            } else {
                mostrarMenuAutenticado();
            }
            
            int opcion = obtenerOpcion();
            
            if (usuarioLogueado == null) {
                switch (opcion) {
                    case 1:
                        usuarioLogueado = usuarioService.iniciarSesion();
                        break;
                    case 2:
                        usuarioService.registrarUsuario();
                        break;
                    case 3:
                        usuarioService.listarUsuarios();
                        break;
                    case 4:
                        mostrarInfoSistema();
                        break;
                    case 0:
                        salir = true;
                        System.out.println("¡Hasta pronto! 👋");
                        break;
                    default:
                        System.out.println("Opción no válida ❌");
                }
            } else {
                switch (opcion) {
                    case 1:
                        catalogoService.mostrarCatalogoCompleto();
                        break;
                    case 2:
                        if ("proveedor".equals(usuarioLogueado.getRol()) || "admin".equals(usuarioLogueado.getRol())) {
                            proveedorService.gestionarVinilos(usuarioLogueado.getIdUsuario());
                        } else {
                            System.out.println("❌ Solo los proveedores pueden gestionar vinilos");
                        }
                        break;
                    case 3:
                        pedidoService.realizarPedido(usuarioLogueado.getIdUsuario());
                        break;
                    case 4:
                        if ("proveedor".equals(usuarioLogueado.getRol())) {
                            pedidoService.gestionarPedidosProveedor(usuarioLogueado.getIdUsuario());
                        } else {
                            pedidoService.verMisPedidos(usuarioLogueado.getIdUsuario());
                        }
                        break;
                    case 5:
                        catalogoService.buscarRecopilaciones();
                        break;
                    case 6:
                        catalogoService.buscarPorGenero(); // ✅ AHORA EXISTE
                        break;
                    case 7:
                        if ("admin".equals(usuarioLogueado.getRol())) {
                            catalogoService.agregarNuevoMP3(); // ✅ AHORA EXISTE
                        } else {
                            System.out.println("❌ Solo administradores pueden agregar MP3s");
                        }
                        break;
                    case 8:
                        usuarioService.actualizarSaldoUsuario(); // ✅ AHORA EXISTE
                        break;
                    case 9:
                        proveedorService.listarProveedores();
                        break;
                    case 10:
                        mostrarInfoSistema();
                        break;
                    case 11:
                        usuarioLogueado = null;
                        System.out.println("✅ Sesión cerrada exitosamente");
                        break;
                    case 0:
                        salir = true;
                        System.out.println("¡Hasta pronto! 👋");
                        break;
                    default:
                        System.out.println("Opción no válida ❌");
                }
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarMenuNoAutenticado() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Listar usuarios");
        System.out.println("4. Información del sistema");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");
    }
    
    private static void mostrarMenuAutenticado() {
        System.out.println("\n--- BIENVENIDO " + usuarioLogueado.getNombre().toUpperCase() + " ---");
        System.out.println("Rol: " + usuarioLogueado.getRol());
        System.out.println("Saldo: $" + usuarioLogueado.getSaldo());
        System.out.println("\n1. Ver catálogo completo");
        if ("proveedor".equals(usuarioLogueado.getRol()) || "admin".equals(usuarioLogueado.getRol())) {
            System.out.println("2. Gestionar mis vinilos");
            System.out.println("4. Gestionar pedidos de vinilos");
        } else {
            System.out.println("3. Realizar pedido");
            System.out.println("4. Ver mis pedidos");
        }
        System.out.println("5. Buscar recopilaciones");
        System.out.println("6. Buscar por género");
        if ("admin".equals(usuarioLogueado.getRol())) {
            System.out.println("7. Agregar nuevo MP3");
            System.out.println("8. Actualizar saldo usuario");
            System.out.println("9. Listar proveedores");
        }
        System.out.println("10. Información del sistema");
        System.out.println("11. Cerrar sesión");
        System.out.println("0. Salir del sistema");
        System.out.print("Selecciona una opción: ");
    }
    
    private static void mostrarInfoSistema() {
        System.out.println("\n--- INFORMACIÓN DEL SISTEMA ---");
        System.out.println("🔄 Modo: Sin conexión a base de datos");
        System.out.println("💾 Datos: Almacenados en memoria");
        System.out.println("📊 Usuarios precargados:");
        System.out.println("   - juan@email.com / 123 (usuario)");
        System.out.println("   - maria@email.com / 123 (proveedor)");
        System.out.println("   - admin@email.com / admin (admin)");
        System.out.println("🎵 MP3s precargados: 4 canciones");
        System.out.println("💿 Vinilos precargados: 3 discos");
    }
    
    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}