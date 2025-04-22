package models;

import java.time.LocalDate;
import java.util.*;

public class AlertaVencimiento {
    private List<Prestamo> prestamos;

    public AlertaVencimiento(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public List<Prestamo> obtenerAlertasDeVencimiento() {
        LocalDate hoy = LocalDate.now();
        List<Prestamo> alertas = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getRecursoDigital().getEstado() == EstadoRecurso.PRESTADO) {
                LocalDate fechaDevolucion = prestamo.getFechaFin();
                if (fechaDevolucion.equals(hoy) || fechaDevolucion.equals(hoy.plusDays(1))) {
                    alertas.add(prestamo);
                }
            }
        }
        return alertas;
    }
}
