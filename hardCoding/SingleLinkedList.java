// package hardCoding;

// import java.util.*;

// class Node{
//     int data;
//     Node next;
//     Node(int data){
//         this.data=data;
//         this.next=null;
//     }

// }

// public class SingleLinkedList {
//     Node head;
//     public void addFirst(int data){
//         Node newNode = new Node(data);
//         if(head==null){
//             head=newNode;
//             return;
//         }
//         newNode.next=head;
//         head=newNode;
//     }
//         public void addLast(int data){
//         System.out.println("adding last");
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
//     }
//     public void addAt(int index,int data){
//         if(isEmpty()){
//             System.out.println("list is empty");
//             return;
//         }
//         if(index==0){
//             addFirst(data);
//             return;
//         }
//         Node newNode = new Node(data);
//         Node temp=head;
//         int x =0;
//         while(x<index-1 && temp!=null){

//             temp=temp.next;
//             x++;
//             //System.out.println(x+" curr "+curr.data+" temp "+temp.data);
//         }
//         if(temp==null){
//             System.out.println("index out of bounds");
//             return;
//         }

//         newNode.next=temp.next;
//         temp.next=newNode;

//     }
//     public void removeFirst(){
//         if(head==null){
//             System.out.println("head is null");
//             return;
//         }
//         head=head.next;
//     }
//     public void removeLast(){
//         if(head==null){
//             System.out.println("head is null");
//             return;
//         }
//         Node curr = head;
//         //Node temp = head.next;
//         while(curr.next.next!=null){
//             curr=curr.next;
//             //temp=temp.next;
//         }
//         curr.next=null;
//     }
//     public void removeAt(int index){
//         if(isEmpty()){
//             System.out.println("list is empty");
//             return;
//         }
//         if(head.next==null)
//         {
//             head=null;
//             return;
//         }

//         Node curr=head;
//         while(curr.next.next!=null){
//             curr=curr.next;
           
//         }
//         curr.next=null;
//         // System.out.println("not removed");

//     }
//     public int get(int index){
//         if(isEmpty()) return -1;
//         Node temp = head;
//         int count=0;
//         while(temp!=null){
//             if(count==index) return temp.data;
//             temp=temp.next;
//             count++;
//         }
//         return -1; 
//     }
//     public boolean contains(int data){
//         if(head==null){
//             //System.out.println("head is null");
//             return false;
//         }
//         Node temp = head;
//         while(temp!=null){
//             if(temp.data==data) return true;
//             temp=temp.next;
//         }
//         return false;
//     }
//     public int size(){
//         if(head==null) return 0;
//         int size=0;
//         Node temp = head;
//         while(temp!=null){
//             temp=temp.next;
//             size++;
//         }
//         return size;
//     }
//     public void reverse(){
//         if(head==null || head.next==null) return;
//         Node prev=null;
//         Node curr=head;
//         while(curr!=null){
//             Node temp = curr.next;
//             curr.next=prev;
//             prev=curr;
//             curr=temp;
//             //System.out.println("temp: "+temp.data+" prev: "+prev.data+" curr: "+curr.data);
//             System.out.println("-----------------------");
//         }
//         head=prev;
//     }
//     public int findMiddle(){
//         if(head==null) return -1;
//         if(head.next==null) return head.data;
//         Node slow = head;
//         Node fast = head;
//         while(fast!=null && fast.next!=null){
//             slow=slow.next;
//             fast=fast.next.next;
//         }
//         return slow.data;
//     }
//     public boolean hasCycle(){
//         if(head==null || head.next==null) return false;
//         Node slow=head;
//         Node fast=head;
//         while(fast!=null && fast.next!=null){
//             slow=slow.next;
//             fast=fast.next.next;
//             if(slow==fast) return true;
//         }
//         return false;
//     }
//     public int getNthFromEnd(int n){
//         if(head==null) return -1;
//         Node fast=head,slow=head;
//         for(int i=0;i<n;i++){
//             if(fast==null) return -1;
//             fast=fast.next;
//         }
//         while(fast!=null){
//             slow=slow.next;
//             fast=fast.next;
//         }
//         return slow.data;
//     }
//     public void removeDuplicates(){
//         if(head==null) return;
//         Node prev = null;
//         Node curr = head;
//         Set<Integer> set = new HashSet<>();
//         //set.add(left.data);
//         while(curr!=null){
//             if(set.contains(curr.data)){
//                 prev.next=curr.next;
//             }else{
//                 set.add(curr.data);
//                 prev=curr;
//             }
//             curr=curr.next;
//         }
//         System.out.println(set);
//     }
//     public boolean isPalindrome(){
//         Stack<Integer> stack = new Stack<>();
//         Node temp = head;
//         while(temp!=null){
//             stack.push(temp.data);
//             temp=temp.next;
//         }
//         temp=head;
//         while (temp!=null) {
//             if(stack.pop()!=temp.data) return false;
//             temp=temp.next;
//         }
//         return true;
        
