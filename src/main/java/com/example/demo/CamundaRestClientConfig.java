package com.example.demo;

import org.camunda.community.rest.client.api.HistoricProcessInstanceApi;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CamundaRestClientConfig {

    @Bean
    @Primary
    public ApiClient camundaApiClient(){
        ApiClient apiClient = new ApiClient();
//        apiClient.setBasePath("http://camundaEngine:8080/engine-rest");
        apiClient.setBasePath("http://localhost:8080/engine-rest");
        return apiClient;
    }

    @Bean
    public ProcessDefinitionApi processDefinitionApi2(@Qualifier("camundaApiClient") ApiClient client){
        return new ProcessDefinitionApi(client);
    }

    @Bean
    public HistoricProcessInstanceApi historicProcessInstanceApi(ApiClient apiClient){
        return new HistoricProcessInstanceApi(apiClient);
    }
}
