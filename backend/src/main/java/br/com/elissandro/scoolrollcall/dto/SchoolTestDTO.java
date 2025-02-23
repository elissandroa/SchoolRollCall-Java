package br.com.elissandro.scoolrollcall.dto;

import br.com.elissandro.scoolrollcall.entities.SchoolTest;

public class SchoolTestDTO {
	private Long id;
	private String name;
	private String description;
	private Double grade;
	
	public SchoolTestDTO() {
	}
	
	public SchoolTestDTO(Long id, String name, String description, Double grade) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.grade = grade;
	}
	
	public SchoolTestDTO(SchoolTest entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		grade = entity.getGrade();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

}
