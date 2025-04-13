package models;

public class Audiolibro extends RecursoDigital{
    public int duracion;

    public Audiolibro(String autor, String titulo, int duracion) {
        super(autor, titulo);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
