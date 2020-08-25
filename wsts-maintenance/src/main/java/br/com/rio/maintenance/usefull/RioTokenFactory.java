package br.com.rio.maintenance.usefull;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RioTokenFactory {

	private RestTemplate restTemplate;

	@Autowired
	public RioTokenFactory(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public String getTokenInRioByUserAndPass(String user, String pass, String urlauthrio) throws RestClientException, JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(user, pass);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		return new JSONObject(restTemplate.exchange(urlauthrio, HttpMethod.POST, request, String.class).getBody()).getString("access_token");
	}

	public String getTokenInRio(String client_id, String client_secret, String urlauthrio) throws RestClientException, JSONException {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "partner_integration");
		body.add("integration_id", "060b1669-2472-406f-8504-de50deda37da");
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(client_id, client_secret);
		headers.add("Accept", "application/x-www-form-urlencoded");
		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
		return new JSONObject(restTemplate.exchange(urlauthrio, HttpMethod.POST, entity, String.class).getBody()).getString("access_token");
	}
}
