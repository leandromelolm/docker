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

### Abrir banco com PgAdmin

1. Abrir o endereço no navegador: http://localhost:15432/login
2. Login: `admin@admin.com` Password: `admin`
3. Servers > Register > Server
4. Aba General \ Name: `Local`
5. Aba Connection \ 
```
Hostname/address: spring-postgres-db 
Port: 5432  
Maintenance database: postgres
Username: postgres
Password: postgres123!
```