package models;


public abstract class RecursoDigital {
    private String autor;
    private String titulo;
    private boolean disponible = true;

    public RecursoDigital(String autor, String titulo) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede ser nulo.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo.");
        }
        this.autor = autor;
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede ser nulo.");
        }
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo.");
        }
        this.titulo = titulo;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public String estadoDisponible() {
        return disponible ? "Disponible" : "No Disponible";
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void mostrarInformacion() {
        System.out.println(" - Título: " + getTitulo());
        System.out.println(" - Autor: " + getAutor());
        System.out.println(" - Tipo: " + this.getClass().getSimpleName());
        System.out.println(" - Estado: " + estadoDisponible());
    }
}
