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
public class LiquidFuelConsumptionModelResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("sample_date_time")
	private String sampleDateTime;
	
	@JsonProperty("created_date_time")
	private String createdDateTime;
	
	@JsonProperty("received_date_time")
	private String receivedDateTime;
	
	@JsonProperty("value")
	private String value;
}
