# CityTransportMST

## Description
This project implements **Prim's** and **Kruskal's** algorithms to compute the **Minimum Spanning Tree (MST)** for a city transportation network.  
Graphs are represented as JSON files with vertices, edges, and weights.

## Project Structure
CityTransportMST/
├─ src/ # Java source files
│ ├─ Main.java
│ ├─ Graph.javaResults will be printed to console and saved to output.json.

│ └─ Edge.java
├─ graphs/ # Input JSON files
│ ├─ small.json
│ ├─ medium.json
│ └─ large.json
├─ lib/ # Required libraries (Gson)
│ └─ gson-2.10.1.jar
├─ output.json # Output results after running program
└─ README.md # This file

## How to Run
1. Make sure Java 24+ is installed.  
2. Add Gson library to classpath.  
3. Run `Main.java`:

```bash
javac -cp lib/gson-2.10.1.jar src/*.java
java -cp "src;lib/gson-2.10.1.jar" citytransport.Main
Results will be printed to console and saved to output.json.

Results

MST total cost is identical for both algorithms.

Execution time depends on graph size: small, medium, and large graphs tested.

Output can be used for analytical report.
| File        | Vertices | Edges | Kruskal MST | Prim MST | Kruskal Time (ms) | Prim Time (ms) |
| ----------- | -------- | ----- | ----------- | -------- | ----------------- | -------------- |
| small.json  | 5        | 4     | 16          | 16       | 0.015             | 0.027          |
| medium.json | 10       | 10    | 19          | 19       | 0.022             | 0.044          |
| large.json  | 20       | 38    | 75          | 75       | 1.564             | 4.988          |
Interpretation

Both algorithms produce the same total MST cost.

Execution time increases with graph size.

Kruskal is faster on sparse graphs, while Prim is better suited for dense graphs with a priority queue.

| Algorithm | Advantages                                                           | Disadvantages                                            |
| --------- | -------------------------------------------------------------------- | -------------------------------------------------------- |
| Kruskal   | Efficient on sparse graphs; simple implementation using edge sorting | Less efficient on dense graphs                           |
| Prim      | Works well on dense graphs; easy to implement with a priority queue  | Slower on large graphs without optimized data structures |

Conclusions

Both algorithms correctly compute the MST for graphs of any size.

Kruskal is recommended for sparse graphs.

Prim is recommended for dense graphs.

The choice depends on graph density, size, and implementation convenience.
