package models;

public class Libro extends RecursoDigital{
    private String genero;

    public Libro(String autor, String titulo, String genero) {
        super(autor, titulo);
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
