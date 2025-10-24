package hardCoding;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    Queue<Integer> q1= new LinkedList<>();
    Queue<Integer> q2= new LinkedList<>();
    public void push(int x){
        q2.offer(x);
        while(!q1.isEmpty()){
            q2.offer(q1.peek());
            q1.poll();
        }
        Queue<Integer> temp = q1;
        q1=q2;
        q2=temp;

        //q1.offer(x);
    }
    public int pop(){
        if(q1.isEmpty()){
            System.out.println("stack is empty");
            return -1;
        }
        // while(q1.size()>1){
        //     q2.offer(q1.poll());
        // }
        // int top = q1.poll();
        // Queue<Integer> temp = q1;
        // q1=q2;
        // q2=temp;
        // return top;
        return q1.poll();
    }
    public int top(){
        return q1.peek();
    }
    public boolean isEmpty(){
        return q1.isEmpty();
    }
    public int size(){
        return q1.size();
    }
    public void printStack(){
        // System.out.println(q1);
        Queue<Integer> temp = new LinkedList<>();
        System.out.print("top -> ");
        while(!q1.isEmpty()){
            int val = q1.poll();
            System.out.print(val+" -> ");
            temp.offer(val);
        }
        while(!temp.isEmpty()){
            q1.offer(temp.poll());
        }
        System.out.println("null");
    }
    public static void main(String[] args) {
        StackUsingQueue s = new StackUsingQueue();
        s.push(3);
        s.push(4);
        s.push(2);
        s.printStack();
    }


}
