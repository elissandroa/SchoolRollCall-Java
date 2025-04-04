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

import br.com.elissandro.scoolrollcall.dto.SchoolTestDTO;
import br.com.elissandro.scoolrollcall.entities.Discipline;
import br.com.elissandro.scoolrollcall.entities.SchoolTest;
import br.com.elissandro.scoolrollcall.repositories.DisciplineRepository;
import br.com.elissandro.scoolrollcall.repositories.SchoolTestRepository;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class SchoolTestService {
	
	@Autowired
	private SchoolTestRepository repository;
	
	@Autowired
	private DisciplineRepository disciplineRepository;

	@Transactional(readOnly = true)
	public Page<SchoolTestDTO> findAllPaged(Pageable pageable) {
		Page<SchoolTest> list = repository.searchAllSchoolTests(pageable);
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
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new SchoolTestDTO(entity);
	}

	@Transactional
	public SchoolTestDTO update(Long id, SchoolTestDTO dto) {
		try {
			SchoolTest entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
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
	
	private void copyDtoToEntity(SchoolTestDTO dto, SchoolTest entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setGrade(dto.getGrade());
		Discipline discipline = disciplineRepository.getReferenceById(dto.getDiscipline().getId());
		entity.setDiscipline(discipline);
	}
}
