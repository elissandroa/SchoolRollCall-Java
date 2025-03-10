package br.com.elissandro.scoolrollcall.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.elissandro.scoolrollcall.dto.TeacherDTO;
import br.com.elissandro.scoolrollcall.tests.Factory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TeacherResourceIT {
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalteacheres;
	
	private TeacherDTO teacherDTO;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalteacheres = 4L;
		teacherDTO = Factory.createTeacherDTO();
	}

	@Test
	public void findAllShouldReturnSortedPageWhenSortByName() throws Exception {
		ResultActions result =
				mockMvc.perform(get("/teachers?page=0&size=12&sort=name,asc")
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.totalElements").value(countTotalteacheres));
		result.andExpect(jsonPath("$.content").exists());	
	}
	
	@Test
	public void updateShouldReturnteacherDTOWhenIdExists() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(teacherDTO);
		
		String expectedName = teacherDTO.getName();
		
		ResultActions result =
				mockMvc.perform(put("/teachers/{id}", existingId)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.name").value(expectedName));
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(teacherDTO);
		
		ResultActions result =
				mockMvc.perform(put("/teachers/{id}", nonExistingId)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findByIdShouldReturnteacherWhenIdExists() throws Exception {
		ResultActions result =
				mockMvc.perform(get("/teachers/{id}", existingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.name").exists());
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result =
				mockMvc.perform(get("/teachers/{id}", nonExistingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
		ResultActions result =
				mockMvc.perform(delete("/teachers/{id}", existingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result =
				mockMvc.perform(delete("/teachers/{id}", nonExistingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	
		
}
