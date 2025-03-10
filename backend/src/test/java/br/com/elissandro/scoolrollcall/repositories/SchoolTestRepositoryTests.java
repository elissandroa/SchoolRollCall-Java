package br.com.elissandro.scoolrollcall.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.elissandro.scoolrollcall.entities.SchoolTest;
import br.com.elissandro.scoolrollcall.tests.Factory;

@DataJpaTest
public class SchoolTestRepositoryTests {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalSchoolTests;
	
	@Autowired
	private SchoolTestRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalSchoolTests = 4L;
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		Optional<SchoolTest> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test	
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		SchoolTest SchoolTest = Factory.createSchoolTest();
		SchoolTest.setId(null);
		SchoolTest = repository.save(SchoolTest);
		
		Assertions.assertNotNull(SchoolTest.getId());
		Assertions.assertEquals(countTotalSchoolTests + 1, SchoolTest.getId());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		Optional<SchoolTest> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		Optional<SchoolTest> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
