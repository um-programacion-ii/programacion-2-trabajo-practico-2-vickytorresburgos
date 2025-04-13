package gestores;

import modelos.RecursoDigital;

import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {
    private List<RecursoDigital> recursos;

    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        this.recursos.add(recurso);
        System.out.println("Recurso: " + recurso.getTitulo() + "agregado");
    }

    public void eliminarRecurso(String titulo){
        recursos.removeIf(recurso -> recurso.getTitulo().equals(titulo));
        System.out.println("Recurso: " + titulo + "eliminado correctamente");
    }

    public RecursoDigital buscarRecurso(String titulo){
        for (RecursoDigital recurso : recursos){
            if (recurso.getTitulo().equals(titulo)){
                return recurso;
            }
        }
        System.out.println("Recurso: " + titulo + "no encontrado.");
        return null;
    }
}
