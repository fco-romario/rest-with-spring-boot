package br.com.romario.api_gateway.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

//import com.github.dozermapper.core.DozerBeanMapperBuilder;
//import com.github.dozermapper.core.Mapper;

public class Mapper {
	
	//private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	private static ModelMapper mapper = new ModelMapper();
	
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
