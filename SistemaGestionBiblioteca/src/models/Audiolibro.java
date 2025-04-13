package models;

public class Audiolibro extends RecursoDigital{
    public String formato;

    public Audiolibro(String autor, String titulo, String duracion) {
        super(autor, titulo);
        this.formato = duracion;
    }

    public String getDuracion() {
        return formato;
    }

    public void setDuracion(String duracion) {
        this.formato = formato;
    }
}
