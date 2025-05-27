#!/bin/bash
# Script de teste para retornar um filme por id

curl -X GET http://localhost:8080/movies/$1

  # EOF
