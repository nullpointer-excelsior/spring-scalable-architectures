-- Crear las bases de datos
CREATE DATABASE monolith;
CREATE DATABASE products_ms;
CREATE DATABASE cart_ms;

-- Crear los usuarios
CREATE USER monolith WITH PASSWORD 'monolith';
CREATE USER products_ms WITH PASSWORD 'products_ms';
CREATE USER cart_ms WITH PASSWORD 'cart_ms';

-- Conceder permisos de conexión y creación en cada base de datos
GRANT CONNECT, CREATE ON DATABASE monolith TO monolith;
GRANT CONNECT, CREATE ON DATABASE products_ms TO products_ms;
GRANT CONNECT, CREATE ON DATABASE cart_ms TO cart_ms;

-- Conceder permisos sobre el esquema 'public' de cada base de datos
\c monolith
GRANT ALL PRIVILEGES ON SCHEMA public TO monolith;

\c products_ms
GRANT ALL PRIVILEGES ON SCHEMA public TO products_ms;

\c cart_ms
GRANT ALL PRIVILEGES ON SCHEMA public TO cart_ms;

-- Permisos adicionales de manipulación de datos (opcional, puedes personalizar)
\c monolith
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO monolith;

\c products_ms
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO products_ms;

\c cart_ms
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO cart_ms;
