#Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#Run stage

FROM eclipse-temurin:17-jre-jemmy
WORKDIR /app
copy --from=build /app/target/*.jar app.jar
EXPOSE 7071
ENTRYPOINT ["java", "-jar", "app.jar"]
