import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CubeRubik implements CubeRubikInterface {

    interface lambda { //это нормально?
        void turn(int i);
    }

    MatrixClass<Color> front;
    MatrixClass<Color> back;
    MatrixClass<Color> left;
    MatrixClass<Color> right;
    MatrixClass<Color> top;
    MatrixClass<Color> bottom;

    private MatrixClass[] face;
    private int size;

    CubeRubik(int n) {
        size = n;
        front = new MatrixClass<>(n, n); //       !!!! поменяй
        back = new MatrixClass<>(n, n);
        left = new MatrixClass<>(n, n); // относительно наблюдателя
        right = new MatrixClass<>(n, n);
        top = new MatrixClass<>(n, n);
        bottom = new MatrixClass<>(n, n);


        front.writerIn(Color.RED);
        back.writerIn(Color.ORANGE);
        left.writerIn(Color.YELLOW);
        right.writerIn(Color.WHITE);
        top.writerIn(Color.GREEN);
        bottom.writerIn(Color.BLUE);

        face = new MatrixClass[]{front, right, back, left, top, bottom};
    }

    public void turnCubeRight() {
        turnPlanes(this::planeHorizonToRight, 0, size);
    }

    public void turnCubeLeft() {
        turnPlanes(this::planeHorizonToLeft, 0, size);
    }

    public void turnCubeUp() {
        turnPlanes(this::planeVerticalToTop, 0, size);
    }

    public void turnCubeDown() {
        turnPlanes(this::planeVerticalToBottom, 0, size);
    }

    public void putCubeOnLeft() {
        turnPlanes(this::planeSideToLeft, 0, size);
    }

    public void putCubeOnRight() {
        turnPlanes(this::planeSideToRight, 0, size);
    }

    public void turnLayerRight(int start, int stop) {
        turnPlanes(this::planeHorizonToRight, start, ++stop);
    }

    public void turnLayerLeft(int start, int stop) {
        turnPlanes(this::planeHorizonToLeft, start, ++stop);
    }

    public void turnLayerUp(int start, int stop) {
        turnPlanes(this::planeVerticalToTop, start, ++stop);
    }

    public void turnLayerDown(int start, int stop) {
        turnPlanes(this::planeVerticalToBottom, start, ++stop);
    }

    public void turnLayerDepthLeft(int start, int stop) {
        turnPlanes(this::planeSideToLeft, start, ++stop);
    }

    public void turnLayerDepthRight(int start, int stop) {
        turnPlanes(this::planeSideToRight, start, ++stop);
    }

    private void turnPlanes(lambda turn, int start, int stop) {
        for (int i = start; i < stop; i++) {
            turn.turn(i);
        }
    }

    private void planeHorizonTo90(int row, MatrixClass<Color>[] faceHorizon) {
        List<Color> lastRow = faceHorizon[3].getRow(row);
        List<Color> nowRow;
        for (int i = 0; i < 4; i++) {
            if (i != 2) {
                nowRow = faceHorizon[i].getRow(row);
                faceHorizon[i].setRow(lastRow, row);
            } else {
                nowRow = faceHorizon[i].getRow(size - row - 1);
                reverse(nowRow);
                reverse(lastRow);
                faceHorizon[i].setRow(lastRow, size - row - 1);
            }

            lastRow = nowRow;
        }

    }

    public void planeHorizonToLeft(int row) {
        MatrixClass[] faceHorizon = {front, left, back, right};//jkjkhjk
        planeHorizonTo90(row, faceHorizon);

        if (row == 0) top = top.turnTo90();
        if (row == size - 1) bottom = bottom.turnTo270();
    }

    public void planeHorizonToRight(int row) {
        MatrixClass[] faceHorizon = {front, right, back, left};//jkjkjjkljkl
        planeHorizonTo90(row, faceHorizon);

        if (row == 0) top = top.turnTo270();
        if (row == size - 1) bottom = bottom.turnTo90();
    }

    private void reverse(List<Color> a) {
        for (int i = 0; i < size / 2; i++) {
            Color temp = a.get(i);
            a.set(i, a.get(size - 1 - i));
            a.set(size - 1 - i, temp);
        }
    }


    private void planeVerticalTo90(int column, MatrixClass<Color>[] faceVertical) {
        List<Color> lastColumn = faceVertical[3].getColumn(column);
        List<Color> nowColumn;
        for (int i = 0; i < 4; i++) {
            nowColumn = faceVertical[i].getColumn(column);
            faceVertical[i].setColumn(lastColumn, column);
            lastColumn = nowColumn;
        }
    }

    public void planeVerticalToTop(int column) {
        MatrixClass[] faceVertical = {front, top, back, bottom};
        planeVerticalTo90(column, faceVertical);
        if (column == 0) left = left.turnTo270();
        if (column == size - 1) right = right.turnTo90();
    }

    public void planeVerticalToBottom(int column) {
        MatrixClass[] faceVertical = {front, bottom, back, top};
        planeVerticalTo90(column, faceVertical);
        if (column == 0) left = left.turnTo90();
        if (column == size - 1) right = right.turnTo270();
    }


    public void planeSideToRight(int trueDepth) {
        MatrixClass[] faceSide = {left, top, right, bottom};
        int depth = size - 1 - trueDepth;
        List lastLine = faceSide[3].getRow(trueDepth);
        lastLine = toRight(depth, 0, lastLine, faceSide);
        toRight(trueDepth, 2, lastLine, faceSide);
        if (trueDepth == 0) front = front.turnTo90();
        if (trueDepth == size - 1) back = back.turnTo270();
    }

    private List<Color> toRight(int depth, int i, List<Color> lastLine, MatrixClass<Color>[] faceSide) {
        List<Color> nowLine = faceSide[i].getColumn(depth);
        reverse(nowLine);
        faceSide[i].setColumn(lastLine, depth);
        lastLine = nowLine;
        i++;
        nowLine = faceSide[i].getRow(depth);
        faceSide[i].setRow(lastLine, depth);
        return nowLine;
    }

    public void planeSideToLeft(int trueDepth) {
        MatrixClass[] faceSide = {top, left, bottom, right};
        int depth = size - 1 - trueDepth;
        List lastLine = faceSide[3].getColumn(trueDepth);
        lastLine = toLeft(depth, 0, lastLine, faceSide);
        toLeft(trueDepth, 2, lastLine, faceSide);
        if (trueDepth == 0) front = front.turnTo270();
        if (trueDepth == size - 1) back = back.turnTo90();
    }

    private List<Color> toLeft(int depth, int i, List<Color> lastLine, MatrixClass<Color>[] faceSide) {
        List<Color> nowLine = faceSide[i].getRow(depth);
        reverse(nowLine);
        faceSide[i].setRow(lastLine, depth);
        lastLine = nowLine;
        i++;
        nowLine = faceSide[i].getColumn(depth);
        faceSide[i].setColumn(lastLine, depth);
        return nowLine;
    }


    //писатель
    public void writer() {
        printTopBottomBack(top);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 3 * size; j++) {
                if (j < size) System.out.print(left.get(i, j) + " ");
                else if (j < 2 * size) System.out.print(front.get(i, j - size) + " ");
                else if (j < 3 * size) System.out.print(right.get(i, j - 2 * size) + " ");
            }
            System.out.print("\n");
        }
        printTopBottomBack(bottom);
        printTopBottomBack(back);
    }

    private void printTopBottomBack(MatrixClass<Color> face) {
        String empty = makeStr();
        for (int i = 0; i < size; i++) {
            System.out.print(empty);
            for (int j = 0; j < size; j++) {
                System.out.print(face.get(i, j) + " ");
            }
            System.out.print("\n");
        }
    }

    private String makeStr() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < size; i++) {
            temp.append("   ");
        }
        return temp.toString();
    }

    public CubeRubik copy() {
        CubeRubik temp = new CubeRubik(size);

        for (int i = 0; i < 6; i++) {
            temp.face[i] = face[i].copy();
        }
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CubeRubik cubeRubik = (CubeRubik) o;

        if (size != cubeRubik.size) return false;
        if (front != null ? !front.equals(cubeRubik.front) : cubeRubik.front != null) return false;
        if (back != null ? !back.equals(cubeRubik.back) : cubeRubik.back != null) return false;
        if (left != null ? !left.equals(cubeRubik.left) : cubeRubik.left != null) return false;
        if (right != null ? !right.equals(cubeRubik.right) : cubeRubik.right != null) return false;
        if (top != null ? !top.equals(cubeRubik.top) : cubeRubik.top != null) return false;
        if (bottom != null ? !bottom.equals(cubeRubik.bottom) : cubeRubik.bottom != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(face, cubeRubik.face);
    }

    @Override
    public int hashCode() {
        int result = front != null ? front.hashCode() : 0;
        result = 31 * result + (back != null ? back.hashCode() : 0);
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (top != null ? top.hashCode() : 0);
        result = 31 * result + (bottom != null ? bottom.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(face);
        result = 31 * result + size;
        return result;
    }

    public CubeRubik randomize() {
        CubeRubik cube = copy();
        int i = 0;
        while (i < size * 6) {
            Random r = new Random();
            int next = r.nextInt(5);
            int start = r.nextInt(size - 1);
            switch (next) {
                case 0:
                    cube.turnLayerRight(start, r.nextInt(size - start - 1) + start);
                    break;
                case 1:
                    cube.turnLayerLeft(start, r.nextInt(size - start - 1) + start);
                    break;
                case 2:
                    cube.turnLayerUp(start, r.nextInt(size - start - 1) + start);
                    break;
                case 3:
                    cube.turnLayerDown(start, r.nextInt(size - start - 1) + start);
                    break;
                case 4:
                    cube.turnLayerDepthRight(start, r.nextInt(size - start - 1) + start);
                    break;
                case 5:
                    cube.turnLayerDepthLeft(start, r.nextInt(size - start - 1) + start);
                    break;
            }
            ++i;
        }
        return cube;
    }
}