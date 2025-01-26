# Usa una imagen oficial de Maven para construir el proyecto
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Configura el directorio de trabajo en el contenedor
WORKDIR /proyecto-market

# Copia los archivos del proyecto al contenedor
COPY . .

# Construye el proyecto
RUN mvn clean package -DskipTests

# Usa una imagen ligera de Java para ejecutar la aplicación
FROM eclipse-temurin:17-jdk-jammy

# Configura el directorio de trabajo en el contenedor
WORKDIR /proyecto-market

# Copia el archivo JAR desde la etapa de construcción
COPY --from=build /proyecto-market/target/*.jar proyecto-market.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Ejecuta la aplicación
CMD ["java", "-jar", "proyecto-market.jar"]

