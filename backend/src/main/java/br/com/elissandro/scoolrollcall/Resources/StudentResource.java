package br.com.elissandro.scoolrollcall.Resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.elissandro.scoolrollcall.dto.StudentDTO;
import br.com.elissandro.scoolrollcall.services.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {
	
	@Autowired
	private StudentService service;

	@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
	@GetMapping
	public ResponseEntity<Page<StudentDTO>> findAll(Pageable pageable) {
		Page<StudentDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
		StudentDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
	@PostMapping
	public ResponseEntity<StudentDTO> insert(@Valid @RequestBody StudentDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> update(@PathVariable Long id,@Valid @RequestBody StudentDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
