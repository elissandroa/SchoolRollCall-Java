package br.com.elissandro.scoolrollcall.dto;

import br.com.elissandro.scoolrollcall.entities.Instrument;
import jakarta.validation.constraints.NotBlank;

public class InstrumentDTO {

	private Long id;
	@NotBlank(message = "Campo obrigatório")
	private String name;

	public InstrumentDTO() {
	}

	public InstrumentDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public InstrumentDTO(Instrument entity) {
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
