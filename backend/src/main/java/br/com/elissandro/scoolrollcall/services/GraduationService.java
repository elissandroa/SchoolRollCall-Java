package br.com.elissandro.scoolrollcall.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.elissandro.scoolrollcall.dto.GraduationDTO;
import br.com.elissandro.scoolrollcall.entities.Graduation;
import br.com.elissandro.scoolrollcall.repositories.GraduationRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class GraduationService {
	
	@Autowired
	private GraduationRepository repository;

	@Transactional(readOnly = true)
	public List<GraduationDTO> findAll() {
		List<Graduation> list = repository.findAll();
		return list.stream().map(x -> new GraduationDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public GraduationDTO findById(Long id) {
		Optional<Graduation> obj = repository.findById(id);
		Graduation entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new GraduationDTO(entity);
	}

	@Transactional
	public GraduationDTO insert(GraduationDTO dto) {
		Graduation entity = new Graduation();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new GraduationDTO(entity);
	}

	@Transactional
	public GraduationDTO update(Long id, GraduationDTO dto) {
		try {
			Graduation entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new GraduationDTO(entity);
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
	
	private void copyDtoToEntity(GraduationDTO dto, Graduation entity) {
		entity.setName(dto.getName());
	}


}
