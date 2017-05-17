public class Summataulukko {
    private long[] taulukko;
    
    Summataulukko(long[] taulukko) {
        this.taulukko = new long[taulukko.length];
        this.taulukko[0] = taulukko[0];
        for (int i = 1; i < taulukko.length; i++) {
            this.taulukko[i] = taulukko[i] + this.taulukko[i-1];
        }
    }
    
    long summa(int l, int r){
        if (l == 0) {
            return this.taulukko[r];
        }
        return this.taulukko[r] - this.taulukko[l-1];
    }
}
