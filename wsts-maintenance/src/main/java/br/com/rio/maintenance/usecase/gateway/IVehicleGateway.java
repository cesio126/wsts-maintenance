package br.com.rio.maintenance.usecase.gateway;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.rio.maintenance.dataprovider.repository.entity.VehicleEntity;
import br.com.rio.maintenance.usecase.domain.response.VehicleDomainResponse;

public interface IVehicleGateway {
	public List<VehicleDomainResponse> getVehicleByVin(String vin, Pageable pageable);
	
	public List<VehicleDomainResponse> getAll(Pageable pageable);
	
	public VehicleDomainResponse create(VehicleEntity vehicle);
}