package br.com.rio.maintenance.entrypoint.model.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleModelRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean active;
	private String vin;
	private String totalFuelUsed;
	private String totalDistanceTrip;
	private String totalEngineHours;
	private String snapshotDateTime;
}
