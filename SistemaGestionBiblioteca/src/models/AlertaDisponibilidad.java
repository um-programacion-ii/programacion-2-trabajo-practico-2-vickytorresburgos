package models;

import java.util.*;

public class AlertaDisponibilidad {
    private List<Reserva> reservas;

    public AlertaDisponibilidad(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Reserva> obtenerReservasDisponibles() {
        List<Reserva> disponibles = new ArrayList<>();
        for (Reserva reserva : reservas) {
            RecursoDigital recurso = reserva.getRecursoDigital();
            if (recurso.estaDisponible()) {
                disponibles.add(reserva);
            }
        }
        return disponibles;
    }
}
