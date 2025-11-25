public class Demo {
    public static int fib(int n){
        if(n==0 || n==1) return n;
        return fib(n-1) + fib(n-2);
    }
    public static int climbingStairs(int n){
        return climbing(n);
    }
    public static int climbing(int n){
        if(n<=1){
            return 1;
        }
        return climbing(n-2) + climbing(n-1);  
    }
    public static int frogJump(int[] stones){
        // if(stones.length==1) return true;
        // if(stones.length>2 && stones[1]!=1) return false;
        return jump(stones, stones.length-1);
    }
    public static int jump(int[] stones,int idx){
        if(idx==0) return 0;
        int left = jump(stones, idx-1) + Math.abs(stones[idx]-stones[idx-1]);
        int right = Integer.MAX_VALUE;
        if(idx>1)
        right = jump(stones, idx-2) + Math.abs(stones[idx-2]-stones[idx]);
        
        return Math.min(left, right);
    }
    public static void main(String[] args) {
        //System.out.println(fib(6));
       // System.out.println(fib(45));
        // System.out.println("45 "+climbingStairs(45));
        // System.out.println("6 "+climbingStairs(6));
        System.out.println(frogJump(new int[]{30,10,60,10,60,50}));
    }
}
