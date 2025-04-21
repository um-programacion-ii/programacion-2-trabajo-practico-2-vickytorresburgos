package models;

import java.time.LocalDate;

public class Prestamo {
    private Usuario usuario;
    private RecursoDigital recursoDigital;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Prestamo(Usuario usuario, RecursoDigital recursoDigital, LocalDate fechaInicio, LocalDate fechaFin) {
        this.usuario = usuario;
        this.recursoDigital = recursoDigital;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecursoDigital() {
        return recursoDigital;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRecursoDigital(RecursoDigital recursoDigital) {
        this.recursoDigital = recursoDigital;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void mostrarInformacion() {
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Recurso: " + recursoDigital.getTitulo());
        System.out.println("Fecha de inicio: " + getFechaInicio());
        System.out.println("Fecha de fin: " + getFechaFin());
    }
}
