package br.com.elissandro.scoolrollcall.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.elissandro.scoolrollcall.entities.Address;
import br.com.elissandro.scoolrollcall.entities.Tutor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class TutorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo obrigatório")
	private String name;
	@Email(message = "Favor entrar com um email válido")
	private String email;
	@NotBlank(message = "Campo obrigatório")
	private String phone;
	
	private List<AddressDTO> addresses = new ArrayList<>();

	public TutorDTO() {
	}

	public TutorDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public TutorDTO(Tutor entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		phone = entity.getPhone();
	}
	
	public TutorDTO(Tutor entity, Set<Address> addresses) {
		this(entity);
		addresses.forEach(address -> this.addresses.add(new AddressDTO(address)));
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}	
}
