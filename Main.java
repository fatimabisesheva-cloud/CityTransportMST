package citytransport;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File folder = new File("graphs"); // Папка с JSON-файлами
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Папка 'graphs' не найдена!");
            return;
        }

        List<Map<String, Object>> allResults = new ArrayList<>();

        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (!file.getName().endsWith(".json")) continue;

            System.out.println("Processing: " + file.getName());

            try {
                // Чтение графа
                GraphData data = gson.fromJson(new FileReader(file), GraphData.class);
                Graph graph = new Graph(data.vertices);
                for (GraphEdge e : data.edges) {
                    graph.addEdge(e.src, e.dest, e.weight);
                }

                // Kruskal
                long startKruskal = System.nanoTime();
                List<Edge> kruskalMST = Kruskal.kruskalMST(graph);
                long endKruskal = System.nanoTime();
                double kruskalTime = (endKruskal - startKruskal) / 1e6;

                // Prim
                long startPrim = System.nanoTime();
                List<Edge> primMST = Prim.primMST(graph);
                long endPrim = System.nanoTime();
                double primTime = (endPrim - startPrim) / 1e6;

                // Вывод в консоль
                System.out.println("Kruskal's MST:");
                printMST(kruskalMST);
                System.out.printf("Execution time: %.3f ms%n%n", kruskalTime);

                System.out.println("Prim's MST:");
                printMST(primMST);
                System.out.printf("Execution time: %.3f ms%n", primTime);
                System.out.println("------------------------------------------------------");

                // Сохраняем результаты
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("file", file.getName());
                resultMap.put("vertices", graph.vertices);
                resultMap.put("edges_count", graph.edges.size());
                resultMap.put("kruskal_total", totalWeight(kruskalMST));
                resultMap.put("prim_total", totalWeight(primMST));
                resultMap.put("kruskal_time_ms", kruskalTime);
                resultMap.put("prim_time_ms", primTime);
                resultMap.put("kruskal_edges", kruskalMST);
                resultMap.put("prim_edges", primMST);

                allResults.add(resultMap);

            } catch (Exception e) {
                System.out.println("Ошибка при обработке файла: " + file.getName());
                e.printStackTrace();
            }
        }

        // Сохраняем все результаты в один output.json
        try (FileWriter writer = new FileWriter("output.json")) {
            gson.toJson(allResults, writer);
            System.out.println("\n✅ All results saved to output.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Вспомогательные методы
    public static void printMST(List<Edge> edges) {
        int total = 0;
        for (Edge e : edges) {
            System.out.println(e);
            total += e.weight;
        }
        System.out.println("Total weight: " + total);
    }

    public static int totalWeight(List<Edge> edges) {
        int total = 0;
        for (Edge e : edges) total += e.weight;
        return total;
    }
}

// Классы для чтения JSON
class GraphData {
    int vertices;
    List<GraphEdge> edges;
}

class GraphEdge {
    int src;
    int dest;
    int weight;
}
