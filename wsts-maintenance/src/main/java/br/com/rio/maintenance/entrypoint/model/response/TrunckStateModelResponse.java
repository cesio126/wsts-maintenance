package br.com.rio.maintenance.entrypoint.model.response;

import java.io.Serializable;
import java.util.List;

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
public class TrunckStateModelResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("items")
	private List<ItemsModelResponse> items;
}
