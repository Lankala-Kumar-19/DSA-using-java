package HeapST;

import java.util.*;

public class Medium {
    public static int findKthSmallest(int[] arr, int k){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)-> b-a);
        for(int i : arr){
            maxHeap.add(i);
            if(maxHeap.size()>k) maxHeap.poll();
            System.out.println(maxHeap);

        }

        return maxHeap.poll();
    }
    public static int findKthLargest(int[] arr, int k){
        PriorityQueue<Integer> minHeap= new PriorityQueue<>();
        for(int i : arr){
            minHeap.add(i);
            if(minHeap.size()>k) minHeap.poll();
            System.out.println(minHeap);       
        }
        return minHeap.poll();
    }

    public static int[] mergeKSorted(List<int[]> lists){
        List<Integer> x = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int[] i : lists){
            for(int j : i) minHeap.add(j);
            System.out.println(minHeap);
        }
        
        while (!minHeap.isEmpty()) {
            x.add(minHeap.poll());
        }
        return x.stream().mapToInt(Integer:: intValue).toArray();
    }
    public static void display(int[] x){
        for (int i : x) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static int[] topKFrequent(int[] arr,int k){
        Map<Integer,Integer> map = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)-> map.get(a) - map.get(b));
        List<Integer> x = new ArrayList<>();
        for(int i : arr){
            map.put(i, map.getOrDefault(i, 0)+1);
            // minHeap.add(map.get(i));
            // if(minHeap.size()>k) minHeap.poll();
            // System.out.println(minHeap);
        }
        for(int n : map.keySet()){
            minHeap.add(n);
            if(minHeap.size()>k) minHeap.poll();
        }
        System.out.println(minHeap);        
        System.out.println(map);
        while (!minHeap.isEmpty()) {
            x.add(minHeap.poll());
        }
        System.out.println(x);
        return x.stream().mapToInt(Integer::intValue).toArray();
    }
    static PriorityQueue<Integer> minHeap,maxHeap;
    static boolean even = true;
    public Medium() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a,b)-> b-a);
    }
    public static void addNum(int num){
        if(!even){
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
        else{
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
        System.out.println(maxHeap+"    "+minHeap);
        even=!even;
    }
    public static double findMedian(){
        if(even){
            double m = minHeap.peek()+maxHeap.peek();
            return m/2;
        }
        else{
            return maxHeap.peek();
        }
    }
    public static int minCostToCombine(int[] arr){
        if(arr==null || arr.length==0) return 0;
        int cost = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i : arr){
            minHeap.add(i);
        }
        System.out.println(minHeap);
        //cost=minHeap.poll();
        while (minHeap.size()>1) {
            int sum = minHeap.poll();
            sum+=minHeap.poll();
            cost+=sum;
            minHeap.offer(sum);
            System.out.println(minHeap);
        }
        return cost;
    }

    public static void main(String[] args) {
        System.out.println(minCostToCombine(new int[]{10}));
        // int[] a = {1,1,1,2,2,3};
        // int[] b = {1};
        // int[] c= {4,4,4,6,6,7,8,8,8,8};
        // int[] d = {5,5,5,5,1,1,2,2,2};
        // List<int[]> x = new ArrayList<>();
        // x.add(a);
        // x.add(b);
        // x.add(c);
        // topKFrequent(a, 2);
        // topKFrequent(b, 1);
        // topKFrequent(c, 2);
        //topKFrequent(d, 3);
        // System.out.println(findKthSmallest(a, 3));
        // System.out.println(findKthLargest(a, 3));
        // System.out.println(findKthSmallest(a, 3));
        // System.out.println(findKthLargest(a, 3));
        // display(mergeKSorted(x));
    }
}
