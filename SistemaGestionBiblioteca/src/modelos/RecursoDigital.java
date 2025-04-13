package modelos;

public abstract class RecursoDigital {
    private String autor;
    private String titulo;

    public RecursoDigital(String autor, String titulo) {
        if (autor == null){
            throw new IllegalArgumentException("Error. Debe ingresar un autor");
        }
        if (titulo == null){
            throw new IllegalArgumentException("Error. Debe ingresar un titulo");
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
        if (autor == null){
            throw new IllegalArgumentException("Error. Debe ingresar un autor");
        }
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        if (titulo == null){
            throw new IllegalArgumentException("Error. Debe ingresar un titulo");
        }
        this.titulo = titulo;
    }
}
