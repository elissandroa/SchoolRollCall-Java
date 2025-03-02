package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.ClassRoom;
import br.com.elissandro.scoolrollcall.entities.School;

public class SchoolDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;

	private List<ClassRoomDTO> classRooms = new ArrayList<>();
	private List<AddressDTO> address = new ArrayList<>();
	
	public SchoolDTO() {
	}
	
	public SchoolDTO(Long id, String name, Long addressId) {
		this.id = id;
		this.name = name;
	}
	
	public SchoolDTO(School entity) {
		id = entity.getId();
		name = entity.getName();
	}
	
	public SchoolDTO(School entity, Set<ClassRoom> classRooms, Set<Address> address) {
		this(entity);
		classRooms.forEach(classroom -> this.classRooms.add(new ClassRoomDTO(classroom)));
		address.forEach(addr -> this.address.add(new AddressDTO(addr)));
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

	public List<ClassRoomDTO> getClassRooms() {
		return classRooms;
	}
	
	public List<AddressDTO> getAddress() {
		return address;
	}
}
