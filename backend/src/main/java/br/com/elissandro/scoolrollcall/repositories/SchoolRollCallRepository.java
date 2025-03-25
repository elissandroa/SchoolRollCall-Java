package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.SchoolRollCall;

@Repository
public interface SchoolRollCallRepository extends JpaRepository<SchoolRollCall, Long> {

	@Query(value = "SELECT obj FROM SchoolRollCall obj JOIN FETCH obj.student",
			countQuery = "SELECT COUNT(obj) FROM SchoolRollCall obj JOIN obj.student")
	Page<SchoolRollCall> searchAllSchoolRollCallPaged(Pageable pageable);
}
