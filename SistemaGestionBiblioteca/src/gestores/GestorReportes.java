package gestores;
import models.TipoRecurso;
import models.Prestamo;


import java.util.*;
import java.util.stream.Collectors;

public class GestorReportes {
    private List<Prestamo> prestamos;

    public GestorReportes(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public void reporteRecursosMasPrestados() {
        if (prestamos == null || prestamos.isEmpty()) {
            throw new IllegalArgumentException("No hay préstamos registrados para generar el reporte.");
        }
        Map<String, Long> conteo = prestamos.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getRecursoDigital().getTitulo(),
                        Collectors.counting()
                ));
        conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.printf("Recurso: %s - Préstamos: %d%n", e.getKey(), e.getValue()));
    }

    public void reporteUsuariosMasActivos() {
        if (prestamos == null || prestamos.isEmpty()) {
            throw new IllegalArgumentException("No hay préstamos registrados para generar el reporte.");
        }
        Map<String, Long> conteo = prestamos.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getUsuario().getNombre(),
                        Collectors.counting()
                ));
        conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.printf("Usuario: %s - Préstamos: %d%n", e.getKey(), e.getValue()));
    }

    public void estadisticasPorCategoria() {
        if (prestamos == null || prestamos.isEmpty()) {
            throw new IllegalArgumentException("No hay préstamos registrados para generar el reporte.");
        }
        Map<TipoRecurso, Long> conteo = prestamos.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getRecursoDigital().getTipoRecurso(),
                        Collectors.counting()
                ));
        conteo.forEach((categoria, cantidad) ->
                System.out.printf("Categoría: %s - Préstamos: %d%n", categoria, cantidad)
        );
    }

    public void mostrarReportes() {
        reporteRecursosMasPrestados();
        reporteUsuariosMasActivos();
        estadisticasPorCategoria();
    }
}
