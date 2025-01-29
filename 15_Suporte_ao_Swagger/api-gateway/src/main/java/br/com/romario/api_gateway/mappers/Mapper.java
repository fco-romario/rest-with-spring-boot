package br.com.romario.api_gateway.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.romario.api_gateway.data.vo.v1.BookVO;
import br.com.romario.api_gateway.data.vo.v1.PersonVO;
import br.com.romario.api_gateway.models.Book;
import br.com.romario.api_gateway.models.Person;

//import com.github.dozermapper.core.DozerBeanMapperBuilder;
//import com.github.dozermapper.core.Mapper;

public class Mapper {
	
	//private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	private static ModelMapper mapper = new ModelMapper();
	
	static { //O código dentro do bloco static será executado uma única vez, quando a classe for carregada.
		mapper.createTypeMap(Person.class, PersonVO.class).addMapping(Person::getId, PersonVO::setKey);
		mapper.createTypeMap(PersonVO.class, Person.class).addMapping(PersonVO::getKey, Person::setId);
		mapper.createTypeMap(Book.class, BookVO.class).addMapping(Book::getId, BookVO::setKey);
		mapper.createTypeMap(BookVO.class, Book.class).addMapping(BookVO::getKey, Book::setId);
	}
	
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parseListObjects(List<O> origins, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for(O o: origins) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}
}
