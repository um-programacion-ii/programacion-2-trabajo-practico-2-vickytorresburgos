package models;

import enums.NivelAlerta;
import gestores.GestorNotificaciones;
import services.NotificacionesService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RecordatorioAutomatico {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final GestorNotificaciones gestorNotificaciones;

    public RecordatorioAutomatico(GestorNotificaciones gestorNotificaciones) {
        this.gestorNotificaciones = gestorNotificaciones;
    }

    public void iniciarRecordatorios() {
        Runnable tarea = () -> {
            String mensaje = "Recordatorio: Navega por los recursos y hace tu préstamo!.";
            gestorNotificaciones.enviar(NivelAlerta.INFO, mensaje);
        };
        scheduler.scheduleAtFixedRate(tarea, 10, 30, TimeUnit.SECONDS);
    }

    public void detener() {
        scheduler.shutdown();
    }
}
