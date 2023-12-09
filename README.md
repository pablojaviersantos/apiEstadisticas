# apiEstadisticas
## titulo secundario
## Para deploy:


docker build -t api_estadisticas:v1 -f .\infra\Dockerfile .

docker run -p 8080:8080 -e POSTGRES_DB="postgres" -e POSTGRES_HOST="192.168.0.54" -e POSTGRES_PASSWORD="pablo1984
" -e POSTGRES_PORT="5432" -e POSTGRES_USER="postgres"  api_estadisticas:v1

TAGGING:


      docker tag api_estadisticas:v1 localhost:5000/api_estadisticas


PUSHING


      docker push localhost:5000/api_estadisticas