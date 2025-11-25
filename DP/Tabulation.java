public class Tabulation {
    public static int fib(int n){
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public static int climbingStairs(int n){
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public static int frogJump(int[] n){
        int[] dp = new int[n.length];
        dp[0]=0;
        int prev = 0,prev2=0;
        for(int i=1;i<n.length;i++){
            int one = prev + Math.abs(n[i]-n[i-1]);
            int two = Integer.MAX_VALUE;
            if(i>1)
            two = prev2 + Math.abs(n[i]-n[i-2]);
            int curr = Math.min(one,two);
            prev2=prev;
            prev = curr;
        }
        return prev;
    }
    public static void main(String[] args) {
        System.out.println(fib(5));
        System.out.println(climbingStairs(10));
    }
}
