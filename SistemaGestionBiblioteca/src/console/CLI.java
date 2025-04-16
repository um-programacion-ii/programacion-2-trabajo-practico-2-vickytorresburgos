package console;

import models.*;
import services.GestorRecursos;
import services.GestorUsuarios;

import java.util.List;
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
                    models.Usuario usuario = gestorUsuarios.buscarUsuario(idBuscar);
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
        System.out.println("5. Mostrar todos los recursos");
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
                    System.out.println("Seleccione el tipo de recurso que desea agregar");
                    System.out.println("1. Audiolibro");
                    System.out.println("2. Ensayo");
                    System.out.println("3. Libro");
                    System.out.println("4. Revista");
                    System.out.println("5. Volver");

                    int tipoRecurso = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoRecurso == 5) break;

                    try {
                        System.out.println("Ingrese el titulo del recurso: ");
                        String titulo = scanner.nextLine();

                        System.out.println("Ingrese el autor del recurso: ");
                        String autor = scanner.nextLine();

                        String tipo = switch (tipoRecurso) {
                            case 1 -> "audiolibro";
                            case 2 -> "ensayo";
                            case 3 -> "libro";
                            case 4 -> "revista";
                            default -> throw new IllegalArgumentException("Opción de recurso inválida.");
                        };

                        RecursoDigital recurso = null;

                        switch (tipo.toLowerCase()) {
                            case "audiolibro":
                                System.out.println("Ingrese el formato:");
                                String formato = scanner.nextLine();
                                recurso = new Audiolibro(autor, titulo, formato);
                                break;

                            case "ensayo":
                                System.out.println("Ingrese el tema del ensayo:");
                                String tema = scanner.nextLine();
                                recurso = new Ensayo(autor, titulo, tema);
                                break;

                            case "libro":
                                System.out.println("Ingrese el género del libro:");
                                String genero = scanner.nextLine();
                                recurso = new Libro(autor, titulo, genero);
                                break;

                            case "revista":
                                System.out.println("Ingrese la categoría de la revista:");
                                String categoria = scanner.nextLine();
                                recurso = new Revista(autor, titulo, categoria);
                                break;

                            default:
                                throw new IllegalArgumentException("Tipo de recurso no valido");
                        }
                        // Agregar el recurso al GestorRecursos
                        if (recurso != null) {
                            gestorRecursos.agregarRecurso(recurso);
                            System.out.println("Recurso agregado exitosamente.");
                        } else {
                            System.out.println("Error: No se pudo crear el recurso.");
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al agregar el recurso: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error inesperado: " + e.getMessage());
                    }
                break;

                case 2:
                    System.out.println("Eliminar Recurso");
                    break;
                case 3:
                    System.out.print("Ingrese el ID del recurso a buscar: ");
                    String idBuscar = scanner.nextLine();
                    models.RecursoDigital recursoEncontrado = gestorRecursos.buscarRecurso(idBuscar);
                    if (recursoEncontrado != null) {
                        System.out.println("Recurso encontrado: Título=" + recursoEncontrado.getTitulo());
                    }
                    break;
                case 4:
                    System.out.println("Volver al Menú Principal");
                    ejecutarMenuPrincipal();
                    break;
                case 5:
                    System.out.println("Listado de Recursos:");
                    List<RecursoDigital> recursos = gestorRecursos.mostrarRecursos();

                    if (recursos.isEmpty()) {
                        System.out.println("No hay recursos registrados.");
                    } else {
                        for (RecursoDigital recurso : recursos) {
                            recurso.mostrarInformacion();
                            System.out.println("----------------------------");
                        }
                    }
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente");
            }
        } while (opcion != 4);
    }
}
