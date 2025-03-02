package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.elissandro.scoolrollcall.entities.ClassRoom;
import br.com.elissandro.scoolrollcall.entities.Student;

public class ClassRoomDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	private List<StudentDTO> students = new ArrayList<>();
	
	public ClassRoomDTO() {
	}

	public ClassRoomDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ClassRoomDTO(ClassRoom entity) {
		id = entity.getId();
		name = entity.getName();
	}
	
	public ClassRoomDTO(ClassRoom entity, List<Student> students) {
		this(entity);
		students.forEach(student -> this.students.add(new StudentDTO(student)));
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
	
	public List<StudentDTO> getStudents() {
		return students;
	}

}
