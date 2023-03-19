## App Spring com Postgres mais Docker


### Use com ambientes de desenvolvimento do Docker

docker version: 20.10.12 <br>
docker compose version: v2.16.0


### Aplicação Java com framework Spring e um banco de dados Postgres

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
Comandos para executar docker-compose.yml
```shell
docker compose up -d
```
```shell
docker compose up
```


Listing containers:
```shell
$ docker ps
```

After the application starts, navigate to `http://localhost:8080` in your web browse or run:
```bash
$ curl localhost:8080
<!DOCTYPE HTML>
<html>
<head>
  <title>Getting Started: Serving Web Content</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<p>Hello from Docker!</p>
</body>
```

Stop and remove the containers
```shell
docker compose down
```

Ver container mesmo os parados
```shell
docker container ls -a
docker ps -a
```

___
### Remover Imagens docker (deletar imagens)

Listar imagens docker
```shell
docker images -a
```
```bash
docker image ls
```

Remover imagem docker
```bash
docker rmi <id-da-imagem>
```
Forçar remoção de imagem docker
```bash
docker rmi -f <id-da-imagem>
```

Remover várias imagens
```bash
docker rmi <id-da-imagem> <id-da-imagem> ...
```

> https://www.freecodecamp.org/portuguese/news/como-remover-imagens-e-conteineres-no-docker


### Remover container docker

Listar container
```bash
$ docker container ls -a
```

Para deletar múltiplos containers
```bash
$ docker container rm <container ID> <container ID>
```

Remover container com volumes
```bash
docker-compose down --volumes
```

Se você precisar parar e remover todos os contêineres, redes e todas as imagens usadas por qualquer serviço no arquivo docker-compose.yml, use o comando:
```bash
docker-compose down --rmi all
```


> https://learn.microsoft.com/pt-br/visualstudio/docker/tutorials/tutorial-multi-container-app-mysql
> https://www.hostinger.com.br/tutoriais/remover-imagem-docker


### Remover volume docker

Listar volumes

```bash
docker volume ls
```

Remover volume

```bash
docker volume rm <volume_name> <volume_name>
```

> https://www.hostinger.com.br/tutoriais/remover-imagem-docker


### Docker logs

```bash
docker container logs -f id_ou_nome_do_container
```

> https://medium.com/@gomex/logs-no-docker-c6f3c7fa6ee4


Os volumes do Docker são gerenciados pelo Docker e um diretório é criado em `/var/lib/docker/volumes` na instância de contêiner que contém os dados de volume.


### Remover todos containers e volumes docker (cuidado)

Comando remove todos os contêineres não utilizados
```bash
docker system prune -a
```

Remove todos os volumes não utilizados
```bash
docker volume prune
```


### Gerar Jar no Maven
na pasta do projeto onde fica o src e o pom.xml digitar o comando
```bash
mvn clean package
```



### Observações

Para executar no modo test do application.properties é preciso deletar a pasta schema.sql.


### Referências do projeto

> https://github.com/docker/awesome-compose/tree/master/spring-postgres