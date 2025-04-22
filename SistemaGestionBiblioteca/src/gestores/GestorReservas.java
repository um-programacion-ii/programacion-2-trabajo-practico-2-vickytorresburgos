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

    public synchronized void realizarReserva(Usuario usuario, RecursoDigital recurso) {
        System.out.println("[" + Thread.currentThread().getName() + "] Realizando reserva para: " + recurso.getTitulo());
        Reserva nuevaReserva = new Reserva(usuario, recurso, LocalDate.now());
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

    public synchronized void asignarReservaSiExiste(RecursoDigital recurso, List<Prestamo> prestamos) {
        Reserva proximaReserva = obtenerProximaReserva(recurso);

        if (proximaReserva != null) {
            System.out.println("[" + Thread.currentThread().getName() + "] Asignando reserva si existe para: " + recurso.getTitulo());
            System.out.println("[" + Thread.currentThread().getName() + "] Recurso asignado automáticamente a " + proximaReserva.getUsuario().getNombre());
            System.out.println("[" + Thread.currentThread().getName() + "] Préstamo automático desde reserva registrado.");
            String destino = proximaReserva.getUsuario().getEmail();
            String mensaje = "Tu reserva del recurso: " + recurso.getTitulo() + " ya esta disponible";
            gestorNotificaciones.enviar(servicioNotificaciones, destino, mensaje);

            recurso.setEstado(EstadoRecurso.PRESTADO);
            System.out.println("El recurso fue asignado automáticamente a: ");
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

    public synchronized List<Reserva> obtenerReservasPendientes() {
        return new ArrayList<>(colaReservas);
    }
}
