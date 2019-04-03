package com.genefinder.demo.service;

import com.genefinder.demo.domain.entity.Gene;
import com.genefinder.demo.domain.entity.repository.GeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneService {

	@Autowired
	private GeneRepository geneRepository;

	public List<Gene> findGenes(String pattern, String species, Integer limit) {
		List<Gene> genes = geneRepository.findByPatternAndSpeciesLimitedTo(pattern, species, limit);

		return genes;
	}
}
