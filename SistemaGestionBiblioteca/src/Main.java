import console.CLI;
import gestores.GestorRecursos;
import gestores.GestorUsuarios;
import services.NotificacionesService;
import services.NotificacionesServiceEmail;
import services.NotificacionesServiceSMS;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        NotificacionesService notificacionesServiceEmail = new NotificacionesServiceEmail();
        NotificacionesService notificacionesServiceSMS = new NotificacionesServiceSMS();

        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        Scanner scanner = new Scanner(System.in);

        CLI cli = new CLI(gestorUsuarios, gestorRecursos, scanner, notificacionesServiceEmail, notificacionesServiceSMS);

        cli.ejecutarMenuPrincipal();
        scanner.close();
    }
}