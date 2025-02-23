package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.Discipline;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

}
