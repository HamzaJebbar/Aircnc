package com.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface VoyageurRepository extends JpaRepository <Voyageur,Integer> {
	List<Voyageur> findAll();

}
