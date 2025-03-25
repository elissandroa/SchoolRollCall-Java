package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query(value = """
			SELECT obj FROM Student obj
			JOIN FETCH obj.graduation
			JOIN FETCH obj.instrument
			JOIN FETCH obj.classRoom
			WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%'))
			""", countQuery = """
			SELECT COUNT(obj) FROM Student obj
			JOIN obj.graduation
			JOIN obj.instrument
			JOIN obj.classRoom
			WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%'))
			""")
	Page<Student> searchStudenWidthGraduationInstrumentAndClassRoom(String name, Pageable pageable);
}
