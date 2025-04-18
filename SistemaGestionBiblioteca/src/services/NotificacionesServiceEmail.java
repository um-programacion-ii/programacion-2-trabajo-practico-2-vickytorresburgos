package services;

public class NotificacionesServiceEmail implements NotificacionesService{
    @Override
    public void enviarNotificacion(String destino, String mensaje) {
        System.out.println("[Email] enviado a " + destino + ": " + mensaje);
    }
}
