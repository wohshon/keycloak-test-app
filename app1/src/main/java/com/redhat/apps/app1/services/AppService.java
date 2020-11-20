package com.redhat.apps.app1.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppService {
    @Value("${service1.url}")
    String url;

    public String echo(String message) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response  = restTemplate.getForEntity(url + "/0001", String.class);
        log.info("response {}",response.getBody());
        return "called service1 "+response.getBody();
    }
}
