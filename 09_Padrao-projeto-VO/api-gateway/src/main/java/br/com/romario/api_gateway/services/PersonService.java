package br.com.romario.api_gateway.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.romario.api_gateway.data.vo.v1.PersonVO;
import br.com.romario.api_gateway.exceptions.ResourceNotFoundException;
import br.com.romario.api_gateway.mapper.DozerMapper;
import br.com.romario.api_gateway.models.Person;
import br.com.romario.api_gateway.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger =  Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people");	
		
		List<PersonVO> people = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		
		if(people.isEmpty()) throw new ResourceNotFoundException("No records found");
		return people;
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person");	
		
		var entity =  personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID " + id));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);	
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person");
		
		var entity = DozerMapper.parseObject(findById(person.getId()),  Person.class);
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person");
		
		var entity = DozerMapper.parseObject(findById(id),  Person.class);
		personRepository.delete(entity);
	}
}
