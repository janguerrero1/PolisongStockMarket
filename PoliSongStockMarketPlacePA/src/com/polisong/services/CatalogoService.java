package com.polisong.services;

import com.polisong.dao.ViniloDAO;
import com.polisong.dao.MP3DAO;
import java.util.Scanner;

public class CatalogoService {
    private static Scanner scanner = new Scanner(System.in);
    private ViniloDAO viniloDAO = new ViniloDAO();
    private MP3DAO mp3DAO = new MP3DAO();
    
    public void mostrarCatalogoCompleto() {
        System.out.println("\n--- CATÁLOGO COMPLETO ---");
        
        System.out.println("\n🎵 CANCIONES MP3 DISPONIBLES:");
        mp3DAO.obtenerMP3s();
        
        System.out.println("\n💿 VINILOS DISPONIBLES:");
        viniloDAO.obtenerVinilos();
        
        System.out.println("\n¿Desea buscar una canción específica? (s/n)");
        String respuesta = scanner.nextLine();
        if ("s".equalsIgnoreCase(respuesta)) {
            buscarCancionEspecifica();
        }
    }
    
    private void buscarCancionEspecifica() {
        System.out.print("Ingrese el nombre de la canción a buscar: ");
        String nombreCancion = scanner.nextLine();
        
        System.out.println("\n🔍 Resultados en MP3:");
        mp3DAO.buscarMP3PorNombre(nombreCancion);
        
        System.out.println("\n🔍 Resultados en Vinilos:");
        viniloDAO.buscarVinilosPorCancion(nombreCancion);
    }
    
    public void buscarRecopilaciones() {
        System.out.println("\n--- RECOPILACIONES ---");
        System.out.println("1. Ver mis recopilaciones");
        System.out.println("2. Buscar recopilaciones públicas");
        System.out.println("3. Crear nueva recopilación");
        System.out.print("Seleccione: ");
        
        int opcion = Integer.parseInt(scanner.nextLine());
        
        switch (opcion) {
            case 1:
                System.out.println("📚 Mis recopilaciones (próximamente)");
                break;
            case 2:
                System.out.println("🌐 Recopilaciones públicas (próximamente)");
                break;
            case 3:
                System.out.println("🆕 Crear recopilación (próximamente)");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
    
    // MÉTODO AGREGADO: buscarPorGenero
    public void buscarPorGenero() {
        System.out.println("\n--- BÚSQUEDA POR GÉNERO ---");
        System.out.print("Ingrese el género a buscar: ");
        String genero = scanner.nextLine();
        
        System.out.println("\n🎵 MP3s del género '" + genero + "':");
        mp3DAO.obtenerMP3sPorGenero(genero);
    }
    
    // MÉTODO AGREGADO: agregarNuevoMP3
    public void agregarNuevoMP3() {
        System.out.println("\n--- AGREGAR NUEVO MP3 ---");
        
        System.out.print("Nombre de la canción: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Duración (formato HH:MM:SS): ");
        String duracion = scanner.nextLine();
        
        System.out.print("Tamaño en MB: ");
        float tamano = Float.parseFloat(scanner.nextLine());
        
        System.out.print("Calidad (Kbps): ");
        int calidad = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        
        System.out.print("Precio: ");
        float precio = Float.parseFloat(scanner.nextLine());
        
        com.polisong.models.MP3 mp3 = new com.polisong.models.MP3(nombre, duracion, tamano, calidad, genero, precio);
        boolean exito = mp3DAO.insertarMP3(mp3);
        
        if (exito) {
            System.out.println("✅ MP3 agregado exitosamente");
        } else {
            System.out.println("❌ Error al agregar MP3");
        }
    }
    
    // MÉTODO ADICIONAL: buscarVinilosPorArtista
    public void buscarVinilosPorArtista() {
        System.out.println("\n--- BÚSQUEDA DE VINILOS POR ARTISTA ---");
        System.out.print("Ingrese el nombre del artista: ");
        String artista = scanner.nextLine();
        
        System.out.println("\n🔍 Vinilos de '" + artista + "':");
        // Esta funcionalidad buscaría vinilos por artista
        System.out.println("(Funcionalidad en desarrollo)");
    }
}