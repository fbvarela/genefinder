package com.genefinder.demo.service;

import com.genefinder.demo.api.controller.GeneController;
import com.genefinder.demo.api.dto.GeneResponseDTO;
import com.genefinder.demo.domain.entity.Gene;
import com.genefinder.demo.domain.mapper.GeneMapper;
import com.genefinder.demo.domain.repository.GeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneController.class);

	@Autowired
	private GeneMapper mapper;

	@Autowired
	private GeneRepository geneRepository;

	public List<GeneResponseDTO> findGenes(String pattern, String species, Integer limit) throws Exception {
		LOGGER.info("GeneService.findGenes : service logic to get all Genes by species with a limit {}", pattern + " - " + species + " - " + limit);

		try {
			List<Gene> genes = geneRepository.findByPatternAndSpeciesLimitedTo(pattern, species, limit);


			List<GeneResponseDTO> geneResponseDTOS = new ArrayList<>();

			for(Gene gene : genes) {
				geneResponseDTOS.add(mapper.geneToGeneDto(gene));
			}


			LOGGER.info("GeneService.findGenes : END OK - {}", genes.size());

			return geneResponseDTOS;

		}catch (Exception e) {
			throw new Exception("Data error finding genes", e);

		}
	}
}
