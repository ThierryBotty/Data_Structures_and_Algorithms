import java.util.*;

public class Main {    
    public static int montakoKivea(int[] kivet) {
        Stack<Integer> pino = new Stack();
        
        for (int kivi : kivet) {
            if (!pino.isEmpty() && (pino.peek() == kivi)) {
                pino.pop();
            } else {
                pino.add(kivi);
            }
        }
        return pino.size();
    }
    
    
    public static void main(String[] args) {
        System.out.println(montakoKivea(new int[]{1,1,1,1})); //0
        System.out.println(montakoKivea(new int[]{1,1,1})); //1
        System.out.println(montakoKivea(new int[]{1,2,3,3,2,1})); //0
        System.out.println(montakoKivea(new int[]{1,2,3,2,1})); //5
    }        
}

