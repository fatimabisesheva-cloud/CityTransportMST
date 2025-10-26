package citytransport;

import java.util.*;

public class Prim {
    public static List<Edge> primMST(Graph graph) {
        int V = graph.vertices;
        List<Edge> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        visited[0] = true;
        for (Edge e : graph.edges) {
            if (e.src == 0 || e.dest == 0) pq.add(e);
        }

        while (!pq.isEmpty() && result.size() < V - 1) {
            Edge edge = pq.poll();
            int v = visited[edge.src] ? edge.dest : edge.src;
            if (visited[v]) continue;
            visited[v] = true;
            result.add(edge);

            for (Edge e : graph.edges) {
                if ((e.src == v && !visited[e.dest]) || (e.dest == v && !visited[e.src])) {
                    pq.add(e);
                }
            }
        }

        return result;
    }
}
