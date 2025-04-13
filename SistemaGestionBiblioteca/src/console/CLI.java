package consola;

import gestores.GestorRecursos;
import gestores.GestorUsuarios;
import modelos.RecursoDigital;
import modelos.Usuario;

import java.util.Scanner;

public class CLI {
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private Scanner scanner;

    public CLI(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos, Scanner scanner) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("Bienvenido a la biblioteca digital de la UM! Seleccione una opción para continuar: ");
        System.out.println("1. Gestión de Usuarios");
        System.out.println("2. Gestión de Recursos");
        System.out.println("3. Salir");
    }

    public void ejecutarMenuPrincipal() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    ejecutarMenuUsuarios();
                    break;
                case 2:
                    ejecutarMenuRecursos();
                    break;
                case 3:
                    System.out.println("Saliendo de la biblioteca digital...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 3);
        scanner.close();
    }

    public void mostrarMenuUsuarios() {
        System.out.println("A través de este menu podrá gestionar los usuario de la biblioteca! Seleccione una opción para continuar: ");
        System.out.println("1. Agregar Usuario");
        System.out.println("2. Eliminar Usuario");
        System.out.println("3. Buscar Usuario");
        System.out.println("4. Volver al Menú Principal");
    }

    public void ejecutarMenuUsuarios() {
        int opcion;
        do {
            mostrarMenuUsuarios();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Agregar Usuario");
                    try{
                        System.out.println("Ingrese el nombre del usuario: ");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingrese el email del usuario: ");
                        String email = scanner.nextLine();
                        gestorUsuarios.agregarUsuario(new Usuario(nombre, email));
                        System.out.println("Usuario agregado exitosamente.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al agregar usuario: " + e.getMessage());
                        System.out.println("Por favor, revise los datos ingresados.");
                    }
                    break;

                case 2:
                    System.out.println("Eliminar Usuario");
                    System.out.println("Ingrese el ID del usuario: ");
                    String idEliminar = scanner.nextLine();
                    gestorUsuarios.eliminarUsuario(idEliminar);
                    break;
                case 3:
                    System.out.println("Buscar Usuario");
                    System.out.println("Ingrese el ID del usuario: ");
                    String idBuscar = scanner.nextLine();
                    modelos.Usuario usuario = gestorUsuarios.buscarUsuario(idBuscar);
                    if (usuario != null) {
                        System.out.println("Usuario encontrado! \n" +
                                "Nombre: " + usuario.getNombre() +
                                "\nId: " + usuario.getId() +
                                "\nEmail: " + usuario.getEmail());
                    }
                    break;
                case 4:
                    System.out.println("Volver al Menú Principal");
                    ejecutarMenuPrincipal();
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente");
            }
        } while (opcion != 4);
    }

    public void mostrarMenuRecursos() {
        System.out.println("A través de este menu podrá gestionar los recursos de la biblioteca! Seleccione una opción para continuar: ");
        System.out.println("1. Agregar Recurso");
        System.out.println("2. Eliminar Recurso");
        System.out.println("3. Buscar Recurso");
        System.out.println("4. Volver al Menú Principal");
    }

    public void ejecutarMenuRecursos() {
        int opcion;
        do {
            mostrarMenuRecursos();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Agregar Recurso");
                    try{
                        System.out.println("Ingrese el titulo del recurso: ");
                        String titulo = scanner.nextLine();

                        System.out.println("Recurso agregado exitosamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al agregar el recurso: " + e.getMessage());
                        System.out.println("Por favor, revise los datos ingresados.");
                    }
                    break;

                case 2:
                    System.out.println("Eliminar Recurso");
                    break;
                case 3:
                    System.out.print("Ingrese el ID del recurso a buscar: ");
                    String idBuscar = scanner.nextLine();
                    modelos.RecursoDigital recursoEncontrado = gestorRecursos.buscarRecurso(idBuscar);
                    if (recursoEncontrado != null) {
                        System.out.println("Recurso encontrado: Título=" + recursoEncontrado.getTitulo());
                    }
                    break;
                case 4:
                    System.out.println("Volver al Menú Principal");
                    ejecutarMenuPrincipal();
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente");
            }
        } while (opcion != 4);
    }
}
