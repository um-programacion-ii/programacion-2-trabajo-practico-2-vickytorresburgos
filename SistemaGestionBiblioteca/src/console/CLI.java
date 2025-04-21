package console;

import comparadores.ComparadorAutor;
import comparadores.ComparadorRenovaciones;
import comparadores.ComparadorTitulo;

import exceptions.RecursoNoDisponibleException;
import exceptions.RecursoNoEncontradoException;
import exceptions.UsuarioNoEncontradoException;
import gestores.GestorReservas;
import models.*;

import gestores.GestorRecursos;
import gestores.GestorUsuarios;
import gestores.GestorPrestamos;

import services.NotificacionesService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private final GestorUsuarios gestorUsuarios;
    private final GestorRecursos gestorRecursos;
    private final Scanner scanner;
    private final NotificacionesService notificacionesServiceEmail;
    private final NotificacionesService notificacionesServiceSMS;
    private final GestorPrestamos gestorPrestamos;
    private final GestorReservas gestorReservas;

    public CLI(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos, Scanner scanner, NotificacionesService notificacionesServiceEmail, NotificacionesService notificacionesServiceSMS, GestorPrestamos gestorPrestamos, GestorReservas gestorReservas) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.notificacionesServiceEmail = notificacionesServiceEmail;
        this.notificacionesServiceSMS = notificacionesServiceSMS;
        this.gestorPrestamos = gestorPrestamos;
        this.gestorReservas = gestorReservas;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("Bienvenido a la biblioteca digital de la UM! \nSeleccione una opción para continuar: ");
        System.out.println("1. Gestión de Usuarios");
        System.out.println("2. Gestión de Recursos");
        System.out.println("3. Gestión de Préstamos");
        System.out.println("4. Gestión de Reservas");
        System.out.println("5. Salir");
    }

    public void ejecutarMenuPrincipal() throws RecursoNoEncontradoException {
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
                    ejecutarMenuPrestamos();
                    break;
                case 4:
                    ejecutarMenuReservas();
                    break;
                case 5:
                    System.out.println("Saliendo de la biblioteca digital...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
        scanner.close();
    }

    public void mostrarMenuUsuarios() {
        System.out.println("A través de este menu podrá gestionar los usuario de la biblioteca! \nSeleccione una opción para continuar: ");
        System.out.println("1. Agregar Usuario");
        System.out.println("2. Eliminar Usuario");
        System.out.println("3. Buscar Usuario");
        System.out.println("4. Volver al Menú Principal");
        System.out.println("5. Mostrar lista de usuarios");
    }

    public void ejecutarMenuUsuarios() throws RecursoNoEncontradoException {
        int opcion;
        do {
            mostrarMenuUsuarios();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Agregar Usuario");
                    try {
                        System.out.println("Ingrese el nombre y apellido del usuario: ");
                        String nombre = scanner.nextLine();

                        System.out.println("Ingrese el email del usuario: ");
                        String email = scanner.nextLine();

                        System.out.println("Ingrese el telefono del usuario: ");
                        String telefono = scanner.nextLine();

                        System.out.println("¿Cómo desea notificar al usuario? (Email/SMS): ");
                        String preferenciaNotificacion = scanner.nextLine().toLowerCase();

                        gestorUsuarios.registrarUsuario(nombre, email, telefono, preferenciaNotificacion, notificacionesServiceEmail, notificacionesServiceSMS);
                        System.out.println("Usuario agregado exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al agregar al usuario: " + e.getMessage());
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

                    try {
                        Usuario usuario = gestorUsuarios.buscarUsuario(idBuscar);
                        System.out.println("Usuario encontrado:");
                        System.out.println("ID: " + usuario.getId());
                        System.out.println("Nombre: " + usuario.getNombre());
                        System.out.println("Email: " + usuario.getEmail());
                        System.out.println("Teléfono: " + usuario.getTelefono());
                    } catch (UsuarioNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Volver al Menú Principal");
                    ejecutarMenuPrincipal();
                    break;
                case 5:
                    System.out.println("Lista de usuarios: ");
                    gestorUsuarios.mostrarUsuarios();
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente");
            }
        } while (opcion != 4);
    }

    public void mostrarMenuRecursos() {
        System.out.println("A través de este menu podrá gestionar los recursos de la biblioteca! \nSeleccione una opción para continuar: ");
        System.out.println("1. Agregar recurso");
        System.out.println("2. Eliminar recurso");
        System.out.println("3. Buscar recurso");
        System.out.println("4. Mostrar todos los recursos");
        System.out.println("5. Mostrar recursos por tipo");
        System.out.println("6. Ordenar recursos");
        System.out.println("7. Volver al Menú Principal");
    }

    public void ejecutarMenuRecursos() throws RecursoNoEncontradoException {
        int opcion;
        do {
            mostrarMenuRecursos();
            opcion = scanner.nextInt();
            scanner.nextLine();
            String titulo;
            String autor;

            switch (opcion) {
                case 1:
                    System.out.println("Agregar Recurso \nSeleccione el tipo de recurso que desea agregar");
                    System.out.println("1. Audiolibro");
                    System.out.println("2. Ensayo");
                    System.out.println("3. Libro");
                    System.out.println("4. Revista");
                    System.out.println("5. Volver");

                    int tipoRecursoInput = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoRecursoInput == 5) break;

                    TipoRecurso tipoRecurso = null;
                    switch (tipoRecursoInput) {
                        case 1:
                            tipoRecurso = TipoRecurso.AUDIOLIBRO;
                            break;
                        case 2:
                            tipoRecurso = TipoRecurso.ENSAYO;
                            break;
                        case 3:
                            tipoRecurso = TipoRecurso.LIBRO;
                            break;
                        case 4:
                            tipoRecurso = TipoRecurso.REVISTA;
                            break;
                        default:
                            System.out.println("Opción inválida.");
                            break;
                    }
                    System.out.println("Ingrese el titulo del recurso: ");
                    titulo = scanner.nextLine();

                    System.out.println("Ingrese el autor del recurso: ");
                    autor = scanner.nextLine();

                    switch (tipoRecurso) {
                        case AUDIOLIBRO:
                            System.out.print("Ingrese el formato del audiolibro (MP3, WAV, etc.): ");
                            break;
                        case ENSAYO:
                            System.out.print("Ingrese el tema del ensayo: ");
                            break;
                        case LIBRO:
                            System.out.print("Ingrese el género del libro: ");
                            break;
                        case REVISTA:
                            System.out.print("Ingrese la categoría de la revista: ");
                            break;
                    }
                    String detalle = scanner.nextLine();

                    try {
                        gestorRecursos.agregarRecurso(tipoRecurso, titulo, autor, detalle);
                        System.out.println("Recurso agregado exitosamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al agregar recurso: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Eliminar Recurso");

                    System.out.print("Ingrese el título del recurso a eliminar: ");
                    String tituloEliminar = scanner.nextLine();

                    try {
                        gestorRecursos.eliminarRecurso(tituloEliminar);
                        System.out.println("Recurso eliminado exitosamente.");
                    } catch (RecursoNoDisponibleException e) {
                        System.out.println("Error al eliminar el recurso: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Buscar recurso por título y/o autor");

                    System.out.print("Ingrese título (o deje vacío para ignorar): ");
                    titulo = scanner.nextLine().trim();

                    System.out.print("Ingrese autor (o deje vacío para ignorar): ");
                    autor = scanner.nextLine().trim();

                    try {
                        List<RecursoDigital> resultados = gestorRecursos.buscarPorTituloYAutor(titulo, autor);

                        System.out.println("Recursos encontrados:");
                        for (RecursoDigital recurso : resultados) {
                            recurso.mostrarInformacion();
                            System.out.println("---------------------------");
                        }
                    } catch (RecursoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
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

                case 5:
                    System.out.println("Mostrar recursos por tipo");
                    System.out.println("Seleccione el tipo de recurso que desea ver:");
                    System.out.println("1. Audiolibro");
                    System.out.println("2. Ensayo");
                    System.out.println("3. Libro");
                    System.out.println("4. Revista");
                    System.out.println("5. Volver al menú de recursos");

                    int tipoFiltro = scanner.nextInt();
                    scanner.nextLine();

                    TipoRecurso tipoRecursoFiltro = null;
                    switch (tipoFiltro) {
                        case 1:
                            tipoRecursoFiltro = TipoRecurso.AUDIOLIBRO;
                            break;
                        case 2:
                            tipoRecursoFiltro = TipoRecurso.ENSAYO;
                            break;
                        case 3:
                            tipoRecursoFiltro = TipoRecurso.LIBRO;
                            break;
                        case 4:
                            tipoRecursoFiltro = TipoRecurso.REVISTA;
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Opción inválida.");
                            break;
                    }

                    if (tipoRecursoFiltro != null) {
                        mostrarRecursosPorTipo(tipoRecursoFiltro);
                    }
                    break;
                case 6:
                    System.out.println("Seleccione el criterio de ordenamiento:");
                    System.out.println("1. Por título");
                    System.out.println("2. Por autor");
                    System.out.println("3. Por cantidad de renovaciones");
                    int criterio = scanner.nextInt();
                    scanner.nextLine();

                    List<RecursoDigital> listaOrdenada = gestorRecursos.mostrarRecursos(); // Copia actual

                    switch (criterio) {
                        case 1:
                            listaOrdenada.sort(new ComparadorTitulo());
                            break;
                        case 2:
                            listaOrdenada.sort(new ComparadorAutor());
                            break;
                        case 3:
                            listaOrdenada.sort(new ComparadorRenovaciones());
                            break;
                        default:
                            System.out.println("Opción inválida.");
                            continue;
                    }
                    System.out.println("Recursos ordenados:");
                    for (RecursoDigital recurso : listaOrdenada) {
                        recurso.mostrarInformacion();
                        System.out.println("----------------------------");
                    }
                    break;

                case 7:
                    System.out.println("Volver al Menú Principal");
                    ejecutarMenuPrincipal();
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente");
            }
        } while (opcion != 7);
    }

    public void mostrarRecursosPorTipo(TipoRecurso tipoRecurso) {
        List<RecursoDigital> recursosFiltrados = gestorRecursos.filtrarPorTipo(tipoRecurso);

        if (recursosFiltrados.isEmpty()) {
            System.out.println("No se encontraron recursos de tipo " + tipoRecurso);
        } else {
            for (RecursoDigital recurso : recursosFiltrados) {
                recurso.mostrarInformacion();
                System.out.println("----------------------------");
            }
        }
    }

    public void mostrarMenuPrestamos() {
        System.out.println("A través de este menu podrá gestionar los préstamos y renovaciones de la biblioteca! \nSeleccione una opcion para continuar: ");
        System.out.println("1. Prestar un recurso");
        System.out.println("2. Devolver un recurso");
        System.out.println("3. Renovar un recurso");
        System.out.println("4. Mostrar los préstamos");
        System.out.println("5. Volver al menu principal");
    }

    public void ejecutarMenuPrestamos() {
        int opcion;
        do {
            mostrarMenuPrestamos();
            opcion = scanner.nextInt();
            scanner.nextLine();
            String titulo;
            String autor;

            switch (opcion) {
                case 1:
                    System.out.println("Prestar recurso \nSeleccione el recurso que desea prestar: ");
                    System.out.println("1. Audiolibro");
                    System.out.println("2. Ensayo");
                    System.out.println("3. Libro");
                    System.out.println("4. Revista");
                    System.out.println("5. Volver al menu de recursos");

                    int tipoPrestable = scanner.nextInt();
                    scanner.nextLine();
                    if (tipoPrestable == 5) break;

                    try {
                        System.out.println("Ingrese el ID del usuario: ");
                        String idUsuario = scanner.nextLine();

                        Usuario usuario = gestorUsuarios.buscarUsuario(idUsuario);
                        if (usuario == null) {
                            System.out.println("Usuario no encontrado.");
                            break;
                        }

                        System.out.println("Ingrese el titulo del recurso: ");
                        titulo = scanner.nextLine();

                        System.out.println("Ingrese el autor del recurso: ");
                        autor = scanner.nextLine();

                        RecursoDigital recurso = gestorRecursos.buscarRecurso(titulo, autor);

                        if (recurso == null) {
                            System.out.println("Recurso no encontrado.");
                            break;
                        }

                        if (recurso.getEstado() != EstadoRecurso.DISPONIBLE) {
                            System.out.println("El recurso no está disponible actualmente.");
                            System.out.print("¿Desea reservarlo para una fecha futura? (s/n): ");
                            String respuesta = scanner.nextLine().trim().toLowerCase();

                            if (respuesta.equals("s")) {
                                System.out.print("Ingrese la fecha deseada para la reserva (YYYY-MM-DD): ");
                                String fechaStr = scanner.nextLine();
                                try {
                                    LocalDate fechaReserva = LocalDate.parse(fechaStr);
                                    Reserva nuevaReserva = new Reserva(usuario, recurso, fechaReserva);
                                    gestorReservas.agregarReserva(nuevaReserva);
                                    System.out.println("Reserva realizada para la fecha " + fechaReserva + "!");
                                } catch (DateTimeParseException e) {
                                    System.out.println("Fecha inválida. Reserva no realizada.");
                                }
                            } else {
                                System.out.println("Reserva cancelada.");
                            }
                            break;
                        }

                        System.out.println("Ingrese la fecha de inicio del préstamo (YYYY-MM-DD):");
                        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());

                        System.out.println("Ingrese la fecha de fin del préstamo (YYYY-MM-DD):");
                        LocalDate fechaFin = LocalDate.parse(scanner.nextLine());

                        gestorPrestamos.prestarRecurso(usuario, recurso, fechaInicio, fechaFin);
                        System.out.println("¡Préstamo registrado con éxito!");

                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de fecha inválido. Use el formato YYYY-MM-DD.");
                    } catch (RecursoNoDisponibleException e) {
                        System.out.println("Error al realizar el préstamo: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Devolver recurso:");
                    try {
                        System.out.println("Ingrese el ID del usuario que realizó el préstamo: ");
                        String idUsuario = scanner.nextLine();

                        Usuario usuarioDevuelve = gestorUsuarios.buscarUsuario(idUsuario);
                        if (usuarioDevuelve == null) {
                            System.out.println("Usuario no encontrado.");
                            break;
                        }

                        System.out.print("Ingrese el título del recurso: ");
                        String tituloDev = scanner.nextLine();

                        System.out.print("Ingrese el autor del recurso: ");
                        String autorDev = scanner.nextLine();

                        RecursoDigital recursoDev = gestorRecursos.buscarRecurso(tituloDev, autorDev);

                        if (recursoDev == null) {
                            System.out.println("Recurso no encontrado.");
                            break;
                        }

                        gestorPrestamos.devolverRecurso(recursoDev, idUsuario);
                        System.out.println("Recurso devuelto correctamente.");

                        gestorReservas.asignarReservaSiExiste(recursoDev, gestorPrestamos.getPrestamos());


                    } catch (RecursoNoDisponibleException e) {
                        System.out.println("Error al devolver: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Renovar recurso:");
                    try {
                        System.out.print("Ingrese el título del recurso: ");
                        String tituloRenovar = scanner.nextLine();

                        System.out.print("Ingrese el autor del recurso: ");
                        String autorRenovar = scanner.nextLine();

                        RecursoDigital recursoRenovar = gestorRecursos.buscarRecurso(tituloRenovar, autorRenovar);

                        if (recursoRenovar == null) {
                            System.out.println("Recurso no encontrado.");
                            break;
                        }
                        gestorRecursos.renovarRecurso(recursoRenovar);
                        System.out.println("Recurso renovado correctamente.");

                    } catch (RecursoNoDisponibleException e) {
                        System.out.println("Error al renovar: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Lista de préstamos");
                    List<Prestamo> prestamos = gestorPrestamos.mostrarPrestamos();
                    if (prestamos == null || prestamos.isEmpty()) {
                        System.out.println("No hay préstamos registrados");
                    } else {
                        for (Prestamo prestamo : prestamos) {
                            prestamo.mostrarInformacion();
                            System.out.println("----------------------------");
                        }
                    }
                    break;
                case 5:
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente");
            }
        } while (opcion != 5);
    }

    public void mostrarMenuReservas() {
        System.out.println("A través de este menu podrá gestionar las reservas de la biblioteca! \nSeleccione una opcion para continuar: ");
        System.out.println("1. Reservar un recurso");
        System.out.println("2. Ver reservas pendientes");
        System.out.println("3. Volver al menu principal");
    }

    public void ejecutarMenuReservas() {
        int opcion;
        do {
            mostrarMenuReservas();
            opcion = scanner.nextInt();
            scanner.nextLine();

            String titulo;
            String autor;

            switch (opcion) {
                case 1:
                    System.out.println("Reservar un recurso");
                    try {
                        System.out.print("Ingrese el ID del usuario: ");
                        String idUsuario = scanner.nextLine();

                        Usuario usuario = gestorUsuarios.buscarUsuario(idUsuario);
                        if (usuario == null) {
                            System.out.println("Usuario no encontrado.");
                            break;
                        }

                        System.out.print("Ingrese el título del recurso: ");
                        titulo = scanner.nextLine();

                        System.out.print("Ingrese el autor del recurso: ");
                        autor = scanner.nextLine();

                        RecursoDigital recurso = gestorRecursos.buscarRecurso(titulo, autor);
                        if (recurso == null) {
                            System.out.println("Recurso no encontrado.");
                            break;
                        }

                        gestorReservas.realizarReserva(usuario, recurso);
                        System.out.println("¡Reserva realizada con éxito!");

                    } catch (Exception e) {
                        System.out.println("Error al realizar la reserva: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Reservas pendientes:");
                    List<Reserva> pendientes = gestorReservas.obtenerReservasPendientes(); // revisar,devuelve objeto

                    if (pendientes.isEmpty()) {
                        System.out.println("No hay reservas pendientes.");
                    } else {
                        for (Reserva r : pendientes) {
                            System.out.println("Usuario: " + r.getUsuario().getNombre());
                            System.out.println("Recurso: " + r.getRecursoDigital().getTitulo());
                            System.out.println("Fecha de reserva: " + r.getFechaReserva());
                            System.out.println("----------------------------");
                        }
                    }
                    break;
                case 3:
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente");
            }
        } while (opcion != 3);
    }
}
