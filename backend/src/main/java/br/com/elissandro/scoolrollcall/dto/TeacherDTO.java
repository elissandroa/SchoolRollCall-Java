package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.Discipline;
import br.com.elissandro.scoolrollcall.entities.Teacher;

public class TeacherDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String phone;
	private Long addressId;
	private List<Discipline> disciplines = new ArrayList<>();
	

	public TeacherDTO() {
	}
	
	public TeacherDTO(Long id, String name, Long addressId, String phone) {
		this.id = id;
		this.name = name;
		this.addressId = addressId;
		this.phone = phone;
	}
	
	public TeacherDTO(Teacher entity) {
		id = entity.getId();
		name = entity.getName();
		phone = entity.getPhone();
		addressId = entity.getAddress().getId();
	}
	
	public TeacherDTO(Teacher entity, Set<Discipline> disciplines) {
		this(entity);
		disciplines.forEach(discipline -> this.disciplines.add(discipline));
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

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}
	
	public String getPhone() {
		return phone;
	}
}
