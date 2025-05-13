package com.example.demo;

import org.camunda.community.rest.client.api.HistoricProcessInstanceApi;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.dto.HistoricProcessInstanceDto;
import org.camunda.community.rest.client.dto.ProcessInstanceWithVariablesDto;
import org.camunda.community.rest.client.dto.StartProcessInstanceDto;
import org.camunda.community.rest.client.invoker.ApiException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class CamundaService{
    private final ProcessDefinitionApi processDefinitionApi;
    private final HistoricProcessInstanceApi historicProcessInstanceApi;

    public CamundaService(ProcessDefinitionApi processDefinitionApi, HistoricProcessInstanceApi historicProcessInstanceApi) {
        this.processDefinitionApi = processDefinitionApi;
        this.historicProcessInstanceApi = historicProcessInstanceApi;
    }

    public void startMultipleProcessInstances(int numberOfInstances) throws ApiException {
        for (int i = 1; i < numberOfInstances+1; i++) {
            //aktuellen timestamp ermitteln in Millisekunden
            long timestampStart = System.currentTimeMillis();
            Instant instant = Instant.ofEpochMilli(timestampStart);

            //Zeit in Format HH:MM:SS.sss umwandeln
            String timestampFormattedStart = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
                    .withZone(ZoneId.systemDefault())
                    .format(instant);

            //Start der Prozesinstanz
            String processInstanceId = startProcessInstance();

            if(i == 1) {
                //Ausgabe Start mit timestamp
                System.out.printf("Instance #%d STARTED - %s%n", i, timestampFormattedStart);
            }
            //Warten auf Abschluss der Instanz
            waitForCompletion(processInstanceId);

            if(i == numberOfInstances) {
                //Ausgabe Ende mit timestamp
                long timestampEnd = System.currentTimeMillis();
                Instant instant2 = Instant.ofEpochMilli(timestampEnd);

                //Zeit in Format HH:MM:SS.sss umwandeln
                String timestampFormattedEnd = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
                        .withZone(ZoneId.systemDefault())
                        .format(instant);
                System.out.printf("Instance #%d DONE - %s%n", i, timestampFormattedEnd);
            }
        }
    }
    private String startProcessInstance() throws ApiException {
        StartProcessInstanceDto instanceDto = new StartProcessInstanceDto();

        ProcessInstanceWithVariablesDto instance=  processDefinitionApi.startProcessInstanceByKey("C7_complex-long", instanceDto);

        return instance.getId();
    }

    private void waitForCompletion(String processInstanceId) {
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
