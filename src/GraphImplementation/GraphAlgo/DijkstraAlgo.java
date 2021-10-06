package GraphImplementation.GraphAlgo;
import java.util.*;
class DijkstraAlgo {
    static final int MAX = 100;
    final int TEMP = 0;
    final int PERM = 1;
    final int INFINITY = 999999999;
    final int NIL = -1;
    public int n;
    static int adj[][] = new int[MAX][MAX];
    static int predecessor[] = new int[MAX];
    static int pathLength[] = new int[MAX];
    static int status[] = new int[MAX];
    private Scanner sc = new Scanner(System.in);
    void findPath(int s,int v){
        int i,u;
        int path[] = new int[MAX];
        int shortdist = 0;
        int count = 0;
        while(v != s){
            count++;
            path[count] = v;
            u = predecessor[v];
            shortdist += adj[u][v];
            v = u;
        }
        count++;
        path[count] = s;
        System.out.print("Shortest path is : ");
        for(i=count;i>=1;i--){
            System.out.print(path[i] + " ");
        }
        System.out.println();
        System.out.println("Shortest distance is : " + shortdist);
    }
    void Dijkstra(int s){
        int i,current;
        for(i=0;i<n;i++){
            predecessor[i] = NIL;
            pathLength[i] = INFINITY;
            status[i] = TEMP;
        }
        pathLength[s] = 0;
        while(true){
            current = min_temp();
            if(current == NIL)
                return ;
            status[current] = PERM;
            for(i=0;i<n;i++){
                if(adj[current][i] != 0 && status[i] == TEMP){
                    if(pathLength[current] + adj[current][i] < pathLength[i]){
                        predecessor[i] = current;
                        pathLength[i] = pathLength[current] + adj[current][i];
                    }
                }
            }
        }
    }
    int min_temp(){
        int i;
        int min = INFINITY;
        int k = NIL;
        for(i=0;i<n;i++){
            if(status[i] == TEMP && pathLength[i] < min){
                min = pathLength[i];
                k = i;
            }
        }
        return k;
    }
    void create_graph(){
        int maxEdge,gtype,s,v,weight;
        System.out.println("Enter the number of vertices : ");
        n = sc.nextInt();
        System.out.println("Enter 1 for undirected graph or any other key for directed graph : ");
        gtype = sc.nextInt();
        if(gtype == 1)
            maxEdge = n*(n-1)/2;
        else
            maxEdge = n*(n-1);
        for(int i=0;i<maxEdge;i++){
            System.out.println("Enter the edge (u v) or (-1 -1) to quit : ");
            s = sc.nextInt();
            v = sc.nextInt();
            System.out.println("Enter the weight of edge (" + s + " " + v + ") : ");
            weight = sc.nextInt();
            if(s == -1 && v == -1){
                break;
            }
            if(s <= -1 || s >= n || v >= n || v <= -1){
                i--;
                System.out.println("Invalid Input");
            }
            else{
                if(gtype == 1) {
                    adj[s][v] = weight;
                    adj[v][s] = weight;
                }
                else
                    adj[s][v] = weight;
            }
        }
    }
}
//class MainClass{
//    public static void main(String args[]){
//        Scanner sc = new Scanner(System.in);
//        DijkstraAlgo dks = new DijkstraAlgo();
//        int s,v;
//        dks.create_graph();
//        System.out.println("Enter the source vertex : ");
//        s = sc.nextInt();
//        dks.Dijkstra(s);
//        while(true){
//            System.out.println("Enter destination vertex (-1 to quit) : ");
//            v = sc.nextInt();
//            if(v == -1)
//                break;
//            if(v < 0 || v >= dks.n)
//                System.out.println("Invalid input");
//            else if(v == s)
//                System.out.println("Source vertex and destination vertex are same");
//            else
//                dks.findPath(s,v);
//        }
//    }
//}
