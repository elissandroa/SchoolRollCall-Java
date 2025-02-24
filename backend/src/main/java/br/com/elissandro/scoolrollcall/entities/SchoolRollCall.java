package br.com.elissandro.scoolrollcall.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_school_roll_call")
public class SchoolRollCall implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean presence;
	
	@OneToOne
	@JoinColumn(name = "classroom_id")
	private ClassRoom classRoom;
	
	@ManyToMany
	@JoinTable(name = "tb_school_roll_call_student",
		joinColumns = @JoinColumn(name = "school_roll_call_id"),
		inverseJoinColumns = @JoinColumn(name = "student_id")
	)
	private Set<Student> students = new HashSet<>();
	
	public SchoolRollCall() {
	}
	
	public SchoolRollCall(Long id, Boolean presence, ClassRoom classRoom) {
		this.id = id;
		this.presence = presence;
		this.classRoom = classRoom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getPresence() {
		return presence;
	}

	public void setPresence(Boolean presence) {
		this.presence = presence;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public Set<Student> getStudents() {
		return students;
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
		SchoolRollCall other = (SchoolRollCall) obj;
		return Objects.equals(id, other.id);
	}

}
