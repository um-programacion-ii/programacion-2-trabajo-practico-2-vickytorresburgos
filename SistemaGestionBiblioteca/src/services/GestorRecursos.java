package services;
import models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GestorRecursos {
    private List<RecursoDigital> recursos;

    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    /*
    public void agregarRecurso(String tipo, String autor, String titulo) {
        RecursoDigital recurso;

        switch (tipo.toLowerCase()) {
            case "audiolibro":
                System.out.println("Ingrese la duración (en minutos):");
                String formato = scanner.nextInt();
                scanner.nextLine();
                recurso = new Audiolibro(autor, titulo, formato);
                break;
            case "ensayo":
                recurso = new Ensayo(autor, titulo, tema);
                break;
            case "libro":
                recurso = new Libro(autor, titulo, genero);
                break;
            case "revista":
                recurso = new Revista(autor, titulo,categoria);
                break;
            default:
                throw new IllegalArgumentException("Tipo de recurso no conocido");
        }
        this.recursos.add(recurso);
    }
    */

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

    public List<RecursoDigital> getRecursos() {
        return recursos;
    }

}
