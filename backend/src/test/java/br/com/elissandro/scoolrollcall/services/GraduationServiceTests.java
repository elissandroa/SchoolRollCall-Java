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

import br.com.elissandro.scoolrollcall.dto.GraduationDTO;
import br.com.elissandro.scoolrollcall.entities.Graduation;
import br.com.elissandro.scoolrollcall.repositories.GraduationRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import br.com.elissandro.scoolrollcall.tests.Factory;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class GraduationServiceTests {
	
	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private GraduationDTO GraduationDTO;
	private Graduation Graduation;
	private PageImpl<Graduation> page;
	
	@Mock
	private GraduationRepository repository;
	
	@InjectMocks
	private GraduationService service;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 2L;
		GraduationDTO = Factory.createGraduationDTO();
		Graduation = Factory.createGraduation();
		page = new PageImpl<>(List.of(Graduation));
		

		when(repository.existsById(existingId)).thenReturn(true);
		doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
		when(repository.existsById(dependentId)).thenThrow(DatabaseException.class);
		
		when(repository.findById(existingId)).thenReturn(Optional.of(new Graduation()));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		when(repository.save(ArgumentMatchers.any())).thenReturn(Graduation);
		
		when(repository.getReferenceById(existingId)).thenReturn(Graduation);
		when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
		
	}
	
	@Test	
	public void findByIdShouldReturnGraduationDTOWhenIdExists() {
		GraduationDTO result = service.findById(existingId);
		
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
		Page<GraduationDTO> result = service.findAllPaged(pageable);
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findAll(PageRequest.of(0, 10));
	}
	
	@Test
	public void insertShouldReturnGraduationDTO() {
		GraduationDTO result = service.insert(GraduationDTO);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any());
	}
	
	@Test
	public void updateShouldReturnGraduationDTOWhenIdExists() {
		GraduationDTO result = service.update(existingId, GraduationDTO);
		
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
