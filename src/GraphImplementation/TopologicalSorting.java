package GraphImplementation;
import java.util.*;
public class TopologicalSorting {
    static int n;
    static int adj[][] = new int[100][100];
    static Queue<Integer> q = new LinkedList<>();
    static Scanner sc = new Scanner(System.in);
    static void create_graph(){
        int maxEdge,u,v,gtype;
        System.out.print("Enter the number of vertices : ");
        n = sc.nextInt();
        System.out.print("Enter 1 for undirected graph else any other key for directed graph : ");
        gtype = sc.nextInt();
        if(gtype == 1)
            maxEdge = n*(n-1)/2;
        else
            maxEdge = n*(n-1);
        for(int i=0;i<maxEdge;i++){
            System.out.print("Enter edge (u v) or (-1 -1 to quit) : ");
            u = sc.nextInt();
            v = sc.nextInt();
            if(u == -1 && v == -1)
                break;
            else if(u <= -1 || u >= n || v <= -1 || v >= n){
                System.out.println("Invalid Input");
                i--;
            }
            else{
                if(gtype == 1){
                    adj[u][v] = 1;
                    adj[v][u] = 1;
                }
                else
                    adj[u][v] = 1;
            }
        }
    }
    static int indegree(int v){
        int i,in_deg = 0;
        for(i=0;i<n;i++){
            if(adj[i][v] == 1)
                in_deg++;
        }
        return in_deg;
    }
//    public static void main(String args[]){
//        int i,v,count,topo_order[],indeg[];
//        topo_order = new int[100];
//        indeg = new int[100];
//        create_graph();
//        for(i=0;i<n;i++){
//            indeg[i] = indegree(i);
//            if(indeg[i] == 0)
//                q.add(i);
//        }
//        count = 0;
//        while(!q.isEmpty() && count < n){
//            v = q.remove();
//            topo_order[++count] = v;
//            for(i=0;i<n;i++){
//                if(adj[v][i] == 1){
//                    adj[v][i]= 0;
//                    indeg[i] -= 1;
//                    if(indeg[i] == 0)
//                        q.add(i);
//                }
//            }
//        }
//        if(count < n){
//            System.out.println("No topological ordering possible, graph contain cycle");
//            return ;
//        }
//        System.out.println("Vertices in topological order are : ");
//        for(i=1;i<=count;i++){
//            System.out.print(topo_order[i] + " ");
//        }
//        System.out.println();
//    }
}
