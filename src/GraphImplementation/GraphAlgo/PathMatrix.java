package GraphImplementation.GraphAlgo;

import java.util.Scanner;

public class PathMatrix {
    private static final int MAX = 100;
    private static int n;
    static int adj[][] = new int[MAX][MAX];
    private static Scanner sc = new Scanner(System.in);
    public static void create_graph(){
        int maxEdge,gtype,u,v,i;
        System.out.println("Enter the number of vertices : ");
        n = sc.nextInt();
        System.out.println("Enter 1 for undirected graph or else other key for directed graph : ");
        gtype = sc.nextInt();
        if(gtype == 1)
            maxEdge = n*(n-1)/2;
        else
            maxEdge = n*(n-1);
        for(i=0;i<maxEdge;i++){
            System.out.print("Enter edge (u v) or (-1 -1) to quit : ");
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
    public static void display(int matrix[][]){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void pow_matrix(int p,int adjp[][]){
        int i,j,k;
        int temp[][] = new int[MAX][MAX];
        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                adjp[i][j] = adj[i][j];
            }
        }
        for(k=1;k<p;k++){
            multiply(adjp,adj,temp);
            for(i=0;i<n;i++){
                for(j=0;j<n;j++)
                    adjp[i][j] = temp[i][j];
            }
        }
    }
    public static void multiply(int mat1[][],int mat2[][],int mat3[][]){
        int i,j,k;
        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                mat3[i][j] = 0;
                for(k=0;k<n;k++){
                    mat3[i][j] = mat3[i][j] + mat1[i][k]*mat2[k][j];
                }
            }
        }
    }
//    public static void main(String args[]){
//        int adjp[][] = new int[MAX][MAX];
//        int X[][] = new int[MAX][MAX];
//        int path[][] = new int[MAX][MAX];
//        int i,j,p;
//        create_graph();
//        System.out.println("The adjacency matrix : ");
//        display(adj);
//        for(i=0;i<n;i++){
//            for(j=0;j<n;j++)
//                X[i][j] = 0;
//        }
//        for(p=1;p<=n;p++){
//            pow_matrix(p,adjp);
//            System.out.println("Adjacency matrix raised to power " + p + " is : ");
//            display(adjp);
//            for(i=0;i<n;i++)
//                for(j=0;j<n;j++)
//                    X[i][j] = X[i][j] + adjp[i][j];
//        }
//        System.out.println("The matrix X is : ");
//        display(X);
//        for(i=0;i<n;i++){
//            for(j=0;j<n;j++){
//                if(X[i][j] == 0)
//                    path[i][j] = 0;
//                else
//                    path[i][j] = 1;
//            }
//        }
//        System.out.println("The path matrix is : ");
//        display(path);
//    }
}
