package br.com.elissandro.scoolrollcall.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.tests.Factory;

@DataJpaTest
public class AddressRepositoryTests {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalAddresss;
	
	@Autowired
	private AddressRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalAddresss = 25L;
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		Optional<Address> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test	
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Address Address = Factory.createAddress();
		Address.setId(null);
		Address = repository.save(Address);
		
		Assertions.assertNotNull(Address.getId());
		Assertions.assertEquals(countTotalAddresss + 1, Address.getId());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		Optional<Address> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		Optional<Address> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
