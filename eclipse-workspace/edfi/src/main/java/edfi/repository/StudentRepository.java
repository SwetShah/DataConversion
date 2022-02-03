package edfi.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor(onConstructor = @__({ @Autowired }))
public class StudentRepository {

	private RestTemplate restTemplate;
	final private String TOKEN_URL = "https://edfisb.ped.state.nm.us/v5.3.0_NM_Sandbox/api/oauth/token";
	final private String DATA_URL = "https://edfisb.ped.state.nm.us:443/v5.3.0_NM_Sandbox/api/data/v3/ed-fi/students";

	public String getAuthenticationToken(String clientId, String clientSecret) {

		String token = "";
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(TOKEN_URL);
			Map<String, String> map = new HashMap<>();
			map.put("client_id", clientId);
			map.put("client_secret", clientSecret);

			ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), map, String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Request to fetch the token failed");
			e.printStackTrace();
		}
		return token;
	}

	public String pushDataToODS(String token, String json) {

		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(DATA_URL);

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);

			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, String> map = new HashMap<>();
			map.put("data", json);

			HttpEntity requestEntity = new HttpEntity<>(map, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), map, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				return "Data push successfull";
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("Failed to push data to ODS");
			e.printStackTrace();
		}

		return "";

	}

}
