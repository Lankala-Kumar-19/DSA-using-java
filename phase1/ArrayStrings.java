package phase1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayStrings {
    public static void reverseArray(int[] a){
        for(int i=0,j=a.length-1;i<j;i++,j--){
            int temp=a[i];
            a[i]=a[j];
            a[j]=temp;
        }
    }
    public static boolean isPalindrome(int[] a){
        if(a.length==0|| a.length==1) return true;
        int i=0,j=a.length-1;
        while(i<j){
            if(a[i]!=a[j]) return false;
            i++;
            j--;
        }
        return true;
    }
    public static void moveZeroes(int[] a){
        int i=0,j=0;
        while(j<a.length){
            if(a[j]!=0){
                if(i!=j){
                    a[i]=a[j];
                }
                i++;
            }
            j++;
        }
        while(i<a.length){
            a[i]=0;
            i++;
        }
    }
    public static int removeDuplicates(int[] a){
        int i=0,j=1;
        while(j<a.length){
            // if(i==j){
            //     i++;
            //     j++;
            //     continue;
            // }
            // if(a[i]==a[j]){
            //     i++;
            // }
            if(a[i]<a[j]){
                i++;
                a[i]=a[j];
                // i++;
            }
            j++;
        }
        return i+1;
    }
    public static void printArray(int[] a){
        for(int i:a){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static int longestSubstring(String s){
        Set<Character> set = new HashSet<>();
        int maxLen=0;
        int left=0,right=0;
        while(right<s.length()){
            char c = s.charAt(right);
            while(set.contains(c)){
                set.remove(s.charAt(left));
                left++;
            }
            maxLen=Math.max(maxLen, right-left+1);
            set.add(c);
            right++;
        }
        return maxLen;
    }
    // public static List<List<String>> groupAnagrams(String[] s){
    //     List<List<String>> list=new ArrayList<>();
    //     List<String> x=new ArrayList<>();
    //     //x.add(s[0]);
    //     int i=0;
    //     int j=0;
    //     while(j<s.length){
    //         x.add(s[j]);
    //         System.out.println("----------");
    //         while(isAnagram(s[i], s[j]) && i!=j){
    //             x.add(s[i]);
    //             i++;
    //             System.out.println(x);
    //         }
    //         System.out.println("-----------");
    //         list.add(x);
    //         j++;
    //         x=new ArrayList<>();
    //     }
    //     return list;

    // }
    public static List<List<String>> groupAnagrams(String[] strs){
        if(strs==null || strs.length==0) return new ArrayList<>();

        Map<String,List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);

        }
        return new ArrayList<>(map.values());
        
    }
    public static boolean isAnagram(String s1,String s2){
        Map<Character,Integer> m1=new HashMap<>();
        Map<Character,Integer> m2=new HashMap<>();
        System.out.println(s1+"   "+s2);
        for(char s:s1.toCharArray()){
            m1.put(s, m1.getOrDefault(s, 0)+1);
        }
        for(char c : s2.toCharArray()){
            m2.put(c, m2.getOrDefault(c, 0)+1);
        }
        return m1.equals(m2);
    }
    public static void main(String[] args) {
        // int[] a ={1,2,3,4,5};
        // int[] b ={1,2,3,2,1};
        // int[] c = {0,1,0,3,12};
        // int[] d= {0,0,1,1,1,2,2,3,3,4};
        String[] e= {"eat","tea","tan","ate","nat","bat"};
        // System.out.println(longestSubstring("abcabcbb"));
        // System.out.println(removeDuplicates(d));
        // printArray(d);
        // moveZeroes(c);
        // printArray(c);
        // reverseArray(a);
        // printArray(a);
        // System.out.println(isPalindrome(a));
        // System.out.println(isPalindrome(b));
        System.out.println(groupAnagrams(e));
    }
}
