package Backtracking;

import java.util.*;

public class Level1 {
    public static int climbingStairs(int n){
        if(n==0) return 1;
        if(n<0) return 0;
        return climbingStairs(n-1)+climbingStairs(n-2);
    }
    public static int climbingStairsII(int n){
        int[] x = new int[n+1];
        Arrays.fill(x, -1);
        return climbingStairsII(n, x);
    }
    public static int climbingStairsII(int n,int[] x){
        if(n==0) return 1;
        if(n<0) return 0;
        if(x[n]!=-1) return x[n];
        x[n] = climbingStairsII(n-1, x) + climbingStairsII(n-2, x);
        return x[n];
    }
    public static List<List<String>> subSequences(String s){
        List<List<String>> res = new ArrayList<>();
        subSequences(s, 0, new ArrayList<>(), res);
        return res;
    }
    public static void subSequences(String s,int ind, List<String> cur,List<List<String>> res){
        if(ind==s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        cur.add(s.charAt(ind)+"");
        subSequences(s, ind+1, cur, res);
        cur.remove(cur.size()-1);
        subSequences(s, ind+1, cur, res);

    }
    public static void main(String[] args) {
        // System.out.println(climbingStairs(5));
        // System.out.println(climbingStairsII(5));
        System.out.println(subSequences("abc"));
    }
}
