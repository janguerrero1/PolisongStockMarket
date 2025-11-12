package com.polisong.dao;

import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private static List<String> pedidos = new ArrayList<>();
    private static int nextId = 1;
    
    static {
        pedidos.add("Pedido{idPedido=1, idUsuario=1, estado='entregado', total=25.50, fecha='2024-01-15'}");
        pedidos.add("Pedido{idPedido=2, idUsuario=1, estado='enviado', total=59.99, fecha='2024-01-16'}");
        pedidos.add("Pedido{idPedido=3, idUsuario=2, estado='pendiente', total=15.75, fecha='2024-01-17'}");
    }
    
    public boolean crearPedido(int idUsuario, int idPago, String estado) {
        try {
            String pedido = "Pedido{idPedido=" + nextId + 
                           ", idUsuario=" + idUsuario + 
                           ", idPago=" + idPago +
                           ", estado='" + estado + 
                           "', fecha='" + java.time.LocalDate.now() + "'}";
            pedidos.add(pedido);
            nextId++;
            System.out.println("✅ Pedido creado: " + pedido);
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al crear pedido: " + e.getMessage());
            return false;
        }
    }
    
    public void obtenerPedidosPorUsuario(int idUsuario) {
        System.out.println("\n--- MIS PEDIDOS ---");
        boolean encontrados = false;
        for (String pedido : pedidos) {
            if (pedido.contains("idUsuario=" + idUsuario)) {
                System.out.println(pedido);
                encontrados = true;
            }
        }
        if (!encontrados) {
            System.out.println("No tienes pedidos registrados");
        }
    }
    
    public void obtenerPedidosPorProveedor(int idProveedor) {
        System.out.println("\n--- PEDIDOS DEL PROVEEDOR ---");
        System.out.println("Pedidos relacionados con el proveedor ID: " + idProveedor);
        for (String pedido : pedidos) {
            System.out.println(pedido);
        }
    }
    
    public boolean actualizarEstadoPedido(int idPedido, String nuevoEstado) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).contains("idPedido=" + idPedido)) {
                String pedidoActualizado = pedidos.get(i).replaceFirst("estado='[^']*'", "estado='" + nuevoEstado + "'");
                pedidos.set(i, pedidoActualizado);
                System.out.println("✅ Estado del pedido actualizado a: " + nuevoEstado);
                return true;
            }
        }
        System.out.println("❌ Pedido no encontrado");
        return false;
    }
    
    public boolean registrarEnvioPedido(int idPedido, String fechaEnvio) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).contains("idPedido=" + idPedido)) {
                String pedidoActualizado = pedidos.get(i).replaceFirst("estado='[^']*'", "estado='enviado'");
                pedidos.set(i, pedidoActualizado + ", fechaEnvio='" + fechaEnvio + "'");
                System.out.println("✅ Pedido marcado como enviado");
                return true;
            }
        }
        System.out.println("❌ Pedido no encontrado");
        return false;
    }
    
    public boolean registrarRecepcionPedido(int idPedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).contains("idPedido=" + idPedido)) {
                String pedidoActualizado = pedidos.get(i).replaceFirst("estado='[^']*'", "estado='entregado'");
                pedidos.set(i, pedidoActualizado + ", fechaRecepcion='" + java.time.LocalDate.now() + "'");
                System.out.println("✅ Pedido marcado como entregado");
                return true;
            }
        }
        System.out.println("❌ Pedido no encontrado");
        return false;
    }
    
    public void obtenerPedidosPendientes() {
        System.out.println("\n--- PEDIDOS PENDIENTES ---");
        boolean encontrados = false;
        for (String pedido : pedidos) {
            if (pedido.contains("estado='pendiente'")) {
                System.out.println(pedido);
                encontrados = true;
            }
        }
        if (!encontrados) {
            System.out.println("No hay pedidos pendientes");
        }
    }
    
    public List<String> obtenerPedidosPorEstado(String estado) {
        List<String> resultados = new ArrayList<>();
        for (String pedido : pedidos) {
            if (pedido.contains("estado='" + estado + "'")) {
                resultados.add(pedido);
            }
        }
        return resultados;
    }
    
    public boolean cancelarPedido(int idPedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).contains("idPedido=" + idPedido)) {
                String pedidoActualizado = pedidos.get(i).replaceFirst("estado='[^']*'", "estado='cancelado'");
                pedidos.set(i, pedidoActualizado);
                System.out.println("✅ Pedido cancelado");
                return true;
            }
        }
        System.out.println("❌ Pedido no encontrado");
        return false;
    }
    
    public int obtenerTotalPedidos() {
        return pedidos.size();
    }
    
    public int obtenerPedidosPorEstadoCount(String estado) {
        int count = 0;
        for (String pedido : pedidos) {
            if (pedido.contains("estado='" + estado + "'")) {
                count++;
            }
        }
        return count;
    }
    
    public String obtenerPedidoPorId(int idPedido) {
        for (String pedido : pedidos) {
            if (pedido.contains("idPedido=" + idPedido)) {
                return pedido;
            }
        }
        return null;
    }
}
