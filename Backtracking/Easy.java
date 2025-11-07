package Backtracking;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
public class Easy {
    public static int factorial(int n){
        if(n==1 || n==0) return 1;
        return n*factorial(n-1);
    }
    public static int sumOfN(int n){
        if(n<1){
            return 0;
        }
        return n+sumOfN(n-1);
    }
    public static int power(int a,int b){
        if(b<1){
            return 1;
        }
        return a*power(a, b-1);
    }
    public static void oneToN(int n){
        if(n<1){
            return;
        }
        oneToN(n-1);
        System.out.print(n+" ");
    }
    public static String reverse(String s){
        if(s.length()<1){
            return "";
        }
        return reverse(s.substring(1, s.length()))+s.substring(0, 1);
    }
    public static int countDigits(int n){
        if(n<0){
            return 0;
        }
        return countDigits(n/10)+1;
    }
    public static boolean isPalindrome(String s){
        if(s.length()==0 || s.length()==1) return true;
        if(s.charAt(0)!=s.charAt(s.length()-1)) return false;
        return isPalindrome(s.substring(1, s.length()-1));
    }
    public static int fibonacci(int n){
        if(n<1) return 0;
        if(n==1) return 1;
        return fibonacci(n-1)+fibonacci(n-2);
    }
    public static int countZeroes(int n){
        //System.out.println(s);
        if(n==0) return 1;
        if(n<10) return (n==0) ? 1 : 0; 
        if(n%10==0) return 1+countZeroes(n/10);
        else return countZeroes(n/10);
    }
    
