FROM openjdk:11.0-slim
COPY --from=build /workspace/target/cars-0.0.1-SNAPSHOT.jar cars-0.0.1-SNAPSHOT.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","cars-0.0.1-SNAPSHOT.jar"]