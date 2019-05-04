import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPath {

  private static final double EPS = 1e-6;

  // tạo cạnh 
  public static class Edge {
    double cost;
    int from, to;
    public Edge(int from, int to, double cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  // đỉnh đồ thị
  public static class Node {
    int id;
    double value;
    public Node(int id, double value) {
      this.id = id; 
      this.value = value;
    }
  }

  private int n;
  private double[] dist;
  private Integer[] prev;
  private List<List<Edge>> graph;

  private Comparator<Node> comparator = new Comparator<Node>() {
    @Override
    public int compare(Node node1, Node node2) {
      if (Math.abs(node1.value-node2.value) < EPS) return 0;
      return (node1.value - node2.value) > 0 ? +1 : -1;
    }
  }

  public ShortestPath(int n) {
    this.n = n;
    createEmptyGraph();
  }

  public ShortestPath(int n, Comparator<Node> comparator) {
    this(n);
    if (comparator == null) throw new IllegalArgumentException("Comparator cannot be null");
    this.comparator = comparator;
  }

  public void addEdge(int from, int to, int cost) {
    graph.get(from).add(new Edge(from, to, cost));
  }

  public List<List<Edge>> getGraph() {
    return graph;
  }

  public List<Integer> reconstructPath(int start, int end) {
    if (end < 0 || end >= n) throw new IllegalArgumentException("Invalid node index");
    if (start < 0 || start >= n) throw new IllegalArgumentException("Invalid node index");
    double dist = dijkstra(start, end);
    List<Integer> path = new ArrayList<>();
    if (dist == Double.POSITIVE_INFINITY) return path;
    for(Integer at = end; at != null; at = prev[at])
      path.add(at);
    Collections.reverse(path);
    return path;
  }

  public double dijkstra(int start, int end) {
    // tạo mảng và gán giá trị inf
    dist = new double[n];
    Arrays.fill(dist, Double.POSITIVE_INFINITY);
    dist[start] = 0;

    // xét đỉnh tiếp theo
    PriorityQueue<Node> pq = new PriorityQueue<>(2*n, comparator);
    pq.offer(new Node(start, 0));

    // kiểm tra những đỉnh đã được xét            
    boolean[] visited = new boolean[n];
    prev = new Integer[n];

    while(!pq.isEmpty()) {
      Node node = pq.poll();
      visited[node.id] = true;

      if (dist[node.id] < node.value) continue;

      List<Edge> edges = graph.get(node.id);
      for(int i = 0; i < edges.size(); i++) {
        Edge edge = edges.get(i);

        if (visited[edge.to]) continue;

        double newDist = dist[edge.from] + edge.cost;
        if (newDist < dist[edge.to]) {
          prev[edge.to] = edge.from;
          dist[edge.to] = newDist;
          pq.offer(new Node(edge.to, dist[edge.to]));
        }
      }
    
      if (node.id == end) return dist[end];
    }
    return Double.POSITIVE_INFINITY;
  }

  private void createEmptyGraph() {
    graph = new ArrayList<>(n);
    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
  }

}


