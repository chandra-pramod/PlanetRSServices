package com.planetdb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.planetdb.dao.RoutesRepository;
import com.planetdb.model.Routes;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RoutesController {
	
	
	private RoutesRepository repository;
	
	public RoutesController(RoutesRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/routes/")
	List<Routes> getAll() {
		return repository.findAll();
	}

	@GetMapping("/routes/{id}")
	
	Routes getRoutes(Long id) {
		return repository.getOne(id);
	}
	
	@PostMapping("/routes")
	Routes addRoute(@RequestBody Routes routes) {
		return repository.save(routes);
	}
	
	@PutMapping("/routes/{id}")
	Routes updateRoute(@RequestBody Routes routes, @PathVariable Long id) {
		return repository.findById(id).map(extRoute -> {
			extRoute.setPlanetOrigin(routes.getPlanetOrigin());
			extRoute.setPlanetDest(routes.getPlanetDest());
			extRoute.setDistance(routes.getDistance());
			return repository.save(extRoute);
		}).orElseGet(() -> {
			return repository.save(routes);
		});
	}
	
	@DeleteMapping("/routes/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
