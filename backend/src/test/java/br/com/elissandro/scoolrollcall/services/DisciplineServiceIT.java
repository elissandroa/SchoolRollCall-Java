package br.com.elissandro.scoolrollcall.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.com.elissandro.scoolrollcall.dto.DisciplineDTO;
import br.com.elissandro.scoolrollcall.repositories.DisciplineRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class DisciplineServiceIT {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalDisciplines;

	@Autowired
	private DisciplineService service;

	@Autowired
	private DisciplineRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 5L;
		nonExistingId = 1000L;
		countTotalDisciplines = 5L;
	}

	@Test
	public void findAllShouldReturnPage() {


		List<DisciplineDTO> result = service.findAll();

		Assertions.assertFalse(result.isEmpty());

	}

	@Test
	public void findAllShouldReturnEmptyPageWhenPageDoesNotExist() {

		List<DisciplineDTO> result = service.findAll();

		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	public void findAllShouldReturnSortedPageWhenSortByName() {

		List<DisciplineDTO> result = service.findAll();

		Assertions.assertFalse(result.isEmpty());
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {

		service.delete(existingId);

		assertThat(repository.count()).isEqualTo(countTotalDisciplines - 1);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> {
			service.delete(nonExistingId);
		});
	}

}
