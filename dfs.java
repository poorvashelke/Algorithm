// Java program to print DFS traversal from a given given graph
import java.io.*;
import java.util.*;
 
// This class represents a directed graph using adjacency list
// representation
class Graph
{
    private int V;   // No. of vertices
 
    // Array  of lists for Adjacency List Representation
    private LinkedList<Integer> adj[];
 
    // Constructor
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    //Function to add an edge into the graph
    void addEdge(int v, int w)
    {
        adj[v].add(w);  // Add w to v's list.
    }
 
    // A function used by DFS
    void DFSUtil(int v,boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v+" ");
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
 
    // The function to do DFS traversal. It uses recursive DFSUtil()
    void DFS(int v)
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
 
        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited);
    }

    // function to calculate no. of nodes in subtree
    static void numberOfNodes(int s, int e)
    {
        count1[s] = 1;
        for(Integer u: adj[s])
        {
            // condition to omit reverse path
            // path from children to parent
            if(u == e)
                continue;
             
            // recursive call for DFS
            numberOfNodes(u ,s);
             
            // update count[] value of parent using 
            // its children
            count1[s] += count1[u];
        }
    }

    // Returns count of edge in undirected graph
    int countEdges()
    {
        int sum = 0;
     
        //traverse all vertex
        for (int i = 0 ; i < V ; i++)
     
            // add all edge that are linked to the
            // current vertex
            sum += adj[i].size();
     
     
        // The count of edge is always even because in
        // undirected graph every edge is connected
        // twice between two vertices
        return sum/2;
    }

    void DFS(int s)
    {

        // Get all adjacent vertices of the popped vertex s
        // If a adjacent has not been visited, then puah it
        // to the stack.
        Iterator<Integer> itr = adj[s].iterator();
                 
        while (itr.hasNext()) 
        {
            int v = itr.next();
            if(!visited.get(v))
                stack.push(v);
        }
    }
 
 
    public static void main(String args[])
    {
        Graph g = new Graph(4);
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println("Following is Depth First Traversal "+
                           "(starting from vertex 2)");
 
        g.DFS(2);
    }
}