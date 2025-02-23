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

import br.com.elissandro.scoolrollcall.dto.StudentDTO;
import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.Instrument;
import br.com.elissandro.scoolrollcall.entities.Student;
import br.com.elissandro.scoolrollcall.repositories.AddressRepository;
import br.com.elissandro.scoolrollcall.repositories.InstrumentRepository;
import br.com.elissandro.scoolrollcall.repositories.SchoolTestRepository;
import br.com.elissandro.scoolrollcall.repositories.StudentRepository;
import br.com.elissandro.scoolrollcall.repositories.TutorRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private SchoolTestRepository schoolTestRepository; 
	
	@Autowired
	private InstrumentRepository instrumentRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	

	@Transactional(readOnly = true)
	public Page<StudentDTO> findAllPaged(Pageable pageable) {
		Page<Student> list = repository.findAll(pageable);
		return list.map(x -> new StudentDTO(x));
	}

	@Transactional(readOnly = true)
	public StudentDTO findById(Long id) {
		Optional<Student> obj = repository.findById(id);
		Student entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new StudentDTO(entity, entity.getTutors(), entity.getSchoolTests());
	}

	@Transactional
	public StudentDTO insert(StudentDTO dto) {
		Student entity = new Student();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new StudentDTO(entity);
	}

	@Transactional
	public StudentDTO update(Long id, StudentDTO dto) {
		try {
			Student entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
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
	
	private void copyDtoToEntity(StudentDTO dto, Student entity) {
		entity.setName(dto.getName());
		Instrument entityInstrument = new Instrument();
		entityInstrument.setId(dto.getInstrumentId());
		entity.setInstrument(instrumentRepository.getReferenceById(entityInstrument.getId()));
		
		Address entityAddress = new Address();
		entityAddress.setId(dto.getAddressId());
		entity.setAddress(addressRepository.getReferenceById(entityAddress.getId()));
		
		entity.getTutors().clear();
		dto.getTutors().forEach(tutorDto -> {
			entity.getTutors().add(tutorRepository.getReferenceById(tutorDto.getId()));
		});
		entity.getSchoolTests().clear();
		dto.getSchoolTests().forEach(testDto -> {
			entity.getSchoolTests().add(schoolTestRepository.getReferenceById(testDto.getId()));
		});
	}

}
