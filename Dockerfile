FROM adoptopenjdk/openjdk11:alpine-jre
COPY ./target/*.jar ./app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]
