package br.com.elissandro.scoolrollcall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.elissandro.scoolrollcall.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


}
