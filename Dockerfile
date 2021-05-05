From openjdk:11-alpine

EXPOSE 8080

ADD out/artifacts/cars-0.0.1-SNAPSHOT.jar projectcars.jar

ENTRYPOINT ["java","-jar","projectcars.jar"]