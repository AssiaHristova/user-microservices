FROM openjdk:17-oracle
ENV APP_HOME=/userService/
WORKDIR $APP_HOME
COPY target/*.jar userService.jar
EXPOSE 8003
ENTRYPOINT ["java", "-jar","userService.jar"]