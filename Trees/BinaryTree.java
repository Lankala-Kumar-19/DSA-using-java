package Trees;

import java.util.*;

class Node{
    int data;
    public Node left,right;
    public Node(int data){
        this.data=data;
    }
    public Node(int data,Node left,Node right){
        this.data=data;
        this.left=left;
        this.right=right;
    }    
}
class Pair{
    Node node;
    int val;
    public Pair(Node node,int data){
        this.node=node;
        this.val=data;
    }
}
class Tuple{
    Node node;
    int col;
    int row;
    int val;
    public Tuple(Node node,int col, int row){
        this.node=node;
        this.col=col;
        this.row=row;
        this.val=node.data;
    }
}
public class BinaryTree {
   // static Node root;
    public static void preOrder(Node node){
        if(node==null) return;
        System.out.print(node.data+" ");
        preOrder(node.left);
        preOrder(node.right);
    }
    public static void inOrder(Node node){
        if(node==null) return;
        inOrder(node.left);
        System.out.print(node.data+" ");
        inOrder(node.right);
    }
    public static void postOrder(Node node){
        if(node==null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data+" ");
    }
    public static void levelOrder(Node root){
        Queue<Node> queue = new LinkedList<Node>();
        List<List<Integer>> warpList = new LinkedList<>();
        if(root==null) return;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int lvl = queue.size();
            List<Integer> lvlList = new LinkedList<>();

            for(int i=0;i<lvl;i++){
                if(queue.peek().left!=null) queue.offer(queue.peek().left);
                if(queue.peek().right!=null) queue.offer(queue.peek().right);
                lvlList.add(queue.poll().data);
            }
            warpList.add(lvlList);
        }
        for(List<Integer> i : warpList){
            for(int x : i)
                System.out.print(x+" ");
            System.out.println();
        }            
    }
    public static void preOrderIt(Node node){
        Stack<Node> stack = new Stack<>();
        System.out.println("PreOrder it");
        if(node == null) return;
        stack.push(node);
        while (!stack.isEmpty()) {
           // System.out.println(stack);
           System.out.print(stack.peek().data+" ");
            Node x = stack.pop();
            if(x.right!=null) stack.push(x.right);
            if(x.left!=null) stack.push(x.left);
            //stack.pop();
        }
    }
    public static void InOrderIt(Node node){
        if(node==null) return;
        System.out.println("postOrder it");
        Stack<Node> stack = new Stack<>();
       // int left=0,right=0;
        //stack.push(node);
        while (true) {
            if(node!=null){
                stack.push(node);
                node=node.left;
            }
            else{
                if(stack.isEmpty()){
                    break;
                }
                node = stack.pop();
                System.out.print(node.data+" ");
                node=node.right;
            }  
        }
    }
    public static void postOrderIt(Node node){
        if(node == null) return;
        System.out.println("PostOrder rec");
        Stack<Node> stack = new Stack<>();
        Stack<Node> resStack = new Stack<>();
        stack.add(node);
        while (!stack.isEmpty()) {
            Node x = stack.pop();
            if(x.left!=null) stack.push(x.left);
            if(x.right!=null) stack.push(x.right);
            resStack.push(x);
        }
        while (!resStack.isEmpty()) {
            System.out.print(resStack.pop().data+" ");
        }
    }
    public static void postOrderItOne(Node node){
        if(node==null) return;
        System.out.println("POstOrder it one stack");
        Stack<Node> stack = new Stack<>();
        Node curr = node;
        Node last = null;
        while (!stack.isEmpty() || curr!=null) {
            if(curr!=null){
                stack.push(curr);
                curr=curr.left;
            }
            else{
                Node temp = stack.peek();
                if(temp.right!=null && last!=temp.right){
                    curr = temp.right;
                }
                else{
                    System.out.print(temp.data+" ");
                    last=stack.pop();
                }
            }
        }
    }
     public static void preInPost(Node root){
            Stack<Pair> stack = new Stack<>();
            List<Integer> pre = new ArrayList<>();
            List<Integer> in = new ArrayList<>();
            List<Integer> post = new ArrayList<>();

            stack.push(new Pair(root, 1));
            while (!stack.isEmpty()) {
                Pair it = stack.pop();
                if(it.val==1){
                    pre.add(it.node.data);
                    it.val++;
                    stack.push(it);
                    if(it.node.left!=null)
                        stack.push(new Pair(it.node.left, 1));
                }
                else if(it.val==2){
                    in.add(it.node.data);
                    it.val++;
                    stack.add(it);
                    if(it.node.right!=null)
                        stack.push(new Pair(it.node.right, 1));
                }
                else{
                    post.add(it.node.data);
                }

            }
            System.out.println("PreOrder");
            for(int i : pre) System.out.print(i+" ");
            System.out.println();
            System.out.println("InOrder");
            for(int i : in ) System.out.print(i+" ");
            System.out.println();
            System.out.println("PostOrder");
            for(int i : post) System.out.print(i+" ");
            System.out.println();
    }
    public static int maxDepth(Node node){
        if(node==null) return 0;
        return 1 + Math.max(maxDepth(node.left),maxDepth(node.right));
    }
    public static int check(Node node){
        if(node==null) return 0;
        int left = check(node.left);
        int right = check(node.right);
        if(left==-1 || right==-1) return -1;
        if(Math.abs(right-left)>1) return -1;

        return Math.max(left,right)+1;
    }
    public static boolean isBalanced(Node node){
        return check(node)!=-1;
    }
    public static int findMax(Node node,int max[]){
        if(node==null) return 0;
        int left = findMax(node.left,max);
        int right = findMax(node.right,max);
        max[0] = Math.max(max[0], right+left);
        return 1+Math.max(left, right);
    }
    public static int findDiameter(Node node){
        int[] max = new int[1];
        findMax(node, max);
        return max[0];
    }
    public static int maxWidth(Node node){
        if(node==null) return 0;
        Queue<Node> queue = new LinkedList<>();
        int max=Integer.MIN_VALUE;
        queue.offer(node);
        while (!queue.isEmpty()) {
            int lvl = queue.size();
            max = Math.max(max, lvl);
            for(int i=0;i<lvl;i++){
                Node x = queue.poll();
                if(x.left!=null) queue.offer(x.left);
                if(x.right!=null) queue.offer(x.right);
            }
        }
        return max;
    }
    public static int maxSum(Node node){
        int[] maxSum = new int[1];
        findSum(node, maxSum);
        return maxSum[0];

    }
    public static int findSum(Node node,int[] maxSum){
        if(node==null) return 0;
        System.out.println(node.data);
        int leftSum = findSum(node.left, maxSum);
        int rightSum = findSum(node.right, maxSum);
        maxSum[0] = Math.max(maxSum[0], node.data+rightSum+leftSum);

        return node.data+leftSum+rightSum;
    }
    public static int maxPathSum(Node root){
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        pathSumHelper(root, max);
        return max[0];

    }
    public static int pathSumHelper(Node node,int[] max){
        if(node==null) return 0;
        int left = Math.max(0, pathSumHelper(node.left, max));
        int right = Math.max(0, pathSumHelper(node.right, max));

        max[0] = Math.max(max[0], right+left+node.data);

        return node.data+ Math.max(left, right);
    }
    public static boolean isIdentical(Node node1,Node node2){
        if(node1==null && node2==null) return true;
        if(node1==null || node2==null) return false;
        if(node1.data!=node2.data) return false;
        System.out.println("node1 "+node1.data+" node2 "+node2.data);
        boolean l = isIdentical(node1.left, node2.left);
        boolean r = isIdentical(node1.right, node2.right);
        if(!l || !r) return false;
        return true; 
    }
    public static void zigZag(Node node){
        if(node==null) return;
        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        q.offer(node);
        int flag=0;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> x = new ArrayList<>();
            for(int i=0;i<size;i++){
                Node cur = q.poll();
                System.out.println("peek "+cur.data);
                if(flag==0)
                x.add(cur.data);
                else
                x.add(0,cur.data);
                if(cur.left!=null) q.offer(cur.left);
                if(cur.right!=null) q.offer(cur.right);
            }
            flag = flag==0 ? 1 : 0;
            list.add(x);
        }
        for(List<Integer> i : list){
            for(int a : i) System.out.print(a+" ");
            System.out.println();
        }
    }
    private static boolean isLeaf(Node node){
        //if(node==null) return false;
        if(node.right==null && node.left==null) return true;
        return false;
    }
    public static void addLeft(Node node,List<Integer> res){
        if(node == null) return;
        Node cur = node.left;
        while (cur!=null) {
            if(!isLeaf(cur)) res.add(cur.data);
            if(cur.left!=null) cur=cur.left;
            else cur=cur.right;
        }
    }
    public static void addRight(Node node,List<Integer> res){
        if(node==null) return;
        Node cur = node.right;
        List<Integer> tmp = new ArrayList<>();
        while (cur!=null) {
            if(!isLeaf(cur)) tmp.add(cur.data);
            if(cur.right!=null) cur=cur.right;
            else cur=cur.left;
        }
        for(int i=tmp.size()-1;i>=0;i--){
            res.add(tmp.get(i));
        }
    }
    public static void addLeaves(Node node,List<Integer> res){
        if(isLeaf(node)){
            res.add(node.data);
            return;
        }
        if(node.left!=null) addLeaves(node.left, res);
        if(node.right!=null) addLeaves(node.right, res);
    }

    public static void printBoundary(Node node){
        List<Integer> res = new ArrayList<>();
        if(!isLeaf(node)) res.add(node.data);
        addLeft(node, res);
        addLeaves(node, res);
        addRight(node, res);

        for(int i : res) System.out.print(i+" ");
        System.out.println();
    }
    public static void verticalTraversal(Node root){
        if(root==null) return;
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a,b)->{
            if(a.col!=b.col) return Integer.compare(a.col, b.col);
            if(a.row!=b.row) return Integer.compare(a.row, b.row);
            return Integer.compare(a.val, b.val);
        });
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, 0, 0));
        while(!queue.isEmpty()){
            Tuple cur = queue.poll();
            int row = cur.row;
            Node node = cur.node;
            int col = cur.col;
            pq.offer(new Tuple(node, col, row));
            if(node.left!=null){
                queue.offer(new Tuple(node.left, col-1, row+1));
            }
            if(node.right!=null){
                queue.offer(new Tuple(node.right, col+1, row+1));
            }
        }
        TreeMap<Integer,List<Integer>> map = new TreeMap<>();
        while(!pq.isEmpty()){
            Tuple t = pq.poll();
            if(!map.containsKey(t.col)){
                map.put(t.col, new ArrayList<>());
            }
            map.get(t.col).add(t.val);
        }
        System.out.println(map);
        res.addAll(map.values());
        for(List<Integer> x : res){
            for(int i : x) System.out.print(i+" ");
            System.out.println();
        }
    }
    public static void topView(Node root){
        if(root == null) return;
        List<Integer> list = new ArrayList<>();
        Queue<Top> queue = new LinkedList<>();
        queue.offer(new Top(root, 0));
        TreeMap<Integer,Integer> map = new TreeMap<>();
        while (!queue.isEmpty()) {
            Top cur = queue.poll();
            Node node = cur.node;
            int col = cur.col;
           // int val = cur.val;
            if(!map.containsKey(col))
            map.put(col, node.data);
            //System.out.println(map);
            if(node.left!=null) queue.offer(new Top(node.left, col-1));
            if(node.right!=null) queue.offer(new Top(node.right, col+1));
        }
        list.addAll(map.values());
        for(int i : list) System.out.print(i+" ");
        System.out.println();
    }
        public static void bottomView(Node root){
        if(root == null) return;
        List<Integer> list = new ArrayList<>();
        Queue<Top> queue = new LinkedList<>();
        queue.offer(new Top(root, 0));
        TreeMap<Integer,Integer> map = new TreeMap<>();
        while (!queue.isEmpty()) {
            Top cur = queue.poll();
            Node node = cur.node;
            int col = cur.col;
           // int val = cur.val;
            //if(!map.containsKey(col))
            map.put(col, node.data);
            //System.out.println(map);
            if(node.left!=null) queue.offer(new Top(node.left, col-1));
            if(node.right!=null) queue.offer(new Top(node.right, col+1));
        }
        list.addAll(map.values());
        for(int i : list) System.out.print(i+" ");
        System.out.println();
    }
    public static void rightView(Node node,int lvl,List<Integer> res){
        if(node==null) return;
        if(lvl==res.size()) res.add(node.data);
        if(node.right!=null) rightView(node.right, lvl+1, res);
        if(node.left!=null) rightView(node.left, lvl+1, res);
    }
        public static void leftView(Node node,int lvl,List<Integer> res){
        if(node==null) return;
        if(lvl==res.size()) res.add(node.data);
        if(node.left!=null) leftView(node.left, lvl+1, res);
        if(node.right!=null) leftView(node.right, lvl+1, res);  
    }
    public static boolean isSymmetrical(Node root){
        if(root==null) return true;
        // if(root.left==null && root.right==null) return true;
        // if(root.left==null || root.right==null) return false;
        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();
        q1.offer(root.left);
        q2.offer(root.right);
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if(q1.isEmpty() || q2.isEmpty()) return false;
            Node n1 = q1.poll();
            Node n2 = q2.poll();

            if(n1==null && n2==null) continue;
            if(n1==null || n2==null) return false;
            if(n1.data!=n2.data) return false;

            q1.offer(n1.left);
            q1.offer(n1.right);

            q2.offer(n2.right);
            q2.offer(n2.left);

            // if(n1.left==null && n2.right!=null) return false;
            // if(n1.left!=null && n2.right==null) return false;

            // if(n1.right==null && n2.left!=null) return false;
            // if(n1.right!=null && n2.left==null) return false;

            // if(n1.left!=null) q1.offer(n1.left);
            // if(n1.right!=null) q1.offer(n1.right);

            // if(n2.right!=null) q2.offer(n2.right);
            // if(n2.left!=null) q2.offer(n2.left);
        }

        return true;

    }
    public static void getPath(Node node,int x){
        List<Integer> res = new ArrayList<>();
        if(node==null) return;
        getPathHelper(node,res, x);
        for(int i : res)System.out.print(i+" ");
        System.out.println();
    }
    public static boolean getPathHelper(Node node,List<Integer> res,int x){
        if(node==null) return false;
        res.add(node.data);
        if(node.data==x) return true;
        if(getPathHelper(node.left, res, x) || getPathHelper(node.right, res, x))
            return true;
        res.remove(res.size()-1);
        return false;
    }
    public static Node lowCommonAncestor(Node node,Node a,Node b){
        if(node==null || node==a || node==b){
            return node;
        }
        Node left = lowCommonAncestor(node.left, a, b);
        Node right = lowCommonAncestor(node.right, a, b);
        if(left==null) return right;
        else if(right==null) return left;
        else return node;
    }
    public static int maxWidthBT(Node node){
        if(node==null) return 1;
        int maxWidth =0;
        Queue<Level> queue = new LinkedList<>();
        queue.offer(new Level(node, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            int min = queue.peek().index;
            int f=0,l=0;
            for(int i=0;i<size;i++){
                Level cur = queue.poll();
                int index = cur.index - min;
                Node n =cur.node;

                if(i==0) f=index;
                if(i==size-1) l =index;
                if(n.left!=null) queue.offer(new Level(n.left, 2*index));
                if(n.right!=null) queue.offer(new Level(n.right,2*index+1));
            }
            maxWidth = Math.max(maxWidth, l-f+1);

        }
        return maxWidth;
    }
    public static void childrenSum(Node node){
        if(node ==null) return;
        int child = 0;
        if(node.left!=null) child+=node.left.data;
        if(node.right!=null) child+=node.right.data;
        if(node.data< child){
            node.data = child;
        }
        else{
            if(node.left!=null)
                node.left.data = node.data;
            if(node.right!=null)
                node.right.data= node.data;
        }
        childrenSum(node.left);
        childrenSum(node.right);
        int tot = 0;
        if(node.left!=null) tot+=node.left.data;
        if(node.right!=null) tot+=node.right.data;
        if(node.left!=null || node.right!=null) node.data = tot;
    }
    private static void markParents(Node root,Map<Node, Node> parent_track,Node target){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if(cur.left!=null){
                parent_track.put(cur.left, cur);
                queue.offer(cur.left);
            }
            if(cur.right!=null){
                parent_track.put(cur.right, cur);
                queue.offer(cur.right);
            }
        }
    }
    public static void distanceK(Node root,Node target,int k){
        Map<Node,Node> parent_track = new HashMap<>();
        markParents(root, parent_track, null);
        Map<Node,Boolean> visted = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(target);
        visted.put(target, true);
        int lvl = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if(lvl ==k) break;
            lvl++;
            for(int i=0;i<size;i++){
                Node cur = queue.poll();
                if(cur.left!=null && visted.get(cur.left)==null){
                    queue.offer(cur.left);
                    visted.put(cur.left, true);
                }
                if(cur.right!=null && visted.get(cur.right)==null){
                    queue.offer(cur.right);
                    visted.put(cur.right, true);
                }
                if(parent_track.get(cur)!=null && visted.get(parent_track.get(cur))==null){
                    queue.offer(parent_track.get(cur));
                    visted.put(parent_track.get(cur), true);
                }
            }
        }
        //List<Integer> res = new ArrayList<>();
        while(!queue.isEmpty()){
            System.out.print(queue.poll().data+" ");
        }
    }
    public static void burnMinTime(Node root,Node node){
        Map<Node,Node> parent_track = new HashMap<>();
        markParents(root, parent_track, null);
        Queue<Node> queue = new LinkedList<>();
        Map<Node,Boolean> visted = new HashMap<>();
        queue.offer(node);
        visted.put(node, true);
        

        int minTime = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean burn = false;
            
            // for(Map.Entry<Node,Boolean> m : visted.entrySet()){
            //     System.out.print(m.getKey().data+" = "+m.getValue()+" ");
            // }
            // System.out.println();
            for(int i=0;i<size;i++){
                Node cur = queue.poll();
                if(cur.left!=null && visted.get(cur.left)==null){
                    burn=true;
                    queue.offer(cur.left);
                    visted.put(cur.left, true);
                }
                if(cur.right!=null && visted.get(cur.right)==null){
                    burn=true;
                    queue.offer(cur.right);
                    visted.put(cur.right, true);
                }
                if(parent_track.get(cur)!=null && visted.get(parent_track.get(cur))==null){
                    burn=true;
                    queue.offer(parent_track.get(cur));
                    visted.put(parent_track.get(cur), true);
                }
            }
            if(burn) minTime++;
        }
        System.out.println(minTime);
    }

    public static int totalNodes(Node root){
        if(root==null) return 0;
        if(leftHeight(root)==rightHeight(root)){
            return (int) (Math.pow(2, leftHeight(root))-1);
        }
        return 1+totalNodes(root.left)+totalNodes(root.right);

    }
    private static int leftHeight(Node root){
        if(root==null) return 0;
        return 1+leftHeight(root.left);
    }
    private static int rightHeight(Node root){
        if(root==null) return 0;
        return 1+rightHeight(root.right);
    }
    public static Node buildTreeInPre(int[] preOrder, int[] inOrder){
        Map<Integer,Integer> inMap = new HashMap<>();
        for(int i=0;i<inOrder.length;i++){
            inMap.put(inOrder[i], i);
        }
        Node root = buildTreeInPre(preOrder, 0, preOrder.length-1, inOrder, 0, inOrder.length-1, inMap);
        return root;
    }
    public static Node buildTreeInPre(int[] preOrder,int preStart, int preEnd, int[] inOrder,int inStart, int inEnd,Map<Integer,Integer> inMap){
        if (preStart>preEnd || inStart>inEnd) {
            return null;
        }
        Node root = new Node(preOrder[preStart]);
        int inRoot = inMap.get(root.data);
        int numsLeft = inRoot - inStart;
        root.left = buildTreeInPre(preOrder, preStart+1, preStart+numsLeft, inOrder, inStart, inRoot-1, inMap);
        root.right = buildTreeInPre(preOrder, preStart+numsLeft+1, preEnd, inOrder, inRoot+1, inEnd, inMap);
        return root;
    }
    public static Node buildTreeInPost(int[] postOrder,int[] inOrder){
        Map<Integer,Integer> inMap = new HashMap<>();
        for(int i=0;i<postOrder.length-1;i++){
            inMap.put(inOrder[i], i);
        }
        Node root = buildTreeInPost(postOrder, 0, postOrder.length-1, inOrder, 0, inOrder.length-1, inMap);

        return root;
    }
    public static Node buildTreeInPost(int[] postOrder,int postStart,int postEnd, int[] inOrder,int inStart,int inEnd, Map<Integer,Integer> inMap){
        if(postStart>postEnd || inStart>inEnd){
            return null;
        }
        Node root = new Node(postOrder[postEnd]);
        int inRoot = inMap.get(root.data);
        int numsRight = inEnd - inRoot;

        root.right = buildTreeInPost(postOrder, postEnd-numsRight, postEnd-1, inOrder, inRoot+1, inEnd, inMap);
        root.left = buildTreeInPost(postOrder, postStart, postEnd-numsRight-1, inOrder, inStart, inRoot-1, inMap);
        return root;

    }
