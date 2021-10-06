package GraphImplementation.GraphAlgo;

import java.util.Scanner;

public class FloydsAlgo {
    private final int MAX = 100;
    private final int INFINITY = 999999999;
    int n;
    private int adj[][] = new int[MAX][MAX];
    int D[][] = new int[MAX][MAX];
    int Pred[][] = new int[MAX][MAX];
    private Scanner sc = new Scanner(System.in);
    public void create_graph(){
        int i,gtype,u,v,maxEdge,weight;
        System.out.print("Enter the number of vertices : ");
        n = sc.nextInt();
        System.out.print("Enter 1 for undirected graph or else other key for directed graph : ");
        gtype = sc.nextInt();
        if(gtype == 1)
            maxEdge = n*(n-1)/2;
        else
            maxEdge = n*(n-1);
        for(i=0;i<maxEdge;i++){
            System.out.print("Enter edge (u v) or (-1 -1 to quit) : ");
            u = sc.nextInt();
            v = sc.nextInt();
            if(u == -1 && v == -1)
                break;
            else if(u <= -1 || u >= n || v <= -1 || v >= n){
                System.out.println("invalid Input");
                i--;
            }
            else{
                System.out.print("Enter the weight of the edge : ");
                weight = sc.nextInt();
                if(gtype == 1){
                    adj[u][v] = weight;
                    adj[v][u] = weight;
                }
                else
                    adj[u][v] = weight;
            }
        }
    }
    public void FloydWarshall() {
        int i, j, k;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (adj[i][j] == 0) {
                    D[i][j] = INFINITY;
                    Pred[i][j] = -1;
                } else {
                    D[i][j] = adj[i][j];
                    Pred[i][j] = i;
                }
            }
        }
        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (D[i][k] + D[k][j] < D[i][j]) {
                        D[i][j] = D[i][k] + D[k][j];
                        Pred[i][j] = Pred[k][j];
                    }
                }
            }
        }
        System.out.println("Shortest path matrix is : ");
        display(D, n);
        System.out.println("Predecessor matrix is : ");
        display(Pred, n);
        for (i = 0; i < n; i++){
            if (D[i][i] < 0) {
                System.out.println("Error : Negative Cycle");
                return;
            }
        }
    }
    public void findPath(int s,int d){
        int i,count;
        int path[] = new int[MAX];
        if(D[s][d] == INFINITY){
            System.out.println("No path");
            return ;
        }
        count = -1;
        do{
            path[++count] = d;
            d = Pred[s][d];
        }while(d != s);
        path[++count] = s;
        for(i=count;i>=0;i--)
            System.out.print(path[i] + " ");
        System.out.println();
    }
    public void display(int matrix[][],int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
//class MainClass{
//    public static void main(String args[]){
//        Scanner sc = new Scanner(System.in);
//        FloydsAlgo flag = new FloydsAlgo();
//        flag.create_graph();
//        flag.FloydWarshall();
//        int s,d;
//        while(true){
//            System.out.print("Enter the source vertex (-1 to quit) : ");
//            s = sc.nextInt();
//            if(s == -1)
//                break;
//            System.out.print("Enter destination vertex : ");
//            d = sc.nextInt();
//            if(s < 0 || s >= flag.n || d < 0 || d > flag.n-1){
//                System.out.println("Enter valid vertices");
//                continue;
//            }
//            System.out.println("Shortest path is : ");
//            flag.findPath(s,d);
//            System.out.println("Length of this path is : " + flag.D[s][d]);
//        }
//    }
//}