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
