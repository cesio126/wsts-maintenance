package br.com.rio.maintenance.entrypoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.rio.maintenance.commons.factory.VehicleTrackFactory;
import br.com.rio.maintenance.dataprovider.mapper.request.LoginDataProviderMapperRequest;
import br.com.rio.maintenance.entrypoint.model.request.LoginModelRequest;
import br.com.rio.maintenance.usecase.LoginUseCase;
import br.com.rio.maintenance.usecase.domain.response.LoginDomainResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/login")
@Slf4j
@Validated
public class LoginController {
	
	@Value("${app.set.url.truckstate}")
	private String url;

	@Autowired
	private LoginUseCase useCase;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLogin(@RequestParam("user") String user,
											  @RequestParam("pass") String pass) {
		log.info("Get Login.");
		
		if (StringUtils.isEmpty(user) || StringUtils.isEmpty(pass)) {
			return new ResponseEntity<String>("user and pass can't be empty.", HttpStatus.BAD_REQUEST);
		}
		
		VehicleTrackFactory factory = new VehicleTrackFactory();
		
		if (factory.getNewTracks(user, pass, url, restTemplate)) {
			log.info("Trucks were updated.");
		}
		
		LoginDomainResponse LoginDomainResponse = useCase.getLoginByUser(user);

		return ResponseEntity.ok(LoginDomainResponse);
	}
	
	@PostMapping(value = {"/create"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestParam("user") String user,
			  @RequestParam("pass") String pass, @RequestParam("role") String role) {
		
		log.info("Get Login.");
		
		if (StringUtils.isEmpty(user) || StringUtils.isEmpty(pass)) {
			return new ResponseEntity<String>("user and pass can't be empty.", HttpStatus.BAD_REQUEST);
		}
		
		if (useCase.create(LoginDataProviderMapperRequest.toDomain(LoginModelRequest.builder()
															.active(true)
															.user(user)
															.pass(pass)
															.role(role)
														.build())) != null) {
			return new ResponseEntity<String>("user created sucessful.", HttpStatus.OK);
			
		}else {

			return new ResponseEntity<String>("user can't be created.", HttpStatus.BAD_REQUEST);
		}
	}
}
