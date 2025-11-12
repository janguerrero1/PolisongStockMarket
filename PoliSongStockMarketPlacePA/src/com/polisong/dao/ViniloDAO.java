package com.polisong.dao;

import com.polisong.models.Vinilo;
import java.util.ArrayList;
import java.util.List;

public class ViniloDAO {
    private static List<Vinilo> vinilos = new ArrayList<>();
    private static int nextId = 1;
    
    static {
        vinilos.add(new Vinilo(2, "Thriller", "Michael Jackson", 1982, 5, 49.99f));
        vinilos.add(new Vinilo(2, "The Dark Side of the Moon", "Pink Floyd", 1973, 3, 59.99f));
        vinilos.add(new Vinilo(2, "Abbey Road", "The Beatles", 1969, 2, 45.99f));
        
        for (int i = 0; i < vinilos.size(); i++) {
            vinilos.get(i).setIdVinilo(i + 1);
        }
        nextId = vinilos.size() + 1;
    }
    
    public boolean insertarVinilo(Vinilo vinilo) {
        try {
            vinilo.setIdVinilo(nextId++);
            vinilos.add(vinilo);
            System.out.println("✅ Vinilo agregado: " + vinilo.getNombre());
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al insertar vinilo: " + e.getMessage());
            return false;
        }
    }
    
    public void obtenerVinilos() {
        System.out.println("\n--- LISTA DE VINILOS ---");
        if (vinilos.isEmpty()) {
            System.out.println("No hay vinilos disponibles");
        } else {
            for (Vinilo vinilo : vinilos) {
                System.out.println(vinilo);
            }
        }
    }
    
    public void obtenerVinilosPorProveedor(int idProveedor) {
        System.out.println("\n--- MIS VINILOS ---");
        boolean encontrados = false;
        for (Vinilo vinilo : vinilos) {
            if (vinilo.getIdProveedor() == idProveedor) {
                System.out.println(vinilo);
                encontrados = true;
            }
        }
        if (!encontrados) {
            System.out.println("No tienes vinilos registrados");
        }
    }
    
    public boolean actualizarInventario(int idVinilo, int nuevoInventario) {
        for (Vinilo vinilo : vinilos) {
            if (vinilo.getIdVinilo() == idVinilo) {
                vinilo.setInventario(nuevoInventario);
                System.out.println("✅ Inventario actualizado para: " + vinilo.getNombre());
                return true;
            }
        }
        System.out.println("❌ Vinilo no encontrado");
        return false;
    }
    
    public void buscarVinilosPorCancion(String nombreCancion) {
        System.out.println("\n🔍 Vinilos que contienen '" + nombreCancion + "':");
        boolean encontrados = false;
        for (Vinilo vinilo : vinilos) {
            if (vinilo.getNombre().toLowerCase().contains(nombreCancion.toLowerCase())) {
                System.out.println(vinilo);
                encontrados = true;
            }
        }
        if (!encontrados) {
            System.out.println("No se encontraron vinilos con esa canción");
        }
    }
    
    public Vinilo buscarViniloPorId(int idVinilo) {
        for (Vinilo vinilo : vinilos) {
            if (vinilo.getIdVinilo() == idVinilo) {
                return vinilo;
            }
        }
        return null;
    }
    
    public List<Vinilo> buscarVinilosPorArtista(String artista) {
        List<Vinilo> resultados = new ArrayList<>();
        for (Vinilo vinilo : vinilos) {
            if (vinilo.getArtista().toLowerCase().contains(artista.toLowerCase())) {
                resultados.add(vinilo);
            }
        }
        return resultados;
    }
    
    public boolean eliminarVinilo(int idVinilo) {
        for (int i = 0; i < vinilos.size(); i++) {
            if (vinilos.get(i).getIdVinilo() == idVinilo) {
                vinilos.remove(i);
                System.out.println("✅ Vinilo eliminado");
                return true;
            }
        }
        System.out.println("❌ Vinilo no encontrado");
        return false;
    }
    
    public List<Vinilo> obtenerVinilosPorAnio(int anio) {
        List<Vinilo> resultados = new ArrayList<>();
        for (Vinilo vinilo : vinilos) {
            if (vinilo.getAnio() == anio) {
                resultados.add(vinilo);
            }
        }
        return resultados;
    }
}
