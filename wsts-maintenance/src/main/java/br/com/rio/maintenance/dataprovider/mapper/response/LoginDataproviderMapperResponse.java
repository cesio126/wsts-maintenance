package br.com.rio.maintenance.dataprovider.mapper.response;

import java.util.ArrayList;
import java.util.List;

import br.com.rio.maintenance.dataprovider.repository.entity.LoginEntity;
import br.com.rio.maintenance.entrypoint.model.response.LoginModelResponse;
import br.com.rio.maintenance.usecase.domain.response.LoginDomainResponse;

public class LoginDataproviderMapperResponse {
	
	public static List<LoginDomainResponse> toListDomain(List<LoginEntity> entities){
		List<LoginDomainResponse> LoginDomainResponses = new ArrayList<>();
		
		entities.forEach(entity -> {
			LoginDomainResponses.add(toDomain(entity));
		});
		
		return LoginDomainResponses;
	}
	public static LoginDomainResponse toDomain(LoginEntity entity){
		
		return LoginDomainResponse.builder()
									.id(entity.getId())
									.active(entity.getActive())
									.pass(entity.getPass())
									.user(entity.getUser())
									.role(entity.getRole())
								.build();
	}
	
	public static List<LoginModelResponse> toListModel(List<LoginDomainResponse> models){
		List<LoginModelResponse> LoginDomainResponses = new ArrayList<>();
		
		models.forEach(model -> {
			LoginDomainResponses.add(toModel(model));
		});
		
		return LoginDomainResponses;
	}
	public static LoginModelResponse toModel(LoginDomainResponse domain){
		
		return LoginModelResponse.builder()
									.id(domain.getId())
									.active(domain.getActive())
									.pass(domain.getPass())
									.user(domain.getUser())
									.role(domain.getRole())
								.build();
	}

}
