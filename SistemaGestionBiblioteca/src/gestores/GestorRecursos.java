package gestores;
import interfaces.Prestable;
import interfaces.Renovable;
import models.*;
import java.util.ArrayList;
import java.util.List;


public class GestorRecursos {

    private final List<RecursoDigital> recursos;

    public List<RecursoDigital> getRecursos() {
        return this.recursos;
    }

    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    public void agregarRecurso(TipoRecurso tipoRecurso, String titulo, String autor, String detalle) {
        RecursoDigital recurso = switch (tipoRecurso) {
            case AUDIOLIBRO -> new Audiolibro(autor, titulo, detalle);
            case ENSAYO -> new Ensayo(autor, titulo, detalle);
            case LIBRO -> new Libro(autor, titulo, detalle);
            case REVISTA -> new Revista(autor, titulo, detalle);
        };

        this.recursos.add(recurso);
    }

    public void eliminarRecurso(String titulo){
        recursos.removeIf(recurso -> recurso.getTitulo().equals(titulo));
    }

    public RecursoDigital buscarRecurso(String titulo, String autor){
        for (RecursoDigital recurso : recursos){
            if (recurso.getTitulo().equalsIgnoreCase(titulo) && recurso.getAutor().equalsIgnoreCase(autor)){
                return recurso;
            }
        }
        return null;
    }

    public List<RecursoDigital> mostrarRecursos() {
        return recursos;
    }

    public void prestarRecurso(RecursoDigital recurso) {
        if (!(recurso instanceof Prestable)) {
            throw new RecursoNoDisponibleException("El recurso no es prestable.");
        }
        if (!recurso.estaDisponible()) {
            throw new RecursoNoDisponibleException("El recurso no está disponible para préstamo.");
        }
        ((Prestable) recurso).prestar();
        recurso.setDisponible(false);
    }


    public void devolverRecurso(RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (!(recurso instanceof Prestable)) {
            throw new RecursoNoDisponibleException("El recurso no es devolvible porque no es prestable.");
        }
        if (recurso.estaDisponible()) {
            throw new RecursoNoDisponibleException("El recurso ya está disponible, no se puede devolver.");
        }
        ((Prestable) recurso).devolver();
        recurso.setDisponible(true);
    }


    public void renovarRecurso(RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (!(recurso instanceof Renovable)) {
            throw new RecursoNoDisponibleException("El recurso no es renovable.");
        }
        ((Renovable) recurso).renovar();
    }
}
