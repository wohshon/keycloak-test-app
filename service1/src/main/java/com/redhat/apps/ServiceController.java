package com.redhat.apps;

import com.redhat.apps.entity.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ServiceController {

    @Value("${test.greetings}")
    String greeting;

    @GetMapping("/test/{msg}")    
    public String test(@PathVariable String msg){
        log.info("msg {}",msg);
        return greeting+" "+msg;

    }
    
    @GetMapping("/product/get/{id}")
    public Product get(@PathVariable String id) {
        log.info("product service");
        Product p = new Product();
        p.setDescription("test product");
        p.setId(id);
        p.setName(id);
        return p;

    }
    
}