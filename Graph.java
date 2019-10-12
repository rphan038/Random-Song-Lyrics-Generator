import java.util.*;
import java.util.concurrent.*;
import java.io.*;


public class Graph {
  class Vertex implements Comparable<Vertex> {
    String name;
    List<Edge> nbs = new ArrayList<Edge>();
    int dist = Integer.MAX_VALUE;
    boolean visited = false;
    Vertex prev;
    
    public Vertex(String n) {
      name = n;
    }
    
    @Override
    public int compareTo(Vertex v) {
      return dist - v.dist;
    }
  }
  
  class Edge {
    Vertex u, v;
    int w;
    
    public Edge(Vertex u, Vertex v, int w) {
      this.u = u;
      this.v = v;
      this.w = w;
    }
  }
  
  Map<String, Vertex> vertexMap = new HashMap<String, Vertex>(); 
  
  /* add an undirected edge */
  public void addEdge(String start, String end, int w) {
    Vertex u = getVertex(start);
    Vertex v = getVertex(end);
    u.nbs.add(new Edge(u, v, w));
    v.nbs.add(new Edge(v, u, w));
  }
  
  /* add a directed edge */
  public void addDirectedEdge(String start, String end, int w) {
    Vertex u = getVertex(start);
    Vertex v = getVertex(end);
    u.nbs.add(new Edge(u, v, w));
  }
    
  // retrieve vertex associated with the given name
  public Vertex getVertex(String name) {
    Vertex v = vertexMap.get(name);
    if (v == null) {
      v = new Vertex(name);
      vertexMap.put(name, v);
    }
    return v;
  }
  //Gets the neighbors of a given vertex and returns it
  public ArrayList<String> getNeighbors(Vertex v) {
      //The output ArrayList containing the list of neighbors
      ArrayList<String> nbs = new ArrayList<String>();
      //For each edge neighbor of v....
      for(Edge e : v.nbs) {
          //Store the first neighbor
          Vertex w = e.v;
          //If they're not null add them to ArrayList
          if(w != null)
              nbs.add(getName(w));
          
      }
      //Return the ArrayList
      return nbs;
  }
  //Gets the name/String of the vertex passed in to the parameter
  public String getName(Vertex v) {
      return v.name;
  }
  
  /* run a BFS form a given start vertex */
  public void bfs(Vertex startVertex) {
    Deque<Vertex> q = new ArrayDeque<Vertex>();
    q.add(startVertex);
    startVertex.dist = 0;
    
    while (!q.isEmpty()) {
      Vertex u = q.poll();
      System.out.println(u.name + " " +u.dist);
      for (Edge e: u.nbs) {
        Vertex v = e.v;
        if (v.dist == Integer.MAX_VALUE) {
          q.add(v);
          v.dist = u.dist+1;
        }
      }
    
    }
  }
  
  /* run a DFS from a given start vertex */
  public void dfs(Vertex startVertex) {
    Deque<Vertex> s = new ArrayDeque<Vertex>();
    s.push(startVertex);
    startVertex.visited = true;
    while (!s.isEmpty()) {
      Vertex u = s.pop();
      System.out.println(u.name);
      for (Edge e: u.nbs) {
        Vertex v = e.v;
        if (!v.visited) {
          s.push(v);
          v.visited = true;
        }
      }
    }
  }
  
  /* run a recursive DFS from a given vertex */
  public void recursiveDfs(Vertex u) {
    u.visited = true;
    System.out.println(u.name);
    for (Edge e: u.nbs) {
      Vertex v = e.v;
      if (!v.visited) recursiveDfs(v);
    }

  }
  
  /* find the shortest path from the given start vertex */
  public void shortestPath(Vertex startVertex) {
    reset();
    PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
    q.add(startVertex);
    startVertex.dist = 0;
    
    while (!q.isEmpty()) {
      Vertex u = q.poll();
      if (u.visited) continue;
      u.visited = true;
      System.out.println(u.name + " " + u.dist + " " + ((u.prev==null)?"":u.prev.name));
      for (Edge e: u.nbs) {
        Vertex v = e.v;
        if (v.dist > u.dist + e.w) {
          q.remove(v);
          v.dist = u.dist + e.w;
          v.prev = u;
          q.add(v);
        }
      }
    }
  }
  
  /* reset the parameters of all vertices */
  public void reset() {
    for (Vertex v: vertexMap.values()) {
      v.dist = Integer.MAX_VALUE;
      v.visited = false;
      v.prev = null;
    }
  }
  
}
