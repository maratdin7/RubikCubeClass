import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CubeRubik extends MatrixClass implements CubeRubikInterface {

    MatrixClass front;
    MatrixClass back;
    MatrixClass left;
    MatrixClass right;
    MatrixClass top;
    MatrixClass bottom;
    List<MatrixClass> faceHorizon;
    List<MatrixClass> faceVertical;
    List<MatrixClass> faceSide;
    int size;

    public CubeRubik(int n) {
        size = n;
        front = new MatrixClass(n, n); //       !!!! поменяй
        back = new MatrixClass(n, n);
        left = new MatrixClass(n, n); // относительно наблюдателя
        right = new MatrixClass(n, n);
        top = new MatrixClass(n, n);
        bottom = new MatrixClass(n, n);

        front.writerIn("re");
        back.writerIn("or");
        left.writerIn("ye");
        right.writerIn("wh");
        top.writerIn("gr");
        bottom.writerIn("bl");
        faceHorizon = Arrays.asList(front, right, back, left);
        faceVertical = Arrays.asList(front, top, back, bottom);
        faceSide = Arrays.asList(left, top, right, bottom);
    }


    public void turnCubeTo90() {

    }

    public void turnCubeUpTo90() {

    }

    public void planeHorizonTo90(int row) { ///sgsfgfdgdfgfdgdgfd лямбда?
        List lastRow = faceHorizon.get(3).getRow(row);
        List nowRow;
        for (int i = 0; i < 4; i++) {
            if (i != 2) {
                nowRow = faceHorizon.get(i).getRow(row);
            } else {
                nowRow = faceHorizon.get(i).getRow(size - row - 1);
                reverse(nowRow);
            }
            faceHorizon.get(i).setRow(lastRow, row);
            lastRow = nowRow;
        }
        if (row == 0) top = top.turnTo270(); //поменяй на поворот против часовой
        if (row == size - 1) bottom = bottom.turnTo90();
    }

    public void reverse(List a) {
        for (int i = 0; i < size / 2; i++) {
            Object temp = a.get(i);
            a.set(i, a.get(size - 1 - i));
            a.set(size - 1 - i, temp);
        }
    }

    public void planeVerticalTo90(int column) {
        List lastColumn = faceVertical.get(3).getColumn(column);
        List nowColumn;
        for (int i = 0; i < 4; i++) {
            nowColumn = faceVertical.get(i).getColumn(column);
            faceVertical.get(i).setColumn(lastColumn, column);
            lastColumn = nowColumn;
        }
        if (column == 0) left = left.turnTo270();
        if (column == size - 1) right = right.turnTo90();
    }

    public void planeSideTo270(int trueDepth) { // от пользователя не по left
        int depth = size - 1 - trueDepth;
        List lastLine = faceSide.get(3).getRow(trueDepth);
        lastLine = to270(depth, 0, lastLine);
        to270(trueDepth, 2, lastLine);
        if (trueDepth == 0) front = front.turnTo90();
        if (trueDepth == size - 1) back = back.turnTo270();
    }

    public List to270(int depth, int i, List lastLine) {
        List nowLine = faceSide.get(i).getColumn(depth);
        reverse(nowLine);
        faceSide.get(i).setColumn(lastLine, depth);
        lastLine = nowLine;
        i++;
        nowLine = faceSide.get(i).getRow(depth);
        faceSide.get(i).setRow(lastLine, depth);
        return nowLine;
    }

    public void writer() {
        MatrixClass temp = new MatrixClass();
        temp.writerIn(" ");
        temp.printer();
    }
}
