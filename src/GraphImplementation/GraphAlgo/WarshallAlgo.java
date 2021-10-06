package GraphImplementation.GraphAlgo;

import java.util.Scanner;

public class WarshallAlgo {
    private final int MAX = 100;
    int n;
    int adj[][] = new int[MAX][MAX];
    private Scanner sc = new Scanner(System.in);
    public void create_graph(){
        int maxEdge,i,u,v,gtype;
        System.out.print("Enter the number of vertices : ");
        n = sc.nextInt();
        System.out.println("Enter 1 for undirected graph else any other key : ");
        gtype = sc.nextInt();
        if(gtype == 1)
            maxEdge = n*(n-1)/2;
        else
            maxEdge = n*(n-1);
        for(i=0;i<maxEdge;i++){
            System.out.println("Enter the edge (u v) or (-1 -1) to quit : ");
            u = sc.nextInt();
            v = sc.nextInt();
            if(u == -1 && v == -1)
                break;
            else if(u <= -1 || u >= n || v <= -1 || v >= n){
                System.out.println("Invalid Input");
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
    public void path(int adj[][],int n){
        int k;
        int P[][] = new int[MAX][MAX];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                P[i][j] = adj[i][j];
            }
        }
        for(k=0;k<n;k++){
            for(int i=0;i<n;i++) {
                for (int j = 0; j < n; j++) {
                    P[i][j] = (P[i][j] | (P[i][k] & P[k][j]));
                }
            }
            System.out.println("P" + k + " is : ");
            display(P,n);
            System.out.println();
        }
        System.out.println("P" + (k-1) + " is the path matrix of the given graph");
    }
    public void display(int matrix[][],int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
//class MainClass{
//    public static void main(String args[]) {
//        WarshallAlgo wrshl = new WarshallAlgo();
//        wrshl.create_graph();
//        System.out.println("The adjacency matrix is : ");
//        wrshl.display(wrshl.adj,wrshl.n);
//        System.out.println();
//        wrshl.path(wrshl.adj,wrshl.n);
//    }
//}