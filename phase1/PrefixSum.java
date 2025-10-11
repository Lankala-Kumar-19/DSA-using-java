package phase1;

import java.util.*;


public class PrefixSum {
    // int[] x;
    // int[] prefixSum;
    // public PrefixSum(int[] x){
    //     this.x=x;
    //     prefixSum = new int[x.length];
    //     prefixSum[0]=x[0];
    //     for(int i=1;i<x.length;i++) prefixSum[i]=prefixSum[i-1] + x[i];
    //     printArray(prefixSum);
    // }

    // public int sumRange(int left, int right){
    //     //if(left==right) return prefixSum[right];
    //     if(left>0) return prefixSum[right]-prefixSum[left-1];
    //     return prefixSum[right];
    // }

    public static int countSubarraysWithSumK(int[] a, int k){
        int prefixSum =0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, 1);
        int count=0;
        for(int i=0;i<a.length;i++){
            prefixSum+=a[i];
            //System.out.println(prefixSum);
            if(map.containsKey(prefixSum-k)){
                count+=map.get(prefixSum-k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0)+1);
            System.out.println(map);
        }
        return count;
    }
    public static int countSubarraysDivByK(int[] nums, int k){
        int prefixSum=0;
        int count=0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i=0;i<nums.length;i++){
            prefixSum+=nums[i];
            int mod = ((prefixSum % k) + k) % k;
            if(map.containsKey(mod)){
                count+=map.get(mod);
            }
            map.put(mod, map.getOrDefault(mod, 0)+1);
            // System.out.println("prefixsum "+prefixSum);
            // System.out.println("count "+count);
            // System.out.println(map);
        }
        return count;
    }
    public static int countSubarraysWithKOdds(int[] nums, int k){
        int prefixSum=0;
        int count=0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i=0;i<nums.length;i++){
            int x = nums[i]%2;
            prefixSum+=x;
            if(map.containsKey(prefixSum-k)){
                count+=map.get(prefixSum-k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0)+1);
        }
        return count;
    }
    public static int maxLengthSubarraySumAtMostK(int[] nums, int k){
        int prefixSum=0;
        int maxLen=0;
        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.put(0, -1);
        int j=0;
        for(int i=0;i<nums.length;i++){
            prefixSum+=nums[i];
            if(prefixSum<=k) maxLen = Math.max(maxLen, i-j+1);
            map.put(prefixSum, (i-j+1));
        }
        return maxLen;
    }



    // private static void printArray(int[] x){
    //     for(int i : x) System.out.print(i+" ");
    //     System.out.println();
    // }


    public static void main(String[] args) {
        // PrefixSum x = new PrefixSum(new int[]{10, 20, 30, 40});
        
        // System.out.println(x.sumRange(3, 3));
        // System.out.println(countSubarraysWithSumK(new int[]{1,2,3}, 3));
        // System.out.println(countSubarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
        // System.out.println(countSubarraysWithKOdds(new int[]{1,1,2,1,1}, 3));
        System.out.println(maxLengthSubarraySumAtMostK(new int[]{1,-1,5,-2,3}, 3));
    }

}
