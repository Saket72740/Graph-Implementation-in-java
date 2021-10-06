package GraphImplementation.GraphAlgo;
import java.util.*;

class BellmanFordAlgo {
    private final int MAX = 100;
    final int INFINITY = 999999999;
    private final int NIL = -1;
    public int n;
    private int adj[][] = new int[MAX][MAX];
    private int predecessor[] = new int[MAX];
    int pathLength[] = new int[MAX];
    boolean isPresent[] = new boolean[MAX];
    private Queue<Integer> q = new LinkedList<>();
    Scanner sc = new Scanner(System.in);
    public void create_graph(){
        int maxEdge,s,v,gtype,w;
        System.out.print("Enter the number of vertices : ");
        n = sc.nextInt();
        System.out.print("Enter 1 for undirected graph else other for directed graph : ");
        gtype = sc.nextInt();
        if(gtype == 1)
            maxEdge = n*(n-1)/2;
        else
            maxEdge = n*(n-1);
        for(int i=0;i<maxEdge;i++) {
            System.out.print("Enter the edge (u v) or (-1 -1) to quit : ");
            s = sc.nextInt();
            v = sc.nextInt();
            if (s == -1 && v == -1)
                return;
            if (s < 0 || s >= n || v < 0 || v >= n) {
                System.out.println("Invalid Input");
                i--;
            }
            else {
                System.out.println("Enter the weight of the edge : ");
                w = sc.nextInt();
                if (gtype == 1) {
                    adj[s][v] = w;
                    adj[v][s] = w;
                } else {
                    adj[s][v] = w;
                }
            }
        }
    }
    public void findPath(int s,int v){
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
        path[count] = count;
        System.out.print("Shortest path is : ");
        for(i=count;i>=1;i--){
            System.out.print(path[i] + " ");
        }
        System.out.println();
        System.out.println("Shortest distance is : " + shortdist);
    }
    public int BellmanFord(int s){
        int k=0,current,i;
        for(i=0;i<n;i++){
            predecessor[i] = NIL;
            pathLength[i] = INFINITY;
            isPresent[i] = false;
        }
        pathLength[s] = 0;
        q.add(s);
        isPresent[s] = true;
        while(!q.isEmpty()){
            current = q.remove();
            isPresent[current] = false;
            if(s == current)
                k++;
            if(k > n)
                return -1;
            for(i=0;i<n;i++) {
                if (adj[current][i] != 0) {
                    if (pathLength[i] > pathLength[current] + adj[current][i]) {
                        pathLength[i] = pathLength[current] + adj[current][i];
                        predecessor[i] = current;
                        if (!isPresent[i]) {
                            q.add(i);
                            isPresent[i] = true;
                        }
                    }
                }
            }
        }
        return 1;
    }
}

//class MainClass{
//    public static void main(String args[]){
//        Scanner sc = new Scanner(System.in);
//        BellmanFordAlgo bfa = new BellmanFordAlgo();
//        int flag,s,v;
//        bfa.create_graph();
//        System.out.print("Enter the source vertex : ");
//        s = sc.nextInt();
//        flag = bfa.BellmanFord(s);
//        if(flag == -1){
//            System.out.println("Error : negative cycle in graph");
//            return ;
//        }
//        while(true){
//            System.out.print("Enter the destination vertex (-1 to quit) : ");
//            v = sc.nextInt();
//            if(v == -1)
//                break;
//            if(v < 0 || v >= bfa.n){
//                System.out.println("Invalid input");
//            }
//            else if(v == s){
//                System.out.println("Source and destination vertices are same");
//            }
//            else if(bfa.pathLength[v] == bfa.INFINITY){
//                System.out.println("There is no path from source to destination vertex");
//            }
//            else
//                bfa.findPath(s,v);
//        }
//    }
//}