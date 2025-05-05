package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ExternalTaskClientConfig {

    @Bean
    public ExternalTaskClient externalTaskClient(){
        return ExternalTaskClient.create()
//                .baseUrl("http://localhost:8080/engine-rest")
                .baseUrl("http://engine:8080/engine-rest")
                .asyncResponseTimeout(5000)
                .build();
    }
}
