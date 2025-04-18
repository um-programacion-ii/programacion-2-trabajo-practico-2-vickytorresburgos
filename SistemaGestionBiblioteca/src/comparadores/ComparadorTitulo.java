package comparadores;
import models.RecursoDigital;
import java.util.Comparator;

public class ComparadorTitulo implements Comparator<RecursoDigital> {
    @Override
    public int compare(RecursoDigital r1, RecursoDigital r2) {
        return r1.getTitulo().compareToIgnoreCase(r2.getTitulo());
    }
}
