package br.com.rio.maintenance.entrypoint.controller.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import br.com.rio.maintenance.dataprovider.repository.IVehicleRepository;
import br.com.rio.maintenance.dataprovider.repository.entity.VehicleEntity;
import br.com.rio.maintenance.entrypoint.controller.VehicleController;
import br.com.rio.maintenance.usecase.VehicleUseCase;
import br.com.rio.maintenance.usecase.domain.response.VehicleDomainResponse;
import br.com.rio.maintenance.usecase.gateway.IVehicleGateway;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Mock
	private VehicleUseCase usecase;
	
	@MockBean
	private RestTemplate restTemplate;
	
	@MockBean
	private IVehicleRepository repository;
	
	@MockBean
	private IVehicleGateway gateway;
	
	@InjectMocks
	private VehicleController controller;
	
	@BeforeEach
	public void setup() {
//		when(repository.findByVin(Mockito.anyString(), Mockito.anyObject())).thenReturn(getVehicleEntity());
//		when(gateway.getVehicleByVin(Mockito.anyString())).thenReturn(getVehicleResponseEntityDomainResponse().getBody());
//		when(usecase.getVehicle(Mockito.anyString())).thenReturn(getVehicleResponseEntityDomainResponse().getBody());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void validCall200OK() throws Exception {
		when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(), Mockito.any(ParameterizedTypeReference.class)))
			.thenReturn(getVehicleResponseEntityDomainResponse());
		
		ResultActions result = this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/vehicle", Mockito.anyString()));
			assertNotNull(result);
				result.andExpect(status().is2xxSuccessful())
					 .andExpect(MockMvcResultMatchers.jsonPath("$[0].active", Matchers.is(true)))
					 .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("9536T8274FR501273")))
					 .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalFuelUsed", Matchers.is("50602.5")))
					 .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalDistanceTrip", Matchers.is("174167.609375")))
					 .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalEngineHours", Matchers.is("3595.25")))
					 .andExpect(MockMvcResultMatchers.jsonPath("$[0].snapshotDateTime", Matchers.is("2019-06-11T19:41:00Z")));
	}
	
	@Test
	public void validRepository() throws Exception {
//		List<VehicleDomainResponse> entity = usecase.getVehicle(Mockito.anyString());
//		
//		assertThat(entity.equals(getVehicleResponseEntityDomainResponse().getBody()));
	}
	
	public List<VehicleEntity> getVehicleEntity() {
		List<VehicleEntity> entities = new ArrayList<VehicleEntity>();		

		entities.add(
				VehicleEntity.builder()
				.active(true)
				.snapshotDateTime("2019-06-11T19:41:00Z")
				.totalDistanceTrip("174167.609375")
				.totalEngineHours("3595.25")
				.totalFuelUsed("50602.5")
				.vin("9536T8274FR501273")
			.build());
		
		entities.add(
				VehicleEntity.builder()
				.active(true)
				.snapshotDateTime("2019-06-10T18:46:17.972Z")
				.totalDistanceTrip("1234.5")
				.totalEngineHours("280")
				.totalFuelUsed("350")
				.vin("9536T8274FR501273")
			.build());
		
		return entities;
	}
	
	public ResponseEntity<List<VehicleDomainResponse>> getVehicleResponseEntityDomainResponse() {
		List<VehicleDomainResponse> entities = new ArrayList<VehicleDomainResponse>();		

		entities.add(
				VehicleDomainResponse.builder()
				.active(true)
				.snapshotDateTime("2019-06-11T19:41:00Z")
				.totalDistanceTrip("174167.609375")
				.totalEngineHours("3595.25")
				.totalFuelUsed("50602.5")
				.vin("9536T8274FR501273")
			.build());
		
		entities.add(
				VehicleDomainResponse.builder()
				.active(true)
				.snapshotDateTime("2019-06-10T18:46:17.972Z")
				.totalDistanceTrip("1234.5")
				.totalEngineHours("280")
				.totalFuelUsed("350")
				.vin("9536T8274FR501273")
			.build());
		
		return ResponseEntity.ok(entities);
	}
}