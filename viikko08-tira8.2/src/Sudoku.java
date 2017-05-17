
public class Sudoku {

    public static void ratkaise(int[][] sudoku) {
        ratkaise(new Solu(0, 0), sudoku);
    }

    private static String[] esim1 = new String[]{
        "8??93???2",
        "??9????4?",
        "7?21??96?",
        "2??????9?",
        "?6?????7?",
        "?7???6??5",
        "?27??84?6",
        "?3????5??",
        "5???62??8"};

    public static void main(String[] args) {
        int[][] sudoku = new int[9][9];

        sudoku = toIntArray(esim1);

        Sudoku.ratkaise(sudoku);
        System.out.println("Ratkaisu:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] toIntArray(String[] lista) {
        int[][] taso = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (lista[i].charAt(j) != '?') {
                    taso[i][j] = Character.digit(lista[i].charAt(j), 10);
                }
            }
        }
        return taso;
    }

    static class Solu {

        int rivi;
        int sarake;

        public Solu(int row, int sarake) {
            super();
            this.rivi = row;
            this.sarake = sarake;
        }

        @Override
        public String toString() {
            return "Cell [row=" + rivi + ", col=" + sarake + "]";
        }
    }

    static boolean toimii(Solu solu, int arvo, int[][] sudoku) {

        if (sudoku[solu.rivi][solu.sarake] != 0) {
        }

        for (int c = 0; c < 9; c++) {
            if (sudoku[solu.rivi][c] == arvo) {
                return false;
            }
        }

        for (int r = 0; r < 9; r++) {
            if (sudoku[r][solu.sarake] == arvo) {
                return false;
            }
        }

        int x1 = 3 * (solu.rivi / 3);
        int y1 = 3 * (solu.sarake / 3);
        int x2 = x1 + 2;
        int y2 = y1 + 2;

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                if (sudoku[x][y] == arvo) {
                    return false;
                }
            }
        }
        return true;
    }

    static Solu seuraavaSolu(Solu s) {

        int rivi = s.rivi;
        int pysty = s.sarake;
        pysty++;

        if (pysty > 8) {
            pysty = 0;
            rivi++;
        }

        if (rivi > 8) {
            return null;
        }

        Solu next = new Solu(rivi, pysty);
        return next;
    }

    static boolean ratkaise(Solu tamanhetkinen, int[][] sudoku) {

        if (tamanhetkinen == null) {
            return true;
        }

        if (sudoku[tamanhetkinen.rivi][tamanhetkinen.sarake] != 0) {
            return ratkaise(seuraavaSolu(tamanhetkinen), sudoku);
        }

        for (int i = 1; i <= 9; i++) {
            boolean toimii = toimii(tamanhetkinen, i, sudoku);

            if (!toimii) {
                continue;
            }

            sudoku[tamanhetkinen.rivi][tamanhetkinen.sarake] = i;

            boolean ratkaistu = ratkaise(seuraavaSolu(tamanhetkinen), sudoku);
            if (ratkaistu) {
                return true;
            } else {
                sudoku[tamanhetkinen.rivi][tamanhetkinen.sarake] = 0;
            }
        }
        return false;
    }
}
