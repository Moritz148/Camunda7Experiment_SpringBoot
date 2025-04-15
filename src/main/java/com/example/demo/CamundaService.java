package com.example.demo;

import org.camunda.community.rest.client.api.HistoricProcessInstanceApi;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.api.ProcessInstanceApi;
import org.camunda.community.rest.client.dto.HistoricProcessInstanceDto;
import org.camunda.community.rest.client.dto.ProcessInstanceDto;
import org.camunda.community.rest.client.dto.ProcessInstanceWithVariablesDto;
import org.camunda.community.rest.client.dto.StartProcessInstanceDto;
import org.camunda.community.rest.client.invoker.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*@Service
public class CamundaService {


    private static final Logger log = LoggerFactory.getLogger(CamundaService.class);
    private final ProcessDefinitionApi processDefinitionApi;

    public CamundaService(ProcessInstanceApi historicProcessInstanceApi, ProcessDefinitionApi processDefinitionApi) {
        this.processDefinitionApi = processDefinitionApi;
    }

    public void startProcessInstance(String processKey){
        StartProcessInstanceDto instanceDto = new StartProcessInstanceDto();

        try {
            ProcessInstanceWithVariablesDto result = processDefinitionApi.startProcessInstanceByKey(processKey, instanceDto);
//            System.out.println("Prozessinstanz gestartet mit ID: " + result.getId());
            log.info("Prozessinstanz gestartet mit ID: " + result.getId());
        } catch (ApiException e){
            System.err.println("Fehler beim Starten der Prozessinstanz: " + e.getResponseBody());
        }
    }
}*/

@Service
public class CamundaService{
    private static final Logger log = LoggerFactory.getLogger(CamundaService.class);
    private final ProcessDefinitionApi processDefinitionApi;
    private final HistoricProcessInstanceApi historicProcessInstanceApi;

    public CamundaService(ProcessDefinitionApi processDefinitionApi, HistoricProcessInstanceApi historicProcessInstanceApi) {
        this.processDefinitionApi = processDefinitionApi;
        this.historicProcessInstanceApi = historicProcessInstanceApi;
    }

    public void startMultipleProcessInstances(int numberOfInstances) throws ApiException {
        for (int i = 1; i < numberOfInstances+1; i++) {
            String processInstanceId = startProcessInstance();
//            System.out.println("Instanz " + i + " gestartet: " + processInstanceId);
            log.info("Instanz " + i + " gestartet: " + processInstanceId);
            waitForCompletion(processInstanceId);

//            System.out.println("Instanz " + i + " abgeschlossen.");
            log.info("Instanz " + i + " abgeschlossen.");
        }
    }
    private String startProcessInstance() throws ApiException {
        StartProcessInstanceDto instanceDto = new StartProcessInstanceDto();

        ProcessInstanceWithVariablesDto instance=  processDefinitionApi.startProcessInstanceByKey("typicalC7process", instanceDto);

        return instance.getId();
    }

    private void waitForCompletion(String processInstanceId) throws ApiException {
        boolean finished = false;
        while (!finished) {
            finished = checkIfInstanceIsCompleted(processInstanceId);
            if (!finished) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private boolean checkIfInstanceIsCompleted(String processInstanceId) {
        try{
            HistoricProcessInstanceDto historicInstance = historicProcessInstanceApi.getHistoricProcessInstance(processInstanceId);

            return historicInstance.getEndTime() != null;
        } catch (Exception e) {
            return false;
        }
    }
}
