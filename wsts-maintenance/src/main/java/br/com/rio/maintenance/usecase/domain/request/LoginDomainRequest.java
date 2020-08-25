package br.com.rio.maintenance.usecase.domain.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDomainRequest  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Boolean active;
	private String user;	
	private String pass;	
	private String role;

}