package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.SchoolTest;
import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.entities.Tutor;

public class StudentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Long instrumentId;
	private Long addressId;
	
	private List<Tutor> tutors = new ArrayList<>();
	private List<SchoolTest> schoolTests = new ArrayList<>();
	
	public StudentDTO() {
	}
	
	public StudentDTO(Long id, String name, Long instrumentId, Long addressId) {
		this.id = id;
		this.name = name;
		this.instrumentId = instrumentId;
		this.addressId = addressId;
	}
	
	public StudentDTO(Student entity) {
		id = entity.getId();
		name = entity.getName();
		instrumentId = entity.getInstrument().getId();
		addressId = entity.getAddress().getId();
	}
	
	public StudentDTO(Student entity, Set<Tutor> tutors, List<SchoolTest> schoolTests) {	
		this(entity);
		tutors.forEach(tutor -> this.tutors.add(tutor));
		schoolTests.forEach(test -> this.schoolTests.add(test));
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

	public Long getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(Long instrumentId) {
		this.instrumentId = instrumentId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public List<Tutor> getTutors() {
		return tutors;
	}

	public List<SchoolTest> getSchoolTests() {
		return schoolTests;
	}
}
