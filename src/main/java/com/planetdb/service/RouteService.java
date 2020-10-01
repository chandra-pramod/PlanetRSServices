package com.planetdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetdb.dao.PlanetRepository;
import com.planetdb.exception.RouteServiceException;
import com.planetdb.model.Planet;
import com.shortestpath.routes.RouteResponse;

@Service
public class RouteService {
	
	@Autowired
	ShortestPathCalculator pathCalulator;
	
	@Autowired
	PlanetRepository planetRepository;
	
	
	public RouteResponse getRouteResponse(String destination) {
		RouteResponse response = null;
		try {
			Graph graph = pathCalulator.calculateDistance();
			for(Node node : graph.getNodes()) {
				if(node.getName().equalsIgnoreCase(destination)) {
					Planet planet = planetRepository.findByPlanetNode(destination);
					response = new RouteResponse();
					response.setPlanetNode(destination);
					response.setPlanetName(planet.getPlanetName());
					response.setDistance(node.getDistance().floatValue());
				}
			}
		} catch (RouteServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
