package com.polisong.dao;

import com.polisong.models.MP3;
import java.util.ArrayList;
import java.util.List;

public class MP3DAO {
    private static List<MP3> mp3s = new ArrayList<>();
    private static int nextId = 1;
    
    static {
        mp3s.add(new MP3("Billie Jean", "00:04:54", 8.5f, 320, "Pop", 1.99f));
        mp3s.add(new MP3("Bohemian Rhapsody", "00:05:55", 9.8f, 320, "Rock", 2.49f));
        mp3s.add(new MP3("Shape of You", "00:03:53", 7.2f, 256, "Pop", 1.79f));
        mp3s.add(new MP3("Sweet Child O' Mine", "00:05:03", 8.9f, 320, "Rock", 2.29f));
        
        for (int i = 0; i < mp3s.size(); i++) {
            mp3s.get(i).setIdMp3(i + 1);
        }
        nextId = mp3s.size() + 1;
    }
    
    public boolean insertarMP3(MP3 mp3) {
        try {
            mp3.setIdMp3(nextId++);
            mp3s.add(mp3);
            System.out.println("✅ MP3 agregado: " + mp3.getNombre());
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al insertar MP3: " + e.getMessage());
            return false;
        }
    }
    
    public void obtenerMP3s() {
        System.out.println("\n--- LISTA DE CANCIONES MP3 ---");
        if (mp3s.isEmpty()) {
            System.out.println("No hay canciones MP3 disponibles");
        } else {
            for (MP3 mp3 : mp3s) {
                System.out.println(mp3);
            }
        }
    }
    
    public void buscarMP3PorNombre(String nombre) {
        System.out.println("\n🔍 Resultados de búsqueda para '" + nombre + "':");
        boolean encontrados = false;
        for (MP3 mp3 : mp3s) {
            if (mp3.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                System.out.println(mp3);
                encontrados = true;
            }
        }
        if (!encontrados) {
            System.out.println("No se encontraron canciones con ese nombre");
        }
    }
    
    public void obtenerMP3sPorGenero(String genero) {
        System.out.println("\n🎵 MP3s del género '" + genero + "':");
        boolean encontrados = false;
        for (MP3 mp3 : mp3s) {
            if (mp3.getGenero().equalsIgnoreCase(genero)) {
                System.out.println(mp3);
                encontrados = true;
            }
        }
        if (!encontrados) {
            System.out.println("No hay canciones de ese género");
        }
    }
    
    public boolean actualizarPrecioMP3(int idMp3, float nuevoPrecio) {
        for (MP3 mp3 : mp3s) {
            if (mp3.getIdMp3() == idMp3) {
                mp3.setPrecio(nuevoPrecio);
                System.out.println("✅ Precio actualizado para: " + mp3.getNombre());
                return true;
            }
        }
        System.out.println("❌ MP3 no encontrado");
        return false;
    }
    
    public MP3 buscarMP3PorId(int idMp3) {
        for (MP3 mp3 : mp3s) {
            if (mp3.getIdMp3() == idMp3) {
                return mp3;
            }
        }
        return null;
    }
    
    public List<MP3> obtenerMP3sPorCalidad(int calidadMinima) {
        List<MP3> resultados = new ArrayList<>();
        for (MP3 mp3 : mp3s) {
            if (mp3.getCalidad() >= calidadMinima) {
                resultados.add(mp3);
            }
        }
        return resultados;
    }
    
    public boolean eliminarMP3(int idMp3) {
        for (int i = 0; i < mp3s.size(); i++) {
            if (mp3s.get(i).getIdMp3() == idMp3) {
                mp3s.remove(i);
                System.out.println("✅ MP3 eliminado");
                return true;
            }
        }
        System.out.println("❌ MP3 no encontrado");
        return false;
    }
    
    public List<MP3> obtenerMP3sPorPrecio(float precioMaximo) {
        List<MP3> resultados = new ArrayList<>();
        for (MP3 mp3 : mp3s) {
            if (mp3.getPrecio() <= precioMaximo) {
                resultados.add(mp3);
            }
        }
        return resultados;
    }
}
