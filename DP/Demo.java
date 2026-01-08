import java.util.*;

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
    public static int frogJumpK(int[] stones,int k){
        return jumpK(stones, k, stones.length-1);
    }
    public static int jumpK(int[] stones,int k,int idx){
        if(idx==0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=k;i++){
            if(idx-i>=0)
            min = Math.min(min,jumpK(stones, k, idx-i)+Math.abs(stones[idx]-stones[idx-i]));
        }
        return min;
    }
    public static int maxSumNonAdj(int[] n,int idx){
        if(idx==0) return n[idx];
        if(idx<0) return 0;

        int pick = n[idx] + maxSumNonAdj(n, idx-2);
        int notPick = maxSumNonAdj(n, idx-1);
        return Math.max(pick, notPick);
    }
    public static int ninjaTraining(int[][] n,int i,int j){
        if(i==0){
            int max=0;
            for(int a=0;a<3;a++){
                if(a!=j)
                max = Math.max(max, n[i][a]);
            }
            return max;
        }
        int max=0;
        for(int a=0;a<3;a++){
            int points=0;
            if(j!=a){
                points = n[i][a] + ninjaTraining(n, i-1, a);
            }
            max=Math.max(max, points);
        }
        // if(j==3){
        //     for(int a=0;a<3;a++){
        //         max=Math.max(max,ninjaTraining(n, i, a));
        //     }
        //     return max;
        // }
        
        // int pick1 = n[i][j]+ninjaTraining(n, i-1,j);
        // int notpick1 = ninjaTraining(n, i,j-1);
        // int max1= Math.max(pick1, notpick1);
        // int pick2 = Integer.MIN_VALUE;
        // if(i-1>0)
        // pick2 = n[i-1][j] + ninjaTraining(n, i-2,j);
        // int notpick2 = ninjaTraining(n, i,j-2);
        // int max2 = Math.max(pick2, notpick2);
        // int pick3 = n[i-2][j] + ninjaTraining(n, i-3,j);
        // int notpick3 = ninjaTraining(n, i,j-3);
        //int pick3 = ninjaTraining(n, 0);

        //return Math.max(Math.max(pick3, notpick3),Math.max(max1,max2));
        return max;
    }
    public static int uniquePaths(int[][] grid,int i,int j){
        if(i==0 && j==0){
            return 1;
        }
        int sum=0;
        int[] row = {-1,0};
        int[] col = {0,-1};
        for(int a=0;a<row.length;a++){
            int r = i+row[a];
            int c = j+col[a];
            if(r>=0 && c>=0)
            sum += uniquePaths(grid,r , c);
        }
        return sum;
    }
    public static int uniquePathsII(int[][] grid,int i,int j){
        if(i==0 && j==0) return 1;
        int paths=0;
        if(i>0 && grid[i-1][j]!=0){
            paths+=uniquePathsII(grid, i-1, j);
        }
        if(j>0 && grid[i][j-1]!=0){
            paths+=uniquePathsII(grid, i, j-1);
        }
        return paths;
    }
    public static int minPathSumGrid(int[][] grid,int i,int j){
        if(i==0 && j==0) return grid[i][j];
        int min = 0;
        int up=Integer.MAX_VALUE;
        int left=Integer.MAX_VALUE;
        if(i>0)
        up = grid[i][j]+ minPathSumGrid(grid, i-1, j);
        if(j>0)
        left = grid[i][j] + minPathSumGrid(grid, i, j-1);
        min += Math.min(up,left);
        return min;
    }
    public static int triangle(int[][] tri,int i,int j){
        if(i==0 && j==0) return tri[0][0];
        if(i<0 || j<0) return Integer.MAX_VALUE;
        System.out.println(i+" "+j);
        int min = 0;
        int dia;
        int up = dia = Integer.MAX_VALUE;
      //  for(int x=j;x>=0;x--){
            
            if(j<i) up = tri[i][j] + triangle(tri, i-1, j);
            if(i>0 && j>0)  dia = Math.min(dia, tri[i][j] + triangle(tri, i-1, j-1));
        //}
        min += Math.min(up, dia);
        System.out.println(i+" "+j+"  "+tri[i][j]+" min: "+min);
        return min;
    }
    public static int maxFallingPathSum(int[][] grid){
        int max=Integer.MIN_VALUE;
        for(int i=0;i<grid[0].length;i++){
            max = Math.max(max, maxFallingPathSumHelper(grid,0,i));
        }
        return max;
    }
    public static int maxFallingPathSumHelper(int[][] grid,int i,int j){
        if(j<0 || j>=grid[0].length ) return Integer.MIN_VALUE;
        if(i==grid.length-1){
            return grid[i][j];
        }
        //int max = 0;
        int d = Integer.MIN_VALUE,dl = Integer.MIN_VALUE,dr = Integer.MIN_VALUE;
       // if(i<grid.length && j<grid[0].length)
        d = grid[i][j] + maxFallingPathSumHelper(grid, i+1, j);
      //  if(j+1<grid[0].length){
        dr = grid[i][j] + maxFallingPathSumHelper(grid, i+1, j+1);
      //  }

       // if(j-1>=0){
        dl = grid[i][j] +maxFallingPathSumHelper(grid,  i+1, j-1);
       // }
        System.out.println("D: "+d+" dr "+dr+" dl "+dl);
        return Math.max(d, Math.max(dl, dr));
        // System.out.println(max);
        // return max;
    }
    public static int ninjaCandy(int[][] grid,int i,int j1,int j2){
        int n = grid.length;
        int m = grid[0].length;
        if(j1<0 || j1>m-1 || j2<0 || j2>m-1) return Integer.MIN_VALUE;
        System.out.println(i+" j1: "+j1+" j2: "+j2);
        if(i==n-1){
            if(j1==j2) return grid[i][j2];
            return grid[i][j1] + grid[i][j2];
        }
        int[] xc = {-1,0,1};
        int maxi=Integer.MIN_VALUE;
        for(int x=0;x<xc.length;x++){
            int newJ = j1+xc[x];
            for(int y=0;y<xc.length;y++){
                int newB = j2+xc[y];
                if(j1==j2) maxi = Math.max(maxi, grid[i][j1] + ninjaCandy(grid, i+1, newJ, newB));
                else{
                   maxi = Math.max(maxi, grid[i][j1] + grid[i][j2] + ninjaCandy(grid, i+1, newJ, newB));
                }
                
            }
        }
        return maxi;
    }
    public static boolean subsetSum(int[] x,int target){
        return subsetSumHelper(x, x.length-1, target);
    }
    public static boolean subsetSumHelper(int[] x,int i,int target){
        if(target==0) return true;
        if(i==0)    return x[i]==target;

        // if (target < 0) return false;
        boolean pick = false;
        if(target>=x[i])    pick = subsetSumHelper(x, i-1, target-x[i]);
        boolean notPick = subsetSumHelper(x, i-1, target);

        return pick || notPick;

    }
    public static boolean partitionEqualSubsetSum(int[] x){
        //if(x.length%2!=0) return false;
        int sum=0;
        for(int i : x) sum+=i;
        if(sum%2!=0) return false;
        return subsetSumHelper(x, x.length-1, sum/2);

    }
    public static int knapsack0By1(int[] wt,int[] val,int W){
        return knapsack0By1Helper(wt, val, wt.length-1, W);

    }
    public static int knapsack0By1Helper(int[] wt,int[] val,int idx,int W){
        if(idx<0 || W==0) return 0;
        int pick = 0 ;
        if(W-wt[idx]>=0)
        pick=val[idx] + knapsack0By1Helper(wt, val, idx-1, W-wt[idx]);
        int notPick = knapsack0By1Helper(wt,val, idx-1, W);
        // System.out.println("*****");
        // System.out.println("idx: "+idx+" "+W);
        // System.out.println(pick +" "+notPick);
        return Math.max(pick,notPick);
    }
    public static int minCoins(int[] x,int target){
        return minCoinsHelper(x, x.length-1, target);
    }
    public static int minCoinsHelper(int[] x,int idx,int target){
        if(idx==0){
            if(target%x[0]==0) return (target/x[0]);

            return Integer.MAX_VALUE;
        }
        int pick = Integer.MAX_VALUE;
        int notPick = minCoinsHelper(x, idx-1, target);;
        if(target>=x[idx]){
            int sub = minCoinsHelper(x, idx, target-x[idx]);
            if(sub!=Integer.MAX_VALUE) pick = 1 + sub;
        }
        
        
        
        return Math.min(pick, notPick);
    }
    public static int InfinityCoinsHelper(int[] x,int idx,int target){
        if(idx==0){
            if(target%x[0]==0) return 1;

            return 0;
        }
        int pick = 0;
        int notPick = InfinityCoinsHelper(x, idx-1, target);;
        if(target>=x[idx]){
            pick = InfinityCoinsHelper(x, idx, target-x[idx]);
            //if(sub!=0) pick = sub;
        }    
        return pick+notPick;
    }
    public static int unboundedKnapsack(int[] wt,int[] val,int W){
        return unboundedKnapsackHelper(wt, val, wt.length-1, W);
    }
    public static int unboundedKnapsackHelper(int[] wt,int[] val,int idx,int W){
       // if(W==0 || idx<0) return 0;
        if (idx == 0) {
            return (W / wt[0]) * val[0];
        }

        int pick = 0;
        int notPick = unboundedKnapsackHelper(wt, val, idx-1, W);
        if(W-wt[idx]>=0) pick = val[idx] + unboundedKnapsackHelper(wt, val, idx, W-wt[idx]);

        return Math.max(pick, notPick);
    }
    public static int rodCutting(int[] price,int N){
        return rodCuttingHelper(price, N, price.length-1);
    }
    public static int rodCuttingHelper(int[] price,int N,int idx){
        if(N==0) return 0;
        if(idx==0) return N * price[0];
        System.out.println(idx+1+" "+price[idx]);
        int pick = 0;
        int notPick = rodCuttingHelper(price, N, idx-1);
        if(N>=idx+1) pick = price[idx]+rodCuttingHelper(price, N-idx-1, idx);

        return Math.max(pick, notPick);
    }
    public static int longestCommonSubseq(String s1, String s2){
        return longestCommonSubseqHelper(s1, s2, s1.length()-1, s2.length()-1);
        
    }
    public static int longestCommonSubseqHelper(String s1,String s2,int idx1,int idx2){
        if(idx1<0 || idx2<0) return 0;
        if(s1.charAt(idx1)==s2.charAt(idx2)) return 1 + longestCommonSubseqHelper(s1, s2, idx1-1, idx2-1);
        else return Math.max(longestCommonSubseqHelper(s1, s2, idx1-1, idx2),longestCommonSubseqHelper(s1, s2, idx1, idx2-1));
    }

    // public static int longestCommonSubseqPrint(String s1,String s2){

    // }

    public static String longestPalindromicSubseq(String s){
        return longestPalindromicSubseqHelper(s, "");
    }
    public static String longestPalindromicSubseqHelper(String s,String cur){
        if(s.length()==0) return cur;
        return "";
    }
    public static void main(String[] args) {
        System.out.println(longestCommonSubseq("adebc", "dcadb"));
        // int[] price = {2,5,7,8,10};
        // System.out.println(rodCutting(price, 5));
        // int[] arr = {1,2,3};
        // System.out.println(InfinityCoinsHelper(arr, arr.length, 4));
        // System.out.println(minCoins(arr, 7));
        // int[] wt = {2,4,6};
        // int[] val = {5,11,13};
        // System.out.println(unboundedKnapsack(wt, val, 10));
        // System.out.println(knapsack0By1(wt, val, 8));
        // int[] q = {2,3,3,3,4,5};
        // System.out.println(partitionEqualSubsetSum(q)); 
        // int[][] x = {{10,50,1},{5,100,11}};
        // int[][] grid = new int[10][10];
        // for (int i = 0; i < 10; i++) {
        //     Arrays.fill(grid[i], 1);
        // }
        // int[][] grid = {
        //     {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        //     {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
        //     {1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
        //     {1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
        //     {1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
        //     {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
        //     {1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
        //     {0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
        //     {1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
        //     {1, 1, 1, 1, 0, 1, 1, 1, 1, 1}
        // };

        int[][] grid = {
            {1, 3, 1, 2, 8},
            {1, 5, 1, 3, 2},
            {4, 2, 1, 7, 3},
            {2, 1, 2, 1, 4},
            {3, 2, 1, 5, 1}
        };
        int[][] tri = {
            {1},
            {2,3},
            {3,6,7},
            {8,9,6,10}
        };
     //   System.out.println(triangle(tri, tri.length-1, 0));
        int[][] spiral = {
            {1,2,3},
            {8,9,4},
            {7,6,5}
        };
        int[] a = {4,3,2,1};
        int[] b = {2,5,1,6,7};
        // System.out.println(subsetSum(a, 5));
        // System.out.println(subsetSum(b, 4));
       // System.out.println(maxFallingPathSum(spiral));
       //System.out.println(ninjaCandy(spiral, 0, 0, 2));
        //System.out.println(minPathSumGrid(grid, grid.length-1, grid[0].length-1));
       //System.out.println(uniquePathsII(grid, grid.length-1, grid[0].length-1));
        //System.out.println(fib(6));
       // System.out.println(fib(45));
        // System.out.println("45 "+climbingStairs(45));
        // System.out.println("6 "+climbingStairs(6));
        // System.out.println(frogJump(new int[]{30,10,60,10,60,50}));
        // System.out.println(frogJumpK(new int[]{10,30,40,20}, 2));
        // System.out.println(ninjaTraining(x, 3));
        //System.out.println(maxSumNonAdj(new int[]{3,2,7,10}, 3));
        //System.out.println(ninjaTraining(x, x.length-1, 3));
    }
}
