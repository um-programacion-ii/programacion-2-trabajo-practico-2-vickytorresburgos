package gestores;

import exceptions.RecursoNoDisponibleException;
import interfaces.Prestable;
import models.EstadoRecurso;
import models.Prestamo;
import models.RecursoDigital;
import models.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorPrestamos {
    private final List<Prestamo> prestamos = new ArrayList<>();

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

    public void devolverRecurso(RecursoDigital recursoDigital) {
        recursoDigital.setEstado(EstadoRecurso.DISPONIBLE);
        prestamos.removeIf(prestamo -> prestamo.getRecursoDigital().equals(recursoDigital));
    }

    public void mostrarPrestamos() {
        if (prestamos.isEmpty()) {
            throw new RuntimeException();
        }
        else {
            for(Prestamo prestamo : prestamos) {
                System.out.println("- Usuario: " + prestamo.getUsuario());
                System.out.println("- Recurso: " + prestamo.getRecursoDigital().getTitulo());
                System.out.println("- Desde: " + prestamo.getFechaInicio());
                System.out.println("- Hasta: " + prestamo.getFechaFin());
            }
        }
    }
}
