// package hardCoding;

// import java.util.Stack;

// class Node{
//     int data;
//     Node prev,next;
//     Node(int data){
//         this.data=data;
//         this.next=null;
//         this.prev=null;
//     }
// }

// public class DoubleLinkedList {
//     Node head;
//     public void addFirst(int data){
//         Node newNode = new Node(data);
//         if(head==null){
//             head=newNode;
//             head.next=null;
//             head.prev=null;
//             return;
//         }
//         head.prev=newNode;
//         newNode.prev=null;
//         newNode.next=head;
//         head=newNode;
//     }
//     public void addLast(int data){
//         Node newNode = new Node(data);
//         if(head==null){
//             head=newNode;
//             return;
//         }
//         Node curr = head;
//         while(curr.next!=null){
//             curr=curr.next;
//         }
//         curr.next=newNode;
//         newNode.prev=curr;
//     }
//     public void addAt(int index,int data){
//         if(index<0) return;
//         if(index==0){
//             addFirst(data);
//             return;
//         }
//         int x=0;
//         Node curr=head;
        
//         while(x<index-1 && curr!=null){
//             curr=curr.next;
//             x++;
//         }
//         if(curr==null) return;
//         Node newNode = new Node(data);
//         // System.out.println("curr"+curr.data+"   curr.next.next"+curr.next.data);
//         newNode.next=curr.next;
//         if(curr.next!=null){
//             curr.next.prev=newNode;
//         }
//         curr.next = newNode;
//         newNode.prev=curr;
//     }
//     public void removeFirst(){
//         if(head==null){
//             return;
//         }
//         if(head.next==null){
//             head=null;
//             return;
//         }
//         head.next.prev=null;
//         head=head.next;
//     }
//     public void removeLast(){
//         if(head==null) return;
//         if(head.next==null){
//             head=null;
//             return;
//         }
//         Node curr=head;
//         while(curr.next!=null){
//             curr=curr.next;
//         }
//         curr.prev.next=null;
//     }
//     public void removeAt(int index){
//         if(head==null) return;
//         if(index==0){
//             removeFirst();
//             return;
//         }
//         int x=0;
//         Node temp = head;
//         while(x<index && temp!=null){
//             temp=temp.next;
//             x++;
//         }
//         if(temp==null) return;
//         System.out.println(temp.data);
//         if(temp.prev!=null)
//         temp.prev.next=temp.next;
//         if(temp.next!=null)
//         temp.next.prev=temp.prev;
//     }

//     public void printForward(){
//         Node temp=head;
//         while(temp!=null){
//             System.out.print(temp.data+" -> ");
//             temp=temp.next;
//         }
//         System.out.println("null");
//     }
//     public int size(){
//         Node temp = head;
//         int size=0;
//         while(temp!=null){
//             temp=temp.next;
//             size++;

//         }
//         return size;
//     }
//     public boolean isEmpty(){
//         return head==null;
//     }
//     public void reverse(){
//         if(head==null || head.next==null) return;
//         Node curr = head;
//         Node temp = null;
//         while(curr!=null){
//             temp = curr.prev;
//             curr.prev=curr.next;
//             curr.next=temp;
//             curr=curr.prev;
//         }
//         if(temp!=null) head=temp.prev;
//     }
//     public int findMiddle(){
//         if(head==null) return -1;
//         Node slow=head,fast=head;
//         while(fast!=null && fast.next!=null){
//             slow=slow.next;
//             fast=fast.next.next;
//         }
//         return slow.data;
//     }
//     public boolean isPalindrome(){
//         if(head==null) return false;
//         if(head.next==null) return true;
//         Node temp=head;
//         Stack<Integer> stack = new Stack<>();
//         while(temp!=null){
//             stack.push(temp.data);
//             temp=temp.next;
//         }
//         temp=head;
//         while(temp!=null){
//             if(stack.pop()!=temp.data) return false;
//             temp=temp.next;
//         }
//         return true;
        
//     }
//     public static void main(String[] args) {
//         DoubleLinkedList dll = new DoubleLinkedList();
//         dll.addFirst(20);
//         dll.addFirst(20);
//         dll.addFirst(10);
//         dll.addLast(10);
//         dll.printForward();
//         dll.addAt(4, 30);
//         // dll.removeFirst();
//         // dll.removeLast();
//         dll.printForward();
//         dll.removeAt(4);
//         dll.printForward();
//         System.out.println(dll.findMiddle());
//         System.out.println(dll.isPalindrome());
//         //dll.reverse();
//         //dll.printForward();
//     }
    
// }
