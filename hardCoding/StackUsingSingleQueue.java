package hardCoding;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingSingleQueue {
    Queue<Integer> q = new LinkedList<>();
    public void push(int x){
        q.offer(x);
        for(int i=0;i<q.size()-1;i++){
            q.offer(q.poll());
        }

    }
    public int pop(){
        if(q.isEmpty()){
            System.out.println("stack is empty");
             return -1;
        }
        return q.poll();
    }
    public int top(){
        if(q.isEmpty()){
            System.out.println("stack is empty");
             return -1;
        }
        return q.peek();
    }
    public boolean isEmpty(){
        return q.size()==0;
    }
    public int size(){
        return q.size();
    }
    public void printStack(){
       // Queue<Integer> temp = new LinkedList<>();
        System.out.print("top -> ");
        for(int i=0;i<q.size();i++){
            int val = q.poll();
            System.out.print(val+" -> ");
            q.offer(val);
        }
        System.out.println("null");
        // q=temp;
    }
    public static void main(String[] args) {
        StackUsingSingleQueue s = new StackUsingSingleQueue();
        s.push(3);
        s.push(4);
        s.push(2);
        s.printStack();
        s.pop();
        s.printStack();
    }
}
