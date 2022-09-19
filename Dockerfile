FROM openjdk:17-jdk-slim
MAINTAINER wingstako

RUN addgroup glitchtako; adduser --ingroup glitchtako --disabled-password wingstako
USER wingstako

ENV AUTH_SECRET=secret
ENV DATASOURCE_URL=jdbc:postgresql://localhost:5432/forum
ENV DATASOURCE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
ENV DATASOURCE_USERNAME=admin
ENV DATASOURCE_PASSWORD=admin

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]