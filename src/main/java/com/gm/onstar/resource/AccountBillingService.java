package com.gm.onstar.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gm.onstar.model.OnStarProfileSubscription;
import com.google.gson.Gson;


@Service
public class AccountBillingService {


	@Autowired
	RestTemplate restTemplate;
	
	public OnStarProfileSubscription getUsersDetails() throws JsonParseException, JsonMappingException, IOException{
		Gson gson = new Gson();
		HttpHeaders headers = new HttpHeaders();
		String onSiteUrl="http://localhost:2131/getDataFromSubscription";
		HttpEntity<?>  entity = new HttpEntity<>(headers);
		ResponseEntity<String> onStarResponseEntity = null;
		try
		{
			onStarResponseEntity = restTemplate.exchange(onSiteUrl, HttpMethod.GET, entity, String.class);
		}catch(Exception e){
			System.out.println("ERROR#######" +  e.getMessage());
		}	
		OnStarProfileSubscription onStarProfileSubscription = gson.fromJson(onStarResponseEntity.getBody(), OnStarProfileSubscription.class);
		return onStarProfileSubscription;
		
	}
}
