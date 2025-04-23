package models;

import java.util.ArrayList;
import java.util.List;

public class HistorialAlertas {
    private static final List<String> historial = new ArrayList<>();

    public static void registrar(String mensaje) {
        historial.add(mensaje);
    }

    public static void mostrarHistorial() {
        for (String m : historial) {
            System.out.println(m);
        }
    }

    public static void limpiar() {
        historial.clear();
    }
}
