package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.SchoolTest;

@Repository
public interface SchoolTestRepository extends JpaRepository<SchoolTest, Long> {

}
