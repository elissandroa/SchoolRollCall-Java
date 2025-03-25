package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.ClassRoom;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

	@Query(value = "SELECT obj FROM ClassRoom obj JOIN FETCH obj.students",
			countQuery = "SELECT COUNT(obj) FROM ClassRoom obj JOIN obj.students")
	Page<ClassRoom> searchClassRoomWithStudents(Pageable pageable);
}
