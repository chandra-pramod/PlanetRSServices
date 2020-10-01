package com.planetdb.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.planetdb.PlanetDbApplicationTests;
import com.shortestpath.routes.RouteResponse;

public class RouteServiceTest extends PlanetDbApplicationTests{

	@Autowired
	private RouteService service;
	
	@Test
	public void testGetRouteResponse() {
		RouteResponse response = service.getRouteResponse("X");
		assertThat(response.getPlanetNode()).isEqualTo("X");
	}
}
