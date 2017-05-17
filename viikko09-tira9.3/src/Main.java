
import java.util.*;

public class Main {

    static int[][] etaisyys;
    static int n;
    static int m;

    static boolean toimiiko(int y, int x) {
        if (y < 0 || x < 0 || y >= n || x >= m) {
            return false;
        }
        return etaisyys[y][x] == -1;
    }

    public static int lyhinReitti(int[][] kartta) {
        n = kartta.length;
        m = kartta[0].length;

        etaisyys = new int[n][m];

        int alkuY = 0, alkuX = 0, maaliY = 0, maaliX = 0;

        for (int i = 0; i < n; i++) {
            for (int e = 0; e < m; e++) {
                etaisyys[i][e] = -1;

                if (kartta[i][e] == 1) {
                    etaisyys[i][e] = 900000000;
                }

                if (kartta[i][e] == 2) {
                    alkuY = i;
                    alkuX = e;
                }

                if (kartta[i][e] == 3) {
                    maaliY = i;
                    maaliX = e;
                }
            }
        }
        etaisyys[alkuY][alkuX] = 0;
        Queue<Integer> jonoY = new ArrayDeque();
        Queue<Integer> jonoX = new ArrayDeque();
        jonoY.add(alkuY);
        jonoX.add(alkuX);

        while (!jonoY.isEmpty()) {
            int y = jonoY.poll();
            int x = jonoX.poll();

            if (toimiiko(y + 1, x)) {
                etaisyys[y + 1][x] = etaisyys[y][x] + 1;
                jonoY.add(y + 1);
                jonoX.add(x);
            }

            if (toimiiko(y - 1, x)) {
                etaisyys[y - 1][x] = etaisyys[y][x] + 1;
                jonoY.add(y - 1);
                jonoX.add(x);
            }

            if (toimiiko(y, x + 1)) {
                etaisyys[y][x + 1] = etaisyys[y][x] + 1;
                jonoY.add(y);
                jonoX.add(x + 1);
            }

            if (toimiiko(y, x - 1)) {
                etaisyys[y][x - 1] = etaisyys[y][x] + 1;
                jonoY.add(y);
                jonoX.add(x - 1);
            }
        }

        return etaisyys[maaliY][maaliX];
    }

    public static void main(String[] args) {
        int[][] kartta = {{1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 3, 1, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 2, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1}};
        System.out.println(lyhinReitti(kartta));
    }
}
