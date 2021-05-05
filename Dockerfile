FROM maven:3.6.0-jdk-11-slim AS build
COPY src /mybackend/src
COPY pom.xml /mybackend
WORKDIR /mybackend
RUN mvn clean install


From openjdk:11.0-slim
COPY --from=build /mybackend/target/cars-0.0.1-SNAPSHOT.jar cars.jar
ENTRYPOINT ["java","-jar","cars.jar"]
EXPOSE 3000