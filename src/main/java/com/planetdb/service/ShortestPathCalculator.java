package com.planetdb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.planetdb.dao.RoutesRepository;
import com.planetdb.model.Routes;

@Service
public class ShortestPathCalculator {

   private String START = null;
   private String END = null;
   
   @Autowired
   RoutesRepository routeRepository;

   public String calculateDistance(String source, String distination) {
       this.START = source;
       this.END = distination;
       List<Routes> routes = new ArrayList<Routes>();
       List<Graph.Edge> edgeList = new ArrayList<Graph.Edge>();
       routes = routeRepository.findAll();
       for (Routes route : routes) {
           Graph.Edge edgeobj = new Graph.Edge(route.getPlanetOrigin(), route.getPlanetDest(), route.getDistance());
           edgeList.add(edgeobj);
       }
       Graph.Edge[] stockArr = new Graph.Edge[routes.size()];
       stockArr = edgeList.toArray(stockArr);

       Graph g = new Graph(stockArr);
       String dij = g.dijkstra(START);
       if (!dij.isEmpty()) {
           return dij;
       }
       String s = g.printPath(END);
       return s;
   }

}

class Graph {

    private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

    /**
     * One edge of the graph (only used by Graph constructor)
     */
    @Override
    public String toString() {
        return "Graph{" + "graph=" + graph + '}';
    }

    /**
     * One edge of the graph (only used by Graph constructor)
     */
    public static class Edge {

        public final String v1, v2;
        public final double dist;

        public Edge(String v1, String v2, double dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    /**
     * One vertex of the graph, complete with mappings to neighbouring vertices
     */
    public static class Vertex implements Comparable<Vertex> {

        public final String name;
        public double dist = Double.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;
        public final Map<Vertex, Double> neighbours = new HashMap<Vertex, Double>();

        public Vertex(String name) {
            System.out.println("New obj crated");
            this.name = name;
        }

        private StringBuilder printPath(StringBuilder sb) {
            if (this == this.previous) {
                sb.append(String.format("%s", this.name));
                System.out.println("Entered    1" + sb.toString());

            } else if (this.previous == null) {
                sb.append(String.format("%s(unreached)", this.name));
                System.out.println("Entered    2" + sb.toString());

            } else {
                this.previous.printPath(sb);
                sb.append("-> ").append(String.format("%s(%.2f)", this.name, this.dist));
                System.out.println("Entered    3" + sb.toString());

            }
            return sb;
        }

        public int compareTo(Vertex other) {
            return Double.compare(dist, other.dist);
        }
    }

    /**
     * Builds a graph from a set of edges
     */
    public Graph(Edge[] edges) {
        graph = new HashMap<String, Vertex>(edges.length);

        //one pass to find all vertices
        for (Edge e : edges) {
            if (!graph.containsKey(e.v1)) {
                graph.put(e.v1, new Vertex(e.v1));
            }
            if (!graph.containsKey(e.v2)) {
                graph.put(e.v2, new Vertex(e.v2));
            }
        }

        //another pass to set neighbouring vertices
        for (Edge e : edges) {
            graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
            //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }

    /**
     * Runs dijkstra using a specified source vertex
     */
    public String dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            sb.append(String.format("Graph doesn't contain start vertex \"%s\"\n", startName));
            return sb.toString();
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<Vertex>();

        // set-up vertices
        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
        return sb.toString();
    }

    /**
     * Implementation of dijkstra's algorithm using a binary heap.
     */
    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
            if (u.dist == Integer.MAX_VALUE) {
                break; // we can ignore u (and any other remaining vertices) since they are unreachable
            }
            //look at distances to each neighbour
            for (Map.Entry<Vertex, Double> a : u.neighbours.entrySet()) {
                v = a.getKey(); //the neighbour in this iteration

                final double alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) { // shorter path to neighbour found
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    /**
     * Prints a path from the source to the specified vertex
     */
    StringBuilder sb = new StringBuilder();

    public String printPath(String endName) {
        if (!graph.containsKey(endName)) {
            sb.append(String.format("Graph doesn't contain end vertex \"%s\"\n", endName));
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return sb.toString();
        }
        sb = graph.get(endName).printPath(sb);
        return sb.toString();

    }

    /**
     * Prints the path from the source to every vertex (output order is not
     * guaranteed)
     */
    public void printAllPaths() {
        for (Vertex v : graph.values()) {
            v.printPath(sb);
            System.out.println();
        }
    }

}