public static String serialize(Node root) {
    if (root == null) return "";

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    StringBuilder sb = new StringBuilder();

    while (!queue.isEmpty()) {
        Node node = queue.poll();

        if (node == null) {
            sb.append("#,");
            continue;
        }

        sb.append(node.data).append(",");
        queue.offer(node.left);
        queue.offer(node.right);
    }

    return sb.toString();
}

    // public static Node deserialize(String s){
    //     String[] x = s.split(",");
    //     //for(String a : x) System.out.print(a+" ");
    //     int i=0;
    //     Queue<Node> queue = new LinkedList<>();
    //     Node root = new Node(Integer.parseInt(x[i++]));
    //     queue.offer(root);
    //     while(i<x.length){
    //         Node parent = queue.poll();
    //         if(!x[i].equals("#")){
    //             Node left = new Node(Integer.parseInt(x[i]));
    //             parent.left = left;
    //             queue.offer(left);
    //         }
    //         if(!x[++i].equals("#")){
    //             Node right = new Node(Integer.parseInt(x[i]));
    //             parent.right = right;
    //             queue.offer(right);
    //         }
    //         i++;
    //     }
    //     return root;
    // }
    public static Node deserialize(String s) {
    if (s == null || s.isEmpty()) return null;

    String[] x = s.split(",");
    int i = 0;

    Node root = new Node(Integer.parseInt(x[i++]));
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty() && i < x.length) {
        Node parent = queue.poll();

        // Left child
        if (i < x.length && !x[i].equals("#") && !x[i].isEmpty()) {
            Node left = new Node(Integer.parseInt(x[i]));
            parent.left = left;
            queue.offer(left);
        }
        i++;

        // Right child
        if (i < x.length && !x[i].equals("#") && !x[i].isEmpty()) {
            Node right = new Node(Integer.parseInt(x[i]));
            parent.right = right;
            queue.offer(right);
        }
        i++;
    }

    return root;
}
static Node prev = null;
    public static void flatten(Node root){
        if(root==null) return;
       // Node prev = null;
        //flatten(root, prev);
        flatten(root.right);
        flatten(root.left);
        // if(prev!=null)
        // System.out.println(prev.data);
        root.right=prev;
        root.left=null;
        prev=root;
        // Node cur = root;
        // while (cur!=null) {
        //     System.out.print(cur.data+" ");
        //     cur=cur.right;
        // }
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(11);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

       // root.left.right.left = new Node(6);
        //root.left.right.right= new Node(9);
       // root.left.left.left = new Node(8);
        Node node2 = new Node(-10);
        node2.left = new Node(9);
        node2.right = new Node(20);
        node2.right.left = new Node(15);
        node2.right.right = new Node(7);
        Node node1 = new Node(-10);
        node1.left = new Node(9);
        node1.right = new Node(20);
        node1.right.left = new Node(15);
        node1.right.right = new Node(7);

        Node node3 = new Node(3);
        node3.left = new Node(5);
        node3.right = new Node(1);
        node3.left.left = new Node(6);
        node3.left.right = new Node(2);
        node3.left.right.left = new Node(7);
        node3.left.right.right = new Node(4);
        node3.right.left = new Node(0);
        node3.right.right = new Node(8);

        Node x = new Node(1);
        x.left = new Node(2);
        x.right = new Node(13);
        x.right.left = new Node(4);
        x.right.right = new Node(5);


        Node y = new Node(1);
        y.left = new Node(2);
        y.right = new Node(5);
        y.left.left = new Node(3);
        y.left.right = new Node(4);
        y.right.right = new Node(6);
        y.right.right.right = new Node(7);

        flatten(y);
        Node c = y;
        while(c!=null){
            System.out.print(c.data+" ");
            c=c.right;
        }

        //System.out.println(serialize(x));

        // List<Integer> res = new ArrayList<>();
        // rightView(root, 0, res);
        // for(int i : res) System.out.print(i+" ");
        // System.out.println();
        // res = new ArrayList<>();
        // leftView(root, 0, res);
        // for(int i : res) System.out.print(i+" ");
        //System.out.println(isSymmetrical(root));
       // getPath(root, 5);
       //System.out.println(maxWidthBT(root));
       
        //distanceK(node3,node3.left,2);
        //burnMinTime(root, root.left);
        // System.out.println(totalNodes(root));
        // System.out.println(serialize(x));
        // System.out.println(serialize(deserialize(serialize(x))));
    }    
}
class Top{
    Node node;
    int col;
    int val;
    public Top(Node node,int col){
        this.node=node;
        this.col=col;
        this.val=node.data;
    }
}
class Level{
    Node node;
    int index;
    public Level(Node node,int index){
        this.node =node;
        this.index=index;
    }
}
