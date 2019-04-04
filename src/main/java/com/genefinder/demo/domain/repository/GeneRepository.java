package com.genefinder.demo.domain.repository;

import com.genefinder.demo.domain.entity.Gene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GeneRepository extends JpaRepository<Gene, String> {

	@Query(value="select * from gene_autocomplete where stable_id like CONCAT(?1, '%') and species = ?2 LIMIT ?3", nativeQuery=true)
	List<Gene> findByPatternAndSpeciesLimitedTo(String pattern, String species, Integer limit);
}
