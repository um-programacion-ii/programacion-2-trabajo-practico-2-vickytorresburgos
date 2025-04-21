import console.CLI;
import exceptions.RecursoNoEncontradoException;

import gestores.GestorPrestamos;
import gestores.GestorRecursos;
import gestores.GestorReservas;
import gestores.GestorUsuarios;

import services.NotificacionesService;
import services.NotificacionesServiceEmail;
import services.NotificacionesServiceSMS;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RecursoNoEncontradoException {

        NotificacionesService notificacionesServiceEmail = new NotificacionesServiceEmail();
        NotificacionesService notificacionesServiceSMS = new NotificacionesServiceSMS();

        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        GestorReservas gestorReservas = new GestorReservas();
        GestorPrestamos gestorPrestamos = new GestorPrestamos(gestorReservas);

        Scanner scanner = new Scanner(System.in);

        CLI cli = new CLI(
                gestorUsuarios,
                gestorRecursos,
                scanner,
                notificacionesServiceEmail,
                notificacionesServiceSMS,
                gestorPrestamos,
                gestorReservas);
        cli.ejecutarMenuPrincipal();
        scanner.close();
    }
}