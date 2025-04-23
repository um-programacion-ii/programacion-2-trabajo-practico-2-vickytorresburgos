package services;

public class NotificacionesServiceSMS implements NotificacionesService{
    @Override
    public void enviarNotificacion(String destino, String mensaje) {
        System.out.println("[SMS] enviado a " + destino + ": " + mensaje);
    }
}
