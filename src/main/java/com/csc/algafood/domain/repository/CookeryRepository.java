package com.csc.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc.algafood.domain.model.Cookery;

@Repository
public interface CookeryRepository extends JpaRepository<Cookery, Long>{
	
	public List<Cookery> findByNameContaining(String name); 
	
	boolean existsByNameContainingIgnoreCase(String name);
	
}
