package com.planetdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.planetdb.model.Traffic;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {

}
