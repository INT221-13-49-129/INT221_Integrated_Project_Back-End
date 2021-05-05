From openjdk:16-alpine

EXPOSE 8080

ADD out/cars-0.0.1-SNAPSHOT.jar projectcars.jar

ENTRYPOINT ["java","-jar","projectcars.jar"]