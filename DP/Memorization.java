import java.util.Arrays;

public class Memorization {
    static int[] memo;
    public static int fib(int n){
        if(n==0 || n==1) return n;
        if(memo[n]!=-1) return memo[n];
        memo[n] = fib(n-1) + fib(n-2);
        return memo[n];
    }
    public static void main(String[] args) {
        memo = new int[11];
        Arrays.fill(memo, -1);
        
        System.out.println(fib(5));
    }
}
