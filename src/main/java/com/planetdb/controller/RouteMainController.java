package com.planetdb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.planetdb.service.RouteService;

@RestController
public class RouteMainController {

	private RouteService routeService;
	
	public RouteMainController(RouteService routeService) {
		this.routeService = routeService;
	}

	@GetMapping("/getdistance/{target}")
	public ResponseEntity findDistance(@PathVariable String target) {
		return ResponseEntity.status(HttpStatus.OK).body(routeService.getRouteResponse(target));
	}
}
