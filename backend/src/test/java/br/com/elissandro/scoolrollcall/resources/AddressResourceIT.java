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

import br.com.elissandro.scoolrollcall.dto.AddressDTO;
import br.com.elissandro.scoolrollcall.tests.Factory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AddressResourceIT {
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalAddresses;
	
	private AddressDTO addressDTO;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalAddresses = 6L;
		addressDTO = Factory.createAddressDTO();
		addressDTO.setId(existingId);
	}

	@Test
	public void findAllShouldReturnSortedPageWhenSortByName() throws Exception {
		ResultActions result =
				mockMvc.perform(get("/addresses?page=0&size=12&sort=street,asc")
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.totalElements").value(countTotalAddresses));
		result.andExpect(jsonPath("$.content").exists());	
	}
	
	@Test
	public void updateShouldReturnAddressDTOWhenIdExists() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(addressDTO);
		
		String expectedStreet = addressDTO.getStreet();
		
		ResultActions result =
				mockMvc.perform(put("/addresses/{id}", existingId)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.street").value(expectedStreet));
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(addressDTO);
		
		ResultActions result =
				mockMvc.perform(put("/addresses/{id}", nonExistingId)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findByIdShouldReturnAddressWhenIdExists() throws Exception {
		ResultActions result =
				mockMvc.perform(get("/addresses/{id}", existingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.street").exists());
		result.andExpect(jsonPath("$.num").exists());
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result =
				mockMvc.perform(get("/addresses/{id}", nonExistingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
		ResultActions result =
				mockMvc.perform(delete("/addresses/{id}", existingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result =
				mockMvc.perform(delete("/addresses/{id}", nonExistingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	
		
}
