package GraphImplementation.MinimumSpanningTreeAlgo;
import java.util.Scanner;

public class PrimsAlgo {
    private static Scanner sc = new Scanner(System.in);
    private static final int MAX = 100;
    private static final int INFINITY = 999999999;
    private static final int NIL = -1;
    private static final int TEMP = 0;
    private static final int PERM = 1;
    public static class edge{
        int u;
        int v;
        edge(int u,int v){
            this.u = u;
            this.v = v;
        }
    }
    static int n;
    static int adj[][] = new int[MAX][MAX];
    static int predecessor[] = new int[MAX];
    static int status[] = new int[MAX];
    static int length[] = new int[MAX];
    public static void create_graph(){
        int i,maxEdge,u,v,wt;
        System.out.print("Enter number of vertices : ");
        n = sc.nextInt();
        maxEdge = n*(n-1);
        for(i=1;i<=maxEdge;i++){
            System.out.print("Enter edge " + i + " (-1 -1 to quit) : ");
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
            }
        }
    }
    public static void maketree(int r,edge tree[]){
        int current,i;
        int count = 0;
        for(i=0;i<n;i++){
            predecessor[i] = NIL;
            length[i] = INFINITY;
            status[i] = TEMP;
        }
        length[r] = 0;
        while(true){
            current = min_temp();
            if(current == NIL){
                if(count == n-1)
                    return ;
                else{
                    System.out.println("Graph is not connected, No spanning tree possible");
                    System.exit(0);
                }
            }
//            System.out.println(current);
            status[current] = PERM;
            if(current != r){
                count++;
                tree[count] = new edge(predecessor[current],current);
            }
            for(i=0;i<n;i++){
                if(adj[current][i] > 0 && status[i] == TEMP){
                    if(adj[current][i] < length[i]){
                        predecessor[i] = current;
                        length[i] = adj[current][i];
                    }
                }
            }
        }
    }
    public static int min_temp(){
        int i;
        int min = INFINITY;
        int k = -1;
        for(i=0;i<n;i++){
            if(status[i] == TEMP && length[i] < min){
                min = length[i];
                k = i;
            }
        }
        return k;
    }
//    public static void display(int matrix[][]){
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//    public static void main(String[] args){
//        int wt_tree = 0;
//        int i,root;
//        edge[] tree = new edge[MAX];
//        create_graph();
//        //display(adj);
//        System.out.print("Enter the root vertex : ");
//        root = sc.nextInt();
//        maketree(root,tree);
//        System.out.println("Edges to be included in spanning tree are : ");
//        for(i=1;i<n;i++){
//            System.out.print(tree[i].u + " -> ");
//            System.out.println(tree[i].v);
//            wt_tree += adj[tree[i].u][tree[i].v];
//        }
//        System.out.println("Weight of spanning tree is : " + wt_tree);
//    }
}
