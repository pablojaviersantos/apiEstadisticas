# apiEstadisticas
Repo creado para la entrevista de Grupo Quinto Andar.

## Funcionamiento.
La url cuenta con 2 PathVariables los cuales nos permiten un endpoint dinamico.
Este puede tener todos los labels (all) o un label (a,b,c..) en particular con todos (all) o un correspondiente calculo (minimo,maximo,..)

http://localhost:8085/api/statistics/all/all/value

http://localhost:8085/api/statistics/b/maximo/value

## Pasos de deploy.
1. Buildear el proyecto - task build


2. Buildear la imagen con Docker situados en el directorio del proyecto:

       
      docker build -t api_estadisticas:v1 -f .\infra\Dockerfile .
3. Para correr la imagen de docker se hace de la siguienta manera:

       
      
      docker run -p 8080:8080 -e POSTGRES_DB="postgres" -e POSTGRES_HOST="192.168.1.84" -e POSTGRES_PASSWORD="pablo1984" -e POSTGRES_PORT="5432" -e POSTGRES_USER="postgres"  api_estadisticas:v1

4. Taggear y pushear

   
   TAGGING:
            
      
      docker tag api_estadisticas:v1 localhost:5000/api_estadisticas

   PUSHING:

       
      docker push localhost:5000/api_estadisticas

5. Crear cronjob en kubernetes:

       
      kubectl apply -f .\infra\deployment.yaml

6. Acceso Swagger

  http://localhost:8085/swagger-ui/index.html#/statistic-controller/getStatistics