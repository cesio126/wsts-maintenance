package br.com.rio.maintenance.dataprovider.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rio.maintenance.dataprovider.mapper.request.LoginDataProviderMapperRequest;
import br.com.rio.maintenance.dataprovider.mapper.response.LoginDataproviderMapperResponse;
import br.com.rio.maintenance.dataprovider.repository.ILoginRepository;
import br.com.rio.maintenance.dataprovider.repository.entity.LoginEntity;
import br.com.rio.maintenance.usecase.domain.request.LoginDomainRequest;
import br.com.rio.maintenance.usecase.domain.response.LoginDomainResponse;
import br.com.rio.maintenance.usecase.gateway.ILoginGateway;

@Component
public class LoginDataProvider implements ILoginGateway {

	@Autowired
	private ILoginRepository repository;
	
	public List<LoginDomainResponse> getAll(){
		return LoginDataproviderMapperResponse.toListDomain(repository.findAll());
	}
	
	public LoginDomainResponse create(LoginEntity entity) {
		return LoginDataproviderMapperResponse.toDomain(repository.save(entity));
	}

	@Override
	public LoginDomainResponse getLogin(LoginDomainRequest request) {
		return LoginDataproviderMapperResponse.toDomain(repository.findByUser(LoginDataProviderMapperRequest.toEntity(request)));
	}

	@Override
	public LoginDomainResponse getLogin(String user) {
		return LoginDataproviderMapperResponse.toDomain(repository.findByUser(user));
	}
}