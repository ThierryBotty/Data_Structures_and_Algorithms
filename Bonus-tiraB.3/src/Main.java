import java.util.*;

public class Main {   
    public static int suurinRyhma(String[] sanat) {
        
        HashMap<String, Integer> anagrammit = new HashMap<String, Integer>();
        for (String sana : sanat) {
            
            char[] muokattuSana = sana.toCharArray();
            Arrays.sort(muokattuSana);
            String uusiSana = Arrays.toString(muokattuSana);
            
            if (anagrammit.containsKey(uusiSana)) {                
                anagrammit.put((uusiSana),anagrammit.get(uusiSana) + 1);
            } else {
                anagrammit.put(uusiSana, 1);
            }
        }
        return Collections.max(anagrammit.values());
    }
    
    public static void main(String[] args) {        
        System.out.println(suurinRyhma(new String[] {"apina", "banaani", "cembalo"}));
        System.out.println(suurinRyhma(new String[] {"talo", "katu", "lato"}));
        System.out.println(suurinRyhma(new String[] {"ab", "ab", "ba", "ba"}));
        System.out.println(suurinRyhma(new String[] {"iines", "otto", "sieni", "toto"}));
    }        
}
