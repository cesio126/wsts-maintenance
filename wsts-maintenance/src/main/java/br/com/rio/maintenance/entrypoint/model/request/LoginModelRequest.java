package br.com.rio.maintenance.entrypoint.model.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginModelRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean active;
	private String user;	
	private String pass;	
	private String role;
}