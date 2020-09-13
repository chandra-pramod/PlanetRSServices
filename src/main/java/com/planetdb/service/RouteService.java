package com.planetdb.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortestpath.routes.RouteRequest;
import com.shortestpath.routes.RouteResponse;



@Service
public class RouteService {
	
	@Autowired
	ShortestPathCalculator pathCalulator;
	
	public RouteResponse getRouteResponse(RouteRequest request) {
		String zxc = pathCalulator.calculateDistance(request.getPlanetOrigin(), request.getPlanetDestination());
		String[] parts = zxc.split("->");
		List<String> sList = Arrays.asList(parts);
		System.out.println("As list" + sList);
		RouteResponse response = new RouteResponse();
		String s = null;
		for (String values : sList) {
			System.out.println("length :" + values.length());
			if (values.length() > 1) {
				System.out.println("Entered");
				String getName = values.substring(0, values.indexOf("("));
				s = values;
				RouteResponse.Route route = new RouteResponse.Route();
				s = s.substring(s.indexOf("(") + 1);
				s = s.substring(0, s.indexOf(")"));
				route.setDistance(new Float(s));
				route.setPlanetNode(getName);
				response.getRoute().add(route);
			}
		}
		return response;

	}

}
