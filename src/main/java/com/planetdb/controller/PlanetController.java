package com.planetdb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.planetdb.dao.PlanetRepository;
import com.planetdb.model.Planet;

@RestController
public class PlanetController {

	private final PlanetRepository repository;
	
	public PlanetController(PlanetRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/planet/")
	List<Planet> getAll() {
		return repository.findAll();
	}

	@GetMapping("/planet/{id}")
	Planet getPlanet(Long id) {
		return repository.getOne(id);
	}
	
	@PostMapping("/planet")
	Planet addPlanet(@RequestBody Planet planet) {
		return repository.save(planet);
	}
	
	@PutMapping("/planet/{id}")
	Planet updatePlanet(@RequestBody Planet planet, @PathVariable Long id) {
		return repository.findById(id).map(extextPlanet -> {
			extextPlanet.setPlanetName(planet.getPlanetName());
			extextPlanet.setPlanetNode(planet.getPlanetNode());
			return repository.save(extextPlanet);
		}).orElseGet(() -> {
			return repository.save(planet);
		});
	}
	
	@DeleteMapping("/planet/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}


}
