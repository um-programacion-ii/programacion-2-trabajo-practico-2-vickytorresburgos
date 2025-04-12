import java.util.UUID;

public class Usuario {
    private String nombre;
    private final String Id;
    private String email;

    public Usuario(String nombre,String email) {
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
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

