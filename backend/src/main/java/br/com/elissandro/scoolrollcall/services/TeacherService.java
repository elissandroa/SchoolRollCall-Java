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
import br.com.elissandro.scoolrollcall.dto.DisciplineDTO;
import br.com.elissandro.scoolrollcall.dto.TeacherDTO;
import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.Discipline;
import br.com.elissandro.scoolrollcall.entities.Teacher;
import br.com.elissandro.scoolrollcall.repositories.AddressRepository;
import br.com.elissandro.scoolrollcall.repositories.DisciplineRepository;
import br.com.elissandro.scoolrollcall.repositories.TeacherRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private DisciplineRepository disciplineRepository;

	@Transactional(readOnly = true)
	public Page<TeacherDTO> findAllPaged(Pageable pageable) {
		Page<Teacher> list = repository.findAll(pageable);
		return list.map(x -> new TeacherDTO(x, x.getDisciplines(), x.getAddresses()));
	}

	@Transactional(readOnly = true)
	public TeacherDTO findById(Long id) {
		Optional<Teacher> obj = repository.findById(id);
		Teacher entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		return new TeacherDTO(entity, entity.getDisciplines(), entity.getAddresses());
	}

	@Transactional
	public TeacherDTO insert(TeacherDTO dto) {
		Teacher entity = new Teacher();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new TeacherDTO(entity, entity.getDisciplines(), entity.getAddresses());
	}

	@Transactional
	public TeacherDTO update(Long id, TeacherDTO dto) {
		try {
			Teacher entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new TeacherDTO(entity, entity.getDisciplines(), entity.getAddresses());
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
	
	private void copyDtoToEntity(TeacherDTO dto, Teacher entity) {
		entity.setName(dto.getName());
		entity.setPhone(dto.getPhone());
		entity.setEmail(dto.getEmail());
		entity.getDisciplines().clear();
		for(DisciplineDTO disciplineDTO : dto.getDisciplines()) {
			Discipline discipline = disciplineRepository.getReferenceById(disciplineDTO.getId());
			entity.getDisciplines().add(discipline);
		}
		
		entity.getAddresses().clear();
		for(AddressDTO addressDTO : dto.getAddresses()) {
			Address address = addressRepository.getReferenceById(addressDTO.getId());
			entity.getAddresses().add(address);
		}
	}


}
