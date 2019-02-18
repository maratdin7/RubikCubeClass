import java.util.List;

public interface Matrix<T> {

    public class Cells {
        int row;
        int column;
    }

    // получение элемента
    T get(int row, int column);

    T get(Cells cell);

// добавление эл-тов

    void set(int row, int column, T value);

    void set(Cells cell, T value);

    // размерность
    int height();

    int wigth();

    //вывод
    void printer();

    // равенство
    Boolean equals(MatrixClass<T> second);

    // заполнение
    void writerIn(Integer value);

    void writerIn(T value);

    // поворот
    MatrixClass<T> turnTo90();

    MatrixClass<T> turnToAny(Integer angel);

    //вырез строки
    void rowCut(List<T> row);

    //вырез столбца
    void columnCut(List<T> column);
}
