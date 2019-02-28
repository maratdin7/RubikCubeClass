import java.util.Random;

public  class Randomize {
    public static CubeRubikInterface randomize(int size) {
        CubeRubik cube = new CubeRubik(size);
        int i = 0;
        Random r = new Random();
        while (i < size * 6) {
            int next = r.nextInt(5);
            int start = r.nextInt(size - 1);
            int stop = r.nextInt(size - start - 1) + start;
            switch (next) {
                case 0:
                    cube.turnLayerRight(start, stop);
                    break;
                case 1:
                    cube.turnLayerLeft(start, stop);
                    break;
                case 2:
                    cube.turnLayerUp(start, stop);
                    break;
                case 3:
                    cube.turnLayerDown(start, stop);
                    break;
                case 4:
                    cube.turnLayerDepthRight(start, stop);
                    break;
                case 5:
                    cube.turnLayerDepthLeft(start, stop);
                    break;
            }
            ++i;
        }
        return cube;
    }
}

