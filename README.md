# Golden Raspberry Awards (Desafio Técnico)

Este repositório implementa uma API RESTful para consulta e manipulação dos dados do Golden Raspberry Awards.

## Índice
- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Executar](#como-executar)
  - [Pré-requisitos](#pré-requisitos)
  - [Rodando com Docker Compose](#rodando-com-docker-compose)
  - [Executando em modo Dev (Quarkus)](#executando-em-modo-dev-quarkus)
- [Testes de Integração](#testes-de-integração)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Contato](#contato)

---

## Sobre o Projeto
Este projeto expõe endpoints REST para consulta, criação, atualização e remoção de filmes indicados ao prêmio Golden Raspberry Awards, além de endpoints para análise de intervalos entre prêmios de produtores vencedores. O objetivo é demonstrar domínio em Java, Quarkus, boas práticas de API, testes e conteinerização.

## Funcionalidades
- Listagem de filmes e vencedores
- Cadastro, atualização e remoção de filmes
- Consulta de intervalos entre prêmios de produtores
- Testes automatizados de integração

## Tecnologias Utilizadas
- Java 21
- Quarkus 3.x
- Maven
- JUnit 5
- Docker & Docker Compose
- H2 Database (memória)

## Como Executar

### Pré-requisitos
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- (Opcional) Java 21+ e Maven, caso queira rodar localmente sem container

### Rodando com Docker Compose
1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd <diretorio-do-projeto>
   ```
2. Suba o ambiente de desenvolvimento:
   ```bash
   make up
   # ou diretamente
   docker-compose -f docker-compose-dev.yaml up --build -d
   ```
3. Acesse a aplicação em: [http://localhost:8080](http://localhost:8080)

4. Para parar e remover o container:
   ```bash
   make down
   ```

### Executando em modo Dev (Quarkus)
Se preferir rodar o Quarkus em modo desenvolvimento:

1. Acesse o container:
   ```bash
   make run
   # ou
   docker exec -it gra.dev sh -c "cd /app/gra && ./mvnw quarkus:dev"
   ```
2. O hot reload estará disponível em [http://localhost:8080](http://localhost:8080)

## Testes de Integração
Os testes automatizados garantem a qualidade e o correto funcionamento da API.

### Executando os testes via Maven
Dentro do container ou localmente (com Java 21+):
```bash
cd gra
./mvnw test
```

### Testando os endpoints manualmente
Scripts de exemplo estão disponíveis em `.devcontainer/test/`:
- Criar filme: `bash .devcontainer/test/create_movie.sh`
- Buscar filme por ID: `bash .devcontainer/test/get_movie_by_id.sh <id>`
- Atualizar filme: `bash .devcontainer/test/update_movie.sh <id>`
- Deletar filme: `bash .devcontainer/test/delete_movie_by_id.sh <id>`

## Estrutura do Projeto
```
/app
├── Makefile
├── docker-compose-dev.yaml
├── gra/
│   ├── mvnw, pom.xml
│   ├── src/
│   │   ├── main/java/com/gra/api/...
│   │   ├── main/resources/
│   │   └── test/java/com/gra/api/...
│   └── ...
└── .devcontainer/test/ (scripts de teste)
```

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato
Desenvolvido por [Seu Nome].
- Email: seu.email@exemplo.com
- LinkedIn: [Seu LinkedIn](https://www.linkedin.com/)

---

> Este projeto foi desenvolvido como parte de um desafio técnico. Obrigado pela oportunidade de avaliação!
