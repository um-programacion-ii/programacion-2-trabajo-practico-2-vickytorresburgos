package models;

public class Ensayo extends RecursoDigital{
    private String tema;

    public Ensayo(String autor, String titulo, String tema) {
        super(autor, titulo);
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
