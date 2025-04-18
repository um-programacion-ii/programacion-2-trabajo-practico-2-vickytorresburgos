package comparadores;
import models.RecursoDigital;
import java.util.Comparator;

public class ComparadorAutor implements Comparator<RecursoDigital> {
    public int compare(RecursoDigital r1, RecursoDigital r2) {
        return r1.getAutor().compareToIgnoreCase(r2.getAutor());
    }
}
