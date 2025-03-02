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
@Table(name = "tb_student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToMany
	@JoinTable(name = "tb_student_tutor", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "tutor_id"))
	private Set<Tutor> tutors = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "tb_student_school_test", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "school_test_id"))
	private Set<SchoolTest> schoolTests = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "tb_student_instrument", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "instrument_id"))
	private Set<Instrument> instruments = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "tb_student_address", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private Set<Address> addresses = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "tb_student_graduation", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "graduation_id"))
	private Set<Graduation> graduations = new HashSet<>();

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;

	public Student() {
	}

	public Student(Long id, String name) {
		this.id = id;
		this.name = name;
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

	public Set<Tutor> getTutors() {
		return tutors;
	}
	
	public Set<SchoolTest> getSchoolTests() {
		return schoolTests;
	}

	public Set<Instrument> getInstruments() {
		return instruments;
	}
	
	public Set<Address> getAddresses() {
		return addresses;
	}
	
	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
	
	public Set<Graduation> getGraduations() {
		return graduations;
	}

	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
		updatedAt = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
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
		Student other = (Student) obj;
		return Objects.equals(id, other.id);
	}

}
