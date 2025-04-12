package gestores;

import modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario){
        this.usuarios.add(usuario);
        System.out.println("Usuario: " + usuario.getNombre() + "agregado correctamente.");
    }
    public void eliminarUsuario(String id){
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
        System.out.println("Usuario numero: " + id + "eliminado correctamente.");
    }
    public Usuario buscarUsuario(String id){
        for(Usuario usuario : usuarios){
            if(usuario.getId().equals(id)){
                return usuario;
            }
        }
        System.out.println("Usuario numero: " + id + "no encontrado.");
        return null;
    }
}
