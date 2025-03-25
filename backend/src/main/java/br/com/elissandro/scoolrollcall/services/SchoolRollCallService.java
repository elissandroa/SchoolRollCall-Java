package br.com.elissandro.scoolrollcall.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.elissandro.scoolrollcall.dto.SchoolRollCallDTO;
import br.com.elissandro.scoolrollcall.entities.SchoolRollCall;
import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.repositories.SchoolRollCallRepository;
import br.com.elissandro.scoolrollcall.repositories.StudentRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class SchoolRollCallService {
	
	@Autowired
	private SchoolRollCallRepository repository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional(readOnly = true)
	public Page<SchoolRollCallDTO> findAllPaged(Pageable pageable) {
		Page<SchoolRollCall> list = repository.searchAllSchoolRollCallPaged(pageable);
		return list.map(x -> new SchoolRollCallDTO(x));
	}

	@Transactional(readOnly = true)
	public SchoolRollCallDTO findById(Long id) {
		Optional<SchoolRollCall> obj = repository.findById(id);
		SchoolRollCall entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new SchoolRollCallDTO(entity);
	}

	@Transactional
	public SchoolRollCallDTO insert(SchoolRollCallDTO dto) {
		SchoolRollCall entity = new SchoolRollCall();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new SchoolRollCallDTO(entity);
	}


	@Transactional
	public SchoolRollCallDTO update(Long id, SchoolRollCallDTO dto) {
		try {
			SchoolRollCall entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new SchoolRollCallDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		try {
			if(!repository.existsById(id)) {
				throw new ResourceNotFoundException("Id not found " + id);
			}
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDtoToEntity(SchoolRollCallDTO dto, SchoolRollCall entity) {
		entity.setDate(dto.getDate());
		entity.setPresence(dto.getPresence());
		entity.setJustification(dto.getJustification());
		Student student = studentRepository.getReferenceById(dto.getStudent().getId());
		entity.setStudent(student);
	}
}
