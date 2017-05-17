
import java.io.*;

public class Main {

    public static int rekursio(File f, String s) throws IOException {
        //toteuta minut
        File[] kansiot = f.listFiles();
        int lukumaara = 0;
        for (File kansio : kansiot) {
            if (kansio.isDirectory()) {
                lukumaara += rekursio(kansio, s);
            }
            if (kansio.getName().contains(s)) {
                lukumaara++;
            }
        }
        return lukumaara;
    }

    public static int laske(String search) throws IOException { //ÄLÄ KOSKE TÄHÄN METODIIN
        File kansio = new File("test" + File.separator + "mockfiles");
        return rekursio(kansio, search);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(laske("txt"));
        System.out.println(laske("asd"));
        System.out.println(laske("rekursio"));
    }
}
