package br.com.elissandro.scoolrollcall.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.elissandro.scoolrollcall.entities.Teacher;
import br.com.elissandro.scoolrollcall.tests.Factory;

@DataJpaTest
public class TeacherRepositoryTests {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalTeachers;
	
	@Autowired
	private TeacherRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalTeachers = 4L;
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		Optional<Teacher> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test	
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Teacher Teacher = Factory.createTeacher();
		Teacher.setId(null);
		Teacher = repository.save(Teacher);
		
		Assertions.assertNotNull(Teacher.getId());
		Assertions.assertEquals(countTotalTeachers + 1, Teacher.getId());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		Optional<Teacher> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		Optional<Teacher> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
