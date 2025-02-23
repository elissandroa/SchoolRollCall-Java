package br.com.elissandro.scoolrollcall.dto;

import br.com.elissandro.scoolrollcall.entities.Tutor;

public class TutorDTO {

	private Long id;
	private String name;

	public TutorDTO() {
	}

	public TutorDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public TutorDTO(Tutor entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
