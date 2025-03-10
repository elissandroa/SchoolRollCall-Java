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

import br.com.elissandro.scoolrollcall.dto.SchoolTestDTO;
import br.com.elissandro.scoolrollcall.entities.Discipline;
import br.com.elissandro.scoolrollcall.entities.SchoolTest;
import br.com.elissandro.scoolrollcall.repositories.DisciplineRepository;
import br.com.elissandro.scoolrollcall.repositories.SchoolTestRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import br.com.elissandro.scoolrollcall.tests.Factory;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class SchoolTestServiceTests {
	
	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private SchoolTestDTO schoolTestDTO;
	private SchoolTest schoolTest;
	private Discipline discipline;
	private PageImpl<SchoolTest> page;
	
	@InjectMocks
	private SchoolTestService service;
	
	@Mock
	private SchoolTestRepository repository;
	
	@Mock
	private DisciplineRepository disciplineRepository;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 2L;
		discipline = Factory.createDiscipline();
		schoolTestDTO = Factory.createSchoolTestDTO();
		schoolTest = Factory.createSchoolTest();
		page = new PageImpl<>(List.of(schoolTest));
		

		when(repository.existsById(existingId)).thenReturn(true);
		doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
		when(repository.existsById(dependentId)).thenThrow(DatabaseException.class);
		
		when(repository.findById(existingId)).thenReturn(Optional.of(schoolTest));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		when(repository.getReferenceById(existingId)).thenReturn(schoolTest);
		when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
		when(disciplineRepository.getReferenceById(existingId)).thenReturn(discipline);
		when(disciplineRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
			
		when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		when(repository.save(ArgumentMatchers.any())).thenReturn(schoolTest);
		
	}
	
	@Test	
	public void findByIdShouldReturnSchoolTestDTOWhenIdExists() {
		SchoolTestDTO result = service.findById(existingId);
		
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
		Page<SchoolTestDTO> result = service.findAllPaged(pageable);
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findAll(PageRequest.of(0, 10));
	}
	
	@Test
	public void insertShouldReturnSchoolTestDTO() {
		SchoolTestDTO result = service.insert(schoolTestDTO);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any());
	}
	
	@Test
	public void updateShouldReturnSchoolTestDTOWhenIdExists() {
		SchoolTestDTO result = service.update(existingId, schoolTestDTO);
		
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
