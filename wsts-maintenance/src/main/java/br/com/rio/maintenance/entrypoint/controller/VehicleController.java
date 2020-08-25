package br.com.rio.maintenance.entrypoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rio.maintenance.commons.model.PageDataModelResponse;
import br.com.rio.maintenance.dataprovider.mapper.response.VehicleDataproviderMapperResponse;
import br.com.rio.maintenance.entrypoint.model.response.VehicleModelResponse;
import br.com.rio.maintenance.usecase.VehicleUseCase;
import br.com.rio.maintenance.usecase.domain.response.VehicleDomainResponse;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:8083", maxAge = 3600)
@RestController
@RequestMapping("/vehicle")
@Slf4j
@Validated
public class VehicleController {

	@Autowired
	private VehicleUseCase useCase;

	@GetMapping(value = {"", "/{vin}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getVehiclesByVin(@PathVariable(name = "vin", required = false) String vin, 
											  @RequestParam("field") String field,
											  @RequestParam("page") int page) {
		log.info("Get vehicle.");
		
		if (StringUtils.isEmpty(field) || page <= 0) {
			return new ResponseEntity<String>("page have be necessarily bigger 0 and field can't be empty", HttpStatus.BAD_REQUEST);
		}
		
		List<VehicleDomainResponse> vehicleDomainResponse = useCase.getVehicle(vin, field, page - 1);
		
		List<VehicleModelResponse> vehicles = VehicleDataproviderMapperResponse.toListModel(vehicleDomainResponse);

		return ResponseEntity.ok(PageDataModelResponse.builder()
														 .totalRegisters(vehicleDomainResponse.size() > 0 ? vehicleDomainResponse.get(0).getTotalRegisters() : 0)
														 .vehicle(vehicles)
													 .build());
	}
}