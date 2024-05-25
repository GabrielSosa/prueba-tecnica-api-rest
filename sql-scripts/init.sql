CREATE DATABASE IF NOT EXISTS banco; 
USE banco; 

-- Tabla personas
CREATE TABLE IF NOT EXISTS personas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(255) NOT NULL UNIQUE,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(255) NOT NULL
);

-- Tabla clientes 
CREATE TABLE IF NOT EXISTS cliente (
    cliente_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personaId BIGINT UNIQUE, 
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL,
    FOREIGN KEY (personaId) REFERENCES personas(id)
);

-- tabla de cuentas
CREATE TABLE IF NOT EXISTS cuenta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(255) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(255) NOT NULL,
    saldo_inicial DECIMAL(15, 2) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    cliente_Id BIGINT NOT NULL, 
    FOREIGN KEY (cliente_Id) REFERENCES cliente(cliente_id)
);

-- tabla de movimientos
CREATE TABLE IF NOT EXISTS movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    tipo_movimiento VARCHAR(255) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    cuenta_id BIGINT NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON banco.* TO 'user'@'%';
FLUSH PRIVILEGES;
