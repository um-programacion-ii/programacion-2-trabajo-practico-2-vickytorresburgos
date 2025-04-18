package models;

import interfaces.Prestable;
import interfaces.Renovable;

public class Ensayo extends RecursoDigital implements Prestable, Renovable {
    private String tema;
    private boolean disponible = true;
    private int renovaciones = 0;

    public Ensayo(String autor, String titulo, String tema) {
        super(autor, titulo);
        this.tema = tema;
        this.setTipoRecurso(TipoRecurso.ENSAYO);
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println(" - Tema: " + getTema());
    }

    @Override
    public void prestar() {
        this.disponible = false;
    }

    @Override
    public void devolver() {
        this.disponible = true;
    }

    @Override
    public boolean disponible() {
        return this.disponible;
    }

    @Override
    public void renovar() {
        if (!disponible) {
            renovaciones++;
        }
    }

    @Override
    public int getCantidadRenovaciones() {
        return renovaciones;
    }
}
