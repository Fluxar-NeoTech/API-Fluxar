# Etapa 1: build com Maven e encoding UTF-8
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Forçar encoding UTF-8 no Maven
ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"

# Copia os arquivos de build primeiro para aproveitar cache do Docker
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o .jar (pulando testes)
RUN mvn clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8

# Etapa 2: runtime com JDK leve
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copia apenas o .jar gerado da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Define variável de ambiente para UTF-8 no runtime também
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
