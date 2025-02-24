package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.SchoolRollCall;

public class ClassRoomDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	private List<SchoolRollCall> schoolrollcalls = new ArrayList<>();
	
	public ClassRoomDTO() {
	}

	public ClassRoomDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ClassRoomDTO(ClassRoomDTO entity) {
		id = entity.getId();
		name = entity.getName();
	}
	
	public ClassRoomDTO(ClassRoomDTO entity, Set<SchoolRollCall> schoolrollcalls) {
		this(entity);
		schoolrollcalls.forEach(x -> this.schoolrollcalls.add(x));
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
	
	public List<SchoolRollCall> getSchoolrollcalls() {
		return schoolrollcalls;
	}

}
