package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.Graduation;
import br.com.elissandro.scoolrollcall.entities.Instrument;
import br.com.elissandro.scoolrollcall.entities.SchoolTest;
import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.entities.Tutor;

public class StudentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;

	private List<TutorDTO> tutors = new ArrayList<>();
	private List<SchoolTestDTO> schoolTests = new ArrayList<>();
	private List<InstrumentDTO> instruments = new ArrayList<>();
	private List<AddressDTO> addresses = new ArrayList<>();
	private List<GraduationDTO> graduations = new ArrayList<>();
	
	public StudentDTO() {
	}
	
	public StudentDTO(Long id, String name, Long instrumentId, Long addressId) {
		this.id = id;
		this.name = name;
	}
	
	public StudentDTO(Student entity) {
		id = entity.getId();
		name = entity.getName();
	}
	
	public StudentDTO(Student entity, Set<Tutor> tutors, 
			Set<SchoolTest> schoolTests, Set<Instrument> instruments,
			Set<Address> addresses, Set<Graduation> graduations) {	
		this(entity);
		tutors.forEach(tutor -> this.tutors.add(new TutorDTO(tutor)));
		schoolTests.forEach(test -> this.schoolTests.add(new SchoolTestDTO(test)));
		instruments.forEach(instrument -> this.instruments.add(new InstrumentDTO(instrument)));
		addresses.forEach(address -> this.addresses.add(new AddressDTO(address)));
		graduations.forEach(graduation -> this.graduations.add(new GraduationDTO(graduation)));
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

	public List<TutorDTO> getTutors() {
		return tutors;
	}

	public List<SchoolTestDTO> getSchoolTests() {
		return schoolTests;
	}
	
	public List<InstrumentDTO> getInstruments() {
		return instruments;
	}
	
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	
	public List<GraduationDTO> getGraduations() {
		return graduations;
	}

}
