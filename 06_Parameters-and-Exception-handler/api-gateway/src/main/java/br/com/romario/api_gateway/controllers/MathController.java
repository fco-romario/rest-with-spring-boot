package br.com.romario.api_gateway.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.romario.api_gateway.services.MathService;

@RestController
public class MathController {
	
	@Autowired
	private MathService mathService;
	
    private static final String template = "Hello, %s!";
    private static final AtomicLong count = new AtomicLong();
    
    //@PathVariable : Path Parameters é usado quando o parametro é obrigatório. é necesário inserir o "value" -> http://localhost:8080/api/sum/1/2
    //@RequestParam : Query Parameters é usado quando o parametro não são obrigatório -> localhost:8080/api/greeting?name=romario
    
    @RequestMapping(value = "/api/sum/{numberOne}/{numberTwo}", method= RequestMethod.GET) 
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
    	return mathService.sum(numberOne, numberTwo);
    }
    
    @RequestMapping(value = "/api/subtraction/{numberOne}/{numberTwo}", method= RequestMethod.GET) 
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
    	return mathService.subtraction(numberOne, numberTwo);
    }
    
    @RequestMapping(value = "/api/multiplication/{numberOne}/{numberTwo}", method= RequestMethod.GET) 
    public Double multiplication(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
    	return mathService.multiplication(numberOne, numberTwo);
    }    
    
    @RequestMapping(value = "/api/division/{numberOne}/{numberTwo}", method= RequestMethod.GET) 
    public Double division(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
    	return mathService.division(numberOne, numberTwo);
    }
}
