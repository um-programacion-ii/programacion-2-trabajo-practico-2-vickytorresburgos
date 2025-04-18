package gestores;

import models.Usuario;
import services.NotificacionesService;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private final Map<String, Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new HashMap<>();
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getId())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese ID.");
        }
        usuarios.put(usuario.getId(), usuario);
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

    public void eliminarUsuario(String id) {
        if (!usuarios.containsKey(id)) {
            throw new IllegalArgumentException("No se encontró un usuario con ese ID.");
        }
        usuarios.remove(id);
    }

    public Usuario buscarUsuario(String id) {
        return usuarios.get(id); // Devuelve null si no existe, igual que antes
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario usuario : usuarios.values()) {
                System.out.println("ID: " + usuario.getId());
                System.out.println("Nombre: " + usuario.getNombre());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("Teléfono: " + usuario.getTelefono());
                System.out.println("----------------------");
            }
        }
    }
}
