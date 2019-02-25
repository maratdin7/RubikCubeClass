import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    public void turnTo270() {
        matrix.writerIn(1);
        matrix = matrix.turnTo270();
        matrix.printer();
    }

    @Test
    public void setRow() {
        matrix.writerIn(1);
        matrix.setRow(a, 2);
        assertEquals(a, matrix.getRow(2));
    }

    @Test
    public void setColumn() {
        matrix.writerIn(1);
        matrix = matrix.turnTo90();
        matrix.setColumn(a, 2);
        assertEquals(a, matrix.getColumn(2));
    }

    private List<Integer> a = Arrays.asList(1, 2, 3, 4);

    @Test
    public void getRow() {
        matrix.writerIn(1);
        assertEquals(a, matrix.getRow(0));
        assertNotEquals(a, matrix.getRow(2));
    }

    @Test
    public void getColumn() {
        matrix.writerIn(1);
        assertEquals(a, matrix.turnTo90().getColumn(9));
    }

    @Test
    public void copy() {
        assertEquals(matrix, matrix.copy());
    }
}