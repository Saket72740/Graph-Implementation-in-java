package GraphImplementation.GraphRepresent;
import java.util.*;
public class AdjacencyList {
    Vertex start = null;
    class Vertex{
        int info;
        Vertex nextVertex;
        Edge firstEdge;
        Vertex(int u){
            this.info = u;
            this.firstEdge = null;
            this.nextVertex = null;
        }
    }
    class Edge{
        Vertex destVertex;
        Edge nextEdge;
        Edge(Vertex v){
            this.destVertex = v;
            this.nextEdge = null;
        }
    }
    public Vertex findVertex(int u){
        Vertex ptr,loc;
        ptr = start;
        while(ptr != null){
            if(ptr.info == u){
                loc = ptr;
                return loc;
            }
            ptr = ptr.nextVertex;
        }
        loc = null;
        return loc;
    }
    public void insertVertex(int u){
        Vertex ptr;
        if(start == null){
            start = new Vertex(u);
            return;
        }
        ptr = start;
        while (ptr.nextVertex != null){
            ptr = ptr.nextVertex;
        }
        ptr.nextVertex = new Vertex(u);
    }
    public void insertEdge(int u,int v){
        Vertex locu,locv;
        Edge ptr,temp = null;
        locu = findVertex(u);
        locv = findVertex(v);
        if(locu == null){
            System.out.println("Start vertex not exist first insert the edge " + u);
            return;
        }
        if(locv == null){
            System.out.println("End vertex not exist first insert the edge " + u);
            return;
        }
        temp = new Edge(locv);
        if(locu.firstEdge == null){
            locu.firstEdge = temp;
            return;
        }
        ptr = locu.firstEdge;
        while(ptr.nextEdge != null){
            ptr = ptr.nextEdge;
        }
        ptr.nextEdge = temp;
    }
    public void deleteEdge(int u,int v){
        Vertex locu;
        Edge p,temp;
        locu = findVertex(u);
        if(locu == null){
            System.out.println("Start vertex is not present");
            return ;
        }
        if(locu.firstEdge == null){
            System.out.println("Edge not present");
            return ;
        }
        if(locu.firstEdge.destVertex.info == v){
            temp = locu.firstEdge;
            locu.firstEdge = locu.firstEdge.nextEdge;
            return;
        }
        p = locu.firstEdge;
        while(p.nextEdge != null){
            if(p.nextEdge.destVertex.info == v){
                temp = p.nextEdge;
                p.nextEdge = temp.nextEdge;
                return;
            }
            p = p.nextEdge;
        }
        System.out.println("This edge is not present in graph");
    }
    public void deleteIncomingEdges(int u){
        Vertex ptr;
        Edge p,temp;
        ptr = start;
        while(ptr != null){
            if(ptr.firstEdge == null){
                ptr = ptr.nextVertex;
                continue;
            }
            if(ptr.firstEdge.destVertex.info == u){
                temp = ptr.firstEdge;
                ptr.firstEdge = temp.nextEdge;
                continue;
            }
            p = ptr.firstEdge;
            while(p.nextEdge != null){
                if(p.nextEdge.destVertex.info == u){
                    temp = p.nextEdge;
                    p.nextEdge = temp.nextEdge;
                    continue;
                }
                p = p.nextEdge;
            }
            ptr = ptr.nextVertex;
        }
    }
    public void deleteVertex(int u){
        Vertex ptr,temp;
        Edge p,tempo;
        if(start == null){
            System.out.println("Graph is empty");
            return;
        }
        if(start.info == u){
            temp = start;
            start = temp.nextVertex;
        }
        else{
            ptr = start;
            while(ptr.nextVertex != null){
                if(ptr.nextVertex.info == u)
                    break;
                ptr = ptr.nextVertex;
            }
            if(ptr.nextVertex == null){
                System.out.println("Vertex " + u + " not found");
                return;
            }
            else{
                temp = ptr.nextVertex;
                ptr.nextVertex = temp.nextVertex;
            }
        }
        ptr = start;
        while(ptr != null){
            deleteEdge(ptr.info,u);
            ptr = ptr.nextVertex;
        }
    }
    public void display(){
        Vertex ptr;
        Edge p;
        ptr = start;
        while(ptr != null){
            p = ptr.firstEdge;
            System.out.print(ptr.info + " ");
            while(p != null){
                System.out.print(p.destVertex.info + " ");
                p = p.nextEdge;
            }
            System.out.println();
            ptr = ptr.nextVertex;
        }
    }
}
