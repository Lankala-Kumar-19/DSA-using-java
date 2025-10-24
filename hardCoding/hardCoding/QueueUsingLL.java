package hardCoding;
class Node{
    int data;
    Node next;
    Node(int data){
        this.data=data;
        this.next=null;
    }
}
public class QueueUsingLL {
    Node front,rear;
    QueueUsingLL(){
        this.front=null;
        this.rear=null;
    }
    public void enqueue(int data){
        Node newNode = new Node(data);
        if(front==null){
            front=newNode;
            rear=newNode;
            return;
        }
        rear.next=newNode;
        rear=newNode;
    }
    public int dequeue(){
        if(front==null){
            return -1;
        }
        int x = front.data;
        front=front.next;
        return x;
    }
    public int peek(){
        if(front==null) return -1;
        return front.data;
    }
    public boolean isEmpty(){
        return front==null;
    }
    public int size(){
        int size=0;
        Node temp=front;
        while(temp!=null){
            size++;
            temp=temp.next;
        }
        return size;
    }
    public void printQueue(){
        if(isEmpty()){
            System.out.println("queue is empty");
            return;
        }
        Node temp = front;
        System.out.print("front -> ");
        while(temp!=null){
            System.out.print(temp.data+" -> ");
            temp=temp.next;
        }
        System.out.println("rear");
    }
    public int rear(){
        if(rear==null) return -1;
        return rear.data;
    }
    public int[] toArray(){
        int[] x = new int[size()];
        Node temp= front;
        for(int i=0;i<x.length;i++){
            x[i]=temp.data;
            temp=temp.next;
        }
        return x;
    }
    public void clear(){
        front=null;
        rear=null;
    }
    public static void main(String[] args) {
        QueueUsingLL queue= new QueueUsingLL();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.printQueue();
        queue.clear();
        queue.printQueue();
        //System.out.println(queue.rear());
        // queue.dequeue();
        // queue.dequeue();
        // queue.printQueue();
        // int [] x = queue.toArray();
        // for(int i:x) System.out.print(i+" ");
    }
}
