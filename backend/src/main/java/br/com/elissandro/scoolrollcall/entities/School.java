package br.com.elissandro.scoolrollcall.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_school")
public class School implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant created_at;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updated_at;

	@ManyToMany
	@JoinTable(name = "tb_school_address", joinColumns = @JoinColumn(name = "school_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private Set<Address> address = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "tb_school_classroom", joinColumns = @JoinColumn(name = "school_id"), inverseJoinColumns = @JoinColumn(name = "classroom_id"))
	private Set<ClassRoom> classRooms = new HashSet<>();

	public School() {
	}

	public School(Long id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Address> getAddress() {
		return address;
	}
	
	public Set<ClassRoom> getClassRooms() {
		return classRooms;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public Instant getUpdated_at() {
		return updated_at;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@PrePersist
	public void prePersist() {
		created_at = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {
		updated_at = Instant.now();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		return Objects.equals(id, other.id);
	}

}
