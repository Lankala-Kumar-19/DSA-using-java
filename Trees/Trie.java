package Trees;

class Node{
    Node[] links = new Node[26];
    boolean flag=false;

    public boolean containsKey(char ch){
        return links[ch-'a']!=null;
    }
    public Node get(char ch){
        return links[ch-'a'];
    }
    public void put(char ch,Node node){
        links[ch-'a']=node;
    }
    public void setEnd(){
        flag=true;
    }
    public boolean isEnd(){
        return flag;
    }
}

public class Trie {
    Node root;
    public Trie(){
        root=new Node();
    }
    public void insert(String word){
        Node node = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(!node.containsKey(c)){
                node.put(c,new Node());
            }
            node=node.get(c);
        }
        node.setEnd();
    }
    public boolean search(String word){
        Node node = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(!node.containsKey(c)) return false;
            node=node.get(c);
        }
        return node.isEnd();
    }
    public boolean startsWith(String word){
        Node node = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(!node.containsKey(c)) return false;
            node=node.get(c);
        }
        return true;
    }
    public String completeString(int n,String[] a){
        String x="";
        Trie trie= new Trie();
        for(String i : a) trie.insert(i);
        for(String i : a){
            if(trie.isCompleteWord(i)){
                if(i.length()>x.length()){
                    x=i;
                }
                else if(i.length()==x.length() && i.compareTo(x)<0){
                    x=i;
                }
            }
        }
        return x=="" ? "None" : x;
    }
    private boolean isCompleteWord(String s){
        Node node = root;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(!node.containsKey(c)) return false;
            node=node.get(c);
            if(!node.isEnd()) return false;
        }
        return true;
    }
    public int noOfDistinctSubstrings(String s){
        int cnt=0;
        Node root = new Node();
        for(int i=0;i<s.length();i++){
            Node node = root;
            for(int j=i;j<s.length();j++){
                char c = s.charAt(j);
                if(!node.containsKey(c)){
                    node.put(c, new Node());
                    cnt++;
                }
                node = node.get(c);
            }
        }
        return cnt+1;
    }
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.noOfDistinctSubstrings("ababa"));
    }
}
