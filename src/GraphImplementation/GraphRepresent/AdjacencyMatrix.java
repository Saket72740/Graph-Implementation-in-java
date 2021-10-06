package GraphImplementation.GraphRepresent;
import java.util.*;
class AdjacencyMatrix {
    Scanner sc = new Scanner(System.in);
    static int n;
    static int gtype = 0;
    static int adj[][] = new int[100][100];
    boolean flag = false;
    public void create_graph(){
        flag = true;
        int maxEdge,u,v;
        System.out.print("Enter the number of vertices : ");
        n = sc.nextInt();
        System.out.print("Enter 1 for undirected graph or 2 for directed graph : ");
        gtype = sc.nextInt();
        if(gtype == 1)
            maxEdge = n*(n-1)/2;
        else
            maxEdge = n*(n-1);
        for(int i=0;i<maxEdge;i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            if (u == -1 && v == -1)
                break;
            if (u < 0 || v < 0 || v >= n || u >= n){
                System.out.println("Invalid input");
                i--;
            }
            else{
                if(gtype == 1) {
                    adj[u][v] = 1;
                    adj[v][u] = 1;
                }
                else
                    adj[u][v] = 1;
            }
        }
    }
    public void insert_edge(int u,int v){
        if(u <= -1 || u >= n) {
            System.out.println("Invalid origin");
            return;
        }
        if(v >= n || v<= -1){
            System.out.println("Invalid destination");
            return ;
        }
        if(flag == false){
            System.out.println("First create the graph");
            return ;
        }
        if (gtype == 1) {
            adj[u][v] = 1;
            adj[v][u] = 1;
        }
        else
            adj[u][v] = 1;
    }
    public void insert_edge(int u,int v,int w){
        if(u <= -1 || u >= n) {
            System.out.println("Invalid origin");
            return;
        }
        if(v >= n || v<= -1){
            System.out.println("Invalid destination");
            return ;
        }
        if(flag == false){
            System.out.println("First create the graph");
            return ;
        }
        if (gtype == 1) {
            adj[u][v] = w;
            adj[v][u] = w;
        }
        else
            adj[u][v] = w;
    }
    public void del_edge(int u,int v){
        if(u <= -1 || u >=  n){
            System.out.println("Invalid origin");
            return ;
        }
        if(v <= -1 || v >= n){
            System.out.println("Invalid destination");
            return ;
        }
        if(flag == false){
            System.out.println("First create the graph");
            return ;
        }
        else{
            if(gtype == 1){
                adj[u][v] = 0;
                adj[v][u] = 0;
            }
            else
                adj[u][v] = 0;
        }
    }
    public void display(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }
}
