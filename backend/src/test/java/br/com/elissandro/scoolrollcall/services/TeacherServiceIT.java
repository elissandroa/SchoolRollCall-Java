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

import br.com.elissandro.scoolrollcall.dto.TeacherDTO;
import br.com.elissandro.scoolrollcall.repositories.TeacherRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class TeacherServiceIT {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalTeachers;

	@Autowired
	private TeacherService service;

	@Autowired
	private TeacherRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalTeachers = 4L;
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<TeacherDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());

	}

	@Test
	public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {

		PageRequest pageRequest = PageRequest.of(50, 10);

		Page<TeacherDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortByName() {

		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<TeacherDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {

		service.delete(existingId);

		assertThat(repository.count()).isEqualTo(countTotalTeachers - 1);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> {
			service.delete(nonExistingId);
		});
	}

}
