# Docker


### Docker comandos básicos

| Comandos                                                           | Descrição                                                                                                                 |
|--------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| docker compose up                                                  | Criar e iniciar containers do arquivo compose                                                                             |
| docker compose up -d                                               | Criar e iniciar com terminal liberado                                                                                     |
| docker compose down                                                | Parar containers                                                                                                          |
| docker compose down --rmi all -v                                   | Parar e remover imagens e volumes                                                                                         |
| docker compose down -v                                             | Parar e remove containers e volumes                                                                                       |
| docker container logs -f ID_OU_NOME_CONTAINER                      | Docker logs                                                                                                               |
| docker exec -it CONTAINER_NAME /bin/bash                           | Acessar container                                                                                                         |
| docker container ls -a                                             | Listar container (todos)                                                                                                  |
| docker ps -a                                                       | Listar container (todos)                                                                                                  |
| docker ps                                                          | Lista container em execução                                                                                               |
| docker ps -a -q                                                    | Listar container (-q para exibir apenas id)                                                                               |
| docker ps -a --format '{{.ID}} {{.Names}}'                         | Listar com ID e Nome dos container                                                                                        |
| docker ps -f "status=exited" --format "table {{.ID}}\t{{.Names}}"  | filtra apenas os containers que estão parados (status = exited) e mostra o ID e o nome de cada um em um formato de tabela |
| docker ps -f "status=running" --format "table {{.ID}}\t{{.Names}}" | filtra apenas os containers que estão rodando                                                                             |
| docker ps -a --format "table {{.ID}}\t{{.Names}}\t{{.Status}}"     | Para exibir os containers em um formato de tabela com as informações de ID, nome e status                                 |
| docker stop $(docker ps -a -q)                                     | Parar todos os containers em execução                                                                                     |
| docker container inspect ID_CONTAINER                              | Inspecionar container                                                                                                     |
| docker container stop CONTAINER_ID_OU_NAME                         | Parar container                                                                                                           |
| docker container start CONTAINER_ID_OU_NAME                        | Iniciar container                                                                                                         |
| docker container top CONTAINER_ID_OU_NAME                          | Ver processos do container em execução                                                                                    |
| docker container rm ID_CONTAINER                                   | Remover container                                                                                                         |
| docker container rm CONTAINER_ID CONTAINER_ID                      | Remover múltiplos containers                                                                                              |
| docker image ls -a                                                 | Listar imagens docker                                                                                                     |
| docker images                                                      | Listar imagens docker                                                                                                     |
| docker image inspect IMAGE_ID                                      | Inspecionar Imagens                                                                                                       |
| docker rmi -f IMAGE_ID                                             | Remover imagem (força remoção)                                                                                            |
| docker rmi IMAGE_ID IMAGE_ID                                       | Remover várias imagem                                                                                                     |
| docker volume ls                                                   | Listar volumes                                                                                                            |
| docker volume inspect VOLUME_NAME                                  | Inspecionar volume                                                                                                        |
| docker volume rm VOLUME_ID_ou_VOLUME_NAME                          | Remover volume                                                                                                            |
| docker network ls                                                  | Listar redes                                                                                                              |
| docker network rm NETWORK_ID                                       | Remover rede                                                                                                              |
| docker events                                                      | Inspecionar o que tá acontencendo (usar aba separada)                                                                     |
| docker stats                                                       | Estatisticas da maquina com uso de containers em execução (usar aba separada)                                             |
| docker run -p 81:81 -d -m 512m --cpu-quota 50000 IMAGE_NAME        | Executar container com parametros de memória e cpu de uso limitados                                                       |
| docker system df                                                   | Informações do sistema do docker                                                                                          |
| mvn clean package -DskipTests                                      | Comando maven para gerar jar do projeto spring java (pula os testes)                                                      |
| docker ps -as --format "table {{.ID}}\t{{.Names}}\t{{.Size}}"      | Para ver o tamanho que cada container ocupa no disco                                                                      |
| docker images --format "{{.Repository}}\t{{.Tag}}\t{{.Size}}"      | Para ver o tamanho de todas as imagens no seu sistema Docker                                                              |
| docker system df -v                                                | Mostra informações sobre o uso de espaço em disco do Docker, incluindo informações sobre volumes, imagens e containers    |
| docker system prune -a                                             | Comando remove todos os contêineres não utilizados                                                                        |
| docker volume prune                                                | Remove todos os volumes não utilizados                                                                                    |

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

- Criar um servidor: Server > Register > Server

```
Tab General
    Name: Local
    
Tab Conection
    Host name/address: test-postgres
    Port: 5432
    Maintenance: postgres
    Username: postgres
    Password: postgres123456
```

Criar banco de dados:
- Local > Create > Database 


Os volumes do Docker são gerenciados pelo Docker. Um diretório é criado em `/var/lib/docker/volumes` na instância de contêiner que contém os dados de volume.

### Referências dos projetos
> https://imasters.com.br/banco-de-dados/postgresql-docker-executando-uma-instancia-e-o-pgadmin-4-partir-de-containers
> https://learn.microsoft.com/pt-br/visualstudio/docker/tutorials/tutorial-multi-container-app-mysql
> https://www.hostinger.com.br/tutoriais/remover-imagem-docker
> https://www.freecodecamp.org/portuguese/news/como-remover-imagens-e-conteineres-no-docker
> https://www.hostinger.com.br/tutoriais/remover-imagem-docker
> https://medium.com/@gomex/logs-no-docker-c6f3c7fa6ee4

