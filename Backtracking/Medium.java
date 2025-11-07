package Backtracking;

import java.util.*;

public class Medium {
    public static int nQueens(int n){
        char[][] x = new char[n][n];
        //Arrays.fill(x, '.');
        List<char[][]> res = new ArrayList<>();
        for(int i=0;i<n;i++) Arrays.fill(x[i], '.');
        nQueens(0, n, x, res);
        return res.size();
        
    }
    public static void nQueens(int row,int n,char x[][],List<char[][]> res){
        if(row==n){
            char[][] copy = new char[n][n];
            for(int i=0;i<n;i++){
                copy[i]=x[i].clone();
            }
            res.add(copy);
            return;
        }
        for(int col=0;col<n;col++){
            if(isSafe(x, row, col)){
                x[row][col] = 'Q';
                System.out.println("****row: "+row+" col: "+col);
                nQueens(row+1, n, x, res);
                x[row][col]='.';
                System.out.println("row: "+row+" col: "+col);
            }
                // System.out.println("row: "+row+" col: "+col);
                // nQueens(row, n, x, res);
        }
    }
    private static boolean isSafe(char[][] x,int row,int col){
        for(int i=row;i>=0;i--){
            if(x[i][col]=='Q') return false;
        }
        // for(int i=col;i>=0;i--){
        //     if(x[row][i]=='Q') return false;
        // }
        int i=row,j=col;
        while (i>=0 && j>=0) {
            if(x[i][j]=='Q') return false;
            i--;
            j--;
        }
        i=row;
        j=col;
        while (i>=0 && j<x.length) {
            if(x[i][j]=='Q') return false;
            i--;
            j++;
        }
        return true;
    }
    public static void sudokoSolver(char[][] x){
        solve(x);
    }
    public static boolean solve(char[][] x){
        for(int row=0;row<x.length;row++){
            for(int col=0;col<x[row].length;col++){
                if(x[row][col]=='.'){
                    for(char c = '1';c<='9';c++){
                        if(isCorrect(row, col, c, x)){
                            x[row][col]=c;
                            if(solve(x)) return true;
                            else x[row][col] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean isCorrect(int row,int col,char n,char[][] x){
        for(int i=0;i<9;i++){
            if(x[row][i]==n) return false;

            if(x[i][col]==n) return false;

            if(x[3*(row/3)+i/3][3*(col/3)+i%3]==n) return false;
        }
        return true;

        
        // for(int i=0;i<col;i++){
        //     if(x[row][i]==n) return false;
        // }
        // for(int i=0;i<row;i++){
        //     if(x[i][col]==n) return false;
        // }
        // int rowSt = (row/3) *3;
        // int colSt = (col/3)*3;
        // int rowEnd = rowSt+3;
        // int colEnd = colSt+3;
        // while (rowSt<rowEnd) {
        //     int temp = colSt;
        //     while (temp<colEnd) {
        //         if(x[rowSt][temp]==n) return false;
        //         temp++;
        //     }
        //     rowSt++;
        // }
        
        // return true;
    }
    public static void display(char[][] x){
        for(char[] i : x){
            for(char j : i) System.out.print(j+" ");
            System.out.println();
        }
    }
    public static List<List<String>> palindromePartitioning(String s){
        List<List<String>> res = new ArrayList<>();
        palindromePartitioning(s,0, new ArrayList<>(), res);
        return res;
    }
    public static void palindromePartitioning(String s,int ind,List<String> cur,List<List<String>> res){
        if(ind==s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        };
        for(int i=ind;i<s.length();i++){
            if(isPalindrome(s, ind, i)){
                cur.add(s.substring(ind, i+1));
                palindromePartitioning(s, i+1, cur, res);
                cur.remove(cur.size()-1);
            }

        }

    }
    private static boolean isPalindrome(String s,int i,int j){
        // int i=0,j=s.length()-1;
        while (i<=j) {
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
    public static List<String> findPaths(int[][] a) {
        List<String> res = new ArrayList<>();
        int n = a.length;
        if (a[0][0] == 0 || a[n - 1][n - 1] == 0) return res; // no path possible

        int[][] visited = new int[n][n];
        StringBuilder path = new StringBuilder();
        backtrack(a, 0, 0, visited, path, res);
        return res;
    }

    private static void backtrack(int[][] a, int row, int col, int[][] v, StringBuilder path, List<String> res) {
        int n = a.length;

        // ✅ Base case: reached destination
        if (row == n - 1 && col == n - 1) {
            res.add(path.toString());
            return;
        }

        // Mark current cell as visited
        v[row][col] = 1;

        // ↓ Move Down
        if (row + 1 < n && v[row + 1][col] == 0 && a[row + 1][col] == 1) {
            path.append('D');
            backtrack(a, row + 1, col, v, path, res);
            path.deleteCharAt(path.length() - 1); // backtrack
        }

        // ← Move Left
        if (col - 1 >= 0 && v[row][col - 1] == 0 && a[row][col - 1] == 1) {
            path.append('L');
            backtrack(a, row, col - 1, v, path, res);
            path.deleteCharAt(path.length() - 1);
        }

        // → Move Right
        if (col + 1 < n && v[row][col + 1] == 0 && a[row][col + 1] == 1) {
            path.append('R');
            backtrack(a, row, col + 1, v, path, res);
            path.deleteCharAt(path.length() - 1);
        }

        // ↑ Move Up
        if (row - 1 >= 0 && v[row - 1][col] == 0 && a[row - 1][col] == 1) {
            path.append('U');
            backtrack(a, row - 1, col, v, path, res);
            path.deleteCharAt(path.length() - 1);
        }

        // Unmark current cell before returning (backtrack)
        v[row][col] = 0;
    }

    public static void main(String[] args) {
        //System.out.println(nQueens(4));
        char[][] board = {
    {'5','3','.','.','7','.','.','.','.'},
    {'6','.','.','1','9','5','.','.','.'},
    {'.','9','8','.','.','.','.','6','.'},
    {'8','.','.','.','6','.','.','.','3'},
    {'4','.','.','8','.','3','.','.','1'},
    {'7','.','.','.','2','.','.','.','6'},
    {'.','6','.','.','.','.','2','8','.'},
    {'.','.','.','4','1','9','.','.','5'},
    {'.','.','.','.','8','.','.','7','9'}
};
        // sudokoSolver(board);
        // display(board);
        //System.out.println(palindromePartitioning("aab"));
        int[][] maze1 = {
    {1, 0, 0, 0},
    {1, 1, 0, 1},
    {0, 1, 0, 0},
    {1, 1, 1, 1}
};
    System.out.println(findPaths(maze1));
    }
}
