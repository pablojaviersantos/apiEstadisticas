# Usa una imagen base de Java
FROM openjdk:19

COPY ../build/libs/apiEstadisticas-0.0.1.jar /app/apiEstadisticas-0.0.1.jar

WORKDIR /app

ENV POSTGRES_DB ""
ENV POSTGRES_USER ""
ENV POSTGRES_PASSWORD ""
ENV POSTGRES_HOST ""
ENV POSTGRES_PORT ""

# Comando para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT java -jar apiEstadisticas-0.0.1.jar