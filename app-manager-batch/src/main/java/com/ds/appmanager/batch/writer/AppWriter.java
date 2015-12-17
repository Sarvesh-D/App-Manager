package com.ds.appmanager.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.ds.appmanager.model.ApplicationView;

public class AppWriter implements ItemWriter<ApplicationView> {
	
	private RestTemplate restTemplate;
	
	public static final String URL = "http://localhost:8081/app-manager-services/applications";
	
	public AppWriter() {
		setRestTemplate(new RestTemplate());
		getRestTemplate().getMessageConverters().add(new MappingJacksonHttpMessageConverter());
	}
	
	@Override
	public void write(List<? extends ApplicationView> items) throws Exception {
		for (ApplicationView applicationView : items) {
			restTemplate.postForLocation(URL, applicationView);
		}
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	public void setRestTemplate(RestTemplate restClient) {
		this.restTemplate = restClient;
	}
	
	
}
