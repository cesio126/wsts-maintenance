package br.com.rio.maintenance.entrypoint.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.rio.maintenance.entrypoint.model.request.VehicleModelRequest;
import br.com.rio.maintenance.entrypoint.model.response.TrunckStateModelResponse;
import br.com.rio.maintenance.usecase.domain.request.VehicleDomainRequest;

public class VehicleDomainMapper {
	private VehicleDomainMapper() {
		
	}

	public static VehicleDomainRequest fromDomain(VehicleModelRequest request) {
		return VehicleDomainRequest.builder()
										 .active(request.getActive())
										 .snapshotDateTime(request.getSnapshotDateTime())
										 .totalDistanceTrip(request.getTotalDistanceTrip())
										 .totalEngineHours(request.getTotalEngineHours())
										 .totalFuelUsed(request.getTotalEngineHours())
										 .vin(request.getVin())
									 .build();
	}
	
	public static List<VehicleDomainRequest> fromResponseTruckState(TrunckStateModelResponse responseTruckState){
		List<VehicleDomainRequest> vehicles = new ArrayList<VehicleDomainRequest>();
		
		responseTruckState.getItems().forEach(item -> {
			vehicles.add(VehicleDomainRequest.builder()
									.active(true)
									.snapshotDateTime(item.getState().getSnapshotDateTime() != null ? item.getState().getSnapshotDateTime() : "")
									.totalDistanceTrip(item.getState().getTotalDistanceTravelled() != null ? item.getState().getTotalDistanceTravelled().getValue().toString() : "")
									.totalEngineHours(item.getState().getEngineOperationTime() != null ? item.getState().getEngineOperationTime().getValue().toString() : "")
									.totalFuelUsed(item.getState().getLiquidFuelConsumption() != null ? item.getState().getLiquidFuelConsumption().getValue().toString() : "")
									.vin(item.getIdTruckState())
								.build());
		});
		
		return vehicles;
	}
}
