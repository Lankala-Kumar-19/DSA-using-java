package greedy;

import java.util.*;
class Meeting{
    int st,end,val;
    public Meeting(int st,int end,int val){
        this.st=st;
        this.end=end;
        this.val=val;
    }
}
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
    public static boolean jumpGame(int[] x){
        if(x.length==1) return true;
        int maxJump=0;
        for(int i=0;i<x.length;i++){
            if(i>maxJump) return false;
            int jump = i + x[i];
            maxJump = Math.max(maxJump, jump);
        }
        return maxJump>=x.length;
    }
    public static int jumpGameII(int[] x){
        return jump(0, 0, x);
    }
    public static int jump(int idx,int j,int[] x){
        if(idx>=x.length) return j;
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=x[idx];i++){
            min = Math.min(min,jump(idx+i, j+1, x));
        }
        return min;
    }
    public static int jobSequencing(int[][] jobs){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            // b[1] profits
            int cmp = Integer.compare(b[1], a[1]);
            if(cmp!=0) return cmp;
            //b[0] deadlines
            return Integer.compare(a[0], b[0]);
        });
        int days=0;
        for(int[] i : jobs){
            pq.offer(new int[]{i[1],i[2]});
            days=Math.max(days, i[1]);
        }
        System.out.println(days);
        int[] dl= new int[days+1];
        int max=0;
        while (!pq.isEmpty()) {
            int[] x = pq.poll();
           System.out.println(x[0]+"   "+x[1]);
            for(int j=x[0];j>=1;j--){
                if(dl[j]==0){
                    dl[j]=1;
                    max+=x[1];
                    break;
                }
            }
        }
        return max;
    }
    public static int NMeetings(int[] st,int[] end){
        if(st==null || st.length==0) return 0;
        PriorityQueue<Meeting> q = new PriorityQueue<>((a,b)-> a.end - b.end);
        for(int i=0;i<st.length;i++){
            q.offer(new Meeting(st[i], end[i], i+1));
        }
        //Arrays.sort(end);
        int cnt=1;
        Meeting z = q.poll();
        int free = z.end;
        while (!q.isEmpty()) {
            Meeting m = q.poll();
            if(free>m.st) continue;
            cnt++;
            free = m.end;
        }
        return cnt;
    }
    public static int nonOverlappingIntervals(int[][] intervals){
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals,(a,b)-> Integer.compare(a[1], b[1]));
        int free = intervals[0][1];
        int cnt=0;
        for(int i=1;i<intervals.length;i++){
            if(free>intervals[i][0]){
                cnt++;
                continue;
            }
            free=intervals[i][1];
        }
        return cnt;
    }
    public static List<int[]> insertIntervals(int[][] intervals,int[] newInterval){
        List<int[]> res = new ArrayList<>();
        int x=0;
        int n=intervals.length;
        while (x<n && intervals[x][1]<newInterval[0]) {
            res.add(intervals[x]);
            x++;
        }
        int st = newInterval[0];
       
        //st = Math.min(st, intervals[x][0]);
        int end = newInterval[1];
        while(x<n && intervals[x][0]<=end){
            st = Math.min(st, intervals[x][0]);
            end = Math.max(end, intervals[x][1]);
            x++;
        }
        res.add(new int[]{st,end});
        while (x<n) {
            res.add(intervals[x]);
            x++;
        }
        for(int[] i : res){
            for(int j : i) System.out.print(j+" ");
            System.out.println();
        }
        return res;
    }
    public static int minNoOfPlatforms(int[] arr,int[] dep){
        int n = arr.length;
        Arrays.sort(arr);
        Arrays.sort(dep);
        int cnt=0,max=0;
        int i=0,j=0;
        while (i<n && j<n) {
            if(arr[i]<=dep[j]){
                cnt++;
                i++;
            }
            else{
                cnt--;
                j++;
            }
            max = Math.max(max, cnt);
        }
        return max;
        // int[][] x = new int[n][2];
        // for(int i=0;i<n;i++){
        //     x[i] = new int[]{arr[i],dep[i]};
        // }
        // Arrays.sort(x,(a,b)-> Integer.compare(a[0],b[0]));
        // for(int[] i : x){
        //     for(int j : i) System.out.print(j+" ");
        //     System.out.println();
        // }
        // Queue<int[]> q = new LinkedList<>();
        // q.offer(x[0]);
        // int cnt=1;
        // int free = x[0][1];
        // for(int i=1;i<n;i++){
        //     System.out.println(free+" "+x[i][0]);
        //     if(free<x[i][0]){
        //         free = x[i][1];
        //         q.poll();
        //         q.offer(x[i]);
        //     }
        //     else{
        //         q.offer(x[i]);
        //         free = Math.min(free,x[i][1]);
        //         cnt=Math.max(cnt, q.size());
        //     }
        // }
        // return cnt;
    }
    public static void main(String[] args) {

        int[] a = {900,945,955,1100,1500,1800};
        int[] b = {920,1200,1130,1150,1900,2000};
        System.out.println(minNoOfPlatforms(a, b));
// int[] start = {1, 2, 3, 0};
// int[] end   = {3, 4, 5, 6};
// int[][] intervals = {
//     {2,3},{5,7},{8,10}
// };
        //insertIntervals(intervals, new int[]{1,11});
//         System.out.println(nonOverlappingIntervals(intervals));
       // System.out.println(NMeetings(start, end));
        // int[] greed = {1,5,3,3,4};
        // int[] s = {4,2,1,2,1,3};
        // int[] b = {5,10,5,20,5,10,5};
        // int[] a = {4,3,7,1,2};
        // int[] c = {2,5,0,0};
        // System.out.println(jumpGameII(c));
//         int[][] jobs = {
//     {1, 2, 100}, // {jobId, deadline, profit}
//     {2, 1, 19},
//     {3, 2, 27},
//     {4, 1, 25},
//     {5, 3, 15}
// };
        // System.out.println(jobSequencing(jobs));
        // System.out.println(SJF(a));
        //System.out.println(lemonadeChange(b));
        //System.out.println(assignCookies(greed, s));
    }
}
