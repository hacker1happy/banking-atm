FROM eclipse-temurin:17
WORKDIR /account-microservice
COPY ATMAccountService.jar ATMAccountService.jar
EXPOSE 8001
ENTRYPOINT [ "java" , "-jar" , "ATMAccountService.jar" ]