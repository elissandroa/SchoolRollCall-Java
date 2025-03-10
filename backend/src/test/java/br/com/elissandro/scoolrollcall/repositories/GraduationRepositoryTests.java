package br.com.elissandro.scoolrollcall.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.elissandro.scoolrollcall.entities.Graduation;
import br.com.elissandro.scoolrollcall.tests.Factory;

@DataJpaTest
public class GraduationRepositoryTests {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalGraduations;
	
	@Autowired
	private GraduationRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalGraduations = 9L;
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		Optional<Graduation> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test	
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Graduation Graduation = Factory.createGraduation();
		Graduation.setId(null);
		Graduation = repository.save(Graduation);
		
		Assertions.assertNotNull(Graduation.getId());
		Assertions.assertEquals(countTotalGraduations + 1, Graduation.getId());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		Optional<Graduation> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		Optional<Graduation> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
