package models;

public class Audiolibro extends RecursoDigital{
    public String formato;

    public Audiolibro(String autor, String titulo, String formato) {
        super(autor, titulo);
        this.formato = formato;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Formato: " + getFormato());
    }
}
