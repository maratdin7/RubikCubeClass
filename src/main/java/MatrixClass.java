import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixClass<T> implements Matrix<T> {

    private T[][] matrix;
    private int h;
    private int w;

    MatrixClass(int height, int wigth) {
        if (height < 0 || wigth < 0) throw new IllegalArgumentException();
        matrix = (T[][]) new Object[height][wigth];
        h = height;
        w = wigth;
    }

    public int height() {
        return h;
    }

    public int wigth() {
        return w;
    }

    public T get(int row, int column) { //dskflsjgjksfjkl matrix[i][j]
        indexOut(row, column);
        return matrix[row][column];
    }

    public T get(Cells cell) {
        indexOut(cell.row, cell.column);
        return matrix[cell.row][cell.column];
    }

    @Override
    public void set(int row, int column, T value) {
        indexOut(row, column);
        matrix[row][column] = value;
    }

    public void set(Cells cell, T value) {
        indexOut(cell.row, cell.column);
        matrix[cell.row][cell.column] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatrixClass<?> that = (MatrixClass<?>) o;

        if (h != that.h) return false;
        if (w != that.w) return false;
        return Arrays.deepEquals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(matrix);
        result = 31 * result + h;
        result = 31 * result + w;
        return result;
    }

    private void indexOut(int row, int column) {
        if (row >= h || column >= w) throw new ArrayIndexOutOfBoundsException();
    }

    //вывод

    public void printer() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void writerIn(T value) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                set(i, j, value);
            }
        }
    }

    public MatrixClass<T> turnTo90() { //6yigyujyiuihuihgyju
        MatrixClass<T> temp = new MatrixClass<T>(w, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                try {
                    temp.set(j, h - i - 1, matrix[i][j]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(h - i - 1);
                }
            }
        }
        return temp;
    }

    public MatrixClass<T> turnTo270() {
        MatrixClass<T> temp = new MatrixClass<T>(w, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                try {
                    temp.set(w - j - 1, i, matrix[i][j]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(w - j - 1);
                }
            }
        }
        return temp;
    }

    public List<T> getRow(int row) {
        if (row >= h) throw new IllegalArgumentException();
        ArrayList<T> line = new ArrayList<T>();
        for (int j = 0; j < w; j++) {
            line.add(get(row, j));
        }
        return line;
    }


    public List<T> getColumn(int column) {
        if (column >= w) throw new IllegalArgumentException();
        ArrayList<T> line = new ArrayList<T>();
        for (int i = 0; i < h; i++) {
            line.add(get(i, column));
        }
        return line;
    }

    public void setRow(List<T> line, int row) {
        if (line.size() != w || row >= h) throw new IllegalArgumentException();
        for (int j = 0; j < w; j++) {
            set(row, j, line.get(j));
        }
    }

    public void setColumn(List<T> line, int column) {
        if (line.size() != h || column >= w) throw new IllegalArgumentException();
        for (int i = 0; i < h; i++) {
            set(i, column, line.get(i));
        }
    }

    @Override
    public MatrixClass<T> copy() { // clone
        MatrixClass<T> temp = new MatrixClass<T>(h, w);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                temp.set(i, j, this.get(i, j));
            }
        }
        return temp;
    }
}


