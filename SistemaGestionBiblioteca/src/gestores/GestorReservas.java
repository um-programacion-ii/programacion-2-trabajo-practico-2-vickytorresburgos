package gestores;

import models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class GestorReservas {
    private PriorityBlockingQueue<Reserva> colaReservas = new PriorityBlockingQueue<>();

    public void agregarReserva(Reserva reserva) {
        colaReservas.offer(reserva);
    }

    public void realizarReserva(Usuario usuario, RecursoDigital recurso) {
        Reserva nuevaReserva = new Reserva(usuario, recurso, LocalDate.now());
        agregarReserva(nuevaReserva);
    }

    public Reserva obtenerProximaReserva(RecursoDigital recurso) {
        for (Reserva r : colaReservas) {
            if (r.getRecursoDigital().equals(recurso)) {
                colaReservas.remove(r);
                return r;
            }
        }
        return null;
    }

    public void asignarReservaSiExiste(RecursoDigital recurso, List<Prestamo> prestamos) {
        Reserva proximaReserva = obtenerProximaReserva(recurso);

        if (proximaReserva != null) {
            recurso.setEstado(EstadoRecurso.PRESTADO);
            System.out.println("El recurso fue asignado automáticamente a:");
            System.out.println("-> Usuario: " + proximaReserva.getUsuario().getNombre());
            System.out.println("Válido desde " + LocalDate.now() + " hasta " + LocalDate.now().plusDays(7));

            Prestamo nuevoPrestamo = new Prestamo(
                    proximaReserva.getUsuario(),
                    recurso,
                    LocalDate.now(),
                    LocalDate.now().plusDays(7)
            );
            prestamos.add(nuevoPrestamo);
            System.out.println("Préstamo automático registrado desde reserva.");
        }
    }


    public List<Reserva> obtenerReservasPendientes() {
        return new ArrayList<>(colaReservas);
    }
}
