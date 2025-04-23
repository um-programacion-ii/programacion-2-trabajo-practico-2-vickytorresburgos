package gestores;

import services.NotificacionesService;
import java.util.concurrent.*;

public class GestorNotificaciones {
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void enviar(NotificacionesService servicio, String destino, String mensaje) {
        Future<?> future = executor.submit(() -> servicio.enviarNotificacion(destino, mensaje));
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al enviar notificación: " + e.getMessage());
        }
    }

    public void cerrar() {
        executor.shutdown();
    }
}
