FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

COPY ./report-subscriptions/ /app/
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM openjdk:17-slim

WORKDIR /app
COPY --from=build /app/target/quarkus-app/ /app/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/quarkus-run.jar"]