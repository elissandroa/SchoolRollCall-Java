package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.ClassRoom;
import br.com.elissandro.scoolrollcall.entities.SchoolRollCall;

public class SchoolRollCallDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LocalDate date;
	private Boolean presence;
	private String justification;
	
	private List<ClassRoomDTO> classRooms = new ArrayList<>();

	public SchoolRollCallDTO() {
	}
	
	public SchoolRollCallDTO(Long id, LocalDate date, Boolean presence, String justification) {
		this.id = id;
		this.date = date;
		this.presence = presence;
	}
	
	public SchoolRollCallDTO(SchoolRollCall entity) {
		id = entity.getId();
		date = entity.getDate();
		presence = entity.getPresence();
		justification = entity.getJustification();
	}
	
	public SchoolRollCallDTO(SchoolRollCall entity, Set<ClassRoom> classRooms) {
		this(entity);
		classRooms.forEach(classRoom -> this.classRooms.add(new ClassRoomDTO(classRoom)));	
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
	
	public List<ClassRoomDTO> getClassRooms() {
		return classRooms;
	}
}
