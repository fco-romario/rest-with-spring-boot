package br.com.romario.api_gateway.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import br.com.romario.api_gateway.models.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger =  Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll() {
		logger.info("Finding all people");
		
		List<Person> people = new ArrayList<>();
		
		for (int i = 0; i < 8 ; i++) {
			Person person = mockPerson(i);
			people.add(person);
		}
		return people;
	}

	public Person findById(String id) {
		logger.info("Finding one person");
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Romário");
		person.setLastName("Alves");
		person.setAddress("Fortaleza - CE");
		person.setGender("Male");
		
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Creating one person");
		return person;
	}
	
	public Person delete (Person person) {
		logger.info("Creating one person");
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Updating one person");
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting one person");
	}
	
	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person FirstName " + i);
		person.setLastName("Person LastName " + i);
		person.setAddress("Address name " + i);		
		
		person.setGender( (i % 2) == 0 ? "Male" : "Female");
		
		return person;
	}
}
