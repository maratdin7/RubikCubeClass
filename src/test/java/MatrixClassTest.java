import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatrixClassTest {
    MatrixClass matrix = new MatrixClass(10, 4);

    @Test
    public void height() {
        assertEquals(10, matrix.height());
    }

    @Test
    public void wigth() {
        assertEquals(10, matrix.wigth());
    }

    @Test
    public void get() {
        matrix.set(0, 0, 10);
        assertEquals(10, matrix.get(0, 0));
        assertEquals(null, matrix.get(1, 1));
    }

    @Test
    public void printer() {
        matrix.printer();
    }

    @Test
    public void writerIn() {
        matrix.writerIn("кр");
        matrix.printer();
    }

    @Test
    public void turnTo90() {
        matrix.writerIn(1);
        matrix = matrix.turnTo90();
        matrix.printer();
    }

    @Test
    public void turnToAny() {
        matrix.writerIn(1);
        assertTrue(matrix.equals(matrix.turnToAny(360)));
    }
}