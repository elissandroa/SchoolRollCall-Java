package br.com.elissandro.scoolrollcall.tests;

import java.time.LocalDate;

import br.com.elissandro.scoolrollcall.dto.AddressDTO;
import br.com.elissandro.scoolrollcall.dto.ClassRoomDTO;
import br.com.elissandro.scoolrollcall.dto.DisciplineDTO;
import br.com.elissandro.scoolrollcall.dto.GraduationDTO;
import br.com.elissandro.scoolrollcall.dto.InstrumentDTO;
import br.com.elissandro.scoolrollcall.dto.SchoolDTO;
import br.com.elissandro.scoolrollcall.dto.SchoolRollCallDTO;
import br.com.elissandro.scoolrollcall.dto.SchoolTestDTO;
import br.com.elissandro.scoolrollcall.dto.StudentDTO;
import br.com.elissandro.scoolrollcall.dto.TeacherDTO;
import br.com.elissandro.scoolrollcall.dto.TutorDTO;
import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.ClassRoom;
import br.com.elissandro.scoolrollcall.entities.Discipline;
import br.com.elissandro.scoolrollcall.entities.Graduation;
import br.com.elissandro.scoolrollcall.entities.Instrument;
import br.com.elissandro.scoolrollcall.entities.School;
import br.com.elissandro.scoolrollcall.entities.SchoolRollCall;
import br.com.elissandro.scoolrollcall.entities.SchoolTest;
import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.entities.Teacher;
import br.com.elissandro.scoolrollcall.entities.Tutor;

public class Factory {

	public static Address createAddress() {
		return new Address(null, "Rua 1", "123", "Bairro 1", "Cidade 1", "Estado 1", "12345678");
	}
	
	public static AddressDTO createAddressDTO() {
		return new AddressDTO(createAddress());
	}
	
	public static ClassRoom createClassRoom() {
		return new ClassRoom(null, "Turma 1");
	}
	
	public static ClassRoomDTO createClassRoomDTO() {
		return new ClassRoomDTO(createClassRoom());
	}
	
	public static Discipline createDiscipline() {
		return new Discipline(null, "Disciplina 1");
	}
	
	public static DisciplineDTO createDisciplineDTO() {
		return new DisciplineDTO(createDiscipline());
	}
	
	public static Graduation createGraduation() {
		return new Graduation(null, "Graduação 1");
	}
	
	public static GraduationDTO createGraduationDTO() {
		return new GraduationDTO(createGraduation());
	}
	
	public static Instrument createInstrument() {
		return new Instrument(null, "Instrumento 1");
	}
	
	public static InstrumentDTO createInstrumentDTO() {
		return new InstrumentDTO(createInstrument());
	}
	
	public static SchoolRollCall createSchoolRollCall() {
		return new SchoolRollCall(null, LocalDate.now(),true, "Justificativa 1", createStudent());
	}
	
	public static SchoolRollCallDTO createSchoolRollCallDTO() {
		return new SchoolRollCallDTO(createSchoolRollCall());
	}
	
	public static Student createStudent() {
		return new Student(null, "Aluno de Teste", new Graduation(1L, null), new ClassRoom(1L, null), new Instrument(1L, null), "teste@email.com", "999999999");
	}
	
	public static StudentDTO createStudentDTO() {
		return new StudentDTO(createStudent());
	}
	
	public static School createSchool() {
		return new School(null, "Escola de Teste","999999");
	}
	
	public static SchoolDTO createSchoolDTO() {
		return new SchoolDTO(createSchool());
	}
	
	public static SchoolTest createSchoolTest() {
		return new SchoolTest(null, "Prova de Teste", "Descrição da Prova de Teste", 10.0, new Discipline(1L, null));
	}
	
	public static SchoolTestDTO createSchoolTestDTO() {
		return new SchoolTestDTO(createSchoolTest());
	}
	
	public static Teacher createTeacher() {
		return new Teacher(null, "Professor de Teste", "99999999","teste@gmail.com" );
	}
	
	public static TeacherDTO createTeacherDTO() {
		return new TeacherDTO(createTeacher());
	}
	
	public static Tutor createTutor() {
		return new Tutor(null, "Tutor de Teste", "teste@gmail.com", "999999999");
	}
	
	public static TutorDTO createTutorDTO() {
		return new TutorDTO(createTutor());
	}	
}
