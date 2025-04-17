FROM openjdk:23
LABEL authors="Moritz Schwarz"

COPY target/Camunda7Experiment*.jar /demoC7Application.jar

CMD ["java", "-jar", "/demoC7Application.jar"]