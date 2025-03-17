package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;

import br.com.elissandro.scoolrollcall.entities.Discipline;
import jakarta.validation.constraints.NotBlank;

public class DisciplineDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo obrigatório")
	private String name;
	
	public DisciplineDTO() {
	}

	public DisciplineDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public DisciplineDTO(Discipline entity) {
		id = entity.getId();
		name = entity.getName();
	}
	
	public Long getId() {	
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
