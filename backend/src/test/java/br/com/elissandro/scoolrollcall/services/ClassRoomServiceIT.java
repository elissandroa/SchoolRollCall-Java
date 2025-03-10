package br.com.elissandro.scoolrollcall.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import br.com.elissandro.scoolrollcall.dto.ClassRoomDTO;
import br.com.elissandro.scoolrollcall.repositories.ClassRoomRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class ClassRoomServiceIT {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalClassRooms;

	@Autowired
	private ClassRoomService service;

	@Autowired
	private ClassRoomRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 2L;
		nonExistingId = 1000L;
		countTotalClassRooms = 3L;
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<ClassRoomDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());

	}

	@Test
	public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {

		PageRequest pageRequest = PageRequest.of(50, 10);

		Page<ClassRoomDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortByName() {

		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<ClassRoomDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {

		service.delete(existingId);

		assertThat(repository.count()).isEqualTo(countTotalClassRooms - 1);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> {
			service.delete(nonExistingId);
		});
	}

}
