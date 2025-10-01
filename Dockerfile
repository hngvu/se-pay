# ---------- Build stage ----------
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar sepayment.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sepayment.jar"]
