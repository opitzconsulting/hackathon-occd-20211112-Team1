package com.opitzconsulting.hackathon.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class SampleEntity {
	
	@Id
	private Long id;
	
}
