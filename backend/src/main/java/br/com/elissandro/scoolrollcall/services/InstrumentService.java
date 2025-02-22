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

import br.com.elissandro.scoolrollcall.dto.InstrumentDTO;
import br.com.elissandro.scoolrollcall.entities.Instrument;
import br.com.elissandro.scoolrollcall.repositories.InstrumentRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class InstrumentService {
	
	@Autowired
	private InstrumentRepository repository;

	@Transactional(readOnly = true)
	public Page<InstrumentDTO> findAllPaged(PageRequest pageRequest) {
		Page<Instrument> list = repository.findAll(pageRequest);
		return list.map(x -> new InstrumentDTO(x));
	}

	@Transactional(readOnly = true)
	public InstrumentDTO findById(Long id) {
		Optional<Instrument> obj = repository.findById(id);
		Instrument entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new InstrumentDTO(entity);
	}

	@Transactional
	public InstrumentDTO insert(InstrumentDTO dto) {
		Instrument entity = new Instrument();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new InstrumentDTO(entity);
	}

	@Transactional
	public InstrumentDTO update(Long id, InstrumentDTO dto) {
		try {
			Instrument entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new InstrumentDTO(entity);
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
