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

import br.com.elissandro.scoolrollcall.dto.TutorDTO;
import br.com.elissandro.scoolrollcall.entities.Tutor;
import br.com.elissandro.scoolrollcall.repositories.TutorRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class TutorService {
	
	@Autowired
	private TutorRepository repository;

	@Transactional(readOnly = true)
	public Page<TutorDTO> findAllPaged(Pageable pageable) {
		Page<Tutor> list = repository.findAll(pageable);
		return list.map(x -> new TutorDTO(x));
	}

	@Transactional(readOnly = true)
	public TutorDTO findById(Long id) {
		Optional<Tutor> obj = repository.findById(id);
		Tutor entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new TutorDTO(entity);
	}

	@Transactional
	public TutorDTO insert(TutorDTO dto) {
		Tutor entity = new Tutor();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new TutorDTO(entity);
	}

	@Transactional
	public TutorDTO update(Long id, TutorDTO dto) {
		try {
			Tutor entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new TutorDTO(entity);
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
