From openjdk:11.0-slim
COPY ./target/cars-0.0.1-SNAPSHOT.jar cars-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","cars-0.0.1-SNAPSHOT.jar"]
EXPOSE 3000