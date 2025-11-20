package Graphs;

import java.util.*;
class Pair{
    int first,second;
    int time;
    String sfirst;
    Pair p;
    public Pair(int first,int second){
        this.first=first;
        this.second=second;
    }
    public Pair(int first,Pair p){
        this.first=first;
        this.p=p;
    }
    public Pair(int first,int second,int time){
        this.first=first;
        this.second=second;
        this.time=time;
    }
    public Pair(String sfirst,int second){
        this.sfirst=sfirst;
        this.second=second;
    }

    public String toString(){
        return "first: "+first+" second: "+second+" x: "+time;
    }

}
class Edge implements Comparable<Edge>{
    int src,dest,weight;
    public Edge(int src,int dest,int weight){
        this.src=src;
        this.dest=dest;
        this.weight=weight;
    }
    public int compareTo(Edge compareEdge){
        return this.weight - compareEdge.weight;
    }
};
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
    public static void topoSortSP(int node,ArrayList<ArrayList<Pair>> adj,int[] vis,Stack<Integer> st){
        vis[node]=1;
        for(int i=0;i<adj.get(node).size();i++){
            int v = adj.get(node).get(i).first;
            if(vis[v]==0){
                topoSortSP(v, adj, vis, st);
            }
        }
        st.push(node);
    }
    public static int[] shortestPath(int n,int m,int[][] edge){  
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<Pair>());
        }
        for(int i=0;i<m;i++){
            int u = edge[i][0];
            int v = edge[i][1];
            int wt = edge[i][2];
            adj.get(u).add(new Pair(v, wt));
        } 
        int[] vis = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<n;i++){
            if(vis[i]==0){
                topoSortSP(i, adj, vis, st);
            }
        } 
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0]=0;
        while (!st.isEmpty()) {
            int node = st.peek();
            st.pop();
            for(int i=0;i<adj.get(node).size();i++){
                int v = adj.get(node).get(i).first;
                int wt = adj.get(node).get(i).second;
                if(dist[node] + wt < dist[v]){
                    dist[v] = dist[node] + wt;
                }
            }
        }
        for(int i=0;i<n;i++) if(dist[i]==Integer.MAX_VALUE) dist[i]=-1;
        return dist;
    }
    // public static void topoSortSPU(int node,Stack<Integer> st,int[] vis,ArrayList<ArrayList<Pair>> adj){
    //     vis[node]=1;
    //     for(int i=0;i<adj.get(node).size();i++){
    //         int v = adj.get(node).get(i).first;
    //         if(vis[v]==0) topoSortSPU(v, st, vis, adj);
    //     }
    //     st.push(node);
    // }
    public static int[] shortestPathUnit(int n,int m,int[][] edge){
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<Pair>());
        for(int i=0;i<m;i++){
            int u = edge[i][0];
            int v = edge[i][1];
            adj.get(u).add(new Pair(v, 1));
            adj.get(v).add(new Pair(u, 1));
        }
        int[] dist = new int[n];
        int[] vis = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0]=0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));
       // topoSortSPU(0, st, vis, adj);
       vis[0]=1;
        while (!q.isEmpty()) {
            int node = q.peek().first;
            q.poll();
            for(int i=0;i<adj.get(node).size();i++){
                int v = adj.get(node).get(i).first;
               // System.out.println(node+" "+v+" "+dist[node]+" "+dist[v]);
                if(vis[v]==0){
                    vis[v]=1;
                    dist[v] = dist[node] + 1;
                    q.offer(new Pair(v, dist[v]));
                }
            }
        }
        for(int i=0;i<n;i++) if(dist[i]==Integer.MAX_VALUE) dist[i]=-1;
        return dist;
    }
    public static int wordLadder(String beginWord,String endWord,String[] s){
        Set<String> set = new HashSet<>();
        for(String i : s) set.add(i);
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(beginWord, 1));
        while (!q.isEmpty()) {
            String x = q.peek().sfirst;
            //System.out.println("---"+x.toString());
            int lvl = q.peek().second;
            if(x.toString().equals(endWord)) return lvl;
            q.poll();
            char[] word = x.toCharArray();
            for(int i=0;i<x.length();i++){
               // StringBuilder sb = new StringBuilder(x);
                char old = word[i];
                for(char c='a';c<='z';c++){
                    if(old==c) continue;
                    //sb.setCharAt(i, c);
                    word[i]=c;
                    String newword = new String(word);
                   // System.out.println(sb.toString());
                    if(set.contains(newword)){
                       // System.out.println("****"+sb.toString());
                        q.offer(new Pair(newword, lvl+1));
                        set.remove(newword);
                    }
                }
                word[i]=old;
            }
        }
        return -1;
    }
    public static ArrayList<ArrayList<String>> wordLadderII(String beginWord,String endWord,String[] s){
        Queue<ArrayList<String>> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        for(String i : s) set.add(i);
        int lvl=1;
        ArrayList<String> usedOnLevel = new ArrayList<>();
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        usedOnLevel.add(beginWord);
        q.offer(usedOnLevel);
        while (!q.isEmpty()) {
            ArrayList<String> list = q.poll();
            if(list.size()>lvl){
                lvl=list.size();
                for(String it : usedOnLevel) set.remove(it);
                usedOnLevel.clear();
            }
            String word = list.get(list.size()-1);
            if(word.equals(endWord)){
                if(ans.isEmpty() || ans.get(0).size()==list.size()) ans.add(new ArrayList<>(list));
              //  else if(ans.get(0).size()==list.size()) ans.add(list);
            }
            char[] x = word.toCharArray();
            for(int i=0;i<word.length();i++){
                char old = x[i];
                for(char c ='a';c<='z';c++){
                    if(old==c) continue;
                    x[i]=c;
                    String newString = new String(x);
                    if(set.contains(newString)){
                        list.add(newString);
                        ArrayList<String> temp = new ArrayList<>(list);
                        q.offer(temp);
                        usedOnLevel.add(newString);
                        list.remove(list.size()-1);
                    }
                }
                x[i]=old;
            }
        }
        return ans;
    }
    public static int[] dijkstraAlgo(int n,int[][] edge){
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<Pair>());
        }
        for(int i=0;i<edge.length;i++){
            int u = edge[i][0];
            int v = edge[i][1];
            int wt = edge[i][2];
            adj.get(u).add(new Pair( v,wt));
            adj.get(v).add(new Pair(u, wt));
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0]=0;
        int[] vis = new int[n];

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a,b)-> a.second - b.second);
        minHeap.offer(new Pair(0, 0));
        while (!minHeap.isEmpty()) {
            int wt = minHeap.peek().second;
            int node = minHeap.peek().first;
            minHeap.poll();
            if(vis[node]==1) continue;
            vis[node]=1;
            for(int i=0;i<adj.get(node).size();i++){
                int v = adj.get(node).get(i).first;
                int vwt = adj.get(node).get(i).second;
                if(wt + vwt < dist[v]){
                    dist[v]=vwt+wt;
                    minHeap.offer(new Pair( v,dist[v]));
                }
                
            }
        }
        for(int i=0;i<n;i++) if(dist[i]==Integer.MAX_VALUE) dist[i]=-1;
        return dist; 
    }
    public static int[] dijkstraAlgoSet(int n,int[][] edge){
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<Pair>());
        }
        for(int i=0;i<edge.length;i++){
            int u = edge[i][0];
            int v = edge[i][1];
            int wt = edge[i][2];
            adj.get(u).add(new Pair( v,wt));
            adj.get(v).add(new Pair(u, wt));
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0]=0;
        int[] vis = new int[n];

        TreeSet<Pair> set = new TreeSet<>((a,b)-> (a.second==b.second) ? (a.first-b.first) : (a.second - b.second));
        set.add(new Pair(0, 0));
        while (!set.isEmpty()) {
            Pair p = set.first();
            int wt = p.second;
            int node = p.first;
            set.pollFirst();
            if(vis[node]==1) continue;
            vis[node]=1;
            for(int i=0;i<adj.get(node).size();i++){
                int v = adj.get(node).get(i).first;
                int vwt = adj.get(node).get(i).second;
                if(wt + vwt < dist[v]){
                    set.remove(new Pair(v, dist[v]));
                    dist[v]=vwt+wt;
                    set.add(new Pair(v, dist[v]));
                }
                
            }
        }
        for(int i=0;i<n;i++) if(dist[i]==Integer.MAX_VALUE) dist[i]=-1;
        return dist; 
    }
    public static List<Integer> printShortestPath(int n,int[][] edges){
        int[] dist = new int[n+1];
        dist[1]=0;
        int[] parent = new int[n+1];
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<edges.length;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(new Pair(v, edges[i][2]));
            adj.get(v).add(new Pair(u, edges[i][2]));
        }
        for(int i=0;i<=n;i++) parent[i]=i;
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> a.second-b.second);
        pq.offer(new Pair(1, 0));
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int node = p.first;
            int dis = p.second;
            for(Pair it : adj.get(node)){
                int x = it.first;
                int xdis = it.second; 
                if(dis + xdis < dist[x]){
                    dist[x]= xdis + dis;
                    pq.offer(new Pair(x, dist[x]));
                    parent[x]=node;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        if(dist[n]==Integer.MAX_VALUE) return path;
        int node = n;
        while (parent[node]!=node) {
            path.add(node);
            node=parent[node];
        }
        path.add(1);
        Collections.reverse(path);
        return path;
    }
    public static int shortestDistanceInAMaze(int[] src,int[] dest,int[][] grid){
        if(src[0]==dest[0] && dest[1]==src[1]) return 0;
        int n = grid.length;
        int m = grid[0].length;
      //  ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        int[][] dist = new int[n][m];
        for(int[] i : dist) Arrays.fill(i, Integer.MAX_VALUE);
        dist[src[0]][src[1]] = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,src[0],src[1]));
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int dis = p.first;
            int r = p.second;
            int c = p.time;
            for(int i=0;i<4;i++){
                int nrow = r + dr[i];
                int ncol = c + dc[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && grid[nrow][ncol]==1 && dis+1< dist[nrow][ncol]){
                    dist[nrow][ncol] = 1 + dis;
                    if(nrow==dest[0] && ncol==dest[1]) return dist[nrow][ncol];
                    q.offer(new Pair(dis+1, nrow, ncol));
                }
            }
        }
        return -1;
    }
    public static int pathWithMinEffort(int[][] grid){
        int n =grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];
        for(int[] i : dist) Arrays.fill(i, Integer.MAX_VALUE);
        dist[0][0] =0;
        //int[] x = new int[3];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[0]-b[0]);
        pq.offer(new int[]{0,0,0});
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};
        while (!pq.isEmpty()) {
            int[] x = pq.poll();
            int effort = x[0];
            int r = x[1];
            int c = x[2];
            if(r==n-1 && c==m-1) return effort;
            for(int i=0;i<4;i++){
                int nrow = r + dr[i];
                int ncol = c + dc[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && Math.max(Math.abs(grid[nrow][ncol] - grid[r][c]),effort) < dist[nrow][ncol]){
                    dist[nrow][ncol] = Math.max((int)Math.abs(grid[nrow][ncol] - grid[r][c]),effort) ;
                    pq.offer(new int[]{dist[nrow][ncol],nrow,ncol});
                }
            }
        }
        return -1;
    }
    public static int cheapestFlightsKStops(int n,int src,int dst,int k,int[][] routes){
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src]=0;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<n;i++){
            int u = routes[i][0];
            int v = routes[i][1];
            int price = routes[i][2];
            adj.get(u).add(new Pair(v, price));
            //adj.get(v).add(new Pair(u, price));
        }
        Queue<int[]> q = new LinkedList<>();
        //int[]{stops,node,price}
        q.offer(new int[]{0,src,0});
        while (!q.isEmpty()) {
            int[] x = q.poll();
            int stops = x[0];
            int node = x[1];
            int price = x[2];
            if(stops>k) continue;
            for(Pair p : adj.get(node)){
                if( price + p.second < dist[p.first] && stops<=k){
                    dist[p.first] = price + p.second;
                    q.offer(new int[]{stops+1,p.first,dist[p.first]});
                }
            }
        }
        if(dist[dst]==Integer.MAX_VALUE) return -1;
        return dist[dst];
    }
    public static int minMultiplications(int st,int end,int[] arr){
        //int[] {val,steps}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{st,0});
        int[] dist = new int[100000];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[st]=0;
        while (!q.isEmpty()) {
            int[] x = q.poll();
            // if(x[0]>end) continue; 
            int val = x[0];
            int steps = x[1];
           // if(val>end) continue;
            //if(val==end) return steps;
            for(int i=0;i<arr.length;i++){
                int z = (val*arr[i])%100000;
                if(steps + 1 < dist[z]){
                    dist[z]=steps + 1;
                    if(z==end) return steps+1;
                    q.offer(new int[]{z,steps+1});
                }
                
            }
        }
       // if(dist[end]==Integer.MAX_VALUE)
        return -1;
        //return dist[end];
    }
    public static int numOfWaysToArriveAtDest(int n,int m,int[][] edges){
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int time = edges[i][2];
            adj.get(u).add(new Pair(v, time));
            adj.get(v).add(new Pair(u, time));
        }
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0]=0;
        //int sDist = Integer.MAX_VALUE;
        //int[] {v,time}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[1]-b[1]);
        pq.offer(new int[]{0,0});
        int[] way = new int[n];
        way[0] = 1;
        while (!pq.isEmpty()) {
            int[] x = pq.poll();
            int ad = x[0];
            int time = x[1];
            if(time > dist[ad]) continue;
            for(Pair p : adj.get(ad)){
                int node = p.first;
                int ntime = p.second;
                if(time + ntime < dist[node]){
                    dist[node]=time+ntime;
                    way[node] = way[ad];
                    //System.out.println(node+" "+dist[node]);
                    pq.offer(new int[]{node,dist[node]});
                }
                else if(dist[node]==time+ntime){
                    way[node] = (way[node] + way[ad]) % 1000000007;
                }
            }
        }
        
        return way[n-1];
    }
    public static int[] bellmanFord(int V,ArrayList<ArrayList<Integer>> edges,int s){
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s]=0;
        for(int i=0;i<V-1;i++){
            for(ArrayList<Integer> it : edges){
                int u = it.get(0);
                int v = it.get(1);
                int wt = it.get(2);
                if(dist[u]!=Integer.MAX_VALUE && dist[u] + wt < dist[v]){
                    dist[v] = dist[u] + wt;
                }
            }
        }
        for(ArrayList<Integer> it : edges){
            int u = it.get(0);
            int v = it.get(1);
            int wt = it.get(2);
            if(dist[u]!=Integer.MAX_VALUE && dist[u]+wt < dist[v]){
                int temp[] = new int[1];
                temp[0] = -1;
                return temp;
            }
        }
        return dist;
    }
    public static void floydWarshall(int[][] matrix){
        int n = matrix.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<matrix.length;j++){
                if(matrix[i][j]==-1){
                    matrix[i][j] = Integer.MAX_VALUE;
                }
                if(i==j) matrix[i][j]=0;
            }
        }
        for(int k =0; k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(matrix[i][k]!=Integer.MAX_VALUE && matrix[k][j]!=Integer.MAX_VALUE)
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==Integer.MAX_VALUE) matrix[i][j]=-1;
            }
        }
    }
    public static int findSmallestNumOfNeighbours(int thres,int[][] cities){
        int n =cities.length;
        int m = cities[0].length;
        int cntMax = n,city=-1;
       // int[][] cost = new int[n][m];
        floydWarshall(cities);
        for(int i=0;i<n;i++){
            int cnt=0;
            for(int j=0;j<m;j++){
                if(i!=j && cities[i][j]!=-1 && cities[i][j]<=thres){
                    cnt++;
                }
            }
            if(cnt<=cntMax){
                cntMax=cnt;
                city=i;
            }
        }
        return city;
    }
    public static int primsAlgoMinSpanningTree(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj){
        int[] vis = new int[V];
        //int[]{wt,node}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[0]-b[0]);
        pq.offer(new int[]{0,0});
       // vis[0]=1;
        int sum=0;
        while(!pq.isEmpty()){
            int[] x = pq.poll();
            int node = x[1];
            int wt = x[0];
            if(vis[node]==1) continue;
            vis[node]=1;
            sum+=wt;
            for(int i=0;i<adj.get(node).size();i++){
                int ewt = adj.get(node).get(i).get(1);
                int adjnode = adj.get(node).get(i).get(0);
                if(vis[adjnode]==0){
                    pq.offer(new int[]{ewt,adjnode});
                }
            }
        }
        return sum;
    }
    public static int kruskalsAlgoMinSpanningTree(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj){
        List<Edge> edges = new ArrayList<Edge>();
        for(int i=0;i<V;i++){
            for(int j=0;j<adj.get(i).size();j++){
                int adjNode = adj.get(i).get(j).get(0);
                int wt = adj.get(i).get(j).get(1);
                if(i<adjNode)
                edges.add(new Edge(i, adjNode, wt));
            }
        }
        Disjoint ds = new Disjoint(V);
        Collections.sort(edges);
        int mstWt = 0;
        for(int i=0;i<edges.size();i++){
            int wt = edges.get(i).weight;
            int u = edges.get(i).src;
            int v = edges.get(i).dest;

            if(ds.findUParent(u)!=ds.findUParent(v)){
                mstWt+=wt;
                ds.unionBySize(u, v);
            }
        }
        return mstWt;
    }
    public static int findProvincesDisjoint(int V,ArrayList<ArrayList<Integer>> adj){
        Disjoint ds = new Disjoint(V);
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(adj.get(i).get(j)==1) ds.unionByRank(i, j);
            }
        }
        int cnt=0;
        for(int i=0;i<V;i++){
            if(ds.findUParent(i)==i) cnt++;
        }
        return cnt; 
    }
    public static int numberOfOperationstoMakeNewConnections(int V,ArrayList<ArrayList<Integer>> adj){
        Disjoint ds = new Disjoint(V);
        int e=0;
        for(int i=0;i<adj.size();i++){
            int u = adj.get(i).get(0);
            int v = adj.get(i).get(1);
            if(ds.findUParent(u)==ds.findUParent(v)) e++;
            else ds.unionBySize(u, v);
        }
        int cnt=0;
        for(int i=0;i<V;i++){
            if(ds.findUParent(i)==i) cnt++;
        }
        if(e>=cnt-1)
        return cnt-1;
        return -1;
    }
    public static List<List<String>> accountsMerge(int n,ArrayList<ArrayList<String>> details){
        Disjoint ds = new Disjoint(n);
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j=1;j<details.get(i).size();j++){
                String mail = details.get(i).get(j);
                if(!map.containsKey(mail)) map.put(mail, i);
                else ds.unionBySize(i, map.get(mail));
            }    
        }
        ArrayList<String>[] merged = new ArrayList[n];
        for(int i=0;i<n;i++) merged[i]=new ArrayList<>();

        for(Map.Entry<String,Integer> it : map.entrySet()){
            String mail = it.getKey();
            int node = ds.findUParent(it.getValue());
            merged[node].add(mail);
        }
        List<List<String>> ans = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(merged[i].size()==0) continue;
            Collections.sort(merged[i]);
            List<String> temp = new ArrayList<>();
            temp.add(details.get(i).get(0));
            for(String s : merged[i]) temp.add(s);
            ans.add(temp);
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = 5;
int[][] roads = {
    {0,1,2},
    {0,2,3},
    {1,2,2},
    {1,3,4},
    {2,3,1},
    {3,4,1}
};
System.out.println(numOfWaysToArriveAtDest(n, roads[0].length, roads));
        // int[] arr = {3,10,20};
        // System.out.println(minMultiplications(1, 99999, arr));
