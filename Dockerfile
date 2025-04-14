FROM openjdk:23
LABEL authors="Moritz Schwarz"

COPY target/demo-*.jar /demoC7Application.jar

CMD ["java", "-jar", "/demoC7Application.jar"]