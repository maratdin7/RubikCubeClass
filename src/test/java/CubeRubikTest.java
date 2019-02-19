import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CubeRubikTest {
    CubeRubik cube = new CubeRubik(5);

    @Test
    public void turnCubeTo90() {
    }

    @Test
    public void turnCubeUpTo90() {
    }

    @Test
    public void planeHorizonTo90() {
        List a = Arrays.asList("ye", "ye", "ye", "ye", "ye");
        cube.back.set(2, 0, "qwerty");
        cube.planeHorizonTo90(2);
        assertEquals(a, cube.front.getRow(2));
        for (MatrixClass i : cube.faceHorizon) {
            i.printer();
        }

        cube.top.set(0, 0, "qwerty");
        MatrixClass q = cube.top.turnTo270();
        cube.planeHorizonTo90(0);
        assertTrue(cube.top.equals(q));
    }

    @Test
    public void planeVerticalTo90() {
        List a = Arrays.asList("bl", "bl", "bl", "bl", "bl");
        cube.back.set(1, 0, "qwerty");
        cube.left.set(0, 0, "qwerty");
        cube.planeVerticalTo90(0);
        assertEquals(a, cube.front.getColumn(0));

        for (MatrixClass i : cube.faceVertical) {
            i.printer();
        }
        cube.left.printer();

    }

    @Test
    public void planeSideTo270() {
        List a = Arrays.asList("ye", "ye", "ye", "ye", "qwerty");
        cube.left.set(0, 4, "qwerty");
        cube.right.set(0, 0, "qwerty");
        cube.front.set(0, 0, "qwerty");
        cube.planeSideTo270(0);
        assertEquals(a, cube.top.getRow(4));
        for (MatrixClass i : cube.faceSide) {
            i.printer();
        }
        cube.front.printer();
    }
}