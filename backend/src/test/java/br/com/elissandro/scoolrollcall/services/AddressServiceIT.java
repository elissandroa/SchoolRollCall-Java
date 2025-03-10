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

import br.com.elissandro.scoolrollcall.dto.AddressDTO;
import br.com.elissandro.scoolrollcall.repositories.AddressRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class AddressServiceIT {

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalAddresss;

	@Autowired
	private AddressService service;

	@Autowired
	private AddressRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalAddresss = 6L;
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<AddressDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());

	}

	@Test
	public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {

		PageRequest pageRequest = PageRequest.of(50, 10);

		Page<AddressDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortByName() {

		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<AddressDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
	}

	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {

		service.delete(existingId);

		assertThat(repository.count()).isEqualTo(countTotalAddresss - 1);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> {
			service.delete(nonExistingId);
		});
	}

}
