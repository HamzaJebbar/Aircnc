package com.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HoteRepository extends JpaRepository <Hote,Integer> {
    List<Hote> findByName(String nom);
}
