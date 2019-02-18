import java.util.ArrayList;
import java.util.List;

public class MatrixClass<T> implements Matrix {

    public Object[][] matrix;
    int h;
    int w;

    public MatrixClass(int height, int wigth) {
        if (height < 0 || wigth < 0) throw new IllegalArgumentException();
        matrix = new Object[height][wigth];
        h = height;
        w = wigth;
    }

    public int height() {
        return h;
    }

    public int wigth() {
        return w;
    }

    public Object get(int row, int column) { //dskflsjgjksfjkl matrix[i][j]
        indexOut(row, column);
        return (T) matrix[row][column];
    }

    public Object get(Cells cell) {
        indexOut(cell.row, cell.column);
        return (T) matrix[cell.row][cell.column];
    }

    @Override
    public void set(int row, int column, Object value) {
        indexOut(row, column);
        matrix[row][column] = value;
    }

    public void set(Cells cell, Object value) {
        indexOut(cell.row, cell.column);
        matrix[cell.row][cell.column] = value;
    }

    @Override
    public Boolean equals(MatrixClass second) {
        if (second.height() != h || second.wigth() != w) return false;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                String a = this.get(i, j).toString();
                String b = second.get(i, j).toString();
                if (!a.equals(b)) return false;
            }
        }
        return true;
    }

    public void indexOut(int row, int column) {
        if (row >= h || column >= w) throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    } //fjsklfjsklkjfklsjdkjjkljk

    //вывод

    public void printer() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void writerIn(Integer value) {
        Integer a = value;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                set(i, j, (T) a);
                a++;
            }
        }
    }

    public void writerIn(Object value) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                set(i, j, value);
            }
        }
    }

    public MatrixClass turnTo90() { //6yigyujyiuihuihgyju
        MatrixClass temp = new MatrixClass(w, h);
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

    public MatrixClass turnToAny(Integer angel) {
        int turn = angel / 90;
        MatrixClass temp = this;
        if (angel % 90 != 0) throw new IllegalArgumentException();
        for (int i = 0; i < turn; i++) {
            temp = temp.turnTo90();
        }
        return temp;
    }

    public List getRow(int row) {
        if (row >= h) throw new IllegalArgumentException();
        ArrayList line = new ArrayList();
        for (int j = 0; j < w; j++) {
            line.add(get(row, j));
        }
        return line;
    }


    public List getColumn(int column) {
        if (column >= w) throw new IllegalArgumentException();
        ArrayList line = new ArrayList();
        for (int i = 0; i < h; i++) {
            line.add(get(i, column));
        }
        return line;
    }

    public void setRow(List line, int row) {
        if (line.size() != w || row >= h) throw new IllegalArgumentException();
        for (int j = 0; j < w; j++) {
            set(row, j, line.get(j));
        }
    }

    public void setColumn(List line, int column) {
        if (line.size() != h || column >= w) throw new IllegalArgumentException();
        for (int i = 0; i < h; i++) {
            set(i, column, line.get(i));
        }
    }
}


