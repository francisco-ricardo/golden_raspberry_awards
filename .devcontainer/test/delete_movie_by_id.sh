#!/bin/bash
# Script de teste para deletar um filme por id

curl -X DELETE http://localhost:8080/movies/$1

  # EOF
