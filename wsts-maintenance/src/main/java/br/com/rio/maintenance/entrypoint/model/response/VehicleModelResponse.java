package br.com.rio.maintenance.entrypoint.model.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(value = {"active"})
public class VehicleModelResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private Boolean active;
	private String vin;
	private String totalFuelUsed;
	private String totalDistanceTrip;
	private String totalEngineHours;
	private String snapshotDateTime;
}