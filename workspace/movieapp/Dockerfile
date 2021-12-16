FROM openjdk:8-alpine

COPY target/uberjar/movieapp.jar /movieapp/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/movieapp/app.jar"]
