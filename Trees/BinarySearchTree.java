package Trees;

import java.util.*;



class BSTNode{
    BSTNode left,right;
    int data;
    public BSTNode(){}
    public BSTNode(int data){
        this.data=data;
        this.left=null;
        this.right=null;
    }
}

public class BinarySearchTree {
    public static BSTNode insert(BSTNode root,int data){
        if(root==null){
            // System.out.println("a");
            // root = new BSTNode(data);
            // System.out.println(root.data);
            return new BSTNode(data);
        }
        if(data<root.data) root.left=insert(root.left, data);
        if(data>root.data) root.right=insert(root.right, data);
       // root = new BSTNode(data);
        return root;
        
    }
    public static BSTNode search(BSTNode root,int data){
        if(root==null || root.data==data) return root;
        if(data<root.data) 
            return search(root.left, data);
        else 
            return search(root.right, data);
        
    }
    public static int ceil(BSTNode root,int data){
        if(root==null || root.data==data) return data;
        int ceil =-1;
        BSTNode cur= root;
        while(cur!=null){
            if(cur.data==data) return data;
            if(data>cur.data) cur=cur.right;
            else{
                ceil = Math.min(cur.data, ceil);
                cur=cur.left;
            }
        }
        return ceil;
        // if(data<root.data) return ceil(root.left, data);
        // else return ceil(root.right, data);
    }
    public static int floor(BSTNode root,int key){
        int floor = -1;
        while (root!=null) {
            if(key<root.data){
                root=root.left;
            }
            else{
                floor = Math.max(root.data, floor);
                root=root.right;
            }
        }
        return floor;
    }
    public static BSTNode delete(BSTNode root,int key){
        if (root==null) {
            return null;
        }
        if(key<root.data) delete(root.left, key);
        else if(key>root.data) delete(root.right, key);
        else{
            if(root.left==null && root.right==null){
                return null;
            }
            else if(root.left==null){
                return root.right;
            }
            else if(root.right==null){
                return root.left;
            }
            else{
                BSTNode successor = findMin(root);
                root.data=successor.data;
                root.right=delete(root.right, successor.data);
            }
        }
        return root;    
    }
    private static BSTNode findMin(BSTNode node){
        while (node.left!=null) {
            node=node.left;
        }
        return node;
    }
    public static int findKSmallest(BSTNode node,int k){
        if(node==null) return -1;
        int c=0;
        while(node!=null){
            
            if(node.left==null){
                c++;
                if(c==k) return node.data;
                node=node.right;
            }
            else{
                BSTNode pred = node.left;
                while(pred.right!=null && pred.right!=node){
                    pred=pred.right;
                }
                if(pred.right==null){
                    pred.right=node;
                    node=node.left;
                }
                else{
                    pred.right=null;
                    c++;
                    if(c==k) return node.data;
                    node=node.right;
                }
            }
        }
        return -1;
    }
    public static int findKLargest(BSTNode node,int k){
        if(node==null) return -1;
        int count=0;
        while (node!=null) {
            if(node.right==null){
                count++;
                if(count==k) return node.data;
                node=node.left;
            }
            else{
                BSTNode pred = node.right;
                while (pred.left!=null && pred.left!=node) {
                    pred=pred.left;
                }
                if(pred.left!=node){
                    pred.left=node;
                    node=node.right;
                }
                else{
                    count++;
                    if(count==k) return node.data;
                    pred.left=null;
                    node=node.left;
                }
            }
        }
        return -1;
    }

