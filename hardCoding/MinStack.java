package hardCoding;
import java.util.*;

public class MinStack {

    private Stack<Integer> st;
    private Stack<Integer> minSt;
    public MinStack(){
        this.st = new Stack<>();
        this.minSt = new Stack<>();
    }
    public void push(int val){
        st.push(val);
        if(minSt.isEmpty() || val< minSt.peek()) minSt.push(val);
        
    }
    public void pop(){
        if(st.isEmpty()) return;
        int popped = st.pop();
        if(popped==minSt.peek()) minSt.pop();
    }
    public int top(){
        if(minSt.isEmpty()) throw new RuntimeException( "stack is empty");
        return st.peek();
    }
    public int getMin(){
        if(minSt.isEmpty()) throw new RuntimeException( "stack is empty");
        return minSt.peek();
    }
    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.top());    
        System.out.println(ms.getMin());

    }
}


