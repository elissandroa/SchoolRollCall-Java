package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.elissandro.scoolrollcall.entities.SchoolRollCall;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class SchoolRollCallDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@FutureOrPresent(message = "A data do chamada deve ser no futuro ou no presente")
	private LocalDate date;
	@NotNull(message = "Campo obrigatório")
	private Boolean presence;
	private String justification;
	@NotNull(message = "Campo obrigatório")
	private Long studentId;
	
	public SchoolRollCallDTO() {
	}
	
	public SchoolRollCallDTO(Long id, LocalDate date, Boolean presence, String justification, Long studentId) {	
		this.id = id;
		this.date = date;
		this.presence = presence;
		this.justification = justification;
		this.studentId = studentId;
	}
	
	public SchoolRollCallDTO(SchoolRollCall entity) {
		id = entity.getId();
		date = entity.getDate();
		presence = entity.getPresence();
		justification = entity.getJustification();
		studentId = entity.getStudent().getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean getPresence() {
		return presence;
	}

	public void setPresence(Boolean presence) {
		this.presence = presence;
	}
	
	public String getJustification() {
		return justification;
	}
	
	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Long getStudentId() {
		return studentId;
	}
	
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
}
