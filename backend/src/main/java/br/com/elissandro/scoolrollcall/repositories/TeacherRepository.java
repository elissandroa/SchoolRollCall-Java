package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	@Query(value = """
		    SELECT DISTINCT obj FROM Teacher obj 
		    JOIN FETCH obj.addresses 
		    JOIN FETCH obj.disciplines
		    WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%'))
		    """,
		    countQuery = """
		    SELECT COUNT(obj) FROM Teacher obj 
		    JOIN obj.addresses 
		    JOIN obj.disciplines
		    WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%'))
		    """)
		Page<Teacher> searchAllTeachers(@Param("name") String name, Pageable pageable);

}
