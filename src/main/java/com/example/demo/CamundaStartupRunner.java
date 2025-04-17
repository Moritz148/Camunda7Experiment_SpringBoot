package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CamundaStartupRunner implements CommandLineRunner {

    private final CamundaService camundaService;

    public CamundaStartupRunner(CamundaService camundaService) {
        this.camundaService = camundaService;
    }

    @Override
    public void run(String... args) throws Exception {

        //Anzahl an zu startenden Prozessinstanzen
        int numberOfInstances = 100;

//        camundaService.startProcessInstance("typicalC7process");
        camundaService.startMultipleProcessInstances(numberOfInstances);
    }
}
