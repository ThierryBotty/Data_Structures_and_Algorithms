

public class Putkirikko {

    public static int putkirikko(char[][] talo) {
        // Toteuta minut
        return 0;
    }


    static char[][] esim1 = new char[][] {{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '#', '.', '.', '.', '#', '#', '.', '#'},
        {'#', '.', '#', 'P', '#', '.', '.', '.', 'P', '#'},
        {'#', '.', '.', '.', '.', '.', '#', '#', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    static char[][] esim2 = new char[][] {{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '#', '.', '.', '.', '#', '#', 'P', '#'},
        {'#', '.', '#', 'P', '#', 'P', '.', '.', '.', '#'},
        {'#', 'P', '.', '.', '.', '.', '#', '#', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    public static void main(String[] args) {
        System.out.println(putkirikko(esim1));
        System.out.println(putkirikko(esim2));
    }



}
