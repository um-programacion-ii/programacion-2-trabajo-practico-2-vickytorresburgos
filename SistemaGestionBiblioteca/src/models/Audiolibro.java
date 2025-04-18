package models;

import interfaces.Prestable;

public class Audiolibro extends RecursoDigital implements Prestable {
    public String formato;
    private boolean disponible = true;

    public Audiolibro(String autor, String titulo, String formato) {
        super(autor, titulo);
        this.formato = formato;
        this.setTipoRecurso(TipoRecurso.AUDIOLIBRO);
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println(" - Formato: " + getFormato());
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
}
