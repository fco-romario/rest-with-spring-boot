package br.com.romario.api_gateway.services;

import org.springframework.stereotype.Service;

import br.com.romario.api_gateway.converters.NumberConverter;
import br.com.romario.api_gateway.exceptions.UnsupportedMathOperationException;

@Service
public class MathService {
	
	public Double sum(String numberOne, String numberTwo) throws UnsupportedMathOperationException {
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
    		throw new UnsupportedMathOperationException("Please set a numeric value");
    	}
        return NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo);
	}
	
	public Double subtraction(String numberOne, String numberTwo) throws UnsupportedMathOperationException {    	
    	if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
    		throw new UnsupportedMathOperationException("Please set a numeric value");
    	}
    	return NumberConverter.convertToDouble(numberOne) - NumberConverter.convertToDouble(numberTwo);
    }
	
	public Double multiplication(String numberOne, String numberTwo) throws UnsupportedMathOperationException {    	
    	if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
    		throw new UnsupportedMathOperationException("Please set a numeric value");
    	}
    	return NumberConverter.convertToDouble(numberOne) * NumberConverter.convertToDouble(numberTwo);
    }  
	
	public Double division(String numberOne, String numberTwo) throws UnsupportedMathOperationException {    	
    	if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
    		throw new UnsupportedMathOperationException("Please set a numeric value");
    	}
    	return NumberConverter.convertToDouble(numberOne) / NumberConverter.convertToDouble(numberTwo);
    }
}
