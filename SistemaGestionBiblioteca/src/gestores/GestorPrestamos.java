package gestores;

import enums.EstadoRecurso;
import exceptions.RecursoNoDisponibleException;
import interfaces.Prestable;
import models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorPrestamos {
    private List<Prestamo> prestamos = new ArrayList<>();
    private final GestorReservas gestorReservas;

    public GestorPrestamos(GestorReservas gestorReservas) {
        this.gestorReservas = gestorReservas;
        this.prestamos = new ArrayList<>();
    }

    public synchronized void prestarRecurso(Usuario usuario, RecursoDigital recursoDigital, LocalDate fechaInicio, LocalDate fechaFin) {
        System.out.println("[" + Thread.currentThread().getName() + "] Intentando prestar: " + recursoDigital.getTitulo());

        if (!recursoDigital.estaDisponible()) {
            System.out.println("[" + Thread.currentThread().getName() + "] Recurso no disponible.");
            throw new RecursoNoDisponibleException("El recurso no está disponible.");
        }

        if (!(recursoDigital instanceof Prestable)) {
            System.out.println("[" + Thread.currentThread().getName() + "] Recurso no es prestable.");
            throw new RecursoNoDisponibleException("El recurso no es prestable.");
        }

        ((Prestable) recursoDigital).prestar();
        recursoDigital.setEstado(EstadoRecurso.PRESTADO);

        Prestamo prestamo = new Prestamo(usuario, recursoDigital, fechaInicio, fechaFin);
        prestamos.add(prestamo);
        System.out.println("[" + Thread.currentThread().getName() + "] Préstamo completado.");
    }


    public void devolverRecurso(RecursoDigital recurso, String idUsuario) throws RecursoNoDisponibleException {
        validarEstadoPrestado(recurso);

        Prestamo prestamoActivo = obtenerPrestamoActivo(recurso);
        if (prestamoActivo == null) {
            System.out.println("No se encontró un préstamo activo para este recurso.");
            return;
        }
        if (!prestamoActivo.getUsuario().getId().equals(idUsuario)) {
            System.out.println("Solo el usuario que tomó el préstamo puede devolver este recurso.");
            return;
        }
        recurso.setEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("Recurso devuelto correctamente.");
        gestorReservas.asignarReservaSiExiste(recurso);
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    private void validarEstadoPrestado(RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (recurso.getEstado() != EstadoRecurso.PRESTADO) {
            throw new RecursoNoDisponibleException("El recurso no está actualmente prestado.");
        }
    }


    private Prestamo obtenerPrestamoActivo(RecursoDigital recurso) {
        for (Prestamo p : prestamos) {
            if (p.getRecursoDigital().equals(recurso)) {
                return p;
            }
        }
        return null;
    }

    public List<Prestamo> mostrarPrestamos() {
        return prestamos;
    }

    public boolean renovarPrestamoRecurso(Prestamo prestamo) {
        if (prestamo != null && prestamo.getRecursoDigital().getEstado() == EstadoRecurso.PRESTADO) {
            LocalDate nuevaFechaFin = LocalDate.now().plusDays(7);
            prestamo.setFechaFin(nuevaFechaFin);
            return true;
        }
        return false;
    }
}
