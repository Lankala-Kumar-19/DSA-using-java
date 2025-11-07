package HeapST;

class MinHeap{
    int[] heap;
    int size;
    int capacity;
    public MinHeap(int capacity){
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }
    private int parent(int i){
        return (i-1)/2;
    }
    private int left(int i){
        return 2*i+1;
    }
    private int right(int i){
        return 2*i+2;
    }
    public void insert(int val){
        if(size==capacity){
            System.out.println("overflow");
            return;
        }
        heap[size]=val;
        int cur = size;
        size++;
        heapifyUp(cur);
    }
    public int extractMin(){
        int temp = heap[0];
        heap[0]=heap[size-1];
        size--;
        heapifyDown(0);
        return temp;
    }
    // public int extractMax(){
    //     int temp = heap[size-1];
    //     size--;
    //     heapifyUp(size);
    //     return temp;
    // }

    public void heapifyUp(int index){
        while (index>0 && heap[parent(index)]> heap[index]) {
            int temp = heap[parent(index)];
            heap[parent(index)]=heap[index];
            heap[index]=temp;
            index=parent(index);
        }
    }
    public void heapifyDown(int index){
        int i=index;
        while (true) {
            int left = left(i);
            int right=right(i);
            int small = i;
            if(left<size && heap[left]<heap[small]){
                small=left;
            }
            if(right<size && heap[right]<heap[small]){
                small=right;
            }
            if(small==i){
                break;
            }
            int temp = heap[i];
            heap[i]=heap[small];
            heap[small]=temp;
            i=small;
        }
    }
    public int[] buildHeap(int[] x){
        heap=x;
        for(int i=x.length/2-1;i>=0;i--){
            heapifyDown(i);
        }
        return x;
    }
    public int getMin(){
        return heap[0];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void display(){
        for(int i=0;i<size;i++) System.out.print(heap[i]+" ");
        System.out.println();
    }
}

class MaxHeap {
    int capacity;
    int size;
    int[] h;
    public MaxHeap(int capacity){
        this.capacity=capacity;
        size=0;
        h=new int[capacity];
    }
    private int parent(int i){
        return (i-1)/2;
    }
    private int left(int i){
        return 2*i+1;
    }
    private int right(int i){
        return 2*i+2;
    }
    public void insert(int val){
        h[size]=val;
        int cur = size;
        size++;
        heapifyUp(cur);
    }
    public void heapifyUp(int index){
        while (index>0 && h[parent(index)]<h[index]) {
            int temp = h[parent(index)];
            h[parent(index)]=h[index];
            h[index]=temp;
            index=parent(index);
        }
    }
    public void heapifyDown(int index){
        while (true) {
            int left = left(index);
            int right=right(index);
            int l = index;
            if(left<size && h[left]>h[l]){
                l=left;
            }
            if(right<size && h[right]>h[l]){
                l=right;
            }
            if(l==index){
                break;
            }

            int temp = h[index];
            h[index]=h[l];
            h[l]=temp;
            
            index=l;
        }
    }
    public int extractMax(){
        int temp = h[0];
        h[0] = h[size-1];
        size--;
        heapifyDown(0);
        return temp;
    }
    public int getMax(){
        return h[0];
    }
    public void buildHeap(int[] x){
        h=x;
        for(int i=(x.length/2)-1;i>=0;i--){
            heapifyDown(i);
        }
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void display(){
        for(int i=0;i<size;i++){
            System.out.print(h[i]+" ");
        }
        System.out.println();
    }
    
}

public class HeapSt {
    public static void main(String[] args) {
    //     MinHeap heap = new MinHeap(5);
    //     heap.insert(5);
    //     heap.insert(3);
    //     heap.insert(8);
    //     heap.insert(1);
    //     heap.insert(6);
    //     heap.display();
    //   //  System.out.println(heap.extractMax());
    //     heap.display();
        MaxHeap h = new MaxHeap(5);
        h.insert(10);
        h.insert(22);
        h.insert(9);
        h.insert(15);
        h.insert(12);
        h.display();
        h.extractMax();
        //h.extractMax();
        h.display();
        //h.display();

    }
    
     
}