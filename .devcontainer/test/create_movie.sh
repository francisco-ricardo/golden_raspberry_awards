#!/bin/bash
# Script de teste para criar um novo filme no banco de dados

curl -X POST http://localhost:8080/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Example Movie",
    "year": 2024,
    "studios": "Example Studio",
    "producers": "Producer Name",
    "winner": false
  }'

  # EOF
