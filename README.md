# Projeto RMI

## Descrição

Projeto de RMI para a disciplina de Sistemas Operacionais.

## Requisitos

- Java 11
- Maven
- Docker
- Docker Compose
- **.env (ver .env.template)**

## Execução

### Docker Compose

Utilizar o comando abaixo para subir o banco de dados da aplicação:

```bash
docker-compose up -d
```

### Servidor

Execute o servidor inserindo o arquivo .env como variável de ambiente:

package: `br.dev.phcardoso.rmi.server`

main_file: `RmiServer.java`

### Cliente

Execute o client inserindo o arquivo .env como variável de ambiente:

package: `br.dev.phcardoso.rmi.client`

main_file: `RmiClient.java`
