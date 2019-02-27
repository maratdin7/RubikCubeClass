import java.util.List;

public interface Matrix<T> {

    // получение элемента
    T get(int row, int column);

    // добавление эл-тов
    void set(int row, int column, T value);

    // размерность
    int height();

    int wigth();

    //вывод
    void printer();

    // заполнение
    void writerIn(T value);

    // поворот
    MatrixClass<T> turnTo90();

    MatrixClass<T> turnTo270();

    //row срока
    List<T> getRow(int row);

    //column столбец
    List<T> getColumn(int column);

    //вырез строки
    void setRow(List<T> line, int row);

    //вырез столбца
    void setColumn(List<T> line, int column);

    // равенство
    boolean equals(Object o);

    //hashCode
    int hashCode();

    //clone
    MatrixClass<T> copy();
}
