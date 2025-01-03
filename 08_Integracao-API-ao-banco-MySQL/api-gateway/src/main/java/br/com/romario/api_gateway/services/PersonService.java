package br.com.romario.api_gateway.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.romario.api_gateway.exceptions.ResourceNotFoundException;
import br.com.romario.api_gateway.models.Person;
import br.com.romario.api_gateway.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger =  Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> findAll() {
		logger.info("Finding all people");	
		
		List<Person> people = personRepository.findAll();
		
		if(people.isEmpty()) throw new ResourceNotFoundException("No records found");
		
		return people;
	}

	public Person findById(Long id) {
		logger.info("Finding one person");	
		
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID " + id));
	}
	
	public Person create(Person person) {
		logger.info("Creating one person");
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person");
		
		var entity = findById(person.getId());
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return personRepository.save(person);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person");
		
		var entity = findById(id);
		personRepository.delete(entity);
	}
}
