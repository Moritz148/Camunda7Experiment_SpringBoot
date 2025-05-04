package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.camunda.bpm.client.ExternalTaskClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExternalTaskWorkers {

    private final ExternalTaskClient client;

    public ExternalTaskWorkers(ExternalTaskClient client) {
        this.client = client;
    }

    @PostConstruct
    public void subscribe(){
       /* log.info("Subscribing to external tasks");
        client.subscribe("task1")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task1 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task1 ended");
                }).open();
        client.subscribe("task2")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task2 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task2 ended");
                }).open();
        client.subscribe("task3")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task3 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task3 ended");
                }).open();
        client.subscribe("task4")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task4 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task4 ended");
                }).open();
        client.subscribe("task5")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task5 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task5 ended");
                }).open();
        client.subscribe("task6")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task6 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task6 ended");
                }).open();
        client.subscribe("task7")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task7 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task7 ended");
                }).open();
        client.subscribe("task8")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task8 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task8 ended");
                }).open();
        client.subscribe("task9")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task9 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task9 ended");
                }).open();
        client.subscribe("task10")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Task10 started");
                    externalTaskService.complete(externalTask);
                    log.info("Task10 ended");
                }).open();*/
        //Liste mit Task-Namen
        String[] taskNames = {"task1"};

        for(String taskName : taskNames) {
            subscribeToTask(taskName);
        }
    }

    private void subscribeToTask(String taskName){
        client.subscribe(taskName)
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    //System.out.println("Task " + taskName + " started");
                    externalTaskService.complete(externalTask);
                    //System.out.println("Task " + taskName + " completed");
                }).open();
    }
}
