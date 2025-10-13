// package hardCoding;

// class Node{
//     int data;
//     Node next;
//     Node(int data){
//         this.data=data;
//         this.next=null;
//     }
// }

// public class StackUsingLL {
//     private Node top;
//     public StackUsingLL(){
//         top=null;
//     }
//     public void push(int data){
//         Node newNode = new Node(data);
//         newNode.next=top;
//         top=newNode;
//     }
//     public int pop(){
//         if(top==null) return -1;
//         int x  = top.data;
//         top=top.next;
//         return x;
//     }
//     public int peek(){
//         if(top==null) return -1;
//         return top.data;
//     }
//     public boolean isEmpty(){
//         return top==null;
//     }
//     public int size(){
//         int size=0;
//         Node temp = top;
//         while(temp!=null){
//             temp=temp.next;
//             size++;
//         }
//         return size;
//     }
//     public void printStack(){
//         if(top==null){
//             System.out.println("stack is empty");
//             return;
//         }
//         System.out.print("top -> ");
//         Node temp = top;
//         while(temp!=null){
//             System.out.print(temp.data+" -> ");
//             temp=temp.next;
//         }
//         System.out.println("null");
//     }
//     public static void main(String[] args) {
//         StackUsingLL stack = new StackUsingLL();

//         stack.push(10);
//         stack.push(20);
//         stack.push(30);
//         stack.printStack();


//     }
// }
