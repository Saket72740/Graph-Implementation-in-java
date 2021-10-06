package GraphImplementation.GraphTraversal;
import java.util.*;
public class BFS {
    private final static int initial = 1;
    private final static int waiting = 2;
    private final static int visited = 3;
    private final static int NIL = -1;
    private final static int INFINITY = 99999999;
    static int n;
    static int gtype;
    static int flag = 0;
    static int adj[][] = new int[100][100];
    static int state[] = new int[100];
    static int predecessor[] = new int[100];
    static int distance[] = new int[100];
    Scanner sc = new Scanner(System.in);
    Queue<Integer> q = new LinkedList<>();
    public void create_graph(){
        System.out.print("Enter the number of vertices : ");
        n = sc.nextInt();
        System.out.print("Enter 1 for undirected graph or 2 for directed graph : ");
        gtype = sc.nextInt();
        int maxEdge,u,v;
        if(gtype == 1)
            maxEdge = n*(n-1)/2;
        else
            maxEdge = n*(n-1);
        for(int i=0;i<maxEdge;i++){
            System.out.print("Enter the edge (origin, destination) or (-1 -1 to quit) : ");
            u = sc.nextInt();
            v = sc.nextInt();
            if(u == -1 && v == -1)
                break;
            if(u <= -1 || u >= n || v <= -1 || v >= n){
                System.out.println("Invalid input");
                i--;
            }
            else{
                if(gtype == 1){
                    adj[u][v] = 1;
                    adj[v][u] = 1;
                }
                else{
                    adj[u][v] = 1;
                }
            }
        }
    }
    public void BF_Traversal(){
        int v;
        for(int i=0;i<n;i++){
            state[i] = initial;
            predecessor[i] = NIL;
            distance[i] = INFINITY;
        }
        System.out.print("Enter the starting vertex for BFS : ");
        v = sc.nextInt();
        flag++;
        BFS(v);
        for(int i=0;i<n;i++){
            if(state[i] == initial){
                flag++;
                BFS(i);
            }
        }
    }
    public void BFS(int v){
        int i;
        q.add(v);
        state[v] = waiting;
        distance[v] = 0;
        predecessor[v] = NIL;
        while (!q.isEmpty()){
            v = q.remove();
            System.out.print(v + " ");
            state[v] = visited;
            for(i=0;i<n;i++){
                if(adj[v][i] == 1 && state[i] == initial){
                    q.add(i);
                    state[i] = waiting;
                    if(flag == 1) {
                        predecessor[i] = v;
                        distance[i] = distance[v] + 1;
                    }
                }
            }
        }
        System.out.println();
    }
    public void display(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void shortestDistance(){
        for(int i=0;i<n;i++){
            if(predecessor[i] != NIL)
                System.out.print(predecessor[i] + " ");
            else
                System.out.print("_ ");
        }
        System.out.println();
        for(int i=0;i<n;i++){
            if(distance[i] != INFINITY)
                System.out.print(distance[i] + " ");
            else
                System.out.print("_ ");
        }
        System.out.println();
    }
}
//class MainClass{
//    public static void main(String args[]){
//        BFS bfs = new BFS();
//        bfs.create_graph();
//        bfs.display();
//        bfs.BF_Traversal();
//        bfs.shortestDistance();
//    }
//}
