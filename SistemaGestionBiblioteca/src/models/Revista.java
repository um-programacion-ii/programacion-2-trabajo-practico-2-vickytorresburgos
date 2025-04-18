package models;

import interfaces.Prestable;
import interfaces.Renovable;

public class Revista extends RecursoDigital implements Prestable, Renovable {
    public String categoria;
    private boolean disponible = true;
    private int renovaciones = 0;

    public Revista(String autor, String titulo, String categoria) {
        super(autor, titulo);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println(" - Categoría: " + getCategoria());
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
    public int getCantidadRenovaciones() {
        return renovaciones;
    }

    @Override
    public void renovar() {
        if (!disponible) {
            renovaciones++;
        }
    }


}
