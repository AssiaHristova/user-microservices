FROM openjdk:17-oracle
ENV APP_HOME=/addressService/
WORKDIR $APP_HOME
COPY target/*.jar addressService.jar
EXPOSE 8002
ENTRYPOINT ["java", "-jar","addressService.jar"]
