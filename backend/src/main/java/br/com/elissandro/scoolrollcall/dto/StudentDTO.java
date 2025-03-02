package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.SchoolTest;
import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.entities.Tutor;

public class StudentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	private List<TutorDTO> tutors = new ArrayList<>();
	private List<SchoolTestDTO> schoolTests = new ArrayList<>();
	private List<AddressDTO> addresses = new ArrayList<>();

	private GraduationDTO graduation;
	private ClassRoomDTO classRoom;
	private InstrumentDTO instrument;

	public StudentDTO() {
	}

	public StudentDTO(Long id, String name, Long instrumentId, Long addressId, ClassRoomDTO classRoom,
			GraduationDTO graduation, InstrumentDTO instrument) {
		this.id = id;
		this.name = name;
		this.classRoom = classRoom;
		this.graduation = graduation;
		this.instrument = instrument;
	}

	public StudentDTO(Student entity) {
		id = entity.getId();
		name = entity.getName();
		graduation = new GraduationDTO(entity.getGraduation());
		classRoom = new ClassRoomDTO(entity.getClassRoom());
		instrument = new InstrumentDTO(entity.getInstrument());
	}

	public StudentDTO(Student entity, Set<Tutor> tutors, Set<SchoolTest> schoolTests, Set<Address> addresses) {
		this(entity);
		tutors.forEach(tutor -> this.tutors.add(new TutorDTO(tutor)));
		schoolTests.forEach(test -> this.schoolTests.add(new SchoolTestDTO(test)));
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

	public List<TutorDTO> getTutors() {
		return tutors;
	}

	public List<SchoolTestDTO> getSchoolTests() {
		return schoolTests;
	}

	public InstrumentDTO getInstrument() {
		return instrument;
	}

	public void setInstrument(InstrumentDTO instrument) {
		this.instrument = instrument;
	}

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public GraduationDTO getGraduation() {
		return graduation;
	}

	public ClassRoomDTO getClassRoom() {
		return classRoom;
	}

}
