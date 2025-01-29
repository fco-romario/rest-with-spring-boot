package br.com.romario.api_gateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.romario.api_gateway.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
