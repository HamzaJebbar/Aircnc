package com.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppartementRepository extends JpaRepository<Appartement,Integer> {
	
	List<Appartement> findBy(String adresse);
}
