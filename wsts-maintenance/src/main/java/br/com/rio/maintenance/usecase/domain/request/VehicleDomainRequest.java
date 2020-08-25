package br.com.rio.maintenance.usecase.domain.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleDomainRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private String vin;
	private Boolean active;
	private String totalFuelUsed;
	private String totalDistanceTrip;
	private String totalEngineHours;
	private String snapshotDateTime;
}