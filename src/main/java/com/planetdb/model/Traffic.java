package com.planetdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRAFFIC")
public class Traffic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	long id;
	
	@Column(name="PLANET_ORIGIN", nullable = false)
	String planetOrigin;
	
	@Column(name="PLANET_DEST", nullable = false)
	String planetDest;
	
	@Column(name="DELAY", nullable = false)
	Double trafficDelay;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlanetOrigin() {
		return planetOrigin;
	}

	public void setPlanetOrigin(String planetOrigin) {
		this.planetOrigin = planetOrigin;
	}

	public String getPlanetDest() {
		return planetDest;
	}

	public void setPlanetDest(String planetDest) {
		this.planetDest = planetDest;
	}

	public Double getTrafficDelay() {
		return trafficDelay;
	}

	public void setTrafficDelay(Double trafficDelay) {
		this.trafficDelay = trafficDelay;
	}
	
	

}
