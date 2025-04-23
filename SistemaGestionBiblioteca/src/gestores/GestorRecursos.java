package gestores;
import enums.TipoRecurso;
import exceptions.RecursoNoDisponibleException;
import exceptions.RecursoNoEncontradoException;
import interfaces.Renovable;
import models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GestorRecursos {

    private final List<RecursoDigital> recursos = new ArrayList<>();

    public GestorRecursos() {
    }

    public List<RecursoDigital> getRecursos() {
        return this.recursos;
    }

    public List<RecursoDigital> filtrarPorTipo(TipoRecurso tipoRecurso) {
        return recursos.stream()
                .filter(recurso -> recurso.getClass().getSimpleName().equalsIgnoreCase(tipoRecurso.name()))
                .collect(Collectors.toList());
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

    public List<RecursoDigital> buscarPorTituloYAutor(String titulo, String autor) throws RecursoNoEncontradoException {
        List<RecursoDigital> resultados = new ArrayList<>();
        for (RecursoDigital recurso : recursos) {
            boolean coincideTitulo = titulo == null || titulo.isBlank() || recurso.getTitulo().toLowerCase().contains(titulo.toLowerCase());
            boolean coincideAutor = autor == null || autor.isBlank() || recurso.getAutor().toLowerCase().contains(autor.toLowerCase());
            if (coincideTitulo && coincideAutor) {
                resultados.add(recurso);
            }
        }
        if (resultados.isEmpty()) {
            throw new RecursoNoEncontradoException("No se encontraron recursos con ese título y autor.");
        }
        return resultados;
    }

    public List<RecursoDigital> mostrarRecursos() {
        return recursos;
    }


    public void renovarRecurso(RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (!(recurso instanceof Renovable)) {
            throw new RecursoNoDisponibleException("El recurso no es renovable.");
        }
        ((Renovable) recurso).renovar();
    }
}
