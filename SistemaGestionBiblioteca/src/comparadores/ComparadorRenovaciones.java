package comparadores;
import interfaces.Renovable;
import models.RecursoDigital;
import java.util.Comparator;

public class ComparadorRenovaciones implements Comparator<RecursoDigital> {
    @Override
    public int compare(RecursoDigital r1, RecursoDigital r2) {
        if (r1 instanceof Renovable && r2 instanceof Renovable) {
            int ren1 = ((Renovable) r1).getCantidadRenovaciones();
            int ren2 = ((Renovable) r2).getCantidadRenovaciones();
            return Integer.compare(ren2, ren1); // orden descendente
        } else if (r1 instanceof Renovable) {
            return -1; // Renovable va antes
        } else if (r2 instanceof Renovable) {
            return 1;  // Renovable va antes
        } else {
            return 0;  // ninguno es renovable
        }
    }
}
