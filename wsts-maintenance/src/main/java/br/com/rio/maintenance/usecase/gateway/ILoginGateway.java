package br.com.rio.maintenance.usecase.gateway;

import br.com.rio.maintenance.dataprovider.repository.entity.LoginEntity;
import br.com.rio.maintenance.usecase.domain.request.LoginDomainRequest;
import br.com.rio.maintenance.usecase.domain.response.LoginDomainResponse;

public interface ILoginGateway {

	public LoginDomainResponse getLogin(LoginDomainRequest request);

	public LoginDomainResponse getLogin(String user);
	
	public LoginDomainResponse create(LoginEntity Login);
}
