package citytransport;

import java.util.*;

public class Kruskal {
    public static List<Edge> kruskalMST(Graph graph) {
        List<Edge> result = new ArrayList<>();
        Collections.sort(graph.edges);
        UnionFind uf = new UnionFind(graph.vertices);

        for (Edge e : graph.edges) {
            int root1 = uf.find(e.src);
            int root2 = uf.find(e.dest);
            if (root1 != root2) {
                result.add(e);
                uf.union(root1, root2);
            }
        }
        return result;
    }
}
