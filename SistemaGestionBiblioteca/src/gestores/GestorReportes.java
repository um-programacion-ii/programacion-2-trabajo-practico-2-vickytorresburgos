package gestores;
import enums.TipoRecurso;
import models.Prestamo;


import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class GestorReportes {
    private List<Prestamo> prestamos;
    private ExecutorService executor;

    public GestorReportes(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
        this.executor = Executors.newFixedThreadPool(2);
    }

    private void mostrarProgreso(String mensaje) throws InterruptedException {
        System.out.print(mensaje);
        for (int i = 0; i < 3; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
        System.out.println();
    }

    public Future<String> reporteRecursosMasPrestados() {
        return executor.submit(() -> {
            mostrarProgreso("Generando reportes de recursos más prestados");
            if (prestamos == null || prestamos.isEmpty()) {
                throw new IllegalArgumentException("No hay préstamos registrados para generar el reporte.");
            }
            Map<String, Long> conteo = prestamos.stream()
                    .collect(Collectors.groupingBy(
                            p -> p.getRecursoDigital().getTitulo(),
                                 Collectors.counting()
                    ));
            StringBuilder reporte = new StringBuilder("Recursos más prestados:\n");

            conteo.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(5)
                    .forEach(e -> System.out.printf("Recurso: %s - Préstamos: %d%n", e.getKey(), e.getValue()));
            return reporte.toString();
        });
    }

    public Future<String> reporteUsuariosMasActivos() {
        return executor.submit(() -> {
            mostrarProgreso("Generando reporte de usuarios más activos");
            if (prestamos == null || prestamos.isEmpty()) {
                return "No hay préstamos registrados para generar el reporte.";
            }
            Map<String, Long> conteo = prestamos.stream()
                    .collect(Collectors.groupingBy(
                            p -> p.getUsuario().getNombre(),
                            Collectors.counting()
                    ));
            StringBuilder reporte = new StringBuilder("Usuarios más activos:\n");
            conteo.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(5)
                    .forEach(e -> reporte.append(String.format("Usuario: %s - Préstamos: %d%n", e.getKey(), e.getValue())));
            return reporte.toString();
        });
    }

    public Future<String> estadisticasPorCategoria() {
        return executor.submit(() -> {
            mostrarProgreso("Generando estadísticas por categoría");
            if (prestamos == null || prestamos.isEmpty()) {
                return "No hay préstamos registrados para generar el reporte.";
            }
            Map<TipoRecurso, Long> conteo = prestamos.stream()
                    .collect(Collectors.groupingBy(
                            p -> p.getRecursoDigital().getTipoRecurso(),
                            Collectors.counting()
                    ));
            StringBuilder reporte = new StringBuilder("Estadísticas por categoría:\n");
            conteo.forEach((categoria, cantidad) ->
                    reporte.append(String.format("Categoría: %s - Préstamos: %d%n", categoria, cantidad))
            );
            return reporte.toString();
        });
    }

    public void mostrarReportes() {
        try {
            Future<String> reporteRecursos = reporteRecursosMasPrestados();
            Future<String> reporteUsuarios = reporteUsuariosMasActivos();
            Future<String> reporteCategoria = estadisticasPorCategoria();

            System.out.println(reporteRecursos.get());
            System.out.println(reporteUsuarios.get());
            System.out.println(reporteCategoria.get());

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error al generar reportes: " + e.getMessage());
        }
    }

    public void cerrar() {
        executor.shutdown();
    }
}