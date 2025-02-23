package br.com.elissandro.scoolrollcall.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.elissandro.scoolrollcall.dto.StudentDTO;
import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.repositories.StudentRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	@Transactional(readOnly = true)
	public Page<StudentDTO> findAllPaged(PageRequest pageRequest) {
		Page<Student> list = repository.findAll(pageRequest);
		return list.map(x -> new StudentDTO(x));
	}

	@Transactional(readOnly = true)
	public StudentDTO findById(Long id) {
		Optional<Student> obj = repository.findById(id);
		Student entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new StudentDTO(entity);
	}

	@Transactional
	public StudentDTO insert(StudentDTO dto) {
		Student entity = new Student();
		entity.setName(dto.getName());
		entity.getInstrument().setId(dto.getInstrumentId());
		entity.getAddress().setId(dto.getAddressId());
		entity = repository.save(entity);
		return new StudentDTO(entity);
	}

	@Transactional
	public StudentDTO update(Long id, StudentDTO dto) {
		try {
			Student entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.getInstrument().setId(dto.getInstrumentId());
			entity.getAddress().setId(dto.getAddressId());
			entity = repository.save(entity);
			return new StudentDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		try {
			if (!repository.existsById(id)) {
				throw new ResourceNotFoundException("Id not found " + id);
			}
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

}
