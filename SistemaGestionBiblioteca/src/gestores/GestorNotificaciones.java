package gestores;

import enums.NivelAlerta;
import models.HistorialAlertas;
import services.NotificacionesService;
import java.util.concurrent.*;

public class GestorNotificaciones {
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    private NivelAlerta nivelMinimo = NivelAlerta.INFO;

    private NotificacionesService servicioPorDefecto;
    private String destinoPorDefecto = "@todos";

    public GestorNotificaciones(NotificacionesService servicioPorDefecto) {
        this.servicioPorDefecto = servicioPorDefecto;
    }

    public void enviar(NivelAlerta nivel, String mensaje) {
        enviar(servicioPorDefecto, destinoPorDefecto, nivel, mensaje);
    }

    public void enviar(NotificacionesService servicio, String destino, NivelAlerta nivel, String mensaje) {
        if (nivel.ordinal() >= nivelMinimo.ordinal()) {
            String alertaFormat = "[" + nivel + "]" + mensaje;
            Future<?> future = executor.submit(() -> servicio.enviarNotificacion(destino, mensaje));
            try {
                future.get();
                HistorialAlertas.registrar(alertaFormat);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error al enviar notificacion: " + e.getMessage());}
        }
    }

    public void configurarNivelMinimo(NivelAlerta nuevoNivel) {
        this.nivelMinimo = nuevoNivel;
    }

    public void cerrar() {
        executor.shutdown();
    }
}
