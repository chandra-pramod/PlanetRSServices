package com.planetdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANET")
public class Planet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@Column(name = "PLANET_NODE", nullable = false)
	String planetNode;
	
	@Column(name = "PLANET_NAME", nullable = false)
	String planetName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlanetNode() {
		return planetNode;
	}

	public void setPlanetNode(String planetNode) {
		this.planetNode = planetNode;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	
}
