package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;

import br.com.elissandro.scoolrollcall.entities.SchoolTest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class SchoolTestDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo obrigatório")
	private String name;
	@NotBlank(message = "Campo obrigatório")
	private String description;
	@PositiveOrZero(message = "Campo obrigatório")
	private Double grade;
	@NotNull(message = "Campo obrigatório")
	private Long disciplineId;
	
	private DisciplineDTO discipline;
	
	public SchoolTestDTO() {
	}
	
	public SchoolTestDTO(Long id, String name, String description, Double grade, Long disciplineId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.grade = grade;
		this.disciplineId = disciplineId;
	}
	
	public SchoolTestDTO(SchoolTest entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		grade = entity.getGrade();
		disciplineId = entity.getDiscipline().getId();
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
	
	public DisciplineDTO getDiscipline() {
		return discipline;
	}
	
	public void setDiscipline(DisciplineDTO discipline) {
		this.discipline = discipline;
	}
	
	public Long getDisciplineId() {
		return disciplineId;
	}
	
	public void setDisciplineId(Long disciplineId) {
		this.disciplineId = disciplineId;
	}

}
