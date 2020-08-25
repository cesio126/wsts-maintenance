package br.com.rio.maintenance.dataprovider.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.rio.maintenance.dataprovider.mapper.response.VehicleDataproviderMapperResponse;
import br.com.rio.maintenance.dataprovider.repository.IVehicleRepository;
import br.com.rio.maintenance.dataprovider.repository.entity.VehicleEntity;
import br.com.rio.maintenance.usecase.domain.response.VehicleDomainResponse;
import br.com.rio.maintenance.usecase.gateway.IVehicleGateway;

@Component
public class VehicleDataProvider implements IVehicleGateway {

	@Autowired
	private IVehicleRepository repository;
	
	public List<VehicleDomainResponse> getVehicleByVin(String vin, Pageable pageable){
		return VehicleDataproviderMapperResponse.toListDomain(repository.findByVin(vin, pageable).getContent(), repository.findByVin(vin, pageable).getTotalElements());
	}
	
	public List<VehicleDomainResponse> getAll(Pageable pageable){
		return VehicleDataproviderMapperResponse.toListDomain(repository.findAll(pageable).getContent(), repository.findAll(pageable).getTotalElements());
	}
	
	public VehicleDomainResponse create(VehicleEntity entity) {
		return VehicleDataproviderMapperResponse.toDomain(repository.save(entity), 0);
	}
}