package exceptions;

public class RecursoNoDisponibleException extends RuntimeException {
    public RecursoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}