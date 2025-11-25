import java.util.Arrays;

public class Memorization {
    static int[] memo;
    public static int fib(int n){
        if(n==0 || n==1) return n;
        if(memo[n]!=-1) return memo[n];
        memo[n] = fib(n-1) + fib(n-2);
        return memo[n];
    }
    public static int climbingStairs(int n){
        if(n==0 || n==1) return 1;
        if(memo[n]!=-1) return memo[n];
        memo[n] = climbingStairs(n-1) + climbingStairs(n-2);
        return memo[n];
    }
    public static int frogJump(int[] n,int idx){
        if(idx==0) return 0;
        if(memo[idx]!=-1) return memo[idx];
        int left= frogJump(n, idx-1) + Math.abs(n[idx]-n[idx-1]);
        int right = Integer.MAX_VALUE;
        if(idx>1)
        right= frogJump(n, idx-2) + Math.abs(n[idx]-n[idx-2]);
        memo[idx] = Math.min(left,right);
        return memo[idx];

    }
    public static void main(String[] args) {
        memo = new int[11];
        Arrays.fill(memo, -1);
        
        // System.out.println(fib(5));
        // System.out.println(climbingStairs(5));
        System.out.println(frogJump(new int[]{30,10,60,10,60,50}, 5));
    }
}
