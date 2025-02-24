package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.SchoolRollCall;
import br.com.elissandro.scoolrollcall.entities.Student;

public class SchoolRollCallDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Boolean presence;
	private Long classRoomId;
	
	private List<Student> students = new ArrayList<>();
	
	public SchoolRollCallDTO() {
	}
	
	public SchoolRollCallDTO(Long id, Boolean presence, Long classRoomId) {
		this.id = id;
		this.presence = presence;
		this.classRoomId = classRoomId;
	}
	
	public SchoolRollCallDTO(SchoolRollCall entity) {
		id = entity.getId();
		presence = entity.getPresence();
		classRoomId = entity.getClassRoom().getId();
	}
	
	public SchoolRollCallDTO(SchoolRollCall entity, Set<Student> students) {
		this(entity);
		students.forEach(student -> this.students.add(student));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getPresence() {
		return presence;
	}

	public void setPresence(Boolean presence) {
		this.presence = presence;
	}

	public Long getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(Long classRoomId) {
		this.classRoomId = classRoomId;
	}

	public List<Student> getStudents() {
		return students;
	}
}
