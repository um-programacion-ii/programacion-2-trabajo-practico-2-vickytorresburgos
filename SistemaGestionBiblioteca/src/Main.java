import console.CLI;
import exceptions.RecursoNoEncontradoException;
import gestores.GestorPrestamos;
import gestores.GestorRecursos;
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
        GestorPrestamos gestorPrestamos = new GestorPrestamos();
        Scanner scanner = new Scanner(System.in);

        CLI cli = new CLI(gestorUsuarios, gestorRecursos, scanner, notificacionesServiceEmail, notificacionesServiceSMS,gestorPrestamos);

        cli.ejecutarMenuPrincipal();
        scanner.close();
    }
}