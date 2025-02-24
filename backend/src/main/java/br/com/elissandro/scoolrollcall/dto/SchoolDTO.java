package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.ClassRoom;
import br.com.elissandro.scoolrollcall.entities.School;

public class SchoolDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Long addressId;

	private List<ClassRoom> classrooms = new ArrayList<>();
	
	public SchoolDTO() {
	}
	
	public SchoolDTO(Long id, String name, Long addressId) {
		this.id = id;
		this.name = name;
		this.addressId = addressId;
	}
	
	public SchoolDTO(School entity) {
		id = entity.getId();
		name = entity.getName();
		addressId = entity.getAddress().getId();
	}
	
	public SchoolDTO(School entity, Set<ClassRoom> classrooms) {
		this(entity);
		classrooms.forEach(classroom -> this.classrooms.add(classroom));
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

	public List<ClassRoom> getClassrooms() {
		return classrooms;
	}

}
