package com.genefinder.demo.api.dto;

import com.genefinder.demo.domain.entity.Gene;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GeneResponseDTO {

	private String geneName;
}
