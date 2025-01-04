package br.com.romario.api_gateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.romario.api_gateway.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
