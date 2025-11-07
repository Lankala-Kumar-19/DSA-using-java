package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
class Pair{
    int first,second;
    int time;
    public Pair(int first,int second){
        this.first=first;
        this.second=second;
    }
    public Pair(int first,int second,int time){
        this.first=first;
        this.second=second;
        this.time=time;
    }

}
public class Demo {
    public static ArrayList<Integer> bfsOfGraph(int v,ArrayList<ArrayList<Integer>> ad){
        boolean[] vis = new boolean[v];
        ArrayList<Integer> bfs = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        vis[0]=true;
        while (!q.isEmpty()) {
            Integer node = q.poll();
            bfs.add(node);
            for(Integer it : ad.get(node)){
                if(!vis[it]){
                    vis[it]=true;
                    q.add(it);
                }
            }
        }
        return bfs;
    }
    public static ArrayList<Integer> dfsOfGraph(int v,ArrayList<ArrayList<Integer>> ad){
        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] vis = new boolean[v];
        vis[0]=true;
        dfs(0, vis, dfs, ad);
        return dfs;
    }
    public static void dfs(Integer node,boolean[] vis,ArrayList<Integer> dfs,ArrayList<ArrayList<Integer>> ad){
        vis[node]=true;
        dfs.add(node);
        for(Integer it : ad.get(node)){
            if(!vis[it]) dfs(it, vis, dfs, ad);
        }
    }
    public static int numberOfProvinces(int v,ArrayList<ArrayList<Integer>> ad){
        int cnt =0;
        boolean[] vis = new boolean[v];
        //vis[0]=true;
        for(int i=0;i<v;i++){
            if(!vis[i]){
                //System.out.println("*");
                cnt++;
                dfs(i, vis, new ArrayList<>(), ad);
                
            }
        }
        return cnt;
    }
    public static void bfs(int row,int col,int[][] grid,boolean[][] vis){
        vis[row][col]=true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row, col));
        int n = grid.length;
        int m = grid[0].length;
        while (!q.isEmpty()) {
            int r = q.peek().first;
            int c = q.peek().second;
            q.poll();
            for(int delrow=-1;delrow<2;delrow++){
                for(int delcol=-1;delcol<2;delcol++){
                    int nrow = r+delrow;
                    int ncol = c+delcol;
                    if(nrow>=0 && nrow<grid.length && ncol>=0 && ncol<grid[0].length && grid[nrow][ncol]==1 && !vis[nrow][ncol]){
                        vis[nrow][ncol]=true;
                        q.add(new Pair(nrow, ncol));
                    }
                }
            }
        }
    }
    public static int numberOfIslands(int[][] islands){
       // ArrayList<ArrayList<Integer>> ad = new ArrayList<>();
        boolean[][] vis = new boolean[islands.length][islands[0].length];
        int cnt=0;
        int n=islands.length;
        int m=islands[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!vis[i][j] && islands[i][j]==1){
                    cnt++;
                    bfs(i,j,islands,vis);
                }
            }
        }
        // int v=0;
        // for(int i=0;i<islands.length;i++){
        //     ad.add(new ArrayList<>());
        //     //ArrayList<Integer> x = new ArrayList<>();
        //     for(int j=0;j<islands[i].length;j++){
        //         if(islands[i][j]!=0){
        //             v++;
        //             ad.get(i).add(j);
        //             //x.add(j);
        //         }
        //     }
        // }
        // int c=0;
        // for(ArrayList<Integer> q : ad){
        //     System.out.println(c+" "+q);
        //     c++;
        // }
        return cnt;
        //return numberOfProvinces(v, ad);     
    }
    public static void floodFill(int sr,int sc,int newColor,int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        boolean[][] vis = new boolean[n][m];
        if(newColor==grid[sr][sc]) return;
        int orignal = grid[sr][sc];
        fill(sr, sc, newColor, grid, vis, orignal);
    }
    public static void fill(int sr,int sc,int newColor,int[][] grid,boolean[][] vis,int og){
        if(grid[sr][sc]==newColor) return;
        vis[sr][sc]= true;
        grid[sr][sc]=newColor;
        if(sr>0 && og==grid[sr-1][sc] && !vis[sr-1][sc]){
            fill(sr-1, sc, newColor, grid, vis,og);
        }
        if(sr+1<grid.length && og==grid[sr+1][sc] && !vis[sr+1][sc]){
            fill(sr+1, sc, newColor, grid, vis,og);
        }
        if(sc+1<grid[0].length && og==grid[sr][sc+1] && !vis[sr][sc+1]){
            fill(sr, sc+1, newColor, grid, vis,og);
        }
        if(sc>0 && og==grid[sr][sc-1] && !vis[sr][sc-1]){
            fill(sr, sc-1, newColor, grid, vis,og);
        }
    }
    private static void printGrid(int[][] x){
        for(int[] i : x){
            for(int j : i) System.out.print(j+" ");
            System.out.println();
        }
    }
    public static int rottenOranges(int rot,int fresh,int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        int[][] vis = new int[n][m];
      //  int[][] temp = grid;
        Queue<Pair> q = new LinkedList<>();
        int cntFresh=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==rot){
                    q.offer(new Pair(i, j,0));
                    vis[i][j]=2;
                }
                else vis[i][j]=0;
                if(grid[i][i]==1) cntFresh++;
            }
        }
        int tm=0;
        int[] x = {-1,1,0,0};
        int[] y = {0,0,1,-1};
        int cnt=0;
        while (!q.isEmpty()) {
            int r = q.peek().first;
            int c = q.peek().second;
            int t = q.peek().time;
            tm = Math.max(tm, t);
            q.poll();
            for(int i=0;i<4;i++){
                int nrow = r + x[i];
                int ncol = c + y[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==0 && grid[nrow][ncol]==fresh){
                    vis[nrow][ncol]=2;
                    q.add(new Pair(nrow, ncol, t+1));
                    cnt++;
                }
            }
        }
        printGrid(vis);
        return cnt!=cntFresh ? -1 : tm;
    }
    // public static void rotOranges(int row,int col,int rot,int fresh,int[][] grid,boolean[][] vis,int[] sec){
    //     // vis[row][col] = true;
    //     // grid[row][col]=rot;
    //     // int n=grid.length;
    //     // int m=grid[0].length;
    //     // if(row+1<n && !vis[row+1][col] && grid[row+1][col]==fresh){
    //     //     rotOranges(row+1, col, rot, fresh, grid, vis,sec);
    //     // }
    //     // if(row-1>=0 && !vis[row-1][col] && grid[row-1][col]==fresh){
    //     //     rotOranges(row-1, col, rot, fresh, grid, vis,sec);
    //     // }
    //     // if(col+1<m && !vis[row][col+1] && grid[row][col+1]==fresh){
    //     //     rotOranges(row, col+1, rot, fresh, grid, vis,sec);
    //     // }
    //     // if(col-1>=0 && !vis[row][col-1] && grid[row][col-1]==fresh){
    //     //     rotOranges(row, col-1, rot, fresh, grid, vis,sec);
    //     // }
    //     //sec[0]++;
    //     Queue<Pair> q = new LinkedList<>();
    //     q.offer(new Pair(row, col));
    //     vis[row][col]=true;
    //     int[] x = {-1,1,0,0};
    //     int[] y = {0,0,1,-1};
    //     while (!q.isEmpty()) {
    //         int r = q.peek().first;
    //         int c = q.peek().second;
    //         q.poll();
    //         for(int i=0;i<4;i++){
    //             int delr = r + x[i];
    //             int delc = c + y[i];
    //             if(grid[delr][delc]==fresh){
    //                 vis[delr][delc]=true;
    //                 q.offer(new Pair(delr, delc,));
    //             }
    //         }
    //     }


    // }
    public static boolean isCycle(int v,ArrayList<ArrayList<Integer>> ad){
        // int n = ad.size();
        // int m = ad.get(0).size();
        boolean[] vis = new boolean[v];
        for(int i=0;i<v;i++){
            if(!vis[i]){
               // return detect(i, vis, ad);
               if(detectDfs(i, -1, vis, ad)) return true;
            }
        }
        return false;
    }
    public static boolean detect(int node, boolean[] vis, ArrayList<ArrayList<Integer>> ad){
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(node, -1));
        vis[node]=true;
        while (!q.isEmpty()) {
            int n = q.peek().first;
            int parent = q.peek().second;
            q.poll();
            for(Integer x : ad.get(n)){
                if(!vis[x]){
                    vis[x]=true;
                    q.offer(new Pair(x, n));
                }
                else if(x!=parent){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean detectDfs(int node,int parent,boolean[] vis,ArrayList<ArrayList<Integer>> ad){
        vis[node]=true;
        for(Integer it : ad.get(node)){
            if(!vis[it]){
                if(detectDfs(it, node, vis, ad)) return true;
                //return true;
            }
            else if(it!=parent) return true;
        }
        return false;
    }
    public static int[][] distanceOfnearestCell(int[][] grid){
        int n= grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        int[][] dist = new int[n][m];
        Queue<Pair> q = new LinkedList<>();
       // int cntZeroes=0;
        int[] x = {-1,1,0,0};
        int[] y = {0,0,1,-1};
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    vis[i][j]=1;
                    q.offer(new Pair(i, j, 0));
                }
                // else if(grid[i][j]==0){
                //     vis[i][j]=0;
                //     // cntZeroes++;
                // }
            }
        }
       // int steps=0;

        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            int s = q.peek().time;
            q.poll();
            dist[row][col]=s;
            for(int i=0;i<4;i++){
                int nrow = row + x[i];
                int ncol = col + y[i];
                if(nrow<n && nrow>=0 && ncol<m && ncol>=0 && vis[nrow][ncol]==0){
                    q.offer(new Pair(nrow, ncol, s+1));
                    vis[nrow][ncol]=1;
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) {
        int v = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
        // adj.get(0).add(1);
        // // adj.get(0).add(4);
        // // adj.get(1).add(0);
        // // adj.get(1).add(2);
        // // adj.get(1).add(3);
        // // adj.get(2).add(1);
        // // adj.get(3).add(1);
        // // adj.get(4).add(0);
        // // System.out.println(bfsOfGraph(v,adj));
        // // System.out.println(dfsOfGraph(v, adj));
        // // System.out.println(numberOfProvinces(v, adj));
        int[][] matrix = {
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0},
        
            {1, 1, 0, 1}
        };
        //System.out.println(numberOfIslands(matrix));
        int[][] image = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 0, 1}
        };
        // printGrid(image);
        // floodFill(1, 1, 2, image);
        // System.out.println();
        // printGrid(image);

        int[][] grid = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        printGrid(image);
        System.out.println();
        printGrid(distanceOfnearestCell(image));
       // System.out.println(rottenOranges(2, 1, grid));
        //System.out.println();
        //printGrid(grid);
        //System.out.println(isCycle(v, adj));

    }
}
