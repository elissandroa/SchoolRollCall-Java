package br.com.elissandro.scoolrollcall.dto;

import br.com.elissandro.scoolrollcall.entities.Student;

public class StudentDTO {
	
	private Long id;
	private String name;
	private Long instrumentId;
	private Long addressId;
	
	public StudentDTO() {
	}
	
	public StudentDTO(Long id, String name, Long instrumentId, Long addressId) {
		this.id = id;
		this.name = name;
		this.instrumentId = instrumentId;
		this.addressId = addressId;
	}
	
	public StudentDTO(Student entity) {
		id = entity.getId();
		name = entity.getName();
		instrumentId = entity.getInstrument().getId();
		addressId = entity.getAddress().getId();
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

	public Long getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(Long instrumentId) {
		this.instrumentId = instrumentId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

}
