package br.com.rio.maintenance.dataprovider.mapper.request;

import java.util.ArrayList;
import java.util.List;

import br.com.rio.maintenance.dataprovider.repository.entity.VehicleEntity;
import br.com.rio.maintenance.usecase.domain.request.VehicleDomainRequest;

public class VehicleDataProviderMapperRequest {

	
	public static List<VehicleEntity> toListDomain(List<VehicleDomainRequest> request){
		List<VehicleEntity> userEntitys = new ArrayList<>();
		
		request.forEach(jorney -> {
			userEntitys.add(toDomain(jorney));
		});
		
		return userEntitys;
	}
	public static VehicleEntity toDomain(VehicleDomainRequest request){
		
		return VehicleEntity.builder()
									.active(request.getActive())
									.snapshotDateTime(request.getSnapshotDateTime())
									.totalDistanceTrip(request.getTotalDistanceTrip())
									.totalEngineHours(request.getTotalEngineHours())
									.totalFuelUsed(request.getTotalFuelUsed())
									.vin(request.getVin())
								.build();
	}

}