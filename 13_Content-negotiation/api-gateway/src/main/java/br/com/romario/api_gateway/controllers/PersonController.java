package br.com.romario.api_gateway.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.romario.api_gateway.data.vo.v1.PersonVO;
import br.com.romario.api_gateway.services.PersonService;
import br.com.romario.api_gateway.util.MediaType;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
    private static final AtomicLong count = new AtomicLong();
    
    //@PathVariable : Path Parameters é usado quando o parametro é obrigatório. é necesário inserir o "value" -> http://localhost:8080/api/sum/1/2
    //@RequestParam : Query Parameters é usado quando o parametro não são obrigatório -> localhost:8080/api/greeting?name=romario
    
    @GetMapping(produces =  { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }) 
    public List<PersonVO> findAll() {
    	return personService.findAll();
    }
    
    @GetMapping(value = "/{id}",
    		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }) 
    public PersonVO findById(@PathVariable(value = "id") Long id) {
    	return personService.findById(id);
    }
    
    @PostMapping(
    		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
    		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }) 
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }
    
    @PutMapping(
    		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
    		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }) 
    public PersonVO Update(@RequestBody PersonVO person) {
    	return personService.update(person);
    }
    
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
    	personService.delete(id);
    	return ResponseEntity.noContent().build();
    }
}