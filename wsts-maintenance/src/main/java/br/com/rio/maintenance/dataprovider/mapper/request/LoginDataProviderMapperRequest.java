package br.com.rio.maintenance.dataprovider.mapper.request;

import java.util.ArrayList;
import java.util.List;

import br.com.rio.maintenance.dataprovider.repository.entity.LoginEntity;
import br.com.rio.maintenance.entrypoint.model.request.LoginModelRequest;
import br.com.rio.maintenance.usecase.domain.request.LoginDomainRequest;

public class LoginDataProviderMapperRequest {
	public static List<LoginEntity> toListEntity(List<LoginDomainRequest> requests){
		List<LoginEntity> userEntitys = new ArrayList<>();
		
		requests.forEach(request -> {
			userEntitys.add(toEntity(request));
		});
		
		return userEntitys;
	}
	public static LoginEntity toEntity(LoginDomainRequest request){
		
		return LoginEntity.builder()
								.id(request.getId())
								.active(request.getActive())
								.pass(request.getPass())
								.user(request.getUser())
								.role(request.getRole())
							.build();
	}
	
	public static List<LoginDomainRequest> toListDomain(List<LoginModelRequest> requests){
		List<LoginDomainRequest> requestDomains = new ArrayList<>();
		
		requests.forEach(request -> {
			requestDomains.add(toDomain(request));
		});
		
		return requestDomains;
	}
	public static LoginDomainRequest toDomain(LoginModelRequest request){
		
		return LoginDomainRequest.builder()
								.id(request.getId())
								.active(request.getActive())
								.pass(request.getPass())
								.user(request.getUser())
								.role(request.getRole())
							.build();
	}
}
