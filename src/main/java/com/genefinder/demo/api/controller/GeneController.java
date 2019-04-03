package com.genefinder.demo.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.genefinder.demo.domain.entity.Gene;
import com.genefinder.demo.service.GeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class GeneController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GeneController.class);

	private static final String ENTITY_NAME = "Gene";


	@Autowired
	private GeneService geneService;

	@GetMapping
	public List<Gene> getGenes(@RequestParam String pattern,
							   @RequestParam String species,
							   @RequestParam Integer limit) throws Exception {

		if (pattern.equals("") && species.equals("")) {
			LOGGER.info("GeneController.getGenes : No Genes found : {}", HttpStatus.NO_CONTENT);
			throw new Exception("No Genes found.");
		}

		List<Gene> genes = geneService.findGenes(pattern, species, limit);

		return genes;


	}
}
