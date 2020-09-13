package com.planetdb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.planetdb.dao.TrafficRepository;
import com.planetdb.model.Planet;
import com.planetdb.model.Traffic;

@RestController
public class TrafficController {
	
private final TrafficRepository repository;
	
	public TrafficController(TrafficRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/traffic/")
	List<Traffic> getAll() {
		return repository.findAll();
	}


	@GetMapping("/traffic/{id}")
	Traffic get(Long id) {
		return repository.getOne(id);
	}
	
	@PostMapping("/traffic")
	Traffic addTraffic(@RequestBody Traffic traffic) {
		return repository.save(traffic);
	}
	
	@PutMapping("/traffic/{id}")
	Traffic updateTraffic(@RequestBody Traffic traffic, @PathVariable Long id) {
		return repository.findById(id).map(extTraffic -> {
			extTraffic.setPlanetOrigin(traffic.getPlanetOrigin());
			extTraffic.setPlanetDest(traffic.getPlanetDest());
			extTraffic.setTrafficDelay(traffic.getTrafficDelay());
			return repository.save(extTraffic);
		}).orElseGet(() -> {
			return repository.save(traffic);
		});
	}
	
	@DeleteMapping("/traffic/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
