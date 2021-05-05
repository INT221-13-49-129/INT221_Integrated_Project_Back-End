From openjdk:16-alpine

EXPOSE 8080

ADD .mvn/wrapper/maven-wrapper-0.0.1-SNAPSHOT.jar cars.jar

ENTRYPOINT ["java","-jar","cars.jar"]