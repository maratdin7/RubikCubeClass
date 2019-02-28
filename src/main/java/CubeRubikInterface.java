public interface CubeRubikInterface {
    int size();

    //поворот против часовой стрелке(с помощью горизонтали )
    void turnCubeRight();

    void turnCubeLeft();

    //поворот вверх, вниз
    void turnCubeUp();

    void turnCubeDown();

    //положить куб на левую, правую грань
    void putCubeOnLeft();

    void putCubeOnRight();

    //поворот слоёв
    void turnLayerRight(int start, int stop);

    void turnLayerLeft(int start, int stop);

    void turnLayerUp(int start, int stop);

    void turnLayerDown(int start, int stop);

    void turnLayerDepthLeft(int start, int stop);

    void turnLayerDepthRight(int start, int stop);

    //поворот горизонтальной плоскости
    void planeHorizonToLeft(int row);

    void planeHorizonToRight(int row);

    //поворот вертикальной плоскости
    void planeVerticalToTop(int column);

    void planeVerticalToBottom(int column);

    //поворот в боковой плоскости
    void planeSideToRight(int trueDepth);

    void planeSideToLeft(int trueDepth);

    //писатель
    void writer();

    //hashCode
    int hashCode();

    //равенство
    boolean equals(Object o);

    //copy
    CubeRubikInterface copy();
}
