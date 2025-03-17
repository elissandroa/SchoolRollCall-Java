package br.com.elissandro.scoolrollcall.dto;

import br.com.elissandro.scoolrollcall.entities.Address;
import jakarta.validation.constraints.NotBlank;

public class AddressDTO {

	private Long id;
	@NotBlank(message = "Campo obrigatório")
	private String street;
	@NotBlank(message = "Campo obrigatório")
	private String num;
	@NotBlank(message = "Campo obrigatório")
	private String neighborhood;
	@NotBlank(message = "Campo obrigatório")
	private String city;
	@NotBlank(message = "Campo obrigatório")
	private String state;
	private String zipCode;
	
	public AddressDTO() {
	}
	
	public AddressDTO(Long id, String street, String num, String neighborhood, String city, String state, String zipCode) {
		this.id = id;
		this.street = street;
		this.num = num;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	public AddressDTO(Address entity) {
		id = entity.getId();
		street = entity.getStreet();
		num = entity.getNum();
		neighborhood = entity.getNeighborhood();
		city = entity.getCity();
		state = entity.getState();
		zipCode = entity.getZipCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}

