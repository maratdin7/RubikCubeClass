import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixClassTest {
    private MatrixClass<Integer> matrix = new MatrixClass<>(2, 4);

    private void writerIn() {
        matrix.writerIn(0);
        matrix.set(0, 0, 10);
    }
    @Test
    public void copy() {
        writerIn();
        Matrix<Integer> a =matrix.copy();
        assertNotSame(a, matrix);
        assertEquals(a,matrix);
    }

    @Test
    public void set() {
        writerIn();
        assertEquals((Integer) 10, matrix.get(0, 0));
    }

    @Test
    public void turnTo90() {
        writerIn();
        assertEquals(matrix.turnTo90(), matrix.turnTo270().turnTo270().turnTo270());
        assertEquals((Integer) 10, matrix.turnTo90().get(0, 1));
    }

    @Test
    public void getRow() {
        writerIn();
        assertEquals(matrix.turnTo90().getColumn(1), matrix.getRow(0));
    }

    @Test
    public void setRow() {
        writerIn();
        Matrix<Integer> a = matrix.copy();
        a.setRow(matrix.getRow(0),1);
        assertNotEquals(a,matrix);
        assertEquals(a.getRow(1),matrix.getRow(0));
    }

    @Test
    public void setColumn() {
        writerIn();
        Matrix<Integer> a = matrix.copy();
        a.setColumn(matrix.getColumn(0),1);
        assertNotEquals(a,matrix);
        assertEquals(a.getColumn(1),matrix.getColumn(0));
    }
}