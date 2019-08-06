package com.gm.onstar.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gm.onstar.model.OnStarProfileSubscription;

@Controller
public class UserResource {

    @Autowired
    private KafkaTemplate<String, OnStarProfileSubscription> kafkaTemplate;
    @Autowired
    AccountBillingService service;
    private static final String TOPIC = "OnStarService";

    @RequestMapping(value = "/onstarpublish", method = RequestMethod.GET)
    public String post() throws JsonParseException, JsonMappingException, IOException {
    	OnStarProfileSubscription test = service.getUsersDetails();
        kafkaTemplate.send(TOPIC, test);

        return "Published OnsStarsuccessfully";
    }
}
