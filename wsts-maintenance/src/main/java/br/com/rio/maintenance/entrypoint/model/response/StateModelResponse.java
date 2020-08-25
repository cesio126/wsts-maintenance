package br.com.rio.maintenance.entrypoint.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StateModelResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("snapshot_date_time")
	private String snapshotDateTime;
	
	@JsonProperty("total_distance_travelled")
	private TotalDistanceTravelledModelResponse totalDistanceTravelled;
	
	@JsonProperty("fuel_consumption")
	private LiquidFuelConsumptionModelResponse liquidFuelConsumption;
	
	@JsonProperty("engine_operation_time")
	private EngineOperationTimeModelResponse engineOperationTime;
}
