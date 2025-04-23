package gestores;

import models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import services.NotificacionesService;

public class GestorReservas {
    private PriorityBlockingQueue<Reserva> colaReservas = new PriorityBlockingQueue<>();

    private GestorNotificaciones gestorNotificaciones;
    private NotificacionesService servicioNotificaciones;

    public GestorReservas(GestorNotificaciones gestorNotificaciones, NotificacionesService servicioNotificaciones) {
        this.gestorNotificaciones = gestorNotificaciones;
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public synchronized void agregarReserva(Reserva reserva) {
        colaReservas.offer(reserva);
    }

    public synchronized void realizarReserva(Usuario usuario, RecursoDigital recurso, LocalDate fechaReserva) {
        System.out.println("[" + Thread.currentThread().getName() + "] Realizando reserva para: " + recurso.getTitulo());
        Reserva nuevaReserva = new Reserva(usuario, recurso, fechaReserva);
        agregarReserva(nuevaReserva);
        System.out.println("[" + Thread.currentThread().getName() + "] Reserva agregada a la cola.");
    }

    public synchronized Reserva obtenerProximaReserva(RecursoDigital recurso) {
        for (Reserva r : colaReservas) {
            if (r.getRecursoDigital().equals(recurso)) {
                colaReservas.remove(r);
                return r;
            }
        }
        return null;
    }

    public synchronized void asignarReservaSiExiste(RecursoDigital recurso) {
        for (Reserva r : colaReservas) {
            if (r.getRecursoDigital().equals(recurso)) {
                String destino = r.getUsuario().getEmail();
                String mensaje = "Hola " + r.getUsuario().getNombre() +
                        ", el recurso '" + recurso.getTitulo() +
                        "' que reservaste ya está disponible. ¡Podés hacer el préstamo cuando quieras!";
                gestorNotificaciones.enviar(servicioNotificaciones, destino, mensaje);
                break;
            }
        }
    }

    public synchronized List<Reserva> obtenerReservasPendientes() {
        return new ArrayList<>(colaReservas);
    }

    public synchronized void eliminarReserva(Reserva reserva) {
        if (colaReservas.remove(reserva)) {
            System.out.println("Reserva eliminada correctamente.");
        } else {
            System.out.println("No se encontró la reserva para eliminar.");
        }
    }

}
