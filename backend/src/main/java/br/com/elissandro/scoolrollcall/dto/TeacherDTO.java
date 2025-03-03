package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.Discipline;
import br.com.elissandro.scoolrollcall.entities.Teacher;

public class TeacherDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String phone;
	private String email;
	
	private List<DisciplineDTO> disciplines = new ArrayList<>();
	private List<AddressDTO> addresses = new ArrayList<>();
	

	public TeacherDTO() {
	}
	
	public TeacherDTO(Long id, String name, String phone, String email) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public TeacherDTO(Teacher entity) {
		id = entity.getId();
		name = entity.getName();
		phone = entity.getPhone();
		email = entity.getEmail();
	}
	
	public TeacherDTO(Teacher entity, Set<Discipline> disciplines, Set<Address> addresses) {
		this(entity);
		disciplines.forEach(discipline -> this.disciplines.add(new DisciplineDTO(discipline)));
		addresses.forEach(address -> this.addresses.add(new AddressDTO(address)));
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


	public List<DisciplineDTO> getDisciplines() {
		return disciplines;
	}
	
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
