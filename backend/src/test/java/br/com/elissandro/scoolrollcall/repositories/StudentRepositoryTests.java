package br.com.elissandro.scoolrollcall.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.tests.Factory;

@DataJpaTest
public class StudentRepositoryTests {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalStudents;
	
	@Autowired
	private StudentRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalStudents = 20L;
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		Optional<Student> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test	
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Student Student = Factory.createStudent();
		Student.setId(null);
		Student = repository.save(Student);
		
		Assertions.assertNotNull(Student.getId());
		Assertions.assertEquals(countTotalStudents + 1, Student.getId());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		Optional<Student> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		Optional<Student> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
