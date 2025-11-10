package Graphs;
import java.util.*;
public class Disjoint {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    public Disjoint(int n){
        for(int i=0;i<=n;i++){
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }
    public int findUParent(int node){
        if(node== parent.get(node)) return node;
        int up = findUParent(parent.get(node)); 
        parent.set(node, up);
        return parent.get(node);
    }
    public void unionByRank(int u,int v){
        int up_u = findUParent(u);
        int up_v = findUParent(v);
        if(up_u==up_v) return;
        if(rank.get(up_u)<rank.get(up_v)){
            parent.set(up_u, up_v);
        }
        else if(rank.get(up_v)<rank.get(up_u)){
            parent.set(up_v, up_u);
        }
        else{
            parent.set(up_v, up_u);
            rank.set(up_u, rank.get(up_u)+1);
        }
    }
    public void unionBySize(int u,int v){
        int up_u = findUParent(u);
        int up_v = findUParent(v);
        if(up_u==up_v) return;
        if(size.get(up_u)<size.get(up_v)){
            parent.set(up_u, up_v);
            size.set(up_v, size.get(up_v)+size.get(up_u));
        }
        else{
            parent.set(up_v, up_u);
            size.set(up_u, size.get(up_v)+size.get(up_u));
        }
    }
}
