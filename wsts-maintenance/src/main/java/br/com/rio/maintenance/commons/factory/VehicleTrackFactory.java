package br.com.rio.maintenance.commons.factory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.rio.maintenance.entrypoint.mapper.VehicleDomainMapper;
import br.com.rio.maintenance.entrypoint.model.response.TrunckStateModelResponse;
import br.com.rio.maintenance.usecase.VehicleUseCase;
import br.com.rio.maintenance.usefull.RioTokenFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VehicleTrackFactory {
	
	@Value("${app.set.url.truckstate}")
	private String url;

	@Value("${app.set.token.barrer}")
	private String barrer;

	@Value("${app.set.rio.client_id}")
	private String client_id;

	@Value("${app.set.rio.client_secret}")
	private String client_secret;

	@Value("${app.set.url.truckstateauth}")
	private String urlauthrio;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private VehicleUseCase useCase;

	public VehicleTrackFactory() {
		super();
	}

	//TODO fazer qualquer coisa aqui...
	@Scheduled(cron = "0 1 1 * * ?")
	@EventListener(ApplicationReadyEvent.class)
	public Boolean getNewTracksScheduled() {
		return updateTracks();
	}
	
	public Boolean getNewTracks(String client_id, String client_secret, String url, RestTemplate restTemplate) {
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.url = url;
		this.restTemplate = restTemplate;
		
		return updateTracks();
	}
	
	private Boolean updateTracks() {

		log.info("Schedule update vehicles the task has executed successfully " + new Date());
		URI uri;
		ResponseEntity<TrunckStateModelResponse> result = null;
		try {
			uri = new URI(url);
			RioTokenFactory factory = new RioTokenFactory(restTemplate);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + factory.getTokenInRio(client_id, client_secret, urlauthrio));
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
			
			result = restTemplate.exchange(uri, HttpMethod.GET, entity, TrunckStateModelResponse.class);
			if (result.getStatusCode().is2xxSuccessful()) {
				VehicleDomainMapper.fromResponseTruckState(result.getBody()).forEach(dom -> {
					useCase.create(dom);
				});
			}
		} catch (URISyntaxException e) {
			log.error(e.getMessage());
			return false;
		} catch (RestClientException e) {
			log.error(e.getMessage());
			return false;
		} catch (JSONException e) {
			log.error(e.getMessage());
			return false;
		}
		
		log.info(result.getStatusCode().name());
		return true;
	}
}
