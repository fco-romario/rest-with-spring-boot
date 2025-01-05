package br.com.romario.api_gateway.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.romario.api_gateway.data.vo.v2.PersonVOV2;
import br.com.romario.api_gateway.services.PersonServiceV2;

@RestController
@RequestMapping("/person/v2")
public class PersonControllerV2 {
	
	@Autowired
	private PersonServiceV2 personService;
	
    private static final AtomicLong count = new AtomicLong();
    
    //@PathVariable : Path Parameters é usado quando o parametro é obrigatório. é necesário inserir o "value" -> http://localhost:8080/api/sum/1/2
    //@RequestParam : Query Parameters é usado quando o parametro não são obrigatório -> localhost:8080/api/greeting?name=romario
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<PersonVOV2> findAll() {
    	return personService.findAll();
    }
    
    @GetMapping(value = "/{id}",
    		produces = MediaType.APPLICATION_JSON_VALUE) 
    public PersonVOV2 findById(@PathVariable(value = "id") Long id) {
    	return personService.findById(id);
    }
    
    @PostMapping(
    		produces = MediaType.APPLICATION_JSON_VALUE,
    		consumes = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<PersonVOV2> create(@RequestBody PersonVOV2 person) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }
    
    @PutMapping(
    		produces = MediaType.APPLICATION_JSON_VALUE,
    		consumes = MediaType.APPLICATION_JSON_VALUE) 
    public PersonVOV2 Update(@RequestBody PersonVOV2 person) {
    	return personService.update(person);
    }
    
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
    	personService.delete(id);
    	return ResponseEntity.noContent().build();
    }
}
