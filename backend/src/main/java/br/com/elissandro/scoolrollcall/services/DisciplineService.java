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

import br.com.elissandro.scoolrollcall.dto.DisciplineDTO;
import br.com.elissandro.scoolrollcall.entities.Discipline;
import br.com.elissandro.scoolrollcall.repositories.DisciplineRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class DisciplineService {
	
	@Autowired
	private DisciplineRepository repository;

	@Transactional(readOnly = true)
	public Page<DisciplineDTO> findAllPaged(Pageable pageable) {
		Page<Discipline> list = repository.findAll(pageable);
		return list.map(x -> new DisciplineDTO(x));
	}

	@Transactional(readOnly = true)
	public DisciplineDTO findById(Long id) {
		Optional<Discipline> obj = repository.findById(id);
		Discipline entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new DisciplineDTO(entity);
	}

	@Transactional
	public DisciplineDTO insert(DisciplineDTO dto) {
		Discipline entity = new Discipline();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new DisciplineDTO(entity);
	}

	@Transactional
	public DisciplineDTO update(Long id, DisciplineDTO dto) {
		try {
			Discipline entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new DisciplineDTO(entity);
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
