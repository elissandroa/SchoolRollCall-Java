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
import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.Tutor;
import br.com.elissandro.scoolrollcall.repositories.AddressRepository;
import br.com.elissandro.scoolrollcall.repositories.TutorRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class TutorService {
	
	@Autowired
	private TutorRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Transactional(readOnly = true)
	public Page<TutorDTO> findAllPaged(String name, Pageable pageable) {
		Page<Tutor> list = repository.searchAllTutors(name, pageable);
		return list.map(x -> new TutorDTO(x, x.getAddresses(), x.getStudents()));
	}

	@Transactional(readOnly = true)
	public TutorDTO findById(Long id) {
		Optional<Tutor> obj = repository.findById(id);
		Tutor entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new TutorDTO(entity, entity.getAddresses(), entity.getStudents());
	}

	@Transactional
	public TutorDTO insert(TutorDTO dto) {
		Tutor entity = new Tutor();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new TutorDTO(entity, entity.getAddresses(), entity.getStudents());
	}

	@Transactional
	public TutorDTO update(Long id, TutorDTO dto) {
		try {
			Tutor entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new TutorDTO(entity, entity.getAddresses(), entity.getStudents());
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
	
	private void copyDtoToEntity(TutorDTO dto, Tutor entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		dto.getAddresses().forEach(addressDto -> {
			Address address = addressRepository.getReferenceById(addressDto.getId());
			entity.getAddresses().add(address);
		});
		
	}


}
