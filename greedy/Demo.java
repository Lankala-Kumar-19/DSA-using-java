package greedy;

import java.util.Arrays;

public class Demo {
    public static int assignCookies(int[] greed,int[] s){
        Arrays.sort(greed);
        Arrays.sort(s);
        int cnt=0;
        int i=0,j=0;
       // System.out.println("--");
        while(i<greed.length && j<s.length){
            //System.out.println(greed[i]+" "+s[j]);
            if(greed[i]<=s[j]){
                cnt++;
                i++;
            }
            j++;
        }
        return cnt;
    }
    public static boolean lemonadeChange(int[] bills){
        int five=0,ten=0;
        for(int i : bills){
            System.out.println("cur: "+i+"    ");
            if(i==5){
                five++;
            }
            else{
                int x = i;
                if(x==20){
                    if(!((ten>=1 && five>=1) || five>=3)) return false;
                    if(ten>=1 && five>=1){
                        ten--;
                        five--;
                    } 
                    else five-=3;
                }
                else{
                    if(five<1) return false;
                    ten++;
                    five--;
                }
                // while (i!=5) {
                //     System.out.println("ten: "+ten);
                //     if(ten>0 && i-ten>=5){
                //         i-=10;
                //         ten--;
                //     }
                //     else if(five>0 && i-five>=5){
                //         i-=5;
                //         five--;
                //     }
                //     else return false;
                //     if(i<5) return false;
                // }
                // if(x==10) ten++;
            }
        }
        return true;
    }
    public static int SJF(int[] bt){
        Arrays.sort(bt);
        int[] x = new int[bt.length];
        x[0]=0;
        int z=0;
        for(int i=1;i<bt.length;i++){
            x[i]= bt[i-1] + x[i-1];
            z+=x[i];
        }
        return z/x.length;
    }
    
    public static void main(String[] args) {
        int[] greed = {1,5,3,3,4};
        int[] s = {4,2,1,2,1,3};
        int[] b = {5,10,5,20,5,10,5};
        int[] a = {4,3,7,1,2};
        System.out.println(SJF(a));
        //System.out.println(lemonadeChange(b));
        //System.out.println(assignCookies(greed, s));
    }
}
