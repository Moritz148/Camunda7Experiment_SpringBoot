package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.camunda.bpm.client.ExternalTaskClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExternalTakWorkers {

    private static final Logger log = LoggerFactory.getLogger(ExternalTakWorkers.class);
    private final ExternalTaskClient client;

    public ExternalTakWorkers(ExternalTaskClient client) {
        this.client = client;
    }

    @PostConstruct
    public void subscribe(){
        log.info("Subscribing to external tasks");
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
                }).open();
    }
}
