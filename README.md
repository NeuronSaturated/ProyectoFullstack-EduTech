# ProyectoFullstack-EduTech

Este repositorio contiene todos los microservicios desarrollados para el sistema de gestión de estudiantes de la empresa ficticia **EduTech Innovators SPA**, como parte del proyecto Fullstack de la asignatura **DSY1103**.

---

## Estructura del Proyecto

El proyecto está compuesto por los siguientes microservicios independientes:

| Microservicio           | Descripción                                                   | Puerto |
|-------------------------|---------------------------------------------------------------|--------|
| `estudianteservice`     | Gestión de estudiantes (creación, listado, búsqueda).         | 8081   |
| `cursoservice`          | Administración de cursos disponibles.                         | 8082   |
| `inscripcionservice`    | Inscripciones de estudiantes en cursos.                       | 8083   |
| `evaluacionservice`     | Registro de evaluaciones y puntajes.                          | 8084   |
| `notificacionservice`   | Envío de notificaciones a estudiantes.                        | 8086   |
| `reporteservice`        | Consolidación de datos en reportes a través de REST.          | 8085   |

---

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Maven
- MySQL
- Postman (pruebas)
- Git & GitHub (gestión de versiones)

---

## ¿Cómo ejecutar el proyecto?

1. Asegúrate de tener MySQL y Java 21 correctamente instalados y configurados.
2. Clona este repositorio y abre la carpeta principal con VS Code o tu editor favorito.
3. Crea una base de datos llamada `edutech` (o la que se defina en cada `application.properties`).
4. Configura los accesos a MySQL en cada archivo `application.properties` (usuario y contraseña).
5. Levanta los microservicios de forma individual:

```
bash
cd <nombre-del-servicio>
mvn spring-boot:run
```

**Ejemplo**
```
cd estudianteservice
mvn spring-boot:run
```

---

## Colección de pruebas Postman

Este repositorio incluye una colección de Postman con todas las peticiones necesarias para probar los endpoints de los microservicios.

📂 Archivo: `EduTech Evaluación 2 - API completa.postman_collection.json`

### ¿Cómo usarla?

1. Abre **Postman**.
2. Haz clic en `Import`.
3. Selecciona el archivo `.json` ubicado en la raíz del repositorio.
4. Se cargarán las siguientes carpetas con sus respectivas solicitudes:

   - **Curso**: crear, listar y eliminar cursos.
   - **Estudiante**: registro, búsqueda y eliminación de estudiantes.
   - **Evaluación**: creación y gestión de evaluaciones.
   - **Inscripción**: asignación de estudiantes a cursos.
   - **Notificaciones**: envío de mensajes a estudiantes.
   - **Reporte**: consolidación de inscripciones y evaluaciones.

### Tests integrados

Algunos endpoints contienen scripts de prueba en la pestaña **Tests** de Postman, para validar:

- Código de estado HTTP 200
- Estructura del JSON esperado
- Campos obligatorios presentes



