package services;
import models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GestorRecursos {

    private List<RecursoDigital> recursos;

    public List<RecursoDigital> getRecursos() {
        return this.recursos;
    }

    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        this.recursos.add(recurso);
    }

    public void eliminarRecurso(String titulo){
        recursos.removeIf(recurso -> recurso.getTitulo().equals(titulo));
    }

    public RecursoDigital buscarRecurso(String titulo){
        for (RecursoDigital recurso : recursos){
            if (recurso.getTitulo().equals(titulo)){
                return recurso;
            }
        }
        return null;
    }

    public List<RecursoDigital> mostrarRecursos() {
        return new ArrayList<>(recursos);
    }

}
