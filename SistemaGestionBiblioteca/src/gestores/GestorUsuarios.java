package gestores;

import models.Usuario;
import services.NotificacionesService;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private final List<Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario){
        this.usuarios.add(usuario);
    }

    public void registrarUsuario(String nombre, String email, String telefono, String preferenciaNotificacion, NotificacionesService emailService, NotificacionesService smsService){
        Usuario nuevoUsuario = new Usuario(nombre, email, telefono);
        String mensaje = "Bienvenido/a " + nombre + " a la biblioteca digital.";
        if (preferenciaNotificacion.equals("sms") && !telefono.isEmpty()) {
            smsService.enviarNotificacion(telefono, mensaje);
        } else {
            emailService.enviarNotificacion(email, mensaje);
        }
        this.agregarUsuario(nuevoUsuario);
    }

    public void eliminarUsuario(String id){
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public Usuario buscarUsuario(String id){
        for(Usuario usuario : usuarios){
            if(usuario.getId().equals(id)){
                return usuario;
            }
        }
        return null;
    }
}
