package br.com.elissandro.scoolrollcall.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.elissandro.scoolrollcall.Resources.TutorResource;
import br.com.elissandro.scoolrollcall.dto.TutorDTO;
import br.com.elissandro.scoolrollcall.services.TutorService;
import br.com.elissandro.scoolrollcall.services.exceptions.DatabaseException;
import br.com.elissandro.scoolrollcall.services.exceptions.ResourceNotFoundException;
import br.com.elissandro.scoolrollcall.tests.Factory;

@WebMvcTest(TutorResource.class)
public class TutorResourceTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TutorService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private TutorDTO tutorDTO;
	private PageImpl<TutorDTO> page;
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	
	@BeforeEach
	void setUp() throws Exception {
		tutorDTO = Factory.createTutorDTO();
		page = new PageImpl<>(List.of(tutorDTO));
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		
		when(service.findAllPaged(any())).thenReturn(page);
		
		
		when(service.findById(existingId)).thenReturn(tutorDTO);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		when(service.insert(any())).thenReturn(tutorDTO);
		
		when(service.update(eq(existingId), any())).thenReturn(tutorDTO);
		when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
		
		doNothing().when(service).delete(existingId);
		doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		doThrow(DatabaseException.class).when(service).delete(dependentId);
		
		when(service.insert(any())).thenReturn(tutorDTO);

	}
	
	@Test
	public void findAllShouldReturnPage() throws Exception {
		ResultActions result = mockMvc.perform(get("/tutors")
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
	
	@Test
	public void findByIdShouldReturnTutorWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(get("/tutors/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.name").exists());
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result = mockMvc.perform(get("/tutors/{nonExistingId}", nonExistingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void insertShouldReturnTutorDTOCreated() throws Exception {
		ResultActions result = mockMvc.perform(post("/tutors")
				.content(objectMapper.writeValueAsString(tutorDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.name").exists());
	}
	
	@Test
	public void updateShouldReturnTutorDTOWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(put("/tutors/{id}", existingId)
				.content(objectMapper.writeValueAsString(tutorDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
	
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result = mockMvc.perform(put("/tutors/{id}", nonExistingId)
				.content(objectMapper.writeValueAsString(tutorDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(delete("/tutors/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result = mockMvc.perform(delete("/tutors/{id}", nonExistingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteShouldReturnBadRequestWhenIdIsDependent() throws Exception {
		ResultActions result = mockMvc.perform(delete("/tutors/{id}", dependentId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isBadRequest());
	}
	
}
