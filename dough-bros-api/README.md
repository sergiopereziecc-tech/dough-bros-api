# 🍕 Pizzería API - Spring Boot

API REST para la gestión de pedidos de una pizzería, incluyendo control de clientes, productos y flujo de estados de pedidos.

## 🛠️ Tecnologías
* **Java 17**
* **Spring Boot 3** (Web, Data JPA, Security)
* **H2 Database** (Persistencia en memoria)
* **Swagger/OpenAPI 3** (Documentación interactiva)
* **Lombok**

## 🔐 Seguridad y Roles
La API utiliza autenticación básica con los siguientes usuarios preconfigurados:

| Usuario | Password | Rol | Permisos |
| :--- | :--- | :--- | :--- |
| **admin** | admin123 | ADMIN | Acceso total y gestión de cocina (`/next`) |
| **cliente** | cliente123 | USER | Lectura y creación de pedidos |

## 📖 Documentación (Swagger)
Una vez ejecutada la aplicación, puedes acceder a la interfaz interactiva para probar los endpoints en:
`http://localhost:8080/swagger-ui.html`

## 📊 Base de Datos
Consola H2 disponible en: `http://localhost:8080/h2-console`
* **JDBC URL**: `jdbc:h2:mem:testdb`
* **User**: `sa`
* **Password**: (vacío)

## 🚀 Instalación y Ejecución
1. Clonar el repositorio.
2. Ejecutar `./mvnw spring-boot:run`