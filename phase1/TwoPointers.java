package phase1;

import java.util.*;

public class TwoPointers {
    public static int[] twoSumSorted(int[] numbers, int target){
        int i=0,j=numbers.length-1;
        while(i<j){
            int sum = numbers[i]+numbers[j];
            if(sum==target){
                return new int[]{i+1,j+1};
            }
            else if(sum<target) i++;
            else j--;
        }
        return new int[]{-1,-1};
    }
    public static List<List<Integer>> threeSum(int[] nums){
        //List<Integer> x = new ArrayList<>();
        List<List<Integer>> z = new ArrayList<>();
        Arrays.sort(nums);
       // printArray(nums);
        
        for(int k=0;k<nums.length-2;k++){
            if(k>0 && nums[k]==nums[k-1]) continue;
            int i=k+1,j=nums.length-1;
            while(i<j ){
                int sum = nums[k] + nums[i] + nums[j];
                
                //System.out.println(sum);
                if(sum==0){
                   z.add(Arrays.asList(nums[k],nums[i],nums[j]));
                   while(i<j && nums[i]==nums[i+1]) i++;
                   while(i<j && nums[j]==nums[j-1]) j--;
                    i++;    
                    j--;
                }
                else if(sum>0){
                    j--;
                }
                else{
                    i++;
                }
            }
        }
        return z; 
    } 
    public static int removeDuplicates2(int[] nums){
        // Map<Integer,Integer> map = new HashMap<>();
        // int i=0;
        // for(int j=0;j<nums.length;j++){
        //     map.put(nums[j], map.getOrDefault(nums[j], 0)+1);
        //     System.out.println(map);
        //     if(map.get(nums[j])>2){
        //         continue;
        //     }
        //     nums[i]=nums[j];
        //     i++;
        // }
        // return i;
        int i=0;
        for(int n : nums){
            if(i<2 || n!=nums[i-2]){
                nums[i]=n;
                i++;
            }
        }
        return i;
    }
    public static int maxArea(int[] height){
        int maxArea=0;
        int left=0,right=height.length-1;
        while(left<right){
            int w = right-left;
            int h = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, w*h);
            if(height[left]<height[right]) left++;
            else right--; 
        }
        return maxArea;
    }


    public static void sortColors(int[] nums){
        int low=0,high=nums.length-1;
        int mid=0;
        while(mid<=high){
            if(nums[mid]==0){
                int temp = nums[low];
                nums[low++]=nums[mid];
                nums[mid++]=temp;
            }
            else if (nums[mid]==1){
                mid++;
            }
            else if (nums[mid]==2){
                int temp = nums[high];
                nums[high--]=nums[mid];
                nums[mid]=temp;
            }
        }
    }

    // public static int trap(int[] height){
    //     int left=0,right=1;
    //     int w=0;
    //     while(right<height.length){
    //         if((height[left]<height[right] || right==height.length-1 )){
    //             int l = Math.min(height[left+1], height[right]);
    //             // if(right==height.length-1 ){
    //             //     l = height[left+1];
    //             //     while(left<right){

    //             //     System.out.println(l+"   "+height[right]);
    //             //     w+=(height[right--]-l);
    //             //     }
    //             //     break;
    //             // }
    //             while(left<right){
                    
    //                 System.out.println(l+"   "+height[left]);
    //                 w+=(l-height[left++]);
    //             }
    //         }
    //         right++;
            

    //         // if(height[right]<height[left]){
    //         //     left=right;
    //         //     right++;
    //         // }

    //     }
    //     return w;
    // }

    public static int trap(int[] height){
        int w = 0;
        int left = 0,right=height.length-1;
        int leftMax=0,rightMax=0;
        while(left<right){
             if(height[left] < height[right]){
                if (height[left] >= leftMax){
                    leftMax = height[left];
                }
                else{
                    w+=leftMax-height[left];
                }
                left++;
             }
             else{
                if(height[right]>=rightMax){
                    rightMax = height[right];
                }else{
                    w+=rightMax-height[right];
                }
                right--;
             } 
        }
        return w;
    }




    public static void printArray(int[] a){
        for(int i:a) System.out.print(i+"  ");
    }

    public static void main(String[] args) {
        // int[] a={2,7,11,15};
        // int[] b = {-1,0,1,2,-1,-4};
        // int[] c= {1,1,1,2,2,3};
        // int[] d = {1,8,6,2,5,4,8,3,7};
        // int[] e = {4,3,2,1,4};
        // int[] f={1,1};
        // int[] g= {2,0,2,1,1,0};
        // int[] h = {0,1,0,2,1,0,1,3,2,1,2,1};
        // int[] i = {4,2,0,3,2,5};
        // System.out.println(threeSum(b));
        // int[] x = twoSumSorted(a, 9);
        // printArray(x);
        // System.out.println(removeDuplicates2(c));
        // printArray(c);
        //System.out.println(maxArea(f));
        // sortColors(g);
        // printArray(g);
        //System.out.println(trap(h));
        //System.out.println(trap(h));
    }

}
