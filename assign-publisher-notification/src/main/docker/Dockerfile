FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

COPY ./assign-publisher-notification/ /app/
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM openjdk:17-slim

WORKDIR /app
COPY --from=build /app/target/quarkus-app/ /app/

COPY ./Makefile /app/Makefile
RUN apt-get -y update && apt-get install -y make curl
EXPOSE 8080

COPY ./publish.sh /app/publish.sh
RUN chmod +x /app/publish.sh

ENTRYPOINT ["java","-jar","/app/quarkus-run.jar"]