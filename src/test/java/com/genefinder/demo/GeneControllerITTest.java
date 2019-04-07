package com.genefinder.demo;

import com.genefinder.demo.api.controller.GeneController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GeneControllerITTest {

	@Autowired
	private GeneController geneController;

	@Test
	public void getGenes() throws Exception {

		ResponseEntity expected = new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
		ResponseEntity result = geneController.getGenesName("ENSAMEG","ailuropoda_melanoleuca", 2);

		assertEquals(expected.getStatusCode(), result.getStatusCode());
	}

	@Test
	public void shouldReturnEmptyList() throws Exception {
		ResponseEntity expected = new ResponseEntity(new ArrayList<>(), HttpStatus.OK);

		ResponseEntity result = geneController.getGenesName("FAKE","FAKE", 2);
		assertEquals(expected.getStatusCode(), result.getStatusCode());
		assertEquals(expected, result);

	}

}
