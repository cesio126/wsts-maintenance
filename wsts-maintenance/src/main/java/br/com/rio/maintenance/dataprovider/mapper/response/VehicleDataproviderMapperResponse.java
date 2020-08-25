package br.com.rio.maintenance.dataprovider.mapper.response;

import java.util.ArrayList;
import java.util.List;

import br.com.rio.maintenance.dataprovider.repository.entity.VehicleEntity;
import br.com.rio.maintenance.entrypoint.model.response.VehicleModelResponse;
import br.com.rio.maintenance.usecase.domain.response.VehicleDomainResponse;

public class VehicleDataproviderMapperResponse {

	
	public static List<VehicleDomainResponse> toListDomain(List<VehicleEntity> entities, long l){
		List<VehicleDomainResponse> VehicleDomainResponses = new ArrayList<>();
		
		entities.forEach(entity -> {
			VehicleDomainResponses.add(toDomain(entity, l));
		});
		
		return VehicleDomainResponses;
	}
	public static VehicleDomainResponse toDomain(VehicleEntity entity, long totalRegisters){
		
		return VehicleDomainResponse.builder()
									.active(entity.getActive())
									.snapshotDateTime(entity.getSnapshotDateTime())
									.totalDistanceTrip(entity.getTotalDistanceTrip())
									.totalEngineHours(entity.getTotalEngineHours())
									.totalFuelUsed(entity.getTotalFuelUsed())
									.vin(entity.getVin())
									.totalRegisters(totalRegisters)
								.build();
	}
	public static VehicleModelResponse toModel(VehicleDomainResponse domain){
		
		return VehicleModelResponse.builder()
									.active(domain.getActive())
									.snapshotDateTime(domain.getSnapshotDateTime())
									.totalDistanceTrip(domain.getTotalDistanceTrip())
									.totalEngineHours(domain.getTotalEngineHours())
									.totalFuelUsed(domain.getTotalFuelUsed())
									.vin(domain.getVin())
								.build();
	}
	public static List<VehicleModelResponse> toListModel(List<VehicleDomainResponse> models){
		List<VehicleModelResponse> VehicleDomainResponses = new ArrayList<>();
		
		models.forEach(model -> {
			VehicleDomainResponses.add(toModel(model));
		});
		
		return VehicleDomainResponses;
	}
}
