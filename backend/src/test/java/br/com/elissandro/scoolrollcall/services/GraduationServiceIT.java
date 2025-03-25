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

import br.com.elissandro.scoolrollcall.dto.GraduationDTO;
import br.com.elissandro.scoolrollcall.repositories.GraduationRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class GraduationServiceIT {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalGraduations;

	@Autowired
	private GraduationService service;

	@Autowired
	private GraduationRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 3L;
		nonExistingId = 1000L;
		countTotalGraduations = 9L;
	}

	@Test
	public void findAllListdShouldReturnList() {

		List<GraduationDTO> result = service.findAll();

		Assertions.assertFalse(result.isEmpty());

	}

	@Test
	public void findAllListdShouldReturnEmptyListWhenListDoesNotExist() {

		List<GraduationDTO> result = service.findAll();

		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	public void findAllListdShouldReturnSortedListWhenSortByName() {

		List<GraduationDTO> result = service.findAll();

		Assertions.assertFalse(result.isEmpty());
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {

		service.delete(existingId);

		assertThat(repository.count()).isEqualTo(countTotalGraduations - 1);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> {
			service.delete(nonExistingId);
		});
	}

}
