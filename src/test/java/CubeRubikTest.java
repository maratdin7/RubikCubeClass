import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CubeRubikTest {// не генерируются тесты

    interface lambda { //это нормально?
        void turn();
    }

    private CubeRubik cubeFirst = new CubeRubik(3);
    private CubeRubik cubeSecond = new CubeRubik(3);

    private void turnCube(lambda turn90, lambda turn270) {
        turn90.turn();
        turn270.turn();
        turn270.turn();
        turn270.turn();
    }

    @Test
    public void turnCubeRight() {
        MatrixClass<Color> a = cubeFirst.left.copy();
        turnCube(() -> cubeFirst.turnCubeRight(), () -> cubeSecond.turnCubeLeft());
        assertEquals(cubeFirst, cubeSecond);
        assertEquals(a, cubeFirst.front);
    }

    @Test
    public void turnCubeUp() {
        MatrixClass<Color> a = cubeFirst.bottom.copy();
        turnCube(() -> cubeFirst.turnCubeUp(), () -> cubeSecond.turnCubeDown());
        assertEquals(cubeFirst, cubeSecond);
        assertEquals(a, cubeFirst.front);
    }

    @Test
    public void putCubeOnLeft() {
        MatrixClass<Color> a = cubeFirst.front.turnTo270();
        turnCube(() -> cubeFirst.putCubeOnLeft(), () -> cubeSecond.putCubeOnRight());
        assertEquals(cubeFirst, cubeSecond);
        assertEquals(a, cubeFirst.front);
    }

    @Test
    public void turnLayerRight() {
        MatrixClass<Color> a = cubeFirst.left.copy();
        a.setRow(cubeFirst.front.getRow(0), 0);
        turnCube(() -> cubeFirst.turnLayerRight(1, 2), () -> cubeSecond.turnLayerLeft(1, 2));
        assertEquals(cubeFirst, cubeSecond);
        assertEquals(a, cubeFirst.front);
    }

    @Test
    public void turnLayerUp() {
        MatrixClass<Color> a = cubeFirst.bottom.copy();
        a.setColumn(cubeFirst.front.getColumn(0), 0);
        turnCube(() -> cubeFirst.turnLayerUp(1, 2), () -> cubeSecond.turnLayerDown(1, 2));
        assertEquals(cubeFirst, cubeSecond);
        assertEquals(a, cubeFirst.front);
    }

    @Test
    public void turnLayerDepthLeft() {
        MatrixClass<Color> a = cubeFirst.front.turnTo90();
        turnCube(() -> cubeFirst.turnLayerDepthRight(1, 2), () -> cubeSecond.turnLayerDepthLeft(1, 2));
        assertEquals(cubeFirst, cubeSecond);
        assertEquals(a, cubeFirst.front);
    }

    @Test
    public void planeHorizonToLeft() {
        MatrixClass<Color> a = cubeFirst.top.turnTo270();
        turnCube(() -> cubeFirst.planeHorizonToLeft(0), () -> cubeSecond.planeHorizonToRight(0));
        assertEquals(cubeFirst, cubeSecond);
        assertEquals(a, cubeFirst.top);
    }

    @Test
    public void planeVerticalToTop() {
        MatrixClass<Color> a = cubeFirst.left.turnTo270();
        turnCube(() -> cubeFirst.planeVerticalToTop(0), () -> cubeSecond.planeVerticalToBottom(0));
        assertEquals(cubeFirst, cubeSecond);
        assertEquals(a, cubeFirst.left);
    }

    @Test
    public void planeSideToRight() {
        MatrixClass<Color> a = cubeFirst.front.turnTo90();
        turnCube(() -> cubeFirst.planeSideToRight(0), () -> cubeSecond.planeSideToLeft(0));
        assertEquals(cubeFirst, cubeSecond);
        assertEquals(a, cubeFirst.front);
    }

    @Test
    public void randomize() {
        assertNotEquals(cubeFirst, cubeFirst.randomize());
    }
}