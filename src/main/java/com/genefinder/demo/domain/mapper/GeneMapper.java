package com.genefinder.demo.domain.mapper;

import com.genefinder.demo.api.dto.GeneResponseDTO;
import com.genefinder.demo.domain.entity.Gene;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
public interface GeneMapper {
	GeneMapper INSTANCE = Mappers.getMapper(GeneMapper.class);

	@Mapping(source = "stable_id", target = "geneName")
	GeneResponseDTO geneToGeneDto(Gene gene);
}