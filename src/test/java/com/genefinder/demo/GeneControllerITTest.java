package com.genefinder.demo;

import com.genefinder.demo.api.controller.GeneController;
import com.genefinder.demo.api.dto.GeneResponseDTO;
import com.genefinder.demo.api.exception.ParameterException;
import com.genefinder.demo.domain.mapper.GeneMapper;
import com.genefinder.demo.domain.repository.GeneRepository;
import com.genefinder.demo.service.GeneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GeneControllerITTest {

	@Autowired
	private GeneController geneController;

	@Test
	public void getGenes() throws ParameterException, Exception {

		ResponseEntity expected = new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
		ResponseEntity result = geneController.getGenesName("ENSAMEG","ailuropoda_melanoleuca", 2);

		assertEquals(expected.getStatusCode(), result.getStatusCode());
	}

	@Test
	public void shouldReturnEmptyList() throws ParameterException, Exception {
		ResponseEntity expected = new ResponseEntity(new ArrayList<>(), HttpStatus.OK);

		ResponseEntity result = geneController.getGenesName("FAKE","FAKE", 2);
		assertEquals(expected.getStatusCode(), result.getStatusCode());
		assertEquals(expected, result);

	}

}
