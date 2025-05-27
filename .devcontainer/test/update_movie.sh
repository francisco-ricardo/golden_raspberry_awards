#!/bin/bash
# Script de teste para atualizar um filme no banco de dados

curl -X PUT http://localhost:8080/movies/$1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Title",
    "year": 2025,
    "studios": "Updated Studio",
    "producers": "Updated Producer",
    "winner": false
  }'

  # EOF
