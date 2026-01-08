import java.util.Arrays;

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
    public static int frogJumpK(int[] n,int k){
        
        int[] dp = new int[n.length];
        dp[0]=0;
        for(int i=1;i<n.length;i++){
            int min = Integer.MAX_VALUE;
            int j=1;
            while(j<=k){
                if(i-j>=0){
                    int jump =dp[i-j]+ Math.abs(n[i]-n[i-j]);
                    min = Math.min(min, jump);
                }
                j++;
            }
            dp[i] = min;
        }
        return dp[n.length-1];
    }
    public static int maxSumNonAdj(int[] x){
        int n =x.length;
        int[] dp = new int[n];
        dp[0] = x[0];
        int prev=x[0],prev2=0;
        for(int i=1;i<n;i++){
            int take = x[i];
            if(i>1) take+=prev2;
            int notTake = prev;
            int cur = Math.max(take, notTake);
            prev2=prev;
            prev=cur;

        }
        return dp[n-1];
    }
    public static int houseRobberII(int[] x){
        if(x.length==1) return x[0];
        int[] a = new int[x.length-1];
        int[] b = new int[x.length-1];
        for(int i=0;i<x.length;i++){
            if(i!=0) a[i-1]=x[i];
            if(i!=x.length-1) b[i] = x[i];
        }
        return Math.max(maxSumNonAdj(a), maxSumNonAdj(b));
    }
    public static int ninjaTraining(int[][] n){
        int dp[][] = new int[n.length][4];
        for(int last =0;last<4;last++){
            int best=0;
            for(int task=0;task<3;task++){
                if(task!=last) best=Math.max(n[0][task], best);
            }
            dp[0][last]=best;
        }
        
        for(int day=1;day<n.length;day++){
            for(int last=0;last<4;last++){
                
                int best=0;
                for(int task=0;task<3;task++){
                    if(task!=last)  best=Math.max(best, dp[day-1][task]+n[day][task]);
                }
                dp[day][last]=best;
            }
        }
        
        return dp[n.length-1][3];
    }
    public static int uniquePath(int[][] grid){
        int[][] dp = new int[grid.length][grid[0].length];
        int endr = grid.length-1;
        int endc = grid[0].length-1;
        dp[0][0]=1;
        int nr = grid.length;
        int nc = grid[0].length;
        for(int i=0;i<nr;i++){
            for(int j=0;j<nc;j++){
                if(i>0) dp[i][j]+= dp[i-1][j];
                if(j>0) dp[i][j]+=dp[i][j-1];
                // for(int x=0;x<2;x++){
                //     if(i==0 && j==0){
                //         dp[i][j]=1;
                //         continue;
                //     }
                //     int r = i+dr[x];
                //     int c = j+dc[x];
                //     if(r>=0 && c>=0){
                //         dp[i][j] += dp[r][c];
                //     }
                // }
            }
        }
        return dp[endr][endc];
    }
    public static int uniquePathsII(int[][] grid){
        if(grid[0][0]==0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int[][] dp = new int[nr][nc];
        dp[0][0]=1;
        for(int i=0;i<nr;i++){
            for(int j=0;j<nc;j++){
                if(i>0 && grid[i-1][j]!=0) 
                    dp[i][j]+=dp[i-1][j];
                if(j>0 && grid[i][j-1]!=0)
                    dp[i][j]+=dp[i][j-1];
            }
        }
        return dp[nr-1][nc-1];
    }
    public static int minPathSumGrid(int[][] grid){
        int nr = grid.length;
        int nc = grid[0].length;
        int[][] dp = new int[nr][nc];
        dp[0][0] = grid[0][0];
        for(int i=0;i<nr;i++){
            for(int j=0;j<nr;j++){
                if(i==0 && j==0){
                    dp[i][j] = grid[i][j];
                    continue;
                }
                int up = Integer.MAX_VALUE,left = Integer.MAX_VALUE;
                if(i>0) up = grid[i][j] + dp[i-1][j];
                if(j>0) left = grid[i][j] + dp[i][j-1];
                dp[i][j] = Math.min(up, left);
            }
        }
        return dp[nr-1][nc-1];
    }
    public static int triangle(int[][] tri){
        int[] dp = new int[tri.length];
        //Arrays.fill(dp, -1);
        dp[0] = tri[0][0];
        int n = tri.length;
        for(int i=1;i<n;i++){
            for(int j=i;j>=0;j--){
                if(i==j) dp[j] = dp[j-1] + tri[i][j];
                else if(j==0) dp[j] = dp[j] + tri[i][j];
                else dp[j] = Math.min(dp[j], dp[j-1]) + tri[i][j];
                //System.out.println(dp[i][j]);
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i : dp) min = Math.min(min, i);
        return min;
    }
    public static int maxFallingPathSum(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int i=0;i<m;i++){
            dp[0][i]=grid[0][i];
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                int down,left,right;
                down=left=right=Integer.MIN_VALUE;
                down = dp[i-1][j];
                if(j+1<m)
                right = dp[i-1][j+1];
                if(j-1>=0)
                left = dp[i-1][j-1];

                dp[i][j] = grid[i][j] + Math.max(down, Math.max(left, right));
            }
            // for(int z : dp[i]) System.out.print(z+" ");
            // System.out.println();
           
            
        }
        int max=Integer.MIN_VALUE;
        for(int i : dp[n-1]) max = Math.max(i, max);
        return max;
    }
    // public static int ninjaCandy(int[][] grid){
    //     int n = grid.length;
    //     int m = grid[0].length;
    //     int[] dp = new int[m];
    //     for(int i=0;i<m;i++){
    //         dp[i]= grid[0][i];
    //     }
    //     int j1=0,j2=m-1;
    //     int[] xc = {-1,0,1};
    //     for(int )
    // }
    public static boolean subsetSum(int[] x,int target){
        int n = x.length;
        boolean[][] dp = new boolean[n][target+1];
        for(int i=0;i<n;i++)    dp[i][0]=true;
        if (x[0] <= target)
        dp[0][x[0]] = true;
        for(int i=1;i<n;i++){
            for(int j=1;j<=target;j++){
                boolean notPick = dp[i-1][j];
                boolean pick=false;
                if(x[i]<=j) pick=dp[i-1][j-x[i]];
                dp[i][j]=pick || notPick;
            }    
        }
        return dp[n-1][target];
    }
    public static boolean partitionEqualSubsetSum(int[] x){
        // if(x.length%2!=0) return false;
        int sum=0;
        for(int i : x) sum+=i;
        if(sum%2!=0) return false;
        return subsetSum(x, sum/2);
    }
    public static int partitionTwoSubsetMinAbsSum(int[] x){
        int n = x.length;
        int sum=0;
        for(int i : x) sum+=i;
        boolean[][] dp = new boolean[n][sum+1];
        for(int i=0;i<n;i++) dp[i][0]=true;
        if(x[0]<sum){
            dp[0][x[0]]=true;
        }
        int mini = Integer.MAX_VALUE;
        for(int i=1;i<n;i++){
            for(int j=sum/2;j>=0;j--){
                boolean pick=false,notPick=dp[i-1][j];
                if(x[i]<=j) pick = dp[i-1][j-x[i]];
                dp[i][j]= pick || notPick; 
                if(dp[n-1][j]){
                    int s1 = j;
                    int s2 = sum-j;
                    mini = Math.min(mini, Math.abs(s1-s2));
                }
            }
        }
        return mini;
    }
    public static int countSubsetsSumK(int[] x,int k){
        int n = x.length;
        int[][] dp = new int[n][k+1];
        for(int i=0;i<n;i++) dp[i][0]=1;
        if(x[0]<=k) dp[0][x[0]]=1;

        for(int i=1;i<n;i++){
            for(int j=1;j<=k;j++){
                int pick=0,notPick=dp[i-1][j];
                if(x[i]<=j) pick = dp[i-1][j-x[i]];

                dp[i][j] = pick + notPick;
            }
        }
        return dp[n-1][k];
    }
    public static int countPartitions(int[] x,int d){
        int tol=0;
        for(int i : x) tol+=i;
        if(tol-d<0 || (tol-d)%2!=0) return 0;
        return countPartitions(x, (tol-d)/2);
    }
    public static int targetSum(int[] x,int target){
        return countPartitions(x, target);
    }
    public static int knapsack0By1(int[] wt,int[] val, int W){
        if(W==0) return 0;
        int n = wt.length;
        int[][] dp = new int[n][W+1];
        for(int i=wt[0];i<W;i++){
            dp[0][i] = val[0];
        }
        for(int i=1;i<n;i++){
            for(int w=0;w<=W;w++){
                int pick=Integer.MIN_VALUE,notPick=dp[i-1][w];
                if(w>=wt[i]) pick = val[i] + dp[i-1][w-wt[i]];
                dp[i][w] = Math.max(pick, notPick);
            }
        }
        return dp[n-1][W];
    }
    public static int minCoins(int[] coins, int target){ 
        int n = coins.length;
        int[][] dp = new int[n][target+1];
        for(int i=0;i<n;i++) Arrays.fill(dp[i],Integer.MAX_VALUE);
        for (int j = 0; j <= target; j++) {
            if (j % coins[0] == 0)
                dp[0][j] = j / coins[0];
            else
                dp[0][j] = Integer.MAX_VALUE;
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<=target;j++){
               int pick = Integer.MAX_VALUE;
               int notPick = dp[i-1][j];
               if(coins[i]<=j && dp[i][j-coins[i]]!=Integer.MAX_VALUE) pick = 1+dp[i][j-coins[i]];
               
               dp[i][j] = Math.min(pick, notPick);
            //    if(j==target){
            //     System.out.println("________");
            //    System.out.println(i+" "+j);
            //    System.out.println(pick+" "+notPick);
            //    System.out.println(coins[i]+" "+dp[i][j]);
            //    }
            }
        }
        // for(int i=0;i<n;i++){
        //     System.out.println(dp[i][target]);
        // }
        return dp[n-1][target];
    }
    public static int infinityCoins(int[] coins, int target){ 
        int n = coins.length;
        int[][] dp = new int[n][target+1];
      //  for(int i=0;i<n;i++) Arrays.fill(dp[i],Integer.MAX_VALUE);
        for (int j = 0; j <= target; j++) {
            if (j % coins[0] == 0)
                dp[0][j] = 1;
            else
                dp[0][j] = 0;
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<=target;j++){
               int pick = 0;
               int notPick = dp[i-1][j];
               if(coins[i]<=j && dp[i][j-coins[i]]!=0) pick = dp[i][j-coins[i]];
               
               dp[i][j] = pick + notPick;
            //    if(j==target){
            //     System.out.println("________");
            //    System.out.println(i+" "+j);
            //    System.out.println(pick+" "+notPick);
            //    System.out.println(coins[i]+" "+dp[i][j]);
            //    }
            }
        }
        // for(int i=0;i<n;i++){
        //     System.out.println(dp[i][target]);
        // }
        return dp[n-1][target];
    }
    public static int unboundedKnapsack(int[] wt,int[] val,int W){
        int n = wt.length;
        int[][] dp = new int[n][W+1];
        for(int w=0;w<=W;w++){
            dp[0][w] = (w/wt[0]) * val[0];
        }

        for(int i=1;i<n;i++){
            for(int w=0;w<=W;w++){
                int notPick = dp[i-1][w];
                int pick = 0;
                if(w>=wt[i]) pick = val[i] + dp[i][w-wt[i]];

                dp[i][w] = Math.max(notPick, pick);
            }
        }
        return dp[n-1][W];
    }
    public static int rodCutting(int[] price,int N){
        if(N==0) return 0;
        int l = price.length;
        int[][] dp = new int[l][N+1];
        for(int n=0;n<=N;n++){
            dp[0][n] = n * price[0];
        }
        for(int i=1;i<l;i++){
            for(int n=0;n<=N;n++){
                int pick = 0;
                int notPick = dp[i-1][n];
                if(n>=i+1) pick = price[i] + dp[i][n-i-1];
                dp[i][n] = Math.max(pick, notPick);
            }
        }
        return dp[l-1][N];
    }
    public static int longestCommonSubseq(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int[] prev = new int[m+1];
        int[] cur = new int[m+1];
      ////  int[][] dp = new int[n+1][m+1];
        //for(int i=0;i<n;i++) dp[i][0]=0;
        for(int j=0;j<m;j++) prev[j]=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)) cur[j] = 1 + prev[j-1];
                else cur[j] = Math.max(prev[j], prev[j-1]);
            }  
            prev = cur; 
        }
        return prev[m];        
    }
    public static String longestCommonSubseqPrint(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        // int[] prev = new int[m+1];
        // int[] cur = new int[m+1];
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<n;i++) dp[i][0]=0;
        for(int j=0;j<m;j++) dp[0][j]=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                // System.out.println(i+" "+j);
                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }  
          //  prev = cur; 
        }
        int i=n,j=m;
        StringBuilder sb = new StringBuilder();
        while(i>0 && j>0){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                sb.append(s1.charAt(i-1));
               // System.out.println("**");
                i--;
                j--;
            }
            else if(dp[i][j-1]>dp[i-1][j]) j--;
            else i--;
        }   

        return sb.reverse().toString();    
    }
    public static int longestCommonSubstring(String s1,String s2){
        int n = s1.length();
        int m = s2.length();
        int max=0;
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<n;i++) dp[i][0]=0;
        for(int j=0;j<m;j++) dp[0][j]=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    max = Math.max(max, dp[i][j]);
                }
                else dp[i][j] = 0;
            }
        }

        // System.out.println("  a b z d");
        // for(int[] i: dp){
        //     for(int j : i) System.out.print(j+" ");
        //     System.out.println();
        // }
        return max;
    }
    public static int longestPalindromicSubseq(String s){
        StringBuilder sb = new StringBuilder(s);
        String s2 = sb.reverse().toString();
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            dp[i][0]=0;
            dp[0][i]=0;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        return dp[n][n];
       // return longestCommonSubseqPrint(s, sb.reverse().toString());
    }
    // public static int longestPalindromicSubseq(String s){
    //     StringBuilder sb = new StringBuilder(s);
    //     String s2 = sb.reverse().toString();
    //     int n = s.length();
    //     int[][] dp = new int[n+1][n+1];
    //     for(int i=0;i<=n;i++){
    //         dp[i][0]=0;
    //         dp[0][i]=0;
    //     }
    //     for(int i=1;i<=n;i++){
    //         for(int j=1;j<=n;j++){
    //             if(s.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
    //             else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
    //         }
    //     }
    //     return dp[n][n];
    //    // return longestCommonSubseqPrint(s, sb.reverse().toString());
    // }
    public static String longestPalindromicSubseqPrint(String s){
        StringBuilder sb = new StringBuilder(s);
        String s2 = sb.reverse().toString();
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            dp[i][0]=0;
            dp[0][i]=0;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        int x = dp[n][n];
        int i=n,j=n;
        StringBuilder sb1 = new StringBuilder();
        while (i>0 && j>0) {
            if(s.charAt(i-1)==s2.charAt(j-1)){
                sb1.append(s.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i][j-1]>dp[i-1][j]) j--;
            else i--;
        }
        return sb1.reverse().toString();
       // return longestCommonSubseqPrint(s, sb.reverse().toString());
    }
    public static int minInsertionstoPalin(String s){
        StringBuilder sb = new StringBuilder(s);
        String s2 = sb.reverse().toString();
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            dp[i][0]=0;
            dp[0][i]=0;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        // System.out.print("   ");
        // for(int i=0;i<=n;i++) System.out.print(i+" ");
        // System.out.println();
        // for(int i=0;i<=n;i++){
        //     System.out.print(i+"  ");
        //     for(int j=0;j<=n;j++){
        //         System.out.print(dp[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        return n-dp[n][n];
    //   return s.length()-longestCommonSubseq(s,sb.toString());
    }
    public static int minOpsAtoB(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++)   dp[i][0]=0;
        for(int j=0;j<=m;j++)   dp[0][j] = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j]= Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
      //  return n - dp[n][m];
        return n + m - (2*dp[n][m]);
    }
    public static int shortestCommonSuperseq(String s1,String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++) dp[i][0]=0;
        for(int i=0;i<=m;i++) dp[0][i]=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        int i=n,j=m;
        StringBuilder sb = new StringBuilder();
        String s = s1 + s2;
        System.out.println(s);
        while (i>0 && j>0) {
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i][j-1] >= dp[i-1][j]){
                sb.append(s2.charAt(j-1));
                j--;
            }
            else{
                sb.append(s1.charAt(i-1));
                i--;
            }
        }
        while (i>0) {
            sb.append(s1.charAt(i-1));
            i--;
        }
        while (j>0) {
            sb.append(s2.charAt(j-1));
            j--;
        }
        System.out.println(sb.reverse().toString());
        return n + m - dp[n][m];

    }
    public static void main(String[] args) {
        System.out.println(shortestCommonSuperseq("brute", "groot"));
        // System.out.println(minOpsAtoB("abcd", "anc"));
       // System.out.println(minInsertionstoPalin("abcaa"));
        // System.out.println(longestPalindromicSubseq("bbabcbcab"));
        // System.out.println(longestPalindromicSubseqPrint("bbabcbcab"));
       // System.out.println(longestCommonSubstring("abcd", "abzd"));
        //System.out.println(longestCommonSubseqPrint("abcde", "bdgek"));
        // int[] price = {2,5,7,8,10};
        // System.out.println(rodCutting(price, 5));
       // int[] arr = {1,2,3};
       // System.out.println(minCoins(arr, 7));
      // System.out.println(infinityCoins(arr, 4));
        // int[] wt = {2,4,6};
        // int[] val = {5,11,13};
        // System.out.println(unboundedKnapsack(wt, val, 10));
        // System.out.println(knapsack0By1(wt, val, 8));
        // int[] a = {1,2,2,3};
        // System.out.println(countSubsetsSumK(a, 3));
        // int[] a = {3,2,7};
        // System.out.println(partitionTwoSubsetMinAbsSum(a));
        // int[] a = {4,3,2,1};
        // int[] b = {2,5,1,6,7};
        // System.out.println(subsetSum(a, 5));
        // System.out.println(subsetSum(b, 4));
        // System.out.println(fib(5));
        // System.out.println(climbingStairs(10));
        // System.out.println(houseRobberII(new int[]{3,2,7,10}));
        // int[][] x = {{10,50,1},{5,100,11}};
        // System.out.println(ninjaTraining(x));
        // int[][] grid = new int[10][10];
        // for (int i = 0; i < 10; i++) {
        //     Arrays.fill(grid[i], 1);
        // }
        // System.out.println(uniquePath(grid));
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
        // System.out.println(uniquePathsII(grid));
        // int[][] grid = {
        //     {1, 3, 1, 2, 8},
        //     {1, 5, 1, 3, 2},
        //     {4, 2, 1, 7, 3},
        //     {2, 1, 2, 1, 4},
        //     {3, 2, 1, 5, 1}
        // };

        // System.out.println(minPathSumGrid(grid));

        // int[][] tri = {
        //     {1},
        //     {2,3},
        //     {3,6,7},
        //     {8,9,6,10}
        // };
        // System.out.println(triangle(tri));

        // int[][] spiral = {
        //     {1,2,3},
        //     {8,9,4},
        //     {7,6,5}
        // };
        // System.out.println(maxFallingPathSum(spiral));
    }
}
