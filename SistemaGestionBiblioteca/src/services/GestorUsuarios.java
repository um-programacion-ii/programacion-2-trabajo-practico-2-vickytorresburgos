package services;

import models.Usuario;

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
