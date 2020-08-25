package br.com.rio.maintenance.usefull;

import java.io.IOException;
import java.sql.SQLException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionFactory {
	
	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(Exception ex) {
		//TODO
		//ModelAndView model = new ModelAndView("Erro de manipula��o da sa�da de dados.");
		ModelAndView model = new ModelAndView("Output Error");

		model.addObject("exception", ex.getMessage());
		
		return model;
	}
	
	@ExceptionHandler(SQLException.class)
	public ModelAndView handleSQLException(Exception ex) {
		//TODO
		//ModelAndView model = new ModelAndView("Erro de banco.");
		ModelAndView model = new ModelAndView("DB Error.");

		model.addObject("exception", ex.getMessage());
		
		return model;
	}
	
	@ExceptionHandler(JsonGenerationException.class)
	public ModelAndView handleJsonGenerationException(Exception ex) {
		//TODO
		//ModelAndView model = new ModelAndView("Erro de gera��o de Json");
		ModelAndView model = new ModelAndView("JSON Error.");

		model.addObject("exception", ex.getMessage());
		
		return model;
	}
	
	@ExceptionHandler(JsonMappingException.class)
	public ModelAndView handleJsonMappingException(Exception ex) {
		ModelAndView model = new ModelAndView("Erro de mapeamento de Json.");

		model.addObject("exception", ex.getMessage());
		
		return model;
	}
}
