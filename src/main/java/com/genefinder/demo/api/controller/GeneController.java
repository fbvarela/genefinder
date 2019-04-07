package com.genefinder.demo.api.controller;

import com.genefinder.demo.api.dto.GeneResponseDTO;
import com.genefinder.demo.service.GeneService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.status;

@Setter
@RestController
public class GeneController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneController.class);

	private static final int DEFAULT_LIMIT = 10;

	@Autowired
	private GeneService geneService;

	@GetMapping(value="/genes")
	public ResponseEntity<?> getGenesName(@RequestParam String pattern,
										  @RequestParam String species,
										  @RequestParam Integer limit) {

		LOGGER.info("GeneController.getGenesName : REST request to get all Genes by species with a limit {}", pattern + " - " + species + " - ");

		if (pattern.equals("") || species.equals("")) {

			LOGGER.info("GeneController.getGenesName : Pattern and species are mandatory");
			return status(HttpStatus.BAD_REQUEST).body(new WarningMessage("Pattern and species are mandatory"));
		}

		if (limit == null) limit = DEFAULT_LIMIT;

		List<GeneResponseDTO> genes;
		List<String> genesName;

		try {
			genes = geneService.findGenes(pattern.toUpperCase(), species.toLowerCase(), limit);
			genesName = genes.stream().map(name -> name.getGeneName()).collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.error("GeneService.findGenes : END ERROR - {}", e.getMessage());
			return status(HttpStatus.INTERNAL_SERVER_ERROR).body(new WarningMessage("Unable to retrieve data"));
		}

		LOGGER.info("GeneController.getGenesName : END OK - {}", genes.size());
		return new ResponseEntity(genesName, HttpStatus.OK);
	}

	@Getter
	@Setter
	@AllArgsConstructor
	private class WarningMessage {
		private final String message;
	}
}