package com.genefinder.demo.api.dto;

import com.genefinder.demo.domain.entity.Gene;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class GeneResponseDTO {

	private String geneName;
}