    public static boolean isValidBST(BSTNode root){
        return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static boolean isValidBSTHelper(BSTNode root,int low,int high){
        if(root==null) return true;
        if(root.data>high || root.data<low) return false;
        return isValidBSTHelper(root.left, low, root.data) && isValidBSTHelper(root.right, root.data, high);
    }
    public static BSTNode lowCommonAncestor(BSTNode root,BSTNode n1,BSTNode n2){
        if(root==null) return null;
        if(n1.data>root.data && n2.data>root.data) return lowCommonAncestor(root.right, n1, n2);
        if(n1.data<root.data && n2.data<root.data) return lowCommonAncestor(root.left, n1, n2);
       // else return lowCommonAncestor(root.right, n1, n2);
       return root; 
    }
    public static BSTNode bstFromPre(int[] pre){
        return bstFromPre(pre,Integer.MAX_VALUE,new int[]{0});
    }
    public static BSTNode bstFromPre(int[] pre,int b,int[] i){
        if(i[0]==pre.length || pre[i[0]]>b) return null;
        BSTNode root = new BSTNode(pre[i[0]++]);
        root.left = bstFromPre(pre, root.data, i);
        root.right = bstFromPre(pre, b, i);
        return root;
    } 
    public static BSTNode inOrderSuccessor(BSTNode root,int val){
        BSTNode[] suc = new BSTNode[1];
        suc[0] = null;
        inOrderSuccessor(root, val, suc);
        return suc[0];

    }
    public static BSTNode inOrderSuccessor(BSTNode root,int val,BSTNode[] suc){
        if(root==null) return null;
        if(val<root.data){
            suc[0] = root;
            return inOrderSuccessor(root.left, val,suc);
        }
        else if(val>root.data) return inOrderSuccessor(root.right, val, suc);
        else{
            if(root.right!=null){
                BSTNode temp = root.right;
                while (temp.left!=null) {
                    temp=temp.left;
                }                  
                suc[0]=temp;  
            }
        }
        
        return suc[0];

    }
    public boolean hasTwoSum(BSTNode root,int key){
        Iterator it = new Iterator(root);
        ReverseIterator rit = new ReverseIterator(root);
        int left = it.next().data;
        int right = rit.prev().data;
        while (it.hasNext() && rit.hasPrev() && left<right) {
            int sum = left+right;
            if(sum==key) return true;
            if(sum<key){
                left=it.next().data;
            }
            else right=rit.prev().data;
        }
        return false;
    }
    public static void main(String[] args) {
        BSTNode n1=null;
        //System.out.println(n1);
        n1= insert(n1, 10);
        n1= insert(n1, 15);
        n1= insert(n1, 13);
        n1= insert(n1, 5);
        n1 = insert(n1, 20);
        n1 = insert(n1, 1);
        n1 = insert(n1, 7);

        // BSTNode x = inOrderSuccessor(n1, 13);
        // if(x!=null)
        // System.out.println(x.data);
        Iterator it = new Iterator(n1);
        ReverseIterator rit = new ReverseIterator(n1);
        while (it.hasNext()) {
            System.out.print(it.next().data+" ");
        }
        System.out.println();
        while (rit.hasPrev()) {
            System.out.print(rit.prev().data+" ");
        }
        // System.out.println(n1.data+" "+n1.right.data);
        // System.out.println(search(n1, 11).data);
        // System.out.println(n1.data+" "+n1.right.data);
        //System.out.println(findKSmallest(n1, 1));

    }
}

class Iterator{
    Stack<BSTNode> st;
    //BSTNode x;
    public Iterator(BSTNode root){
        st = new Stack<>();
        //x=root;
        while (root!=null) {
            st.push(root);
            root=root.left;
        }
    }
    public BSTNode next(){
        BSTNode temp = st.pop();
        BSTNode node = temp;
        if(node.right!=null){
            st.push(node.right);
            node=node.right;
            while(node.left!=null) {
                st.push(node.left);
                node=node.left;
            }
        }
        return temp;
    }
    public boolean hasNext(){
        return !st.isEmpty();
    }
}
class ReverseIterator{
    Stack<BSTNode> st;
    public ReverseIterator(BSTNode root){
        st = new Stack<>();
        while (root!=null) {
            st.push(root);
            root=root.right;
        }
    }
    public BSTNode prev(){
        BSTNode temp = st.pop();
        BSTNode node = temp;
        if(temp.left!=null){
            st.push(temp.left);
            temp=temp.left;
            while (temp.right!=null) {
                st.push(temp.right);
                temp=temp.right;
            }
        }
        return node;
    }
    public boolean hasPrev(){
        return !st.isEmpty();
    }
}
class RecoverBST{
    static BSTNode first=null,second=null,prev=null;

    public static void recoverTree(BSTNode root){
        inOrder(root);
        if(first!=null && second!=null){
            int temp = first.data;
            first.data=second.data;
            second.data=temp;
        }

    }
    public static void inOrder(BSTNode root){
        if(root==null) return;

        inOrder(root.left);
        if(prev!=null && prev.data>root.data){
            if(first!=null) first=prev;
            second=root;
        }
        prev=root;

        inOrder(root.right);
    }
}