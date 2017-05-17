public class Puu {
    char arvo;
    Puu vasen;
    Puu oikea;

    public Puu(char arvo, Puu vasen, Puu oikea) {
        this.arvo = arvo;
        this.vasen = vasen;
        this.oikea = oikea;
    }    
    
    public String toString() {
        String vasenNimi = "-";
        if (vasen != null) vasenNimi = vasen.toString();
        String oikeaNimi = "-";
        if (oikea != null) oikeaNimi = oikea.toString();
        return "["+arvo+",   \n"+vasenNimi+","+oikeaNimi+"]";
    }
}
