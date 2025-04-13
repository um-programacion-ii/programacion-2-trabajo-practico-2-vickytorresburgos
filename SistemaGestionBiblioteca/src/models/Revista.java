package models;

public class Revista extends RecursoDigital{
    public String categoria;

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
}
