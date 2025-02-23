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

import br.com.elissandro.scoolrollcall.dto.SchoolTestDTO;
import br.com.elissandro.scoolrollcall.entities.SchoolTest;
import br.com.elissandro.scoolrollcall.repositories.SchoolTestRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class SchoolTestService {
	
	@Autowired
	private SchoolTestRepository repository;

	@Transactional(readOnly = true)
	public Page<SchoolTestDTO> findAllPaged(PageRequest pageRequest) {
		Page<SchoolTest> list = repository.findAll(pageRequest);
		return list.map(x -> new SchoolTestDTO(x));
	}

	@Transactional(readOnly = true)
	public SchoolTestDTO findById(Long id) {
		Optional<SchoolTest> obj = repository.findById(id);
		SchoolTest entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new SchoolTestDTO(entity);
	}

	@Transactional
	public SchoolTestDTO insert(SchoolTestDTO dto) {
		SchoolTest entity = new SchoolTest();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setGrade(dto.getGrade());
		entity = repository.save(entity);
		return new SchoolTestDTO(entity);
	}

	@Transactional
	public SchoolTestDTO update(Long id, SchoolTestDTO dto) {
		try {
			SchoolTest entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setGrade(dto.getGrade());
			entity = repository.save(entity);
			return new SchoolTestDTO(entity);
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

}
