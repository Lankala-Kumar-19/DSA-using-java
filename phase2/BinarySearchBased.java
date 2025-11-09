package phase2;

import java.util.Arrays;

public class BinarySearchBased {
    public static int search(int[] nums, int target){
        int low =0,high=nums.length-1;
        while(low<high){
            int mid = low +(high-low)/2;
            if(nums[mid]==target) return mid;
            else if(nums[mid]<target) low=mid+1;
            else high = mid;
        }
        return -1;
    }
    public static int searchInsert(int[] nums, int target){
        int low=0,high=nums.length-1;
        while(low<high){
            int mid = low + (high-low)/2;
            if(nums[mid]<target) low= mid+1;
            else high=mid; 
        }
        return low;
    }
    public static int[] searchRange(int[] nums, int target){
        int[] x = new int[2];
        x[0] = findFirst(nums, target);
        x[1] = findLast(nums, target);
        return x;
        
    }
    public static int findFirst(int[] nums,int target){
        int low=0,high=nums.length-1;
        while(low<high){
            int mid = low + (high-low)/2;
            if(nums[mid]<target) low = mid+1;
            else high=mid;
        }
        return low;
    }
    public static int findLast(int[] nums,int target){
        int low=0,high = nums.length-1;
        while(low<high){
            int mid = low + (high-low)/2;
            if(nums[mid]<=target) low = mid+1;
            else high=mid;
        }
        return low-1;
    }

    public static void printArray(int[] x){
        for(int i : x) System.out.print(i+" ");
        System.out.println();
    }

    public static int searchRotated(int[] arr,int target){

        int low=0,high=arr.length-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(arr[mid] == target) return mid;
            if(arr[low]<=arr[mid]){
                if(arr[low]<=target && target<=arr[mid]) high = mid-1;
                else low=mid+1;
            }
            else{ 
                if(arr[mid]<=target && target<=arr[high]) low = mid+1;
                else high = mid-1;
            }
            System.out.println(arr[mid]);
        }
        return -1;
    }

    public static int minEatingSpeed(int[] piles, int h){
        int low = 1,high = piles[piles.length-1];
        int minTime=high;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(canEat(piles, mid)<=h){
                minTime=Math.min(minTime, mid);
                high=mid-1;
            }
            else{
                low=mid+1;
            }
        }
        return minTime;

    }
    public static int canEat(int[] piles,int x){
        int q =0;
        for(int i : piles){
            q+=(i+x-1)/x;
        }
        return q;
    }

    public static int shipWithinDays(int[] weights, int D){
        int low =1,high=Arrays.stream(weights).sum();
        int minDays=Integer.MAX_VALUE;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(canPackage(weights, mid)<=D){
                minDays=Math.min(minDays, mid);
                //System.out.println(mid);
                high=mid-1;
            }
            else{
                low=mid+1;
            }
            //System.out.println(minDays);
        }
        return minDays;
    }
    public static int canPackage(int[] weights, int x){
        int q =1;
        int sum=0;
        for(int i : weights){
            //q+=(i+x-1)/x;
            if(sum+i>x){
                q++;
                sum=0;
            }
            sum+=i;
        }
        return q;

    }
    public static int peakIndexInMountainArray(int[] arr){
        int low=0,high=arr.length-1;
        while(low<high){
            int mid = low + (high-low)/2;
            if(arr[mid]<arr[mid+1]){
                low=mid+1;
            }
            else{
                high=mid;
            }
        }
        return low-1;
    }
    public static int firstBadVersion(int n){
        int low=1,high=n;
        while(low<=high){
            int mid = low + (high-low)/2;
            System.out.println("low: "+low+" mid: "+mid+" high: "+high);
            if(isBadVersion(mid)) high=mid-1;
            else low=mid+1;
        }
        return low;
    }
    private static boolean isBadVersion(int v){
        return v>5;
    }

    public static int upperBound(int[] arr, int target){
        int low=0,high=arr.length-1;
        while(low<high){
            int mid = low + (high-low)/2;
            if(arr[mid]<=target) high=mid;
            else low=mid+1;
        }
        return low;
    }


    public static int lowerBound(int[] arr, int target){
        int low=0,high=arr.length-1;
        while(low<high){
            int mid = low + (high-low)/2;
            if(target<=arr[mid]) high=mid;
            else low = mid+1;
        }
        return low;
    }
    public static int allocateBooks(int[] pages, int students){
        if(students>pages.length) return -1;
        int low=0,high=pages.length-1;
        int minMax=Integer.MAX_VALUE;
        while(low<high){
            int mid = low + (high-low)/2;
            if(canRead(pages, students, mid)<=minMax){
                minMax=canRead(pages, students, mid);
                low=mid+1;
            }else{
                high=mid;
            }
        }
        return minMax;
    }
    private static int canRead(int[] pages,int s,int x){
        int[] z = new int[s];
        int q=0,a=0;
        for(int i=0;i<pages.length;i++){
            if( q<s-1 && a>x){
                q++;
                a=0;
            }
            z[q]+=pages[i];
            printArray(z);
            a++;
        }
        return Arrays.stream(z).max().getAsInt();
    }





    public static void main(String[] args) {
        // int[] a = {1,2,3,5,6};
        // int[] b = {4,5,1,2,3};
        // int[] c = {3,6,7,11};
        // int[] d ={1,2,3,4,5,6,7,7,7,7,7,8,9,10};
        // int[] e = {1,2,3,1,1};
        // int[] f = {5,5,5,5};
        // int[] g = {1,2,3,4,3,2,1};
        // int[] h= {10,20,30,40};
        // System.out.println(search(a, 5));
        // System.out.println(searchInsert(a, 4));
        // printArray(searchRange(a, 2));
        //System.out.println(searchRotated(b, 4));
        //System.out.println(canEat(c, 4));
        //System.out.println(minEatingSpeed(c, 8));
        //System.out.println(shipWithinDays(d, 5));
        // System.out.println(shipWithinDays(f, 4));
        //System.out.println(shipWithinDays(e, 1));
        // System.out.println(canPackage(e, 8));
        // System.out.println(peakIndexInMountainArray(g));
        //System.out.println(firstBadVersion(10));
        //System.out.println(lowerBound(d, 8));
        //System.out.println(canRead(h, 2, 2));p
       // System.out.println(allocateBooks(h, 2));
        System.out.println(allocateBooks(new int[]{100,10,10,10}, 2));
        //System.out.println(allocateBooks(g, 0));
    }

}
