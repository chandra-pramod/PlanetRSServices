package com.planetdb.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetdb.dao.PlanetRepository;
import com.planetdb.dao.RoutesRepository;
import com.planetdb.exception.RouteServiceException;
import com.planetdb.model.Planet;
import com.planetdb.model.Routes;

@Service
public class ShortestPathCalculator {

	private static final String START_NODE = "A";

	private final Graph graph = new Graph();

	@Autowired
	private RoutesRepository routeRepository;
	
	@Autowired
	private PlanetRepository planetRepository;

	public Graph calculateDistance() throws RouteServiceException {
		Node source = new Node(START_NODE);
		source.setDistance(0);
		source = getNodeDetails(source);
		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unSettledNodes = new HashSet<>();
		unSettledNodes.add(source);
		while(unSettledNodes.size() != 0) {
			Node lowDistanceNode = findMinDistanceNode(unSettledNodes);
			unSettledNodes.remove(lowDistanceNode);
			for (Entry<Node, Double> adjNodePair :lowDistanceNode.getAdjacentNodes().entrySet()) {
				Node adjacentNode = adjNodePair.getKey();
				Double weight = adjNodePair.getValue();
				if(!settledNodes.contains(adjacentNode)) {
					evaluateNode(lowDistanceNode, adjacentNode, weight);
					adjacentNode = getNodeDetails(adjacentNode);
					unSettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(lowDistanceNode);
		}
		graph.setNodes(settledNodes);
		return graph;
	}

	private void evaluateNode(Node lowDistanceNode, Node adjacentNode, Double weight) {
		Double sourceDistance = lowDistanceNode.getDistance();
		if (weight + sourceDistance < adjacentNode.getDistance()) {
			adjacentNode.setDistance(weight + sourceDistance);
			List<Node> shortestPath = new LinkedList<>(lowDistanceNode.getShortestPath());
			shortestPath.add(lowDistanceNode);
			adjacentNode.setShortestPath(shortestPath);
		}
	}

	private Node findMinDistanceNode(Set<Node> unSettledNodes) {
		Double lowDistance = Double.MAX_VALUE;
		Node lowDistanceNode = null;
		for(Node node  : unSettledNodes) {
			Double currentDistance = node.getDistance();
			if (currentDistance < lowDistance) {
				lowDistance = currentDistance;
				lowDistanceNode = node;
			}
		}
		return lowDistanceNode;
	}

	private Node getNodeDetails(Node node) throws RouteServiceException{
		Planet planet = planetRepository.findByPlanetNode(node.getName());
		if (planet != null) {
			List<Routes> routes = routeRepository.findByPlanetOrigin(node.getName());
			for (Routes route : routes) {
				node.addAdjacentNode(new Node(route.getPlanetDest()), route.getDistance());
			}
		}else {
			throw new RouteServiceException("Destination Node not avaible.");
		}
		return node;
	}
		
}


class Graph {
	private Set<Node> nodes = new HashSet<>();

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node node) {
		nodes.add(node);
	}

}

class Node {
	
	private String name;
	private Double distance = Double.MAX_VALUE;
	Map<Node, Double> adjacentNodes = new HashMap<>();
	List<Node> shortestPath = new LinkedList<>();

	public Node(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(double d) {
		this.distance = d;
	}

	public Map<Node, Double> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void addAdjacentNode(Node node, Double distance) {
		adjacentNodes.put(node, distance);
	}

	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
