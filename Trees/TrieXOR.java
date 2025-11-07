package Trees;

class NodeXor{
    NodeXor links[] = new NodeXor[2];
    public boolean containsKey(int ind){
        return links[ind]!=null;
    }
    public NodeXor get(int ind){
        return links[ind];
    }
    public void put(int ind,NodeXor node){
        links[ind]=node;
    }
}
public class TrieXOR {
    private NodeXor root;
    TrieXOR(){
        root = new NodeXor();
    }
    public void insert(int n){
        NodeXor node = root;
        for(int i=31;i>=0;i--){
            int bit = (n>>i) & 1;
            if(!node.containsKey(bit)) node.put(bit, new NodeXor());
            node = node.get(bit);
        }
    }
    public int getMax(int n){
        NodeXor node = root;
        int max=0;
        for(int i=31;i>=0;i--){
            int bit = (n>>1) & 1;
            if(!node.containsKey(1-bit)){
                max = max | (1<<i);
                node = node.get(1-bit);
            }
            else{
                node = node.get(bit);
            }
        }
        return max; 
    }
    public int maxXOR(int[] n,int[] m){
        int max =0;
        TrieXOR t = new TrieXOR();
        for(int i : n) t.insert(i);
        for(int i : m){
            max = Math.max(max,getMax(i));
        }
        return max;
    }
}
