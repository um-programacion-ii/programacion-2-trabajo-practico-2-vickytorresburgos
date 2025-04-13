package modelos;

import java.util.UUID;

public class Usuario {
    private String nombre;
    private final String Id;
    private String email;

    public Usuario(String nombre,String email) {
        if (nombre == null){
            throw new IllegalArgumentException("Error. Debe ingresar un nombre");
        }
        if (email == null){
            throw new IllegalArgumentException("Error. Debe ingresar una dirección de mail");
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
        if (nombre == null){
            throw new IllegalArgumentException("Error. Debe ingresar un nombre");
        }
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("Error. Debe ingresar una dirección de mail");
        }
        this.email = email;
    }

    public String getInformacion() {
        return "Informacion del usuario: " + this.getId() + "\n" +
                "Nombre: " + this.getNombre() + "\n" +
                "Email:" + this.getEmail();
    }
}

