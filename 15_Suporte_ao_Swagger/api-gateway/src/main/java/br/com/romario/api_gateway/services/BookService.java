package br.com.romario.api_gateway.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.romario.api_gateway.controllers.BookController;
import br.com.romario.api_gateway.data.vo.v1.BookVO;
import br.com.romario.api_gateway.exceptions.RequiredObjectIsNullException;
import br.com.romario.api_gateway.exceptions.ResourceNotFoundException;
import br.com.romario.api_gateway.mappers.Mapper;
import br.com.romario.api_gateway.models.Book;
import br.com.romario.api_gateway.repositories.BookRepository;

@Service
public class BookService {
	
	private Logger logger =  Logger.getLogger(BookService.class.getName());
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<BookVO> findAll() {
		logger.info("Finding all books");	
		
		List<BookVO> people = Mapper.parseListObjects(bookRepository.findAll(), BookVO.class);
		
		if(people.isEmpty()) throw new ResourceNotFoundException("No records found");
		
		people
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return people;
	}

	public BookVO findById(Long id) {
		logger.info("Finding one book");	
		
		var entity =  bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID " + id));
		
		BookVO vo = Mapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public BookVO create(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one book");
		var entity = Mapper.parseObject(book, Book.class);
		var vo = Mapper.parseObject(bookRepository.save(entity), BookVO.class);	
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public BookVO update(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one book");		
		var entity = bookRepository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID " + book.getKey()));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = Mapper.parseObject(bookRepository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one book");
		
		var entity = Mapper.parseObject(findById(id),  Book.class);
		bookRepository.delete(entity);
	}
}
