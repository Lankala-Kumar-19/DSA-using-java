// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Stack;

// public class Demo {
//         public static List<Integer> spiralOrder(int[][] matrix) {
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int[][] vis = new int[m][n];
//         List<Integer> res = new ArrayList<>();
//         spiral(0,0,vis,matrix,res);
//         return res;
//     }
//     public static void spiral(int row,int col,int[][] vis,int[][] matrix,List<Integer> res){
//         System.out.println(row+" "+col+" "+((row<0 || row>matrix[0].length-1) || (col<0 || col>matrix.length-1) ));
//         if(((row<0 || row>=matrix[0].length) || (col<0 || col>=matrix.length) )|| vis[row][col]==1)
//             return;

//         vis[row][col]=1;
//         res.add(matrix[row][col]);
//         //System.out.print(matrix[row][col]+" ");
//         spiral(row,col+1,vis,matrix,res);
//         spiral(row+1,col,vis,matrix,res);
//         spiral(row,col-1,vis,matrix,res);
//         spiral(row-1,col,vis,matrix,res);
//     }
//     public static int[] time(int n,List<String> logs){
//         int[] res = new int[n];
//         int last = 0;
//         Stack<Integer> st = new Stack<>();
//         for(String s : logs){
//             String[] p = s.split(":");
//             int id = Integer.parseInt(p[0]);
//             String type = p[1];
//             int time = Integer.parseInt(p[2]);
//             if(type.equalsIgnoreCase("start")){
//                 if(!st.isEmpty()){
//                     res[st.peek()]+=time - last;
//                 }
//                 st.push(id);
//                 last=time;
//             }
//             else{
//                 res[st.pop()]+=time-last+1;
//                 last=time+1;
//             }
//         }
//         return res;
//     }
//     public static void main(String[] args) {
//         Map<Character,Integer> map = new HashMap<>();
//         Map<Character,Integer> freq = new HashMap<>();
//         // for(char c : s.toCharArray){
//         //     map.put(c,map.getOrDefault(c,0)+1);
//         // }
//         // for(char c : t.toCharArray){
//         //     freq.put(c,freq.getOrDefault(c,0)+1);
//         // }
//         map.equals(freq);
//         int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
//         System.out.println(spiralOrder(mat));
//     }
// }
// class Solution {
   
// }