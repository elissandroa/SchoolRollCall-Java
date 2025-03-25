package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.SchoolTest;

@Repository
public interface SchoolTestRepository extends JpaRepository<SchoolTest, Long> {
	
	@Query(value = "SELECT obj FROM SchoolTest obj JOIN FETCH obj.discipline", 
			countQuery = "SELECT count(obj) FROM SchoolTest obj JOIN obj.discipline")
	Page<SchoolTest> searchAllSchoolTests(Pageable pageable);
}
