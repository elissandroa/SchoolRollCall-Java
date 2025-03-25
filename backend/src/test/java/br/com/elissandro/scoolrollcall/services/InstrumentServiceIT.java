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

import br.com.elissandro.scoolrollcall.dto.InstrumentDTO;
import br.com.elissandro.scoolrollcall.repositories.InstrumentRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class InstrumentServiceIT {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalInstruments;

	@Autowired
	private InstrumentService service;

	@Autowired
	private InstrumentRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 3L;
		nonExistingId = 1000L;
		countTotalInstruments = 4L;
	}

	@Test
	public void findAllShouldReturnPage() {

		List<InstrumentDTO> result = service.findAll();

		Assertions.assertFalse(result.isEmpty());

	}

	@Test
	public void findAllShouldReturnEmptyPageWhenPageDoesNotExist() {

		List<InstrumentDTO> result = service.findAll();

		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	public void findAllShouldReturnSortedPageWhenSortByName() {

		List<InstrumentDTO> result = service.findAll();

		Assertions.assertFalse(result.isEmpty());
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {

		service.delete(existingId);

		assertThat(repository.count()).isEqualTo(countTotalInstruments - 1);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> {
			service.delete(nonExistingId);
		});
	}

}
