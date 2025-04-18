package interfaces;

public interface Prestable {
    void prestar();  // cambiar estado
    void devolver(); // cambiar estado
    boolean disponible(); // consultar estado
}