//     }
//     public Node merge(Node l1,Node l2){
//         if(l1==null) return l2;
//         if(l2==null) return l1;

//         if(l1.data<l2.data){
//             l1.next= merge(l1.next, l2);
//             return l1;
//         }else{
//             l2.next = merge(l1, l2.next);
//             return l2;
//         }
//     }
//     public void removeNthFromEnd(int n){
//         if(n>0 && head==null) return;
//         Node slow=head,fast=head;
//         for(int i=0;i<n;i++){
//             if(fast==null) return;
//             fast=fast.next;
//         }
//         Node prev=null;
//         while(fast!=null){
//             prev=slow;
//             slow=slow.next;
//             fast=fast.next;
//         }
//         prev.next=slow.next;
//     }
//     public void segregateEvenOdd(){
//         Node evenStart=null, evenEnd = null;
//         Node oddStart=null, oddEnd = null;
//         Node curr=head;
//         while(curr!=null){
//             if(curr.data%2==0){
//                 if(evenStart==null){
//                     evenStart = curr;
//                     evenEnd = curr;
//                 }else{
//                     evenEnd.next=curr;
//                     evenEnd=evenEnd.next;
//                 }
//             }else{
//                 if(oddEnd==null){
//                     oddEnd=curr;
//                     oddStart=curr;
//                 }else{
//                     oddEnd.next=curr;
//                     oddEnd=oddEnd.next;
//                 }
//             }
//             curr=curr.next;
//         }
//         if(evenStart==null){
//             head=oddStart;
//             return;
//         }
//         evenEnd.next=oddStart;
//         oddEnd.next=null;
//         head=evenStart;

//     }
//     public void printList(){
//         System.out.println("printing list");
//         Node curr=head;
//         while(curr!=null){
//             System.out.print(curr.data+" -> ");
//             curr=curr.next;
//         }
//         System.out.println("null");
//     }
//     public boolean isEmpty(){
//         return head==null;
//     }
//     public static void main(String[] args) {
//         SingleLinkedList sll = new SingleLinkedList();
//         // sll.addLast(5);
//         // sll.addLast(1);
//         // sll.addFirst(5);
//         // sll.addLast(10);
//         // sll.addAt(0, 1);
//         sll.addLast(17);
//         sll.addLast(15);
//         sll.addLast(8);
//         sll.addLast(12);
//         sll.addLast(10);
//         sll.addLast(5);
//         sll.addLast(4);
//         // sll.removeFirst();
//         // sll.removeLast();
//         //sll.removeAt(3);
        
//         sll.printList();
//         //sll.removeNthFromEnd(1);
//         sll.segregateEvenOdd();
//         sll.printList();
//         //sll.isPalindrome();
//         // sll.reverse();
//         // sll.printList();
//         // sll.removeDuplicates();
//         // sll.printList();
//         // System.out.println(sll.contains(20));
//         // System.out.println(sll.contains(16));
//         //System.out.println(sll.size());
//         //System.out.println(sll.get(4));

//     }
// }

