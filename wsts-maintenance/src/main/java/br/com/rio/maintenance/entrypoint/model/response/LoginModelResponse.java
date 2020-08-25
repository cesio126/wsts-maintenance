package br.com.rio.maintenance.entrypoint.model.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(value = { "active" })
public class LoginModelResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean active;
	private String user;	
	private String pass;	
	private String role;
}
