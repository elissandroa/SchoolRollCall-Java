package br.com.elissandro.scoolrollcall.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.elissandro.scoolrollcall.entities.ClassRoom;
import br.com.elissandro.scoolrollcall.tests.Factory;

@DataJpaTest
public class ClassRoomRepositoryTests {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalClassRooms;
	
	@Autowired
	private ClassRoomRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalClassRooms = 3L;
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		Optional<ClassRoom> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test	
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		ClassRoom ClassRoom = Factory.createClassRoom();
		ClassRoom.setId(null);
		ClassRoom = repository.save(ClassRoom);
		
		Assertions.assertNotNull(ClassRoom.getId());
		Assertions.assertEquals(countTotalClassRooms + 1, ClassRoom.getId());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		Optional<ClassRoom> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		Optional<ClassRoom> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
