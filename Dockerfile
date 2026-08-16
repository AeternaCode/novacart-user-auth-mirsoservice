FROM eclipse-temurin:21-jre

WORKDIR /app

COPY build/libs/user-auth-service-0.0.1-SNAPSHOT.jar user-auth-service.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","user-auth-service.jar"]