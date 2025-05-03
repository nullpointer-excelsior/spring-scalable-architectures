#!/bin/bash


# --- OAUTH STRATEGY ---
# TOKEN=$(curl -u config-client:config-server-secret \
#   -X POST http://localhost:9000/oauth2/token \
#   -H "Content-Type: application/x-www-form-urlencoded" \
#   -d "grant_type=client_credentials&scope=config.read" | jq '.access_token' | tr -d '"')
#   echo "token: $TOKEN"
# curl -H "Authorization: Bearer ${TOKEN}" http://localhost:8888/products-ms/default -v | jq

#  --- BASIC AUTH ---
curl -u "config-server:config-server" http://localhost:8888/products-ms/default -v | jq
