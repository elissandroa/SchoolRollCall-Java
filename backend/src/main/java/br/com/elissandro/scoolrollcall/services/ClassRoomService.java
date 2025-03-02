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

import br.com.elissandro.scoolrollcall.dto.ClassRoomDTO;
import br.com.elissandro.scoolrollcall.dto.StudentDTO;
import br.com.elissandro.scoolrollcall.entities.ClassRoom;
import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.repositories.ClassRoomRepository;
import br.com.elissandro.scoolrollcall.repositories.StudentRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class ClassRoomService {
	
	@Autowired
	private ClassRoomRepository repository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Transactional(readOnly = true)
	public Page<ClassRoomDTO> findAllPaged(Pageable pageable) {
		Page<ClassRoom> list = repository.findAll(pageable);
		return list.map(x -> new ClassRoomDTO(x));
	}

	@Transactional(readOnly = true)
	public ClassRoomDTO findById(Long id) {
		Optional<ClassRoom> obj = repository.findById(id);
		ClassRoom entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new ClassRoomDTO(entity, entity.getStudents());
	}

	@Transactional
	public ClassRoomDTO insert(ClassRoomDTO dto) {
		ClassRoom entity = new ClassRoom();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClassRoomDTO(entity);
	}

	
	@Transactional
	public ClassRoomDTO update(Long id, ClassRoomDTO dto) {
		try {
			ClassRoom entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClassRoomDTO(entity);
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
	
	private void copyDtoToEntity(ClassRoomDTO dto, ClassRoom entity) {
		entity.setName(dto.getName());
		
		
		entity.getStudents().clear();
		for (StudentDTO studentDTO : dto.getStudents()) {
			Student student = studentRepository.getReferenceById(studentDTO.getId());
			entity.getStudents().add(student);
		}
		
	}

}
