import java.util.Arrays;

public class Memorization {
    static int[] memo;
    static int[][] memo1;
    static int[][] memo2 = {
            {-1},
            {-1,-1},
            {-1,-1,-1},
            {-1,-1,-1,-1}
    };
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
    public static int frogJumpK(int[] n,int idx,int k){
        if(idx==0) return 0;
        if(memo[idx]!=-1) return memo[idx];
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=k;i++){
            int jump = 0;
            if(idx-i>=0){
                jump = frogJumpK(n, idx-i, k) + Math.abs(n[idx]-n[idx-i]);
                min = Math.min(min,jump);
            }
        }
        memo[idx] = min;
        return memo[idx];
    }
    public static int maxSumNonAdj(int[] n,int idx){
        if(idx==0) return n[idx];
        if(idx<0) return 0;
        if(memo[idx]!=-1) return memo[idx];

        int pick = n[idx] + maxSumNonAdj(n, idx-2);
        int notPick = maxSumNonAdj(n, idx-1);

        memo[idx] = Math.max(pick, notPick);

        return memo[idx];
    }
    public static int ninjaTraining(int[][] n,int day,int last){
        if(memo1[day][last]!=-1) return memo1[day][last];
        if(day==0){
            int max = 0;
            for(int i=0;i<3;i++){
                if(i!=last){
                    max = Math.max(max,n[day][i]);
                }
            }
            return memo1[0][last]= max;
        }
       memo1[day][last]=-1;
        for(int i=0;i<3;i++){
            int points=0;
            if(i!=last){
                points = n[day][i] + ninjaTraining(n, day-1, i);
            }
            memo1[day][last] = Math.max(memo1[day][last], points);
        }
        return memo1[day][last];
    }
    public static int uniquePath(int[][] grid,int i,int j){
        if(i==0 && j==0) return 1;
        if(memo1[i][j]!=0) return memo1[i][j];
        int[] dr = {-1,0};
        int[] dc = {0,-1};
        for(int x=0;x<2;x++){
            int r = i+dr[x];
            int c = j+dc[x];
            if(r>=0 && c>=0)
            memo1[i][j] += uniquePath(grid, r, c);
        }
        return memo1[i][j];
    }
    public static int uniquePathsII(int[][] grid,int i,int j){
        if(grid[0][0]==0) return 0;
        if(i==0 && j==0) return 1;
        if(memo1[i][j]!=0) return memo1[i][j];

        if(i>0 && grid[i-1][j]!=0)
        memo1[i][j] += uniquePathsII(grid, i-1, j);
        if(j>0 && grid[i][j-1]!=0)
        memo1[i][j] +=uniquePathsII(grid, i, j-1);

        return memo1[i][j];
    }
    public static int minPathSumGrid(int[][] grid,int i,int j){
        if(i==0 && j==0) return grid[0][0];
        if(memo1[i][j]!=-1) return memo1[i][j];
        int up = Integer.MAX_VALUE,left = Integer.MAX_VALUE;
        if(i>0) up = grid[i][j]+minPathSumGrid(grid, i-1, j);
        if(j>0) left = grid[i][j] + minPathSumGrid(grid, i, j-1);

        memo1[i][j] = Math.min(up, left);
        return memo1[i][j];
    }
    public static int triangle(int[][] tri,int i,int j){
        if(i==0 && j==0) return tri[0][0];
        if(i<0 || j<0) return Integer.MAX_VALUE;
        if(memo2[i][j]!=-1) return memo2[i][j];
        int up = Integer.MAX_VALUE;
        int dia = Integer.MAX_VALUE;
        if(j<i) up = tri[i][j] + triangle(tri, i-1, j);
        if(i>0 && j>0) dia = tri[i][j] + triangle(tri, i-1, j-1);

        memo2[i][j] = Math.min(up, dia);
        return memo2[i][j];
    }
    public static int minCoins(int[] x,int target){
        int[] memo = new int[x.length+1];
        Arrays.fill(memo, -1);
        return minCoinsHelper(x, x.length-1,memo, target, 0);
    }
    public static int minCoinsHelper(int[] x,int idx,int[] memo,int target,int cnt){
        if(idx==0){
            if(target%x[0]==0) return cnt + (target/x[0]);

            return Integer.MAX_VALUE;
        }
        if(memo[idx]!=-1) return memo[idx];
        int pick = Integer.MAX_VALUE;
        int notPick ;
        if(target>=x[idx]) pick = minCoinsHelper(x, idx,memo, target-x[idx], cnt+1);
        notPick = minCoinsHelper(x, idx-1,memo, target, cnt);
        
        
        return memo[idx]=Math.min(pick, notPick);
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        System.out.println(minCoins(arr, 7));
        // memo = new int[11];
        // Arrays.fill(memo, -1);
        //unique Paths
        // int[][] grid = new int[10][10];
        
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

        // memo1 = new int[grid.length][grid[0].length];
        // for (int i = 0; i < grid.length; i++) {
        //    // Arrays.fill(grid[i], 1);
        //     Arrays.fill(memo1[i], -1);
        // }

        int[][] tri = {
            {1},
            {2,3},
            {3,6,7},
            {8,9,6,10}
        };

        // memo1 = {
        //     {1},
        //     {2,3},
        //     {3,6,7},
        //     {8,9,6,10}
        // };
        //System.out.println(triangle(tri, tri.length-1, 0));
        // System.out.println(minPathSumGrid(grid, grid.length-1, grid[0].length-1));
       // System.out.println(uniquePathsII(grid, grid.length-1, grid[0].length-1));
        
        // ninja training 
        // memo1 = new int[2][4];
        // for(int i=0;i<2;i++){
        //     Arrays.fill(memo1[i], -1);
        // }

        // int[][] x = {{10,50,1},{5,100,11}};
        // System.out.println(fib(5));
        // System.out.println(climbingStairs(5));
        // System.out.println(frogJump(new int[]{30,10,60,10,60,50}, 5));
       // System.out.println(frogJumpK(new int[]{10,30,40,20}, 2,3));
       // System.out.println(maxSumNonAdj(new int[]{3,2,7,10}, 3));
       //System.out.println(ninjaTraining(x, 1, 3));
    }
}
