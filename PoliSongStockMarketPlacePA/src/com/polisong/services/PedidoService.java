package com.polisong.services;

import com.polisong.dao.PedidoDAO;
import com.polisong.dao.UsuarioDAO;
import java.util.Scanner;

public class PedidoService {
    private static Scanner scanner = new Scanner(System.in);
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public void realizarPedido(int idUsuario) {
        System.out.println("\n--- REALIZAR PEDIDO ---");
        System.out.println("Seleccione el tipo de pedido:");
        System.out.println("1. Pedido de MP3");
        System.out.println("2. Pedido de Vinilo");
        System.out.println("3. Pedido mixto");
        System.out.print("Seleccione: ");
        
        int tipoPedido = Integer.parseInt(scanner.nextLine());
        
        System.out.print("ID del pago (simulado): ");
        int idPago = Integer.parseInt(scanner.nextLine());
        
        boolean exito = pedidoDAO.crearPedido(idUsuario, idPago, "pendiente");
        
        if (exito) {
            System.out.println("✅ Pedido realizado exitosamente");
            
            // QUITADO: Actualización automática de saldo
            // System.out.print("¿Desea actualizar el saldo del usuario? (s/n): ");
            // String respuesta = scanner.nextLine();
            // if ("s".equalsIgnoreCase(respuesta)) {
            //     System.out.print("Nuevo saldo: ");
            //     float nuevoSaldo = Float.parseFloat(scanner.nextLine());
            //     usuarioDAO.actualizarSaldo(idUsuario, nuevoSaldo);
            // }
        } else {
            System.out.println("❌ Error al realizar pedido");
        }
    }
    
    public void verMisPedidos(int idUsuario) {
        System.out.println("\n--- MIS PEDIDOS ---");
        pedidoDAO.obtenerPedidosPorUsuario(idUsuario);
        
        System.out.println("\nOpciones:");
        System.out.println("1. Marcar pedido como recibido");
        System.out.println("2. Volver");
        System.out.print("Seleccione: ");
        
        int opcion = Integer.parseInt(scanner.nextLine());
        
        if (opcion == 1) {
            System.out.print("ID del pedido a marcar como recibido: ");
            int idPedido = Integer.parseInt(scanner.nextLine());
            
            boolean exito = pedidoDAO.registrarRecepcionPedido(idPedido);
            
            if (exito) {
                System.out.println("✅ Pedido marcado como recibido");
            } else {
                System.out.println("❌ Error al actualizar pedido");
            }
        }
    }
    
    public void gestionarPedidosProveedor(int idProveedor) {
        System.out.println("\n--- GESTIÓN DE PEDIDOS (PROVEEDOR) ---");
        pedidoDAO.obtenerPedidosPorProveedor(idProveedor);
        
        boolean volver = false;
        while (!volver) {
            System.out.println("\nOpciones:");
            System.out.println("1. Marcar pedido como enviado");
            System.out.println("2. Ver pedidos pendientes");
            System.out.println("3. Actualizar estado de pedido");
            System.out.println("4. Volver");
            System.out.print("Seleccione: ");
            
            int opcion = Integer.parseInt(scanner.nextLine());
            
            switch (opcion) {
                case 1:
                    marcarPedidoComoEnviado();
                    break;
                case 2:
                    pedidoDAO.obtenerPedidosPendientes();
                    break;
                case 3:
                    actualizarEstadoPedido();
                    break;
                case 4:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    
    private void marcarPedidoComoEnviado() {
        System.out.print("ID del pedido a marcar como enviado: ");
        int idPedido = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Fecha de envío (YYYY-MM-DD): ");
        String fechaEnvio = scanner.nextLine();
        
        boolean exito = pedidoDAO.registrarEnvioPedido(idPedido, fechaEnvio);
        
        if (exito) {
            System.out.println("✅ Pedido marcado como enviado");
        } else {
            System.out.println("❌ Error al actualizar pedido");
        }
    }
    
    private void actualizarEstadoPedido() {
        System.out.print("ID del pedido: ");
        int idPedido = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Estados disponibles: pendiente, confirmado, enviado, entregado, cancelado");
        System.out.print("Nuevo estado: ");
        String nuevoEstado = scanner.nextLine();
        
        boolean exito = pedidoDAO.actualizarEstadoPedido(idPedido, nuevoEstado);
        
        if (exito) {
            System.out.println("✅ Estado del pedido actualizado");
        } else {
            System.out.println("❌ Error al actualizar estado");
        }
    }
    
    public void verTodosLosPedidos() {
        System.out.println("\n--- TODOS LOS PEDIDOS ---");
        System.out.println("Esta funcionalidad mostraría todos los pedidos del sistema");
        System.out.println("(Requiere permisos de administrador)");
    }
}