import console.CLI;
import gestores.GestorRecursos;
import gestores.GestorUsuarios;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        Scanner scanner = new Scanner(System.in);

        CLI cli = new CLI(gestorUsuarios, gestorRecursos, scanner);

        cli.ejecutarMenuPrincipal();
        scanner.close();
    }
}