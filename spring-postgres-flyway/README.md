## App Spring com Postgres mais Docker

- Spring Boot 3.0.4
- Java 17
- H2
- PostgreSQL
- Flyway (faz inserção de dados)

## Iniciar aplicação com docker

Para iniciar a aplicação com docker, basta navegar até o diretório onde o arquivo docker-compose.yml 
está localizado e executar o comando `docker-compose up`. 
Depois que o contêiner for iniciado, as rotas estarão disponíveis na porta padrão 8080.

Observação: no application.yml o profile ativo deve ser o `dev-docker`.

### Abrir o banco de dados  com PgAdmin

1. Abrir o endereço no navegador: http://localhost:55432/login
2. Login: `admin@admin.com` Password: `admin`
3. Servers > Register > Server
4. Fazer conexão com as seguintes configurações:

```
Aba General
Name: Local

Aba Connection
    Hostname/address: spring-postgres-db 
    Port: 5432  
    Maintenance database: postgres
    Username: postgres
    Password: postgres123!
```

### Criar e iniciar um container para executar PgAdmin 4 que permite se conectar com containers iniciados no docker compose
Ao executar `docker compose down` as configurações feita no PgAdmin 4 são apagadas,
e ao reiniciar será necessário fazer todas as configurações do Server para abrir o banco.
Para não precisar refazer as configurações quando executar o `docker compose up`, pode-se criar e iniciar um container  com o PgAdmin 4 a parte.
Quando não precisar mais usar o container é só digitar o comando `docker container stop test-pgadmin-spring-postgres`.
Para iniciar novamente  `docker container start test-pgadmin-spring-postgres`.

Comando para criar e iniciar um container para executar o pgAdmin4 e se conectar a rede criada no docker-compose. Abrir o endereço no navegador: http://localhost:65432/login.

```shell
docker run --name test-pgadmin-spring-postgres --network=spring-postgres -p 65432:80 -e "PGADMIN_DEFAULT_EMAIL=admin@admin.com" -e "PGADMIN_DEFAULT_PASSWORD=admin" -d dpage/pgadmin4
```
Fazer conexão com as seguintes configurações:

```
Aba General
    Name: Local

Aba Connection
    Hostname/address: spring-postgres-db 
    Port: 5432  
    Maintenance database: postgres
    Username: postgres
    Password: postgres123!
```

### Criar e iniciar um container para executar uma instância do PostgreSQL e PgAdmin 4 para executar com profile dev do projeto
Primeiramente no application.yml o profile ativo deve ser o `dev`.

Executar um container docker com postgreSQL.
```shell
docker run --name test-postgres --network=postgres-network -e "POSTGRES_PASSWORD=postgres123456" -p 5432:5432 -v db-data-postgres:/var/lib/postgresql/data -d postgres:15-alpine
```
Executar um container PgAdmin 4 que permite se conectar ao container test-postgres, pois estarão na mesma rede (network).
```shell
docker run --name test-pgadmin-postgres-network --network=postgres-network -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=admin@admin.com" -e "PGADMIN_DEFAULT_PASSWORD=admin" -d dpage/pgadmin4
```

1. Abrir o endereço no navegador: http://localhost:15432/login
2. Login: `admin@admin.com` Password: `admin`
3. Servers > Register > Server
4. Inserir seguintes configurações:

```
Aba General
    Name: Local
    
Aba Connection
    Hostname/address: test-postgres 
    Port: 5432  
    Maintenance database: postgres
    Username: postgres
    Password: postgres123456
```
