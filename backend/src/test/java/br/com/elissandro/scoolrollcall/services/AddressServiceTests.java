package br.com.elissandro.scoolrollcall.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.elissandro.scoolrollcall.dto.AddressDTO;
import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.repositories.AddressRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import br.com.elissandro.scoolrollcall.tests.Factory;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class AddressServiceTests {
	
	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private AddressDTO addressDTO;
	private Address address;
	private PageImpl<Address> page;
	
	@Mock
	private AddressRepository repository;
	
	@InjectMocks
	private AddressService service;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 2L;
		addressDTO = Factory.createAddressDTO();
		address = Factory.createAddress();
		page = new PageImpl<>(List.of(address));
		

		when(repository.existsById(existingId)).thenReturn(true);
		doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
		when(repository.existsById(dependentId)).thenThrow(DatabaseException.class);
		
		when(repository.findById(existingId)).thenReturn(Optional.of(new Address()));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		when(repository.save(ArgumentMatchers.any())).thenReturn(address);
		
		when(repository.getReferenceById(existingId)).thenReturn(address);
		when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
		
	}
	
	@Test	
	public void findByIdShouldReturnAddressDTOWhenIdExists() {
		AddressDTO result = service.findById(existingId);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findById(existingId);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
			});
		Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
		}
	
	@Test	
	public void findAllPagedShouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<AddressDTO> result = service.findAllPaged(pageable);
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findAll(PageRequest.of(0, 10));
	}
	
	@Test
	public void insertShouldReturnAddressDTO() {
		AddressDTO result = service.insert(addressDTO);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any());
	}
	
	@Test
	public void updateShouldReturnAddressDTOWhenIdExists() {
		AddressDTO result = service.update(existingId, addressDTO);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).getReferenceById(existingId);
	}
	
	
	@Test	
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});

	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});

	}
	
		

}
