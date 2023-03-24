## App Spring com Postgres mais Docker


### Use com ambientes de desenvolvimento do Docker

docker version: 20.10.12 <br>
docker compose version: v2.16.0


### Aplicação Java com framework Spring e uso de banco de dados Postgres

estrutura:
```
.
├── backend
│   ├── Dockerfile
│   └── ...
├── db-pw
│   └── password.txt
├── compose.yaml
└── README.md

```

[_compose.yaml_](compose.yaml)
```
services:
  backend:
    build: backend
    ports:
    - 8080:8080
  db:
    image: postgres:15.2
    ...
```
O arquivo compose.yml define um aplicativo com dois serviços `backend` e `db`.
Ao implantar o aplicativo, o docker compose mapeia a porta 8080 do contêiner de serviço de back-end para a porta 8080 do host.
Certifique-se de que a porta 8080 no host não esteja em uso. <br>
O Banco de dados PostgreSQL é mapeado para host 5432. <br>
Host: localhost <br>
Port: 5432 <br>
Database: person_db <br>
Username: postgres <br>
Password: db-wrz2z <br>

### Deploy with docker compose
Comandos para executar docker-compose.yml:
```shell
docker compose up -d
```
```shell
docker compose up
```

Depois que o aplicativo iniciar, navegue até `http://localhost:8080` em seu navegador da Web ou execute:
```bash
$ curl localhost:8080
```

Pare e remova os containers:
```shell
docker compose down
```

Se você precisar parar e remover todos os contêineres, redes e todas as imagens usadas por qualquer serviço no arquivo docker-compose.yml, use o comando:
```bash
docker-compose down --rmi all
```

### Observações

Para executar no modo test do application.properties é preciso deletar a pasta schema.sql.

### Referências do projeto

> https://github.com/docker/awesome-compose/tree/master/spring-postgres