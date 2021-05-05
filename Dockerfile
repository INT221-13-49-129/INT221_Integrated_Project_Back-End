From openjdk:11.0-slim
COPY --from=build /mybackend/target/cars-0.0.1-SNAPSHOT.jar cars.jar
ENTRYPOINT ["java","-jar","cars.jar"]
EXPOSE 3000