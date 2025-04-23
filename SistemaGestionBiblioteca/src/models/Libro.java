package models;

import enums.TipoRecurso;
import interfaces.Prestable;
import interfaces.Renovable;

public class Libro extends RecursoDigital implements Prestable, Renovable {
    private String genero;
    private boolean disponible = true;
    private int renovaciones = 0;

    public Libro(String autor, String titulo, String genero) {
        super(autor, titulo);
        this.genero = genero;
        this.setTipoRecurso(TipoRecurso.LIBRO);
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println(" - Género: " + getGenero());
    }


    @Override
    public void prestar() {
        this.disponible = false; // cambia estado
    }

    @Override
    public void devolver() {
        this.disponible = true; // cambia estado
    }

    @Override
    public boolean disponible() {
        return this.disponible; // consulta estado
    }

    @Override
    public int getCantidadRenovaciones() {
        return renovaciones;
    }

    @Override
    public void renovar() { // agrega una renovacion
        if (!disponible) {
            renovaciones++;
        }
    }
}
