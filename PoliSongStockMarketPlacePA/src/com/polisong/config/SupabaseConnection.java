package com.polisong.config;

public class SupabaseConnection {
    // Esta clase ahora solo simula la conexión
    public static boolean isConnected() {
        return true;
    }
    
    public static String getConnectionStatus() {
        return "✅ Modo sin conexión - Datos en memoria";
    }
}