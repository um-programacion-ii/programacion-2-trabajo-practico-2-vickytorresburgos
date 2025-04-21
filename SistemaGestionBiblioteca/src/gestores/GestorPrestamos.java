package gestores;

import exceptions.RecursoNoDisponibleException;
import interfaces.Prestable;
import models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorPrestamos {
    private List<Prestamo> prestamos = new ArrayList<>();
    private GestorReservas gestorReservas;

    public GestorPrestamos(GestorReservas gestorReservas) {
        this.gestorReservas = gestorReservas;
        this.prestamos = new ArrayList<>();
    }

    public void prestarRecurso(Usuario usuario, RecursoDigital recursoDigital, LocalDate fechaInicio, LocalDate fechaFin) {
        if (!recursoDigital.estaDisponible()) {
            throw new RecursoNoDisponibleException("El recurso no está disponible.");
        }
        if (!(recursoDigital instanceof Prestable)) {
            throw new RecursoNoDisponibleException("El recurso no es prestable.");
        }

        ((Prestable) recursoDigital).prestar();
        recursoDigital.setEstado(EstadoRecurso.PRESTADO);

        Prestamo prestamo = new Prestamo(usuario, recursoDigital, fechaInicio, fechaFin);
        prestamos.add(prestamo);
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

        prestamos.remove(prestamoActivo);
        recurso.setEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("Recurso devuelto correctamente.");

        if (gestorReservas != null) {
            gestorReservas.asignarReservaSiExiste(recurso, prestamos);
        }
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
}
