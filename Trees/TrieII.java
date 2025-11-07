package Trees;

class Node{
    Node[] links = new Node[26];
    int ec=0,sp=0;
    public boolean containsKey(char ch){
        return links[ch-'a']!=null;
    }
    public Node get(char ch){
        return links[ch-'a'];
    }
    public void put(char ch,Node node){
        links[ch-'a']=node;
    }
    
    public void increaseEnd(){
        ec++;
    }
    public void increasePrefix(){
        sp++;
    }
    public void deleteEnd(){
        ec--;
    }
    public void deletePrefix(){
        sp--;
    }
    public int getEnd(){
        return ec;
    }
    public int getPrefix(){
        return sp;
    }
}
public class TrieII {
    private Node root;
    public TrieII(){
        root = new Node();
    }
    public void insert(String s){
        Node node = root;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(!node.containsKey(c)){
                node.put(c, new Node());
            }
            node=node.get(c);
            node.increasePrefix();
        }
        node.increaseEnd();
    }
    public int countWordsEqualTo(String s){
        Node node = root;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(!node.containsKey(c)) return 0;
            node = node.get(c);
        }
        return node.getEnd();
    }
    public int countWordsStartingWith(String s){
        Node node = root;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(!node.containsKey(c)) return 0;
            node = node.get(c);
        }
        return node.getPrefix();
    }
    public void erase(String s){
        Node node = root;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(!node.containsKey(c)) return;
            node=node.get(c);
            node.deletePrefix();
        }
        node.deleteEnd();
    }
}
