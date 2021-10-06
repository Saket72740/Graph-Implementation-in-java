package GraphImplementation.MinimumSpanningTreeAlgo;

import java.util.Scanner;

public class KruskalsAlgo {
    private static final int MAX = 100;
    private static final int NIL = -1;
    private static int n;
    private static int[][] adj = new int[MAX][MAX];
    static Scanner sc = new Scanner(System.in);
    static Edge front = null;
    static class Edge{
        int u;
        int v;
        int wt;
        Edge link;
        Edge(int u,int v,int w){
            this.u = u;
            this.v = v;
            this.wt = w;
            this.link = null;
        }
    }
    public static void make_tree(Edge[] tree){
        Edge temp;
        int v1,v2,root_v1=-1,root_v2=-1;
        int father[] = new int[MAX];
        int i,count=0;
        for(i=0;i<n;i++)
            father[i] = NIL;
        while(!isEmpty() && count < n-1){
            temp = del_pque();
            v1 = temp.u;
            v2 = temp.v;
            while(v1 != NIL){
                root_v1 = v1;
                v1 = father[v1];
            }
            while(v2 != NIL){
                root_v2 = v2;
                v2 = father[v2];
            }
            if(root_v1 != root_v2){
                count++;
                tree[count] = new Edge(temp.u, temp.v,temp.wt);
                father[root_v2] = root_v1;
             }
        }
    }
    public static void insert_pque(int u,int v,int wt){
        Edge q;
        Edge temp = new Edge(u,v,wt);
        if(front == null || temp.wt < front.wt) {
            temp.link = front;
            front = temp;
        }
        else{
            q = front;
            while(q.link != null && q.link.wt <= temp.wt){
                q = q.link;
            }
            temp.link = q.link;
            q.link = temp;
            if(q.link == null)
                temp.link = null;
        }
    }
    public static Edge del_pque(){
        if(front == null)
            return null;
        Edge temp = front;
        front = front.link;
        return temp;
    }
    public static boolean isEmpty(){
        if(front == null)
            return true;
        else
            return false;
    }
    public static void create_graph(){
        int maxEdge,u,v,wt;
        System.out.println("Enter the number of vertices : ");
        n = sc.nextInt();
        maxEdge = n*(n-1)/2;
        for(int i=1;i<maxEdge;i++){
            System.out.print("Enter the edge (u v) or (-1 -1 to quit) : ");
            u = sc.nextInt();
            v = sc.nextInt();
            if(u == -1 && v == -1)
                break;
            else if(u <= -1 || u >= n || v <= -1 || v >= n){
                System.out.println("Invalid Input");
                i--;
            }
            else{
                System.out.print("Enter the weight of this edge : ");
                wt = sc.nextInt();
                adj[u][v] = wt;
                adj[v][u] = wt;
                insert_pque(u,v,wt);
            }
        }
    }
//    public static void main(String[] args){
//        int i;
//        Edge tree[] = new Edge[MAX];
//        int wt_tree = 0;
//        create_graph();
//        make_tree(tree);
//        System.out.println("Edge to be included in minimum spanning tree are : ");
//        for(i=1;i<n;i++){
//            System.out.print(tree[i].u + " -> ");
//            System.out.println(tree[i].v);
//            wt_tree += tree[i].wt;
//        }
//        System.out.println("Weight of this spanning tree is : " + wt_tree);
//    }
}
