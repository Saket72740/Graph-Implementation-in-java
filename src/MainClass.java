import java.util.*;
class MainClass{
    public static void main(String args[]){
        int c[][] = { {0,0,0,0,0,0,0,0,0},
                      {0,0,2,1,3,0,0,0,0},
                      {0,0,0,0,0,2,3,0,0},
                      {0,0,0,0,0,6,7,0,0},
                      {0,0,0,0,0,6,8,9,0},
                      {0,0,0,0,0,0,0,0,6},
                      {0,0,0,0,0,0,0,0,4},
                      {0,0,0,0,0,0,0,0,5},
                      {0,0,0,0,0,0,0,0,0}};
        int cost[] = new int[9];
        cost[8] = 0;
        int path[] = new int[9];
        int d[] = new int[9];
        int min, stage = 4;
        for(int i=7;i>=1;i--){
            min = 999999999;
            for(int k=i+1;k<=8;k++){
                if(c[i][k] != 0 && c[i][k]+cost[k] < min){
                    min = c[i][k] + cost[k];
                    d[i] = k;
                }
            }
            cost[i] = min;
        }
        path[1] = 1;
        path[stage] = 8;
        for(int i=2;i<stage;i++){
            path[i] = d[path[i-1]];
        }
        for(int i:cost)
            System.out.print(i + " ");
        System.out.println();
        for(int i:path)
            System.out.print(i + " ");
        System.out.println();
        for(int i:d)
            System.out.print(i + " ");
        System.out.println();
    }
}