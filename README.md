# docker


### Docker comandos básicos

| Comandos                                      | Descrição                                 |
|-----------------------------------------------|-------------------------------------------|
| docker compose up                        | Criar e iniciar containers do arquivo compose  |
| docker compose up -d                     | Criar e iniciar com terminal liberado          |
| docker compose down                      | Parar containers                               |
| docker compose down --rmi all -v         | Parar e remover imagens e volumes              |
| docker container logs -f ID_OU_NOME_CONTAINER                    | Docker logs            |
| docker exec -it CONTAINER_NAME /bin/bash                   | Acessar container            |
| docker container ls -a                      | Listar container (até os parados)           |
| docker ps -a -q                             | Listar container (-q para exibir apenas id) |
| docker ps -a --format '{{.ID}} {{.Names}}'  | Listar com ID e Nome dos container          |
| docker stop $(docker ps -a -q)              | Parar todos os containers em execução       |
| docker container inspect ID_CONTAINER       | Inspecionar container                       |
| docker container stop CONTAINER_ID_OU_NAME  | Parar container                             |
| docker container start CONTAINER_ID_OU_NAME | Iniciar container                           |
| docker container top CONTAINER_ID_OU_NAME   | Ver processos do container em execução      |
| docker container rm ID_CONTAINER            | Remover container                           |
| docker image ls -a                            | Listar Imagens                            |
| docker image inspect IMAGE_ID                 | Inspecionar Imagens                       |
| docker rmi -f IMAGE_ID                        | Remover imagem                            |
| docker volume ls                       | Listar volumes                                   |
| docker volume inspect VOLUME_NAME      | Inspecionar volume                               |
| docker volume rm ID_VOLUME             | Remover volume                                   |
| docker network ls                                   | Listar redes                        |
| docker network rm NETWORK_ID                        | Remover rede                        |
| docker events                     | Inspecionar o que tá acontencendo (usar aba separada) |
| docker stats    | Estatisticas da maquina com uso de containers em execução (usar aba separada)|
| docker run -p 81:81 -d -m 512m --cpu-quota 50000 IMAGE_NAME  |Executar container com parametros de memória e cpu de uso limitados |
| docker system df                             | Informações do sistema do docker           |
| mvn clean package -DskipTests     | Gerar jar com maven (`java`)                          |


### Docker + PostgreSQL + PgAdmin4

Obtendo as imagens do postgres e pgadmin4
```shell
docker pull postgres:15-alpine
```
```shell
docker pull dpage/pgadmin4
```

Criando uma network para execução dos containers
```shell
docker network create -d bridge postgres-network
```

Executar um container docker com postgres 15
```shell
docker run --name test-postgres --network=postgres-network -e "POSTGRES_PASSWORD=postgres123456" -p 5432:5432 -v db-data-postgres:/var/lib/postgresql/data -d postgres:15-alpine
```

Criando um container para executar pgAdmin4
```shell
docker run --name test-pgadmin --network=postgres-network -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=admin@admin.com" -e "PGADMIN_DEFAULT_PASSWORD=admin" -d dpage/pgadmin4
```
Acessar URL http://localhost:15432 e na tela de login do pgAdmin 4 usar o login e senha atribuidos em PGADMIN_DEFAULT_EMAIL e PGADMIN_DEFAULT_PASSWORD.

- Server > Register > Server
- Aba General
- Campo Name: Local
- Aba Conection
- Host name/address: test-postgres
- Port: 5432
- Maintenance: postgres
- Username: postgres
- Password: postgres123456


Criar banco
- Local > create > Database 



> https://imasters.com.br/banco-de-dados/postgresql-docker-executando-uma-instancia-e-o-pgadmin-4-partir-de-containers


