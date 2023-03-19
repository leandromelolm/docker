# docker


### Docker comandos básicos

| Comandos                                      | Descrição                                 |
|-----------------------------------------------|-------------------------------------------|
| docker compose up                         | Cria e inicia containers do arquivo compose   |
| docker compose up -d                     | Criar e iniciar com terminal liberado          |
| docker compose down                                 |  Para e remove containers           |
| docker compose down --rmi all -v               | Parar e remover imagens e volumes        |
| docker stop $(docker ps -a -q)         |  Parar todos os containers em execução           |
| docker container logs -f ID_OU_NOME_CONTAINER                    | Docker logs            |
| docker exec -it CONTAINER_NAME /bin/bash                   | Acessar container            |
| docker container ls -a                        | Listar container                          |
| docker ps -a -q                               | Listar container (-q para exibir apenas id) |
| docker image ls -a                            | Listar Imagens                            |
| docker volume ls                              | Listar volumes                            |
| docker container rm ID_CONTAINER              | Remover container                         |
| docker rmi -f ID_IMAGE                        | Remover imagem                            |
| docker volume rm ID_VOLUME                    | Remover volume                            |
| docker events                     | Inspecionar o que tá acontencendo (usar aba separada) |
| docker stats    | Estatisticas da maquina com uso de containers em execução (usar aba separada)|
| docker top CONTAINER_ID        | Conferir o processo que está sendo executado no momento  |
| docker run -p 81:81 -d -m 512m --cpu-quota 50000 IMAGE_NAME  |Executar container com parametros de memória e cpu de uso limitados |
| docker container stop CONTAINER_ID_OU_NAME   | Parar container                            |
| docker system df                             | Informações do sistema do docker           |
| docker container inspect CONTAINER_ID        | Inspecionar o container                    |
| mvn clean package -DskipTests             | Gerar jar com maven            |