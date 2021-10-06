package GraphImplementation.GraphTraversal;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.*;

public class DFS {
    private static int n;
    private static int adj[][] = new int[100][100];
    private static int state[] = new int[100];
    private final int initial = 1;
    private final int visited = 2;
    private static int gtype;
    private Stack<Integer> stk = new Stack<>();
    public void DF_Traversal(){
        Scanner sc = new Scanner(System.in);
        int i;
        for(i=0;i<n;i++){
            state[i] = initial;
        }
        System.out.println("Enter the starting node for dfs search : ");
        int v = sc.nextInt();
        dfs(v);
        for(i=0;i<n;i++){
            if(state[i] == initial)
                dfs(i);
        }
    }
    public void DFS(int v){
        stk.push(v);
        while(!stk.isEmpty()) {
            v = stk.pop();
            if(state[v] == initial){
                System.out.print(v + " ");
                state[v] = visited;
            }
            for (int i = 0; i < n; i++) {
                if (adj[v][i] == 1 && state[i] == initial) {
                    stk.push(i);
                }
            }
        }
        System.out.println();
    }
    public void dfs(int v){
        int i;
        System.out.print(v + " ");
        state[v] = visited;
        for(i=0;i<n;i++){
            if(state[i] == initial && adj[v][i] == 1)
                dfs(i);
        }
    }
    public void create_graph() {
        Scanner sc = new Scanner(System.in);
        int maxEdge,u,v;
        System.out.print("Enter the number of vertices in the graph : ");
        n = sc.nextInt();
        System.out.print("Enter 1 for undirected graph or 2 for directed graph : ");
        gtype = sc.nextInt();
        if(gtype == 1)
            maxEdge = n*(n-1)/2;
        else
            maxEdge = n*(n-1);
        for(int i=0;i<maxEdge;i++){
            System.out.println("Enter edge (u v) or (-1 -1) to quit : ");
            u = sc.nextInt();
            v = sc.nextInt();
            if(u == -1 && v == -1)
                break;
            if(u <= -1 || u >= n || v <= -1 || v >= n){
                System.out.println("Invalid input");
                i--;
            }
            else {
                if (gtype == 1){
                    adj[u][v] = 1;
                    adj[v][u] = 1;
                }
                else
                    adj[u][v] = 1;
            }
        }
    }
}

//class MainClass{
//    public static void main(String args[]){
//        DFS Dfs = new DFS();
//        Dfs.create_graph();
//        Dfs.DF_Traversal();
//    }
//}