package br.com.rio.maintenance.usecase.domain.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleDomainResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private Boolean active;
	private String vin;
	private String totalFuelUsed;
	private String totalDistanceTrip;
	private String totalEngineHours;
	private String snapshotDateTime;
	private long totalRegisters;
}