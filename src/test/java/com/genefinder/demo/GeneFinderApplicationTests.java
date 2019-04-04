package com.genefinder.demo;

import com.genefinder.demo.api.controller.GeneController;
import com.genefinder.demo.api.dto.GeneResponseDTO;
import com.genefinder.demo.api.exception.ParameterException;
import com.genefinder.demo.service.GeneService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneFinderApplicationTests {

	private GeneService geneService;

	private GeneController controller;

	@Before
	public void setUp() throws Exception {
		controller = new GeneController();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void happyPath() throws Exception, ParameterException {
		String pattern = "ENSAMEG";
		String species = "ailuropoda_melanoleuca";
		int limit = 2;

		geneService = Mockito.mock(GeneService.class);
		controller.setGeneService(geneService);


		List<String> expected = Arrays.asList("ENSAMEG00000022992", "ENSAMEG00000022989");

		List<GeneResponseDTO> geneResponseDTOS = Arrays.asList(GeneResponseDTO.builder()
					.geneName("ENSAMEG00000022992").build(),
				GeneResponseDTO.builder()
					.geneName("ENSAMEG00000022989").build());

		Mockito.when(geneService.findGenes(pattern, species, limit)).thenReturn(geneResponseDTOS);

		List<String> result = controller.getGenesName(pattern, species, limit);
		assertEquals(result, expected);
	}

}
