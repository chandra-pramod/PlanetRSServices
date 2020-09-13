package com.planetdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.planetdb.model.Routes;

@Repository
public interface RoutesRepository extends JpaRepository<Routes, Long>{

}
