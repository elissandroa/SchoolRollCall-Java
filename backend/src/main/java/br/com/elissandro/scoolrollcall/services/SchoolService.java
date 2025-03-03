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

import br.com.elissandro.scoolrollcall.dto.AddressDTO;
import br.com.elissandro.scoolrollcall.dto.ClassRoomDTO;
import br.com.elissandro.scoolrollcall.dto.SchoolDTO;
import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.ClassRoom;
import br.com.elissandro.scoolrollcall.entities.School;
import br.com.elissandro.scoolrollcall.repositories.AddressRepository;
import br.com.elissandro.scoolrollcall.repositories.ClassRoomRepository;
import br.com.elissandro.scoolrollcall.repositories.SchoolRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class SchoolService {
	
	@Autowired
	private SchoolRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ClassRoomRepository classRoomRepository;

	@Transactional(readOnly = true)
	public Page<SchoolDTO> findAllPaged(Pageable pageable) {
		Page<School> list = repository.findAll(pageable);
		return list.map(x -> new SchoolDTO(x, x.getClassRooms(), x.getAddress()));
	}

	@Transactional(readOnly = true)
	public SchoolDTO findById(Long id) {
		Optional<School> obj = repository.findById(id);
		School entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new SchoolDTO(entity, entity.getClassRooms(), entity.getAddress());
	}

	@Transactional
	public SchoolDTO insert(SchoolDTO dto) {
		School entity = new School();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new SchoolDTO(entity, entity.getClassRooms(), entity.getAddress());
	}

	@Transactional
	public SchoolDTO update(Long id, SchoolDTO dto) {
		try {
			School entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new SchoolDTO(entity, entity.getClassRooms(), entity.getAddress());
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

	private void copyDtoToEntity(SchoolDTO dto, School entity) {
		entity.setName(dto.getName());
		entity.setPhone(dto.getPhone());
		entity.getClassRooms().clear();
		for (ClassRoomDTO classRoomDTO : dto.getClassRooms()) {
			ClassRoom classRoom = classRoomRepository.getReferenceById(classRoomDTO.getId());
			entity.getClassRooms().add(classRoom);
		}
		
		entity.getAddress().clear();
		for (AddressDTO addressDTO : dto.getAddress()) {
			Address address = addressRepository.getReferenceById(addressDTO.getId());
			entity.getAddress().add(address);
		}
	}
}
