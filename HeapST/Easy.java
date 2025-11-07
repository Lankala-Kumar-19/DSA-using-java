package HeapST;

import java.util.PriorityQueue;

public class Easy {
    public static int[] findKLargest(int[] arr,int k){
        int[] x=new int[k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i=0;i<arr.length;i++){
            minHeap.add(arr[i]);
            if(minHeap.size()>k) minHeap.poll();
        }
        int i=k-1;
        while (i>=0) {
            x[i--]=minHeap.poll();
        }
        return x;
    }
    public static int[] findKSmallest(int[] arr,int k){
        int[] x = new int[k];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);
        for(int i : arr){
            maxHeap.add(i);
            if(maxHeap.size()>k) maxHeap.poll();
        }
        while (k>0) {
            x[k-1] = maxHeap.poll();
            k--;
        }
        return x;
    }
    public static boolean isMaxHeap(int[] arr){
        for(int i=0;i<=(arr.length-2)/2;i++){
            int left = arr[2*i+1];
            int right = arr[2*i+2];
            System.out.println("root: "+arr[i]+" left: "+left+" right: "+right);
            if(left>arr[i] || right>arr[i]) return false;
        }
        return true;
    }
    public static boolean isMinHeap(int[] arr){
        for(int i=0;i<=(arr.length-2)/2;i++){
            int left = arr[2*i+1];
            int right = arr[2*i+2];
            System.out.println("root: "+arr[i]+" left: "+left+" right: "+right);
            if(left<arr[i] || right<arr[i]) return false;
        }
        return true;
    }
    public static int[] minToMax(int[] arr){
        //int[] x = new int[arr.length];
        for(int i=arr.length/2-1;i>=0;i--){
            heapify(arr, arr.length, i);
        }
        return arr;
    }
    public static void heapify(int[] arr,int n,int i){
        int l = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(left<n && arr[left]>arr[l]){
            l=left;
        }
        if(right<n && arr[right]>arr[l]){
                l=right;
        }
        if(l!=i){
            int temp = arr[l];
            arr[l]=arr[i];
            arr[i]=temp;
            heapify(arr, n, l);
        }
    }
    private static void display(int[] x){
        for (int i : x) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        // int[] a = {3,1,7,5,9,2};
        // int[] b=findKSmallest(a, 3);
        // display(b);
       // System.out.println(isMinHeap(new int[]{5, 5, 5, 5, 5}));
       display(minToMax(new int[]{5, 10, 7, 20, 30}));
    }     
}
