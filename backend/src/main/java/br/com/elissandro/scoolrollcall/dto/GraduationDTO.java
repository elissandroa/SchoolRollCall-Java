package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;

import br.com.elissandro.scoolrollcall.entities.Graduation;
import jakarta.validation.constraints.NotBlank;

public class GraduationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo obrigatório")
	private String name;

	public GraduationDTO() {
	}

	public GraduationDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public GraduationDTO(Graduation entity) {
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
