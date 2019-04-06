package com.genefinder.demo.api.controller;

import com.genefinder.demo.api.dto.GeneResponseDTO;
import com.genefinder.demo.api.exception.ParameterException;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.genefinder.demo.service.GeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@RestController
public class GeneController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneController.class);

	private static final int DEFAULT_LIMIT = 10;

	@Autowired
	private GeneService geneService;

	@GetMapping
	public ResponseEntity<List<String>> getGenesName(@RequestParam String pattern,
									   				 @RequestParam String species,
									   				 @RequestParam Integer limit)
			throws ParameterException, Exception {

		LOGGER.info("GeneController.getGenesName : REST request to get all Genes by species with a limit {}", pattern + " - " + species + " - ");


		if (pattern.equals("") && species.equals("")) {

			LOGGER.info("GeneController.getGenesName : No Genes found : {}", HttpStatus.NO_CONTENT);
			throw new ParameterException("Pattern and species are mandatory");
		}

		if (limit == null) limit = DEFAULT_LIMIT;
		List<GeneResponseDTO> genes;
		List<String> genesName;

		try {
			genes = geneService.findGenes(pattern, species, limit);
			genesName = genes.stream().map(name -> name.getGeneName()).collect(Collectors.toList());
			LOGGER.info("GeneController.getGenesName : END OK - {}", genes.size());

		} catch (Exception e) {
			LOGGER.error("GeneService.findGenes : END ERROR - {}", e.getMessage());
			throw new Exception("Unable to retrieve data");
		}

		return new ResponseEntity(genesName, HttpStatus.OK);
	}
}