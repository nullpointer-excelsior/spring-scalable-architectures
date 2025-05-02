#!/bin/bash


TOKEN=$(curl -u checkout-client:secret \
  -X POST http://localhost:9000/oauth2/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=client_credentials&scope=carts.read" | jq '.access_token' | tr -d '"')

  echo "token: $TOKEN"

  curl -H "Authorization: Bearer ${TOKEN}" http://localhost:8080/carts/1 -v | jq
