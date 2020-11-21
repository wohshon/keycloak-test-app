package com.redhat.apps.app1.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppService {
    @Value("${service1.url}")
    String url;

    public String echo(String message, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ token);
        
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);        
        //ResponseEntity<String> response  = restTemplate.getForEntity(url + "/0001", String.class);
        ResponseEntity<String> response  = restTemplate.exchange(url+"/0001", HttpMethod.GET, entity, String.class);

        log.info("response {}",response.getBody());
        return "called service1 "+response.getBody();
    }
}
