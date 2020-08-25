package br.com.rio.maintenance.commons.model;

import java.util.List;

import br.com.rio.maintenance.entrypoint.model.response.VehicleModelResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageDataModelResponse {
	private List<VehicleModelResponse> vehicle;

	private long totalRegisters;
}