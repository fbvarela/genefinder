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
public class GeneControllerTests {

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
		assertEquals(result.size(), 2);
		assertEquals(result, expected);
	}

	@Test
	public void shouldReturnAnEmptyListTest() throws Exception, ParameterException {

		String pattern = "FAKEPATTERN";
		String species = "homo fake";
		int limit = 2;

		geneService = Mockito.mock(GeneService.class);
		controller.setGeneService(geneService);

		List<String> expected = new ArrayList<>();

		List<GeneResponseDTO> geneResponseDTOS = new ArrayList<>();

		Mockito.when(geneService.findGenes(pattern, species, limit)).thenReturn(geneResponseDTOS);

		List<String> result = controller.getGenesName(pattern, species, limit);
		assertEquals(result.size(), 0);
		assertEquals(result, expected);
	}



	@Test(expected = ParameterException.class)
	public void shouldThrownParameterExceptionIfNoParametersTest() throws Exception, ParameterException {

		String pattern = "";
		String species = "";
		int limit = 2;

		geneService = Mockito.mock(GeneService.class);
		controller.setGeneService(geneService);

		controller.getGenesName(pattern, species, limit);
	}

	@Test(expected = Exception.class)
	public void shouldThrownExceptionIfRepositoryFailsTest() throws Exception, ParameterException {

		String pattern = "ENSAMEG";
		String species = "ailuropoda_melanoleuca";
		int limit = 2;

		geneService = Mockito.mock(GeneService.class);
		controller.setGeneService(geneService);

		Mockito.when(geneService.findGenes(pattern, species, limit)).thenThrow(Exception.class);

		controller.getGenesName(pattern, species, limit);
	}
}
