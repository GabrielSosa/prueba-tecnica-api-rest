# Proyecto de Prueba Técnica

Este proyecto está diseñado por Gabriel Sosa y es parte de una prueba técnica.

## Requisitos

- Tener instalado Docker.

## Instrucciones para Ejecutar el Proyecto

### Usando Docker Compose

1. **Clonar el repositorio** (si aplica):
    ```sh
    git clone https://github.com/GabrielSosa/prueba-tecnica-api-rest.git
    cd proyecto
    ```

2. **Ejecutar el proyecto**:
    En la raíz del proyecto, ejecuta:
    ```sh
    docker-compose up --build
    ```
    Este comando construirá las imágenes Docker necesarias y levantará los servicios definidos en el archivo `docker-compose.yml`.

### Desde la Línea de Comando

Si prefieres ejecutar los proyectos manualmente sin usar Docker Compose, sigue estos pasos:

1. **Cliente-Persona**:
    Navega a la carpeta del proyecto `cliente-persona` y ejecuta:
    ```sh
    cd cliente-persona
    ./gradlew bootRun --args='--server.port=8081'
    ```

2. **Cuenta-Movimiento**:
    Navega a la carpeta del proyecto `cuenta-movimiento` y ejecuta:
    ```sh
    cd cuenta-movimiento
    ./gradlew bootRun --args='--server.port=8082'
    ```

## Tecnologías Utilizadas

- <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="Java Spring Boot" width="20"/> **Java Spring Boot**: Utilizado para desarrollar las aplicaciones `cliente-persona` y `cuenta-movimiento`.
- <img src="https://www.vectorlogo.zone/logos/mysql/mysql-icon.svg" alt="MySQL" width="20"/> **MySQL**: Base de datos utilizada para almacenar la información de las aplicaciones.

## Estructura del Proyecto

El proyecto está estructurado en varios servicios definidos en el archivo `docker-compose.yml`:

- **MySQL**: Servicio de base de datos.
- **Cliente-Persona**: Servicio de aplicación Spring Boot corriendo en el puerto `8081`.
- **Cuenta-Movimiento**: Servicio de aplicación Spring Boot corriendo en el puerto `8082`.

## Configuración de variables de entorno

Estas son las variables de entorno que se deben configurar.

- `SPRING_DATASOURCE_URL`: Conexion para la base de datos.
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos..
- `SPRING_DATASOURCE_PASSWORD`: Contraseña del usuario de la base de datos.
- `SPRING_URL_CLIENTE`:http://127.0.0.1:8081/cliente (Dirección ip del cliente-persona)

## Redes de Docker

Los servicios están conectados a una red de Docker llamada `spring-net` para permitir la comunicación interna entre ellos.

## Notas Adicionales

- Asegúrate de tener Docker y Docker Compose instalados en tu sistema antes de ejecutar el proyecto.
- Si encuentras problemas de permisos con `gradlew`, asegúrate de que el archivo tiene permisos de ejecución:
    ```sh
    chmod +x gradlew
    ```
##  Prueba para automatizar pruebas QA con el framework de Karate

Se debe tener corriendo en docker compose y el comando para ejecutar las pruebas son:

## Comando para ejecutar pruebas

```gradle
gradlew clean test -i
```


## Autor

Gabriel Sosa

Este proyecto es parte de una prueba técnica y demuestra el uso de Docker, Docker Compose, y Spring Boot en un entorno integrado.

## Licencia

Este proyecto está bajo la licencia MIT. Para más detalles, consulta el archivo [LICENSE](LICENSE).
