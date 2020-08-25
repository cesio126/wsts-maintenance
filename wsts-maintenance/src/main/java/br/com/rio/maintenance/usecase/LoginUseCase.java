package br.com.rio.maintenance.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rio.maintenance.dataprovider.mapper.request.LoginDataProviderMapperRequest;
import br.com.rio.maintenance.usecase.domain.request.LoginDomainRequest;
import br.com.rio.maintenance.usecase.domain.response.LoginDomainResponse;
import br.com.rio.maintenance.usecase.gateway.ILoginGateway;

@Component
public class LoginUseCase {

	@Autowired
	private ILoginGateway gateway;

	public LoginDomainResponse getLogin(LoginDomainRequest request) {
        return gateway.getLogin(request);
    }
	
	public LoginDomainResponse getLoginByUser(String user) {
        return gateway.getLogin(user);
    }
    
    public LoginDomainResponse create(LoginDomainRequest request) {
    	return gateway.create(LoginDataProviderMapperRequest.toEntity(request));
    }
}