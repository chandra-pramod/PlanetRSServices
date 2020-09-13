package com.planetdb.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.planetdb.service.RouteService;
import com.shortestpath.routes.RouteRequest;
import com.shortestpath.routes.RouteResponse;


@Endpoint
public class RouteEndpoint {

	
	RouteService service;
	
	public RouteEndpoint(RouteService service) {
		this.service = service;
	}

	@PayloadRoot(namespace = "http://shortestpath.com/routes", localPart = "gerRouteDistance")
	@ResponsePayload
	public RouteResponse getShortestRoute(@RequestPayload RouteRequest request) {
		
		return service.getRouteResponse(request);
	}
}
