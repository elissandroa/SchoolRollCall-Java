package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.Graduation;

@Repository
public interface GraduationRepository extends JpaRepository<Graduation, Long> {

}
