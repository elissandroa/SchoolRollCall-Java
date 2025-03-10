package br.com.elissandro.scoolrollcall.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.elissandro.scoolrollcall.entities.SchoolRollCall;
import br.com.elissandro.scoolrollcall.tests.Factory;

@DataJpaTest
public class SchoolRollCallRepositoryTests {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalSchoolRollCalls;
	
	@Autowired
	private SchoolRollCallRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalSchoolRollCalls = 10L;
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		Optional<SchoolRollCall> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test	
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		SchoolRollCall SchoolRollCall = Factory.createSchoolRollCall();
		SchoolRollCall.setId(null);
		SchoolRollCall = repository.save(SchoolRollCall);
		
		Assertions.assertNotNull(SchoolRollCall.getId());
		Assertions.assertEquals(countTotalSchoolRollCalls + 1, SchoolRollCall.getId());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		Optional<SchoolRollCall> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		Optional<SchoolRollCall> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
