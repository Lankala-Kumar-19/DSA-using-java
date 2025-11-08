package Graphs;

import java.util.*;
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
    public String toString(){
        return "first: "+first+" second: "+second+" x: "+time;
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
    public static void dfsX(int row,int col,int[][] vis,char mat[][],int delrow[],int[] delcol){
        vis[row][col]=1;
        int n=mat.length;
        int m=mat[0].length;
        for(int i=0;i<4;i++){
            int nrow = row+delrow[i];
            int ncol = col + delcol[i];
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==0 && mat[nrow][ncol]=='O'){
                dfsX(nrow, ncol, vis, mat, delrow, delcol);
            }
        }
    }
    public static char[][] OtoX(int n,int m,char[][] mat){
        int[][] vis = new int[n][m];
        int[] delrow = {-1,0,1,0};
        int[] delcol = {0,-1,0,1};
        for(int j=0;j<m;j++){
            if(vis[0][j]==0 && mat[0][j]=='O'){
                dfsX(0, j, vis, mat, delrow, delcol);
            }
            if(vis[n-1][j]==0 && mat[n-1][j]=='O'){
                dfsX(n-1, j, vis, mat, delrow, delcol);
            }
        }
        for(int i=0;i<n;i++){
            if(vis[i][0]==0 && mat[i][0]=='O'){
                dfsX(i, 0, vis, mat, delrow, delcol);
            }
            if(vis[i][m-1]==0 && mat[i][m-1]=='O'){
                dfsX(i, m-1, vis, mat, delrow, delcol);
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(vis[i][j]==0 && mat[i][j]=='O'){
                    vis[i][j]=1;
                    mat[i][j]='X';
                }
            }
        }
        return mat;
    }
    public static int numberOfEnclaves(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        int[][] vis = new int[n][m];
        Queue<Pair> q = new LinkedList<>();
        for(int i=0;i<m;i++){
            if(vis[0][i]==0 && grid[0][i]==1){
                vis[0][i]=1;
                q.offer(new Pair(0, i));
            }
            if(vis[n-1][i]==0 && grid[n-1][i]==1){
                vis[n-1][i]=1;
                q.offer(new Pair(n-1, i));
            }
        }
        for(int j=0;j<n;j++){
            if(vis[j][0]==0 && grid[j][0]==1){
                vis[j][0]=1;
                q.offer(new Pair(j, 0));
            }
            if(vis[j][m-1]==0 && grid[j][m-1]==1){
                vis[j][m-1]=1;
                q.offer(new Pair(j, m-1));
            }
        }
        int[] delrow={-1,0,1,0};
        int[] delcol={0,-1,0,1};
        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            q.poll();
            for(int i=0;i<4;i++){
                int nrow = row + delrow[i];
                int ncol = col+delcol[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==0 && grid[nrow][ncol]==1){
                    vis[nrow][ncol]=1;
                    q.offer(new Pair(nrow, ncol));
                }
            }
        }
        int cnt=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(vis[i][j]==0 && grid[i][j]==1){
                    vis[i][j]=1;
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public static int numberOfDistinctIslands(int[][] grid){
        Set<ArrayList<String>> set = new HashSet<>();
        int n=grid.length;
        int m=grid[0].length;
        int[][] vis = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(vis[i][j]==0 && grid[i][j]==1){
                    ArrayList<String> vec = new ArrayList<>();
                    numberOfDistinctIslands(i, j, vis, grid, vec, i, j);
                    set.add(vec);
                }
            }
        }
        return set.size();
    }
    public static void numberOfDistinctIslands(int row,int col,int[][] vis,int[][] grid,ArrayList<String> vec,int row0,int col0){
        vis[row][col]=1;
        vec.add(makeKey(row-row0,col-col0));
        int n = grid.length;
        int m = grid[0].length;
        int[] delrow = {-1,0,1,0};
        int[] delcol = {0,-1,0,1};
        for(int i=0;i<4;i++){
            int nrow = row+delrow[i];
            int ncol = col + delcol[i];
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==0 && grid[nrow][ncol]==1){
                numberOfDistinctIslands(nrow, ncol, vis, grid, vec, row0, col0);
            }
        }
    }
    public static String makeKey(int r,int c){
        return r+","+c;
    }
    public static boolean checkBipartiteBFS(int v,ArrayList<ArrayList<Integer>> ad){
        int[] vis = new int[v];
        for(int i=0;i<v;i++) vis[i]=-1;
        for(int i=0;i<v;i++){
            if(vis[i]==-1){
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                vis[i]=0;
                while (!q.isEmpty()) {
                    int cur = q.peek();
                    q.poll();
                    for(Integer it : ad.get(cur)){
                        if(vis[it]==-1){
                            vis[it] = 1-vis[cur];
                            q.offer(it);
                        }
                        else if(vis[it]==vis[cur]){
                            return false;
                        }
                    }
                }
            }
        }
        return true;

    }
    public static boolean checkBipartiteDFS(int v,ArrayList<ArrayList<Integer>> ad){
        int[] vis = new int[v];
        Arrays.fill(vis, -1);
        for(int i=0;i<v;i++){
            if(vis[i]!=-1){
                vis[i]=0;
                if(!checkBipartiteDFSHelper(i, vis, ad)) return false;
            }
        }
        return true;
    }
    public static boolean checkBipartiteDFSHelper(int n,int[] vis,ArrayList<ArrayList<Integer>> ad){
        for(Integer it : ad.get(n)){
            if(vis[it]==-1){
                vis[it] = 1-vis[n];
                if(!checkBipartiteDFSHelper(it, vis, ad)) return false;
            }
            else if(vis[it]==vis[n ]) return false;
        }
        return true;
    }
    public static boolean detectCycleDFS(int v,ArrayList<ArrayList<Integer>> ad){
        int[] vis = new int[v];
        int[] pathVis = new int[v];
        for(int i=0;i<v;i++){
            if(vis[i]==0){
                if(detectCycleDFSHelper(i, vis, pathVis, ad)) return true;
            }
        }
        return false;
    }
    public static boolean detectCycleDFSHelper(int node,int[] vis,int[] pathVis,ArrayList<ArrayList<Integer>> ad){
        vis[node]=1;
        pathVis[node]=1;
        for(Integer it : ad.get(node)){
            if(vis[it]==0){
                if(detectCycleDFSHelper(it, vis, pathVis, ad)) return true;
            }
            else if(pathVis[it]==1) return true;
        }
        pathVis[node]=0;
        return false;
    }
    public static List<Integer> safeStates(int v,ArrayList<ArrayList<Integer>> ad){
        int[] vis = new int[v];
        int[] pathVis = new int[v];
        int[] check = new int[v];
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<v;i++){
            if(vis[i]==0) safeStatesHelper(i, vis, pathVis, check, res, ad);
        }
        for(int i=0;i<v;i++){
            if(check[i]==1) res.add(i);
        }
        return res;
    }
    public static boolean safeStatesHelper(int node,int[] vis,int[] pathVis,int[] check,List<Integer> res,ArrayList<ArrayList<Integer>> ad){
        vis[node]=1;
        pathVis[node]=1;
        check[node]=0;
        for(Integer it : ad.get(node)){
            if(vis[it]==0){
                if(safeStatesHelper(it, vis, pathVis,check, res, ad)) return true;
                //if(!res.contains(node))
            }
            else if(pathVis[it]==1){
               // res.remove(node);
                return true;
            }
        }
        check[node]=1;
        pathVis[node]=0;
        return false;
    }
    public static List<Integer> topologicalSortDFS(int v,ArrayList<ArrayList<Integer>> ad){
        int[] vis = new int[v];
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<v;i++){
            if(vis[i]==0) topologicalSortDFSHelper(i, vis, st, ad);
        }
        List<Integer> res = new ArrayList<>();
        while (!st.isEmpty()) {
            res.add(st.pop());
        }
        return res;

    }
    public static void topologicalSortDFSHelper(int node,int[] vis,Stack<Integer> st,ArrayList<ArrayList<Integer>> ad){
        vis[node]=1;
        for(Integer it : ad.get(node)){
            if(vis[it]==0){
                topologicalSortDFSHelper(it, vis, st, ad);
            }
        }
        st.push(node);
    }
    public static List<Integer> topologicalSortBFS(int v,ArrayList<ArrayList<Integer>> ad){
        int[] inDegree = new int[v];

        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        //Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<v;i++){
            for(Integer it : ad.get(i)){
                inDegree[it]++;
            }
        }
        for(int i=0;i<v;i++){
            if(inDegree[i]==0) q.add(i);
        }
        while (!q.isEmpty()) {
            int n = q.poll();
            res.add(n);
            for(Integer it : ad.get(n)){
                inDegree[it]--;
                if(inDegree[it]==0) q.add(it);
            }
        }
        return res;
    }
    public static boolean cycleExist(int v,ArrayList<ArrayList<Integer>> ad){
        List<Integer> topo = topologicalSortBFS(v, ad);
        return v==topo.size() ? false : true;
    }
    public static boolean courseSchedule(int v,int[][] pre){
        ArrayList<ArrayList<Integer>> ad = new ArrayList<>();
        for(int i=0;i<v;i++){
            ad.add(new ArrayList<>());
        }
        int m = pre.length;
        for(int i=0;i<m;i++){
            ad.get(pre[i][1]).add(pre[i][0]);
        }
        int[] inDegree = new int[v];
        for(int i=0;i<v;i++){
            for(Integer it : ad.get(i)){
                inDegree[it]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<v;i++){
            if(inDegree[i]==0) q.add(i);
        }
        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            topo.add(node);
            for(Integer it : ad.get(node)){
                inDegree[it]--;
                if(inDegree[it]==0) q.offer(it);
            }
        }
        return topo.size()==v ? true : false;
    }
    public static List<Integer> safeStatesBFS(int v,ArrayList<ArrayList<Integer>> ad){
        ArrayList<ArrayList<Integer>> rev = new ArrayList<>();
        for(int i=0;i<v;i++){
            rev.add(new ArrayList<>());
        }
        int[] inDegree = new int[v];
        for(int i=0;i<v;i++){
            for(Integer it : ad.get(i)){
                rev.get(it).add(i);
                inDegree[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<v;i++){
            if(inDegree[i]==0) q.offer(i);
        }
        List<Integer> safe = new ArrayList<>();
        while (!q.isEmpty()) {
            int n = q.poll();
            safe.add(n);
            for(Integer it : rev.get(n)){
                inDegree[it]--;
                if(inDegree[it]==0) q.offer(it);
            }
        }
        return safe;
    }
    public static int alienDictonary(int n,int k,String[] dict){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<k;i++) adj.add(new ArrayList<>());
        for(int i=0;i<n-1;i++){
            String s1 = dict[i];
            String s2 = dict[i+1];
           // System.out.println(s2);
            int j=0;
            while (j<s1.length() && j<s2.length()) {
                //System.out.println(s1.charAt(j)+"   "+s2.charAt(j));
                if(s1.charAt(j)!=s2.charAt(j)){
                    adj.get(s1.charAt(j)-'a').add(s2.charAt(j)-'a');
                    break;
                }
                j++;
            }
        }
        List<Integer> res = topologicalSortBFS(k, adj);
        return res.size()==k ? 1 : 0; 
    }
    // public static int[] shortestPath(int n,int m,ArrayList<ArrayList<Integer>> adj){
        
    //     int[] dist = new int[n];
    //     Arrays.fill(dist, -1);
    //     dist[0]=0;
    //     int[] inDegree = new int[n];
    //     for(int i=0;i<m;i++){
    //         inDegree[adj.get(i).get(1)]++;
    //     }
    //     for(int i : inDegree) System.out.print(i+" ");
    //     //System.out.println();
    //     Queue<Pair> q = new LinkedList<>();
    //     for(int i=0;i<n;i++){
    //         if(inDegree[i]==0) q.offer(new Pair(adj.get(i).get(1), adj.get(i).get(0), adj.get(i).get(2)));
    //     }
    //     System.out.println(q.peek().toString());
    //     while (!q.isEmpty()) {
    //         int node = q.peek().first;
    //         int parent = q.peek().second;
    //         int len = q.peek().time;
    //         q.poll();
    //         for(int i=0;i<m;i++){
    //             if(adj.get(i).get(1)==node && dist[node]==-1){
    //                 dist[node] = dist[parent] + len; 
    //                 inDegree[node]--;
    //                 if(inDegree[node]==0){

    //                 }
    //             }
    //         }
    //     }
    //     return dist;

    // }
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(Arrays.asList(
    new ArrayList<>(Arrays.asList(0, 1, 2)),
    new ArrayList<>(Arrays.asList(0, 4, 1)),
    new ArrayList<>(Arrays.asList(4, 5, 4)),
    new ArrayList<>(Arrays.asList(4, 2, 2)),
    new ArrayList<>(Arrays.asList(1, 2, 3)),
    new ArrayList<>(Arrays.asList(2, 3, 6)),
    new ArrayList<>(Arrays.asList(5, 3, 1))
));
        for(int i=0;i<7;i++) System.out.println(i+" "+adj.get(i));
        //System.out.println(adj);
       System.out.println(shortestPath(6, 7, adj));
        // String[] a = {"baa","abcd","abca","cab","cad"};
        // System.out.println(alienDictonary(5,4, a));
//         int v = 7;
//         ArrayList<ArrayList<Integer>> adj= new ArrayList<>();
//         for (int i = 0; i < v; i++) {
//             adj.add(new ArrayList<>());
//         }
// // adj.get(0).add(1);
// adj.get(3).add(1);
// adj.get(2).add(3);
// //adj.get(3).add(1); // cycle
// adj.get(4).add(0);
// adj.get(4).add(1);
// adj.get(5).add(0);
// adj.get(5).add(2);
//         for(int i=0;i<6;i++){
//             System.out.println(i+" "+adj.get(i));
//         }
       // System.out.println(safeStates(v, adj));
       //System.out.println(topologicalSortDFS(v, adj));
       //System.out.println(topologicalSortBFS(6, adj));
        // System.out.println(checkBipartiteBFS(4, adj1));
        // System.out.println(checkBipartiteDFS(4, adj1));
        //
         // System.out.println(dfsOfGraph(v, adj));
        // // System.out.println(numberOfProvinces(v, adj));
//         int[][] matrix = {
//             {0, 1, 1, 0},
//             {0, 1, 1, 0},
//             {0, 0, 1, 0},
//             {0, 0, 0, 0},
        
//             {1, 1, 0, 1}
//         };
//         //System.out.println(numberOfIslands(matrix));
//         int[][] image = {
//             {0, 0, 0},
//             {0, 1, 0},
//             {1, 0, 1}
//         };
//         // printGrid(image);
//         // floodFill(1, 1, 2, image);
//         // System.out.println();
//         // printGrid(image);

//         int[][] grid = {
//             {2, 1, 1},
//             {1, 1, 0},
//             {0, 1, 1}
//         };
//         printGrid(image);
//         System.out.println();
//         printGrid(distanceOfnearestCell(image));
//        // System.out.println(rottenOranges(2, 1, grid));
//         //System.out.println();
//         //printGrid(grid);
//         //System.out.println(isCycle(v, adj));
//         int[][] grid1 = {
//     {0, 0, 0, 0},
//     {1, 0, 1, 0},
//     {0, 1, 1, 0},
//     {0, 0, 0, 0}
// };
//         System.out.println(numberOfEnclaves(grid1));


    }
}
