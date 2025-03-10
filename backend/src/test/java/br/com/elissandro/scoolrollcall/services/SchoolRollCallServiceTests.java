package br.com.elissandro.scoolrollcall.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.elissandro.scoolrollcall.dto.SchoolRollCallDTO;
import br.com.elissandro.scoolrollcall.entities.SchoolRollCall;
import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.repositories.SchoolRollCallRepository;
import br.com.elissandro.scoolrollcall.repositories.StudentRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import br.com.elissandro.scoolrollcall.tests.Factory;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class SchoolRollCallServiceTests {

	@InjectMocks
	private SchoolRollCallService service;

	@Mock
	private SchoolRollCallRepository repository;
	
	@Mock
	private StudentRepository studentRepository;

	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private SchoolRollCall schoolRollCall;
	private PageImpl<SchoolRollCall> page;
	private SchoolRollCallDTO schoolRollCallDTO;
	private Student student;
	
	

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		schoolRollCall = Factory.createSchoolRollCall();
		page = new PageImpl<>(List.of(schoolRollCall));
		schoolRollCallDTO = Factory.createSchoolRollCallDTO();

		student = Factory.createStudent();
		
		when(repository.save(ArgumentMatchers.any())).thenReturn(schoolRollCall);

		when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		when(repository.findById(existingId)).thenReturn(Optional.of(schoolRollCall));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		when(repository.getReferenceById(existingId)).thenReturn(schoolRollCall);
		when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);	
		
		when(studentRepository.getReferenceById(existingId)).thenReturn(student);
		when(studentRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

		when(repository.existsById(existingId)).thenReturn(true);
		doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
		when(repository.existsById(dependentId)).thenThrow(DataIntegrityViolationException.class);

	}
	
	@Test
	public void findByIdShouldReturnSchoolRollCallDTOWhenIdExists() {
		SchoolRollCallDTO result = service.findById(existingId);
		
		Assertions.assertNotNull(result);
		verify(repository, times(1)).findById(existingId);
	}
	
	@Test
	public void findByIdShouldThrowEntityNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
			});
	}
	
	@Test
	public void insertShouldReturnSchoolRollCallDTO() {
		SchoolRollCallDTO result = service.insert(schoolRollCallDTO);
		Assertions.assertNotNull(result);
	}

	
	@Test
	public void updateShouldReturnSchoolRollCallDTOWhenIdExists() {
		SchoolRollCallDTO result = service.update(existingId, schoolRollCallDTO);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldThrowEntityNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, schoolRollCallDTO);
			});
	}
	
	@Test
	public void findAllPagedShouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<SchoolRollCallDTO> result = service.findAllPaged(pageable);
		Assertions.assertNotNull(result);
		verify(repository, times(1)).findAll(pageable);
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});

		verify(repository, times(1)).deleteById(existingId);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}

	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentIdExists() {
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});
	}

}