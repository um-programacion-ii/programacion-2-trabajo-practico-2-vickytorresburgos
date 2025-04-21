package gestores;

import services.NotificacionesService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GestorNotificaciones {
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void enviar(NotificacionesService servicio, String destino, String mensaje) {
        executor.submit(() -> servicio.enviarNotificacion(destino, mensaje));
    }

    public void cerrar() {
        executor.shutdown();
    }
}
