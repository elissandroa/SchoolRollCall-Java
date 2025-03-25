package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

	@Query(value = """
		    SELECT obj FROM Tutor obj 
		    JOIN FETCH obj.addresses 
		    JOIN FETCH obj.students
		    WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%'))
		    """,
		    countQuery = """
		    SELECT COUNT(obj) FROM Tutor obj 
		    JOIN obj.addresses 
		    JOIN obj.students
		    WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%'))
		    """)
		Page<Tutor> searchAllTutors(@Param("name") String name, Pageable pageable);
}
