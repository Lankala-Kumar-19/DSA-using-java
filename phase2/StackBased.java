package phase2;

import java.util.Stack;

public class StackBased {
    public static boolean isValid(String s){
        if(s.length()%2==1) return false;
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='{' || c=='[' || c=='('){
                st.add(c);
            }
            else{
                if(!st.isEmpty()){
                    if(c==']'){
                        if(st.peek()=='[') st.pop();
                        else return false;
                    }
                    else if(c=='}'){
                        if(st.peek()=='{') st.pop();
                        else return false;
                    }
                    else if(c==')'){
                        if(st.peek()=='(') st.pop();
                        else return false;
                    }
                }
                else{
                    return false;
                }
            }
        }
        return st.empty();
    }
    public static int[] nextGreaterElement(int[] arr){
        Stack<Integer> st = new Stack<>();
        //st.push(-1);
        int[] x = new int[arr.length];
        //x[arr.length-1]=-1;
        for(int i=arr.length-1;i>=0;i--){
            while(!st.isEmpty()  && st.peek()<=arr[i]) st.pop();
            if(st.isEmpty()){
                x[i]=-1;
            }
            else{
                x[i]=st.peek();    
            }
           st.push(arr[i]);
            
        }
        //System.out.println(st);
        return x;
    }
    public static int[] previousSmallerElement(int[] arr){
        Stack<Integer> st = new Stack<>();
        int [] x = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            while(!st.isEmpty() && st.peek()>=arr[i]) st.pop();
            if(st.isEmpty()){
                x[i]=-1;
            }else{
                x[i]=st.peek();
            }
            st.push(arr[i]);
        }
        return x;
    }
    public static int[] nextSmallerElement(int[] arr){
        Stack<Integer> st= new Stack<>();
        int[] x = new int[arr.length];
        for(int i=arr.length-1;i>=0;i--){
            while(!st.isEmpty() && st.peek()>=arr[i]) st.pop();
            if(st.isEmpty()){
                x[i]=-1;
            }else{
                x[i]=st.peek();
            }
            st.push(arr[i]);
        }
        return x;
    }
    public static int[] stockSpan(int[] prices){
        Stack<Integer> st = new Stack<>();
        int [] x = new int[prices.length];
        for(int i=0;i<prices.length;i++){
            while(!st.isEmpty() && prices[st.peek()] <= prices[i]) st.pop();
            if(st.isEmpty()){
                x[i] = i+1;
            }
            else{
                x[i] = i-st.peek();
            }
            st.push(i);
        }
        return x;
    }
    public static int[] dailyTemperatures(int[] temperatures){
        Stack<Integer> st = new Stack<>();
        int[] x = new int[temperatures.length];
        for(int i=temperatures.length-1;i>=0;i--){
            while(!st.isEmpty() && temperatures[st.peek()]<=temperatures[i]) st.pop();
            if(st.isEmpty()) x[i]=0;
            else x[i]=st.peek()-i;
            st.push(i);
        }
        return x;
    }

    // public static int largestRectangleArea(int[] heights){

    // }



    private static void printArray(int[] x){
        for(int i : x) System.out.print(i+" ");
        System.out.println();
    }

    public static void main(String[] args) {
        //System.out.println(isValid("({[]})"));
        // int[] a = {4, 5, 2, 25};
        // int[] b = {13, 7, 6, 12};
        // int[] x = nextGreaterElement(a);
        // printArray(x);
        // int[] y = nextGreaterElement(b);
        // printArray(y);
        // int[] z= nextGreaterElement(new int[]{5});
        // printArray(z);
        // int[] q = nextGreaterElement(new int[]{6,8,0,1,3});
        // printArray(q);
        // int[] w = nextGreaterElement(new int[]{1,3,2,4});
        // printArray(w);
        // int [] a = {1, 3, 0, 2, 5};
        // printArray(previousSmallerElement(a));
        //printArray(nextSmallerElement(new int[]{4, 5, 2, 10, 8}));
        //printArray(stockSpan(new int[]{100, 80, 60, 70, 60, 75, 85}));
        // printArray(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));

    }
}
