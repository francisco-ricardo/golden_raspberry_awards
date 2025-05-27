# Makefile para o projeto Golden Raspberry Awards
#
# Este Makefile fornece uma interface simplificada para gerenciar o ambiente de desenvolvimento
# utilizando Docker Compose. 
# Ele define tarefas comuns para iniciar, parar e interagir com a aplicacao e seus servicos.
#
# Uso:
#   Para executar um target, utilize: `make <target>`
#   Exemplo: `make up` ou `make help`
#
# Dependencias:
#   - Docker
#   - Docker Compose
#   - egrep (para o target 'help')
#

.PHONY: up down run stop help


# Target: up - Inicia o ambiente de desenvolvimento usando Docker Compose.
# Faz o build das imagens se necessário e executa os containers.
#
# Uso: make up
up:
	docker-compose -f docker-compose-dev.yaml up --build -d


# Target: down - Para e remove todos os containers associados ao ambiente de desenvolvimento.
# O `|| true` garante que o comando não falhe caso o container não exista.
#
# Uso: make down
down:
	docker rm -f gra.dev || true


# Target: run - Executa o processo principal de desenvolvimento dentro do container 'gra.dev'.
# Inicia o Quarkus em modo dev no diretório /app/gra.
#
# Uso: make run
run:
	docker exec -it gra.dev sh -c "cd /app/gra && ./mvnw quarkus:dev"


# Target: stop - Encerra o processo principal de desenvolvimento dentro do container 'gra.dev'.
#
# Uso: make stop
stop:
	docker exec -it gra.dev pkill -SIGINT -f "org.apache.maven.wrapper.MavenWrapperMain quarkus:dev"


# Target: help - Exibe uma lista dos targets disponiveis definidos neste Makefile.
#
# Uso: make help
help:
	@egrep "^# Target:" [Mm]akefile


# EOF
