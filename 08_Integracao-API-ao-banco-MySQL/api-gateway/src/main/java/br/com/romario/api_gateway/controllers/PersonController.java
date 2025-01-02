package br.com.romario.api_gateway.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.romario.api_gateway.models.Person;
import br.com.romario.api_gateway.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
    private static final AtomicLong count = new AtomicLong();
    
    //@PathVariable : Path Parameters é usado quando o parametro é obrigatório. é necesário inserir o "value" -> http://localhost:8080/api/sum/1/2
    //@RequestParam : Query Parameters é usado quando o parametro não são obrigatório -> localhost:8080/api/greeting?name=romario
    
    @RequestMapping(method= RequestMethod.GET,
    		produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<Person> findAll() {
    	return personService.findAll();
    }
    
    @RequestMapping(value = "/{id}",
    		method= RequestMethod.GET,
    		produces = MediaType.APPLICATION_JSON_VALUE) 
    public Person findById(@PathVariable(value = "id") Long id) {
    	return personService.findById(id);
    }
    
    @RequestMapping(method= RequestMethod.POST,
    		produces = MediaType.APPLICATION_JSON_VALUE,
    		consumes = MediaType.APPLICATION_JSON_VALUE) 
    public Person create(@RequestBody Person person) {
    	return personService.create(person);
    }
    
    @RequestMapping(method= RequestMethod.PUT,
    		produces = MediaType.APPLICATION_JSON_VALUE,
    		consumes = MediaType.APPLICATION_JSON_VALUE) 
    public Person Update(@RequestBody Person person) {
    	return personService.update(person);
    }
    
    @RequestMapping(value = "{id}",
    		method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
    	personService.delete(id);
    }
}
