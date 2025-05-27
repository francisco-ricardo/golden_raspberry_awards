# Golden Raspberry Awards

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

### Subir o container com Docker Compose

1. Clonar o repositório:
   ```bash
   git clone git@github.com:francisco-ricardo/golden_raspberry_awards.git
   cd golden_raspberry_awards
   ```
2. Subir o ambiente de desenvolvimento:
   ```bash
   make up
   # ou diretamente
   docker-compose -f docker-compose-dev.yaml up --build -d
   ```
3. Parar e remover o container:
   ```bash
   make down
   ```

### Executando em modo Dev (Quarkus)

1. Executar o comando maven:
   ```bash
   make run
   # ou
   docker exec -it gra.dev sh -c "cd /app/gra && ./mvnw quarkus:dev"
   ```
2. Acessar a aplicação em: [http://localhost:8080](http://localhost:8080)

## Testes de Integração

Os testes automatizados garantem a qualidade e o correto funcionamento da API.

### Executando os testes

```
make test
# ou
docker exec -it gra.dev sh -c "cd /app/gra && ./mvnw test" 
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
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/gra/
│   │   │   │       ├── api/           # Camada de apresentação (controllers/resources REST)
│   │   │   │       ├── domain/        # Camada de domínio (modelos, repositórios, serviços de domínio)
│   │   │   │       │   ├── model/     # Entidades e value objects
│   │   │   │       │   ├── repository # Interfaces de repositório
│   │   │   │       │   └── service/   # Interfaces e implementações de regras de negócio
│   │   │   │       ├── infrastructure/# Implementações técnicas (repositórios, startup, CSV parser)
│   │   │   │       │   ├── repository/
│   │   │   │       │   ├── csv/
│   │   │   │       │   └── startup/
│   │   │   │       └── application/   # (Opcional) Serviços de aplicação/orquestração
│   │   │   ├── resources/             # Configurações, scripts SQL, arquivos de dados
│   │   │   │   ├── application.properties
│   │   │   │   ├── import.sql
│   │   │   │   └── data/
│   │   │   │       └── movielist.csv
│   │   └── test/
│   │       └── java/com/gra/api/      # Testes automatizados (REST, integração)
│   └── ...
├── .devcontainer/                     # Arquivos e scripts para o ambiente de testes
│   └── test/                          # Scripts de teste para endpoints
└── ...
```

## Considerações

As seguintes premissas foram assumidas:

- Os cálculos devem considerar apenas premiações consecutivas, desconsiderando premiações não contíguas.

- Se houver somente um Produtor que tenha conquistado mais de uma premiação, ele fará parte tanto do grupo *min* 
quanto do grupo *max*.

- O campo *producers* foi considerado do tipo *String* e não uma lista. Portanto, um mesmo Produtor pode aparecer em diferentes composições com outros produtores e não ser contabilizado como ganhador consecutivo.

- O campo *winner* do modelo *Movie* será definido como *true* sempre que o seu respectivo valor no arquivo CSV for *yes* (case insensitive). Para os demais casos, será considerado como *false*.

- A configuração foi limitada ao profile dev, sem considerar aspectos como cobertura de testes, deploy para a nuvem e pipelines CI/CD.

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato

Desenvolvido por Francisco Ricardo Taborda Aguiar.
- Email: franciscoricardo.dev@gmail.com
- LinkedIn: [https://www.linkedin.com/in/francisco-ricardo-taborda-aguiar-3ab650a0/](https://www.linkedin.com/)
- Github: [https://github.com/francisco-ricardo](https://github.com)

---
