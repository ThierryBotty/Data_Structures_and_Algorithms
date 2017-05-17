
public class Main {
    private static int[][] kartta;

    public static int laskeHuoneet(int[][] kartta) {
        Main.kartta = kartta;
        int n = kartta.length;
        int m = kartta[0].length;
        int tulos = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (kartta[i][j] == 1) {
                    continue;
                }
                tulos++;
                tayta(i, j);
            }
        }
        return tulos;
    }

    private static void tayta(int y, int x) {
        if (kartta[y][x] == 1) {
            return;
        }
        kartta[y][x] = 1;
        tayta(y - 1, x);
        tayta(y + 1, x);
        tayta(y, x - 1);
        tayta(y, x + 1);
    }

    public static void main(String[] args) {
        int[][] kartta = {{1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 1, 0, 0, 0, 1},
        {1, 0, 0, 1, 0, 1, 1, 1},
        {1, 0, 0, 1, 0, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1}};
        System.out.println(laskeHuoneet(kartta));
    }
}
