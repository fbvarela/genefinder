package com.genefinder.demo.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Gene {

	@Id
	private String stable_id;
	private String species;
	private String display_label;
	private String location;
	private String db;
}