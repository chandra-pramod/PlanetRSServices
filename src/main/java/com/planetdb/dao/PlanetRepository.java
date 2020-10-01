package com.planetdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.planetdb.model.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
	
	Planet findByPlanetNode(String planetNode);

}
