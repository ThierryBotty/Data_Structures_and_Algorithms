
public class Main {
    public static int laske(String s, char c) {
        int maara = 0;
        char[] sana = s.toCharArray();
        for (char kirjain : sana) {
            if (c == kirjain) {
                maara++;
            }
        }
        return maara;
    }

    public static void main(String[] args) {
        System.out.println(laske("ABBA", 'B'));
        System.out.println(laske("BANAANI", 'A'));
        System.out.println(laske("APINA", 'Z'));
        System.out.println(laske("CEMBALO", 'C'));
    }
}


