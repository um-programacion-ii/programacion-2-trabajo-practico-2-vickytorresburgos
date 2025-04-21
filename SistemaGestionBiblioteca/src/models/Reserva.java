package models;

import java.time.LocalDate;

public class Reserva implements Comparable<Reserva> {
    private Usuario usuario;
    private RecursoDigital recursoDigital;
    private LocalDate fechaReserva;

    public Reserva(Usuario usuario, RecursoDigital recursoDigital, LocalDate fechaReserva) {
        this.usuario = usuario;
        this.recursoDigital = recursoDigital;
        this.fechaReserva = fechaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecursoDigital() {
        return recursoDigital;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRecursoDigital(RecursoDigital recursoDigital) {
        this.recursoDigital = recursoDigital;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    @Override
    public int compareTo(Reserva otra) {
        return this.fechaReserva.compareTo(otra.fechaReserva);
    }

    public void mostrarInformacion() {
        if (usuario == null) {
            System.out.println("Usuario: [No asignado]");
        } else {
            System.out.println("Usuario: " + usuario.getNombre());
        }
        if (recursoDigital == null) {
            System.out.println("Recurso: [No asignado]");
        } else {
            System.out.println("Recurso: " + recursoDigital.getTitulo());
        }
        System.out.println("Fecha de reserva: " + getFechaReserva());
    }

}
