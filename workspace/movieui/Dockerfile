FROM openjdk:8-alpine

COPY target/uberjar/movieui.jar /movieui/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/movieui/app.jar"]
