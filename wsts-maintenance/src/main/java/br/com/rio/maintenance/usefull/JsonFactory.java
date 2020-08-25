package br.com.rio.maintenance.usefull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;

public class JsonFactory {

	ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

	public static String createJson(String message, HttpStatus status) throws JsonGenerationException, JsonMappingException, IOException {
		
		if (message != null && status != null) {
			Map<String, Object> fileInfo = new HashMap<>();
			fileInfo.put("return_message",message);
			fileInfo.put("return_status", status.value());
			
			return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(fileInfo);
		}
		
		return null;
	}

	public static String createJsonList(String message, HttpStatus status, List<?> json) throws JsonGenerationException, JsonMappingException, IOException {
		
		if (message != null && status != null) {
			Map<String, Object> fileInfo = new HashMap<>();
			fileInfo.put("return_message",message);
			fileInfo.put("return_status", status.value());
			fileInfo.put(json.get(0).getClass().getSimpleName().toLowerCase(), json);
			
			return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(fileInfo);
		}
		
		return null;
	}

	public static String createJsonEntity(String message, HttpStatus status, Object body) throws JsonGenerationException, JsonMappingException, IOException {
		
		if (message != null && status != null) {
			Map<String, Object> fileInfo = new HashMap<>();
			fileInfo.put("return_message",message);
			fileInfo.put("return_status", status.value());
			fileInfo.put(body.getClass().getSimpleName(), body);
			
			return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(fileInfo);
		}
		
		return null;
	}


	public static String createJsonCustom(String message, HttpStatus status, List<Map<?, ?>> regFormatados, String key) throws JsonGenerationException, JsonMappingException, IOException {
		
		if (message != null && status != null) {
			Map<String, Object> fileInfo = new HashMap<>();
			fileInfo.put("return_message",message);
			fileInfo.put("return_status", status.value());
			fileInfo.put(key.toLowerCase(), regFormatados);
			
			return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(fileInfo);
		}
		
		return null;
	}
}
