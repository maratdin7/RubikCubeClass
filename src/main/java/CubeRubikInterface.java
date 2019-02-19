public interface CubeRubikInterface {

    //поворот по часовой стрелке(с помощью горизонтали )
    void turnCubeTo90();

    //поворот вверх (с помощью вертикали и боковой)
    void turnCubeUpTo90();

    //поворот горизонтальной плоскости
    void planeHorizonTo90(int row);

    //поворот вертикальной плоскости
    void planeVerticalTo90(int column);

    //поворот в боковой плоскости
    void planeSideTo270(int depth);

    //писатель
    void writer();

}
