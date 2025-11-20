package phase1;
import java.util.*;
public class SlidingWindow {
    public static int maxSumSubarrayK(int[] nums, int k){
        int maxSum=0,sum=0;
        if(nums.length<k) return -1;
        
        for(int i=0;i<k;i++){
            sum+=nums[i];
        }
        maxSum=sum;
        int i=0,j;
        for(j=k;j<nums.length;j++,i++){
            sum+=nums[j]-nums[i];
            maxSum=Math.max(maxSum, sum);
        }
        return maxSum;

    }
    public static int longestSubstring(String s){
        Map<Character,Integer> map = new HashMap<>();
        
        int maxLen = 0;
        int left = 0,right=0;
        for(right=0;right<s.length();right++){
            char c = s.charAt(right);
            while(map.containsKey(c)){
                char l = s.charAt(left);
                //System.out.println(l);
                map.put(l, map.get(l)-1);
                if(map.get(l)==0){
                    map.remove(l);
                    left++;
                }
            }
            map.put(c, map.getOrDefault(c, 0)+1);
            maxLen = Math.max(maxLen, right-left+1);
        }
        return maxLen;
    }
    public static int lengthOfLongestSubstringKDistinct(String s, int k){
        Map<Character,Integer> map = new HashMap<>();
        int maxLen =0;
        int left =0;
        int right=0;
        while(right<s.length()){
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0)+1);
            while(map.size()>k){
                char l = s.charAt(left);
                //System.out.println(l);
                map.put(l, map.get(l)-1);
                if(map.get(l)==0){
                    map.remove(l);
                    left++;
                }
            }
            maxLen = Math.max(maxLen, right-left+1);
            right++;
        }
        return maxLen;
    }
    // public static String minWindow(String s, String t){
    //     if(s.length()<t.length()) return "";
    //     Map<Character,Integer> freq = new HashMap<>();
    //     Map<Character,Integer> map = new HashMap<>();
    //     for(char c : t.toCharArray()) freq.put(c, freq.getOrDefault(c, 0)+1);
    //     int left=0,right=0;
    //     int minLen = Integer.MAX_VALUE;
    //     String x = "";
    //     while(right<s.length()){
    //         char c = s.charAt(right);
    //         map.put(c, map.getOrDefault(c, 0)+1);
    //         System.out.println(map);
    //         while(map.keySet().containsAll(freq.keySet())){
                
    //             if(minLen>right-left+1){
    //                 minLen = right-left+1;
    //                 x=s.substring(left, right+1);
    //                 System.out.println(x);
    //             }
    //             char l = s.charAt(left);
    //             System.out.println("left: "+left+" l: "+l);
    //             map.put(l, map.get(l)-1);
    //             left++;
    //             if(map.get(l)==0){
    //                 map.remove(l);
    //                 //left++;
    //             }
    //         }
    //         right++;
    //     }
    //     return x;
    // }
    public static String minWindow(String s, String t){
        if(s.length()<t.length()) return "";

        Map<Character,Integer> freq = new HashMap<>();
        for(char c : t.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0)+1);
        }
        int req = freq.size();
        Map<Character,Integer> window = new HashMap<>();
        int minLen = Integer.MAX_VALUE;
        int minLeft =0;
        int left=0,right=0;
        int formed =0;
        while(right<s.length()){
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0)+1);
            if(freq.containsKey(c) && window.get(c).intValue()== freq.get(c).intValue()){
                formed++;
            }
            while(formed==req){
                if(right-left+1<minLen){
                    minLen=right-left+1;
                    minLeft=left;
                }
                char l = s.charAt(left);
                window.put(l, window.get(l)-1);
                if(freq.containsKey(l) && window.get(l).intValue()< freq.get(l).intValue()){
                    formed--;
                }
                left++;
            }
            right++;
        }
        return minLen==Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft+minLen);
    }

    public static boolean checkInclusion(String s1, String s2){
        Map<Character,Integer> freq = new HashMap<>();
        Map<Character,Integer> map = new HashMap<>();
        for(char c : s1.toCharArray()) freq.put(c, freq.getOrDefault(c, 0)+1);
        int size = s1.length();
        for(int i=0;i<size;i++){
            char c = s2.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        if(map.equals(freq)) return true;
        for(int i=size;i<s2.length();i++){
            char left = s2.charAt(i-size);
            char right = s2.charAt(i);
            map.put(left, map.get(left)-1);
            map.put(right, map.getOrDefault(right, 0)+1);
            if(map.get(left)==0) map.remove(left);
           // System.out.println(map);
            if(map.equals(freq)) return true;
        }
        return false;
    }
    public static List<Integer> findAnagrams(String s, String p){
        int[] freq = new int[26];
        int[] window = new int[26];
        int size = p.length();
        List<Integer> list = new ArrayList<>();
        for(char c : p.toCharArray()){
            freq[c-'a']++;
        }
        for(int i=0;i<size;i++){
            char c = s.charAt(i);
            window[c - 'a']++;
        }
        //printArray(freq);
        if(Arrays.equals(freq, window)) list.add(0);
        for(int i=size;i<s.length();i++){
            window[s.charAt(i)-'a']++;
            window[s.charAt(i-size)-'a']--;
            if(Arrays.equals(freq, window)) list.add(i-size+1);
        }
        return list;
        
    }
    public static int characterReplacement(String s, int k){
        int[] freq = new int[26];
        int maxFreq=0,changes=0,maxLen=0;
        int j=0;
        for(int i=0;i<s.length();i++){
            freq[s.charAt(i) - 'A']++;
            maxFreq=Math.max(maxFreq, freq[s.charAt(i)-'A']);
            changes = (i-j+1) - maxFreq;
            if(changes<=k){
                maxLen = Math.max(maxLen, i-j+1);
            }else{
                char c = s.charAt(j);
                freq[c-'A']--;
                j++;
            }
        }
        return maxLen;

    }

    public static int[] maxSlidingWindow(int[] nums, int k){
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++){

            if(!deque.isEmpty() && deque.peekFirst() <= i-k){
                deque.pollFirst();
            }
            while(!deque.isEmpty() && nums[i]>=nums[deque.peekLast()]){
                    deque.pollLast();
            }
            deque.offerLast(i);
            System.out.println(deque+"****");
            if(i>=k-1){
                System.out.println(deque);
                System.out.println(nums[deque.peekFirst()]);
                list.add(nums[deque.peekFirst()]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }



    public static void printArray(int[] x){
        for(int a : x) System.out.print(a+" ");
        System.out.println();
    }


    public static void main(String[] args) {
        //int[] a= {1,2,3,4,5,6,7};
        int[] b = {1,3,-1,-3,5,3,6,7};
        int[] c = maxSlidingWindow(b, 3);
        printArray(c);
        //System.out.println(maxSumSubarrayK(a, 3));
        // System.out.println(longestSubstring("abcabcbb"));
        // System.out.println(longestSubstring("pwwkew"));
        // System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
        // System.out.println(lengthOfLongestSubstringKDistinct("abcadcacacaca", 3));
        // System.out.println(minWindow("ADOBECODEBANC","ABC"));
        // System.out.println(minWindow("a", "a"));
        //System.out.println(checkInclusion("ab", "eidbaooo"));
        // System.out.println(findAnagrams("cbaebabacd", "abc"));
        // System.out.println(findAnagrams("abcccafffdswacf", "ac"));
        // System.out.println(characterReplacement("ABAB",2));
        // System.out.println(characterReplacement("AABABBA", 1));
    }

}
