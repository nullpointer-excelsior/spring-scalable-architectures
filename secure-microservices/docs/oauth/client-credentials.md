
# 2 🔐 Authorization with `client_credentials`

The `client_credentials` grant type is used when a client (such as a backend service or application) needs to access protected resources without user interaction. This is common in service-to-service communication, where the client authenticates using its own credentials.

## 🔸 How It Works
1. The client (e.g., `checkout-client`) sends a POST request to the **Auth Server** with its client ID and secret.
2. The Auth Server authenticates the client and issues an **access token** with the requested scopes.
3. The client includes this token in the `Authorization` header when calling a protected resource on a **Resource Server** (e.g., API Gateway).
4. The Resource Server verifies the token and grants or denies access based on scopes and roles.

## 🛠️ Example Request

```bash

# apps running in local
curl -u checkout-client:secret \
  -X POST http://localhost:9000/oauth2/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=client_credentials&scope=carts.read"

# apps running with docker-compose
curl -u checkout-client:secret \
  -X POST http://auth-server:9000/oauth2/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=client_credentials&scope=carts.read"
```

## 🔑 Response

Copy the `access_token`

```json
{
  "access_token": "XXX",
  "scope": "carts.read",
  "token_type": "Bearer",
  "expires_in": 299
}
```

## 📥 Using the Token

```shell
#!/bin/bash

curl -H "Authorization: Bearer ${TOKEN}" http://localhost:8080/carts/1 -v

```

This flow ensures secure access to backend APIs without exposing user credentials or requiring user interaction.

Or ejecute `./auth-cart-request.sh`

```shell
#!/bin/bash

./auth-cart-request.sh

```