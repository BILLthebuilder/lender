FROM amazoncorretto:17-alpine3.17-jdk
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]