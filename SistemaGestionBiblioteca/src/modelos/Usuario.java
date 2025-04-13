package modelos;

import java.util.UUID;

public class Usuario {
    private String nombre;
    private final String Id;
    private String email;

    public Usuario(String nombre,String email) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido. Debe contener '@'.");
        }
        this.nombre = nombre;
        this.Id = UUID.randomUUID().toString();
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo.");
        }
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido. Debe contener '@'.");
        }
        this.email = email;
    }

    public String getInformacion() {
        return "Informacion del usuario: " + this.getId() + "\n" +
                "Nombre: " + this.getNombre() + "\n" +
                "Email:" + this.getEmail();
    }
}