// int n = 3;

// int[][] flights = {
//     {0, 1, 100},
//     {1, 2, 100},
//     {0, 2, 500}
// };
// int src = 0;
// int dst = 2;
// int k = 1;
// System.out.println(cheapestFlightsKStops(n, src, dst, k, flights));
//         int[][] heights = {
//     {1, 2, 2, 1},
//     {3, 8, 2, 4},
//     {5, 3, 5, 2},
//     {2, 1, 2, 1}
// };
//         System.out.println(pathWithMinEffort(heights));
        // String[] s = {"des","der","dfr","dgt","dfs"};
        // System.out.println(wordLadderII("der", "dfs", s));

//         ArrayList<ArrayList<Integer>> adj = new ArrayList<>(Arrays.asList(
//     new ArrayList<>(Arrays.asList(0, 1, 2)),
//     new ArrayList<>(Arrays.asList(0, 4, 1)),
//     new ArrayList<>(Arrays.asList(4, 5, 4)),
//     new ArrayList<>(Arrays.asList(4, 2, 2)),
//     new ArrayList<>(Arrays.asList(1, 2, 3)),
//     new ArrayList<>(Arrays.asList(2, 3, 6)),
//     new ArrayList<>(Arrays.asList(5, 3, 1))
// ));
int[][] adj = {
    {0, 1, 2},
    {0, 4, 1},
    {4, 5, 4},
    {4, 2, 2},
    {1, 2, 3},
    {2, 3, 6},
    {5, 3, 1}
};
//     int[] x = (dijkstraAlgoSet(7, adj));
//     int[] y = shortestPath(7, adj.length, adj);
//     for(int i : x) System.out.print(i+" ");
//     System.out.println();
//     for(int i : y) System.out.print(i+" ");
//         int[][] edges = {
//     {0, 1},
//     {0, 3},
//     {1, 2},
//     {3, 4},
//     {4, 5},
//     {5, 6},
//     {2, 6}
// };
        //for(int i=0;i<7;i++) System.out.println(i+" "+adj.get(i));
        // int[] x = (shortestPathUnit(7, edges.length,edges ));
        // for(int i : x) System.out.print(i+" ");
        //System.out.println(adj);
      // System.out.println(shortestPath(6, 7, adj));
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
        int[][] image = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 0, 1}
        };
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
       printGrid(distanceOfnearestCell(image));
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