    public static void print(int i,List<Integer> l){
        if(i>=x.length){
            System.out.println(l);
            return;
        }
        l.add(x[i]);
        print(i+1, l);
        l.remove(Integer.valueOf(x[i]));
        print(i+1, l);
    }
    public static void sumOfSubsets(int i,List<Integer> l,int sum){
        
        if(i==x.length){
            if(sum==2){
                System.out.println(l);
            }
            return;
        } 
        l.add(x[i]);
        sumOfSubsets(i+1, l, sum+x[i]);
        l.remove(l.size()-1);
        //sum-=x[i];
        sumOfSubsets(i+1, l, sum);
    }
    public static List<List<Integer>> findCombinations(int[] a,int target){
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(a, target, ans, new ArrayList<>(), 0);
        return ans;
    }
    static int[] x = {2,3,6,7};
    public static void findCombinations(int[] a,int target,List<List<Integer>> res,List<Integer> cur,int index){
        if(index==a.length){
            if(target==0){
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        if(a[index]<=target){
            cur.add(a[index]);
            findCombinations(a, target-a[index], res, cur, index);
            cur.remove(cur.size()-1);
        }
        findCombinations(a, target, res, cur, index+1);
    }
    public static List<List<Integer>> findCombinationsII(int[] a,int target){
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(a);
        findCombinationsII(a, 0, target, new ArrayList<>(), res);
        return res;
    }
    public static void findCombinationsII(int[] a,int i, int target,List<Integer> cur,List<List<Integer>> res){
        
            //System.out.println(cur);
            if(target==0){
            System.out.println("-----");
            res.add(new ArrayList<>(cur)); 
            return; 
            }

        for(int j=i;j<a.length;j++){
            if(j>i && a[j]==a[j-1]) continue;
            if(a[j]>target) break;
            cur.add(a[j]);
            findCombinationsII(a, j+1, target-a[j], cur, res);
            cur.remove(cur.size()-1);
        }   
        // if(a[i]<=target){
        //     cur.add(a[i]);
        //     findCombinationsII(a, i+1, target-a[i], cur, res);
        //     cur.remove(cur.size()-1);
        // }
        // findCombinationsII(a, i+1, target, cur, res);
        
    }
    public static List<Integer> subSets(int[] a){
        List<Integer> res = new ArrayList<>();
        Arrays.sort(a);
        subSets(a, 0,0, res);
        Collections.sort(res);
        return res;
    }
    public static void subSets(int[] a,int ind,int sum, List<Integer> res){
        if(ind==a.length){

            res.add(sum);
            return;
        }
        
        subSets(a, ind+1,sum+a[ind], res);
        
        subSets(a, ind+1,sum,  res);
    }
    public static List<List<Integer>> subSetsII(int[] a){
        List<List<Integer>> res = new ArrayList<>();
        subSetsII(a, 0, new ArrayList<>(), res);
        return res;
    }
    public static void subSetsII(int[] a,int ind,List<Integer> cur, List<List<Integer>> res){
        res.add(new ArrayList<>(cur));
        for(int i=ind;i<a.length;i++){
            if(i!=ind && a[i]==a[i-1]) continue;
            cur.add(a[i]);
            subSetsII(a, i+1, cur, res);
            cur.remove(cur.size()-1);
            //subSetsII(a, ind+1, sum, res);
        }
    }
    public static List<List<Integer>> permutations(int[] a){
        List<List<Integer>> res = new ArrayList<>();
        boolean[] b = new boolean[a.length];
        permutations(a, new ArrayList<>(), b, res);
        return res;
    }
    public static void permutations(int[] a,List<Integer> cur,boolean[] b,List<List<Integer>> res){
        if(cur.size()==a.length){
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i=0;i<a.length;i++){
            if(!b[i]){
                b[i]=true;
                cur.add(a[i]);
                permutations(a, cur, b, res);
            //if(cur.size()>1)
                cur.remove(cur.size()-1);
                b[i]=false;
            }
        }
    }
    public static List<List<Integer>> permutationsII(int[] a){
        List<List<Integer>> res = new ArrayList<>();
        permutationsII(a, 0, res);
        return res;
    }
    public static void permutationsII(int[] a,int ind,List<List<Integer>> res){
        if (ind==a.length) {
            res.add(new ArrayList<>(Arrays.stream(a).boxed().collect(Collectors.toList())));
            //System.out.println(res);
            return;
        }

        for(int i=ind;i<a.length;i++){
            swap(a, ind, i);
            permutationsII(a, ind+1, res);
            swap(a, ind, i);
        }
    }
    private static void swap(int[] a,int n1,int n2){
        int temp = a[n1];
        a[n1]=a[n2];
        a[n2]=temp;
    }
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, "", res);
        return res;
    }
    public static void helper(int n,String s,List<String> res){
        if(s.startsWith(")")) return;
        if(s.length()==n*2){
            System.out.println(s);
           if(isValidParenthese(s))
            res.add(s);
            return;
        }
        System.out.println(res);
        helper(n, s+"(", res);
        helper(n, s+")", res);
    }
    private static boolean isValidParenthese(String s){
        if(s.length()%2==1) return false;
        //Map<Character,Character> map = new HashMap<>();
        Stack<Character> st = new Stack<>();
       // map.put('(', ')');
        for(char c : s.toCharArray()){
            if(c=='('){
                st.push('(');
            }
            if(c==')'){
                if(st.isEmpty()) return false;
                if(st.peek()!='(') return false;
                else st.pop();
            }
        }
        return st.isEmpty();
    }

    
    public static void main(String[] args) {
        //System.out.println(factorial(5));
        //System.out.println(sumOfN(10));
        //System.out.println(power(2, 5));
        // oneToN(10);
        //System.out.println(reverse("abcd"));
       // System.out.println(countDigits(100000));
       //System.out.println(isPalindrome("AAAAAAAAAAA"));
    //    for(int i=0;i<10;i++){
    //     System.out.print(fibonacci(i)+" ");
    //    }
        // int[] a = {10,10001,1002,120,11,30,4002,0};
        // for(int i : a){
        //     System.out.println(i+"  "+countZeroes(i));
        // }
        //print(0, new ArrayList<>());
       // sumOfSubsets(0, new ArrayList<>(), 0);
       //System.out.println(findCombinations(x, 7));
       //System.out.println(findCombinationsII(new int[]{10,1,2,7,6,1,5}, 8));
       //System.out.println(subSets(new int[]{3,1,4} ));
       //System.out.println(subSetsII(new int[]{1,2,3}));
       //System.out.println(permutationsII(new int[]{1,2,3}));
       System.out.println(generateParenthesis(3));
       // System.out.println(isValidParenthese("()(())"));
    }
}
