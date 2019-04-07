package com.genefinder.demo.service;

import com.genefinder.demo.api.dto.GeneResponseDTO;
import com.genefinder.demo.domain.entity.Gene;
import com.genefinder.demo.domain.mapper.GeneMapper;
import com.genefinder.demo.domain.repository.GeneRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneServiceTest {

	private GeneMapper mapper;

	private GeneRepository repository;

	private GeneService service;

	@Before
	public void setUp() {
		service = new GeneService(mapper, repository);
		MockitoAnnotations.initMocks(this);
		initMocks();
	}

	private void initMocks() {
		repository = Mockito.mock(GeneRepository.class);
		mapper = Mockito.mock(GeneMapper.class);

		service.setGeneRepository(repository);
		service.setMapper(mapper);
	}

	@Test
	public void happyPath() throws Exception {

		String pattern = "ENSAMEG";
		String species = "homo_sapiens";
		int limit = 1;

		List<GeneResponseDTO> expected = new ArrayList<>();
		GeneResponseDTO geneResponseDTO1 = createGeneResponseDTO("ENSAMEG00000022992");
		GeneResponseDTO geneResponseDTO2 = createGeneResponseDTO("ENSAMEG00000022989");
		expected.add(geneResponseDTO1);
		expected.add(geneResponseDTO2);

		List<Gene> genes = new ArrayList<>();
		Gene gene1 = createGene("ENSAMEG00000022992", "ailuropoda_melanoleuca", "U1", "GL192372.1:3639244-3639393", "core");
		Gene gene2 = createGene("ENSAMEG00000022989", "ailuropoda_melanoleuca", "U1", "GL197666.1:293-456", "core");
		genes.add(gene1);
		genes.add(gene2);

		Mockito.when(repository.findByPatternAndSpeciesLimitedTo(pattern, species, limit)).thenReturn(genes);
		Mockito.when(mapper.geneToGeneDto(gene1)).thenReturn(geneResponseDTO1);
		Mockito.when(mapper.geneToGeneDto(gene2)).thenReturn(geneResponseDTO2);

		List<GeneResponseDTO> result =  service.findGenes(pattern, species, limit);

		assertEquals(expected, result);
	}

	@Test
	public void shouldReturnEmptyListTest() throws Exception {

		String pattern = "FAKE_PATTERN";
		String species = "fake species";
		int limit = 1;

		List<GeneResponseDTO> expected = new ArrayList<>();

		List<Gene> genes = new ArrayList<>();

		Mockito.when(repository.findByPatternAndSpeciesLimitedTo(pattern, species, limit)).thenReturn(genes);
		List<GeneResponseDTO> result =  service.findGenes(pattern, species, limit);

		assertEquals(expected, result);
	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionTest() throws Exception {

		String pattern = "FAKE";
		String species = "fake species";
		int limit = 1;

		Mockito.when(repository.findByPatternAndSpeciesLimitedTo(pattern, species, limit)).thenThrow(Exception.class);
		service.findGenes(pattern, species, limit);
	}

	private GeneResponseDTO createGeneResponseDTO(String geneName) {
		 return GeneResponseDTO.builder()
				.geneName(geneName).build();
	}

	private Gene createGene(String stableId, String species, String displayLabel, String location, String db) {
		return new Gene(stableId, species, displayLabel, location, db);
	}

}