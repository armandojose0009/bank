# Usa una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el código fuente de tu aplicación al contenedor
COPY . .

# Compila la aplicación utilizando Maven
RUN ./mvnw clean compile

# Expone el puerto en el que tu aplicación Spring se ejecutará (ajusta según sea necesario)
EXPOSE 6001

# Comando para ejecutar la clase WebTestApplication
CMD ["java", "-cp", "src/main/java", "com.test.webTest.WebTestApplication"]
