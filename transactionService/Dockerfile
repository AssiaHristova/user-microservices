FROM openjdk:17-oracle
ENV APP_HOME=/transactionService/
WORKDIR $APP_HOME
COPY target/*.jar transactionService.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar","transactionService.jar"]