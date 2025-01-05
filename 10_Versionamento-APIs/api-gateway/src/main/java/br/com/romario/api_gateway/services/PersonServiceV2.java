package br.com.romario.api_gateway.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.romario.api_gateway.data.vo.v2.PersonVOV2;
import br.com.romario.api_gateway.exceptions.ResourceNotFoundException;
import br.com.romario.api_gateway.mappers.Mapper;
import br.com.romario.api_gateway.models.Person;
import br.com.romario.api_gateway.repositories.PersonRepository;

@Service
public class PersonServiceV2 {
	
	private Logger logger =  Logger.getLogger(PersonServiceV2.class.getName());
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<PersonVOV2> findAll() {
		logger.info("Finding all people");	
		
		List<PersonVOV2> people = Mapper.parseListObjects(personRepository.findAll(), PersonVOV2.class);
		
		if(people.isEmpty()) throw new ResourceNotFoundException("No records found");
		return people;
	}

	public PersonVOV2 findById(Long id) {
		logger.info("Finding one person");	
		
		var entity =  personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID " + id));
		return Mapper.parseObject(entity, PersonVOV2.class);
	}
	
	public PersonVOV2 create(PersonVOV2 person) {
		logger.info("Creating one person");
		
		var entity = Mapper.parseObject(person, Person.class);
		var vo = Mapper.parseObject(personRepository.save(entity), PersonVOV2.class);	
		return vo;
	}
	
	public PersonVOV2 update(PersonVOV2 person) {
		logger.info("Updating one person");
		
		var entity = Mapper.parseObject(findById(person.getId()),  Person.class);
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		entity.setBirthday(person.getbirthday());
		
		var vo = Mapper.parseObject(personRepository.save(entity), PersonVOV2.class);
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person");
		
		var entity = Mapper.parseObject(findById(id),  Person.class);
		personRepository.delete(entity);
	}
}
