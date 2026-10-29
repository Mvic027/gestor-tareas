# Gestor de Tareas

Aplicación de consola en Java para gestionar tareas pendientes. Permite añadir, listar, completar y eliminar tareas, con fecha límite opcional, aviso de tareas atrasadas y guardado automático en fichero.

Proyecto personal desarrollado para practicar programación orientada a objetos, manejo de ficheros y buenas prácticas en Java.

## Funcionalidades

- **Añadir tareas** con descripción y fecha límite opcional (formato `dd/mm/aaaa`)
- **Listar tareas** mostrando su estado y fecha
- **Completar tareas** por su id
- **Eliminar tareas** por su id, renumerando automáticamente las restantes
- **Aviso de tareas atrasadas** cuando la fecha límite ya ha pasado y la tarea no está completada
- **Guardado automático** en `tareas.txt` tras cada cambio, por lo que los datos no se pierden al cerrar el programa
- **Validación de entrada**: el programa no se cierra si se introducen letras en lugar de números, fechas inválidas o descripciones vacías

## Requisitos

- Java 17 o superior
- Maven

## Cómo ejecutarlo

### Desde un IDE (Eclipse, IntelliJ...)

1. Clona el repositorio:
   git clone https://github.com/Mvic027/gestor-tareas.git
2. Importa el proyecto como proyecto Maven.
3. Ejecuta la clase `Main`.

### Desde la terminal


git clone https://github.com/Mvic027/gestor-tareas.git
cd gestor-tareas
mvn compile
java -cp target/classes Main


## Ejemplo de uso

```
=== GESTOR DE TAREAS ===
1. Añadir tarea
2. Listar tareas
3. Completar Tarea
4. Eliminar Tarea
0. Salir
Elige una opción: 1
Añada una descripción a la tarea que desea añadir: Estudiar ISO
Fecha límite (dd/mm/aaaa) o Enter para omitir: 03/11/2026
Tarea creada: [1] [ ] Estudiar ISO (03/11/2026)

Elige una opción: 2
Listando tareas:
[1] [ ] Estudiar ISO (03/11/2026)
[2] [x] Entregar práctica de redes (20/10/2026)
[3] [ ] Pagar matrícula (15/10/2026) ¡ATRASADA!
```

## Estructura del proyecto

```
src/main/java
├── Main.java                  → Menú e interacción con el usuario
├── modelo
│   ├── Tarea.java             → Representa una tarea (descripción, estado, fecha límite)
│   └── GestorTareas.java      → Lógica de gestión de la lista de tareas
└── persistencia
    └── AlmacenTareas.java     → Lectura y escritura de las tareas en fichero
```

El proyecto separa la interfaz de usuario (`Main`), la lógica (`modelo`) y el almacenamiento (`persistencia`), de forma que cada parte puede modificarse sin afectar a las demás.

## Tecnologías

- Java 17
- Maven
- `java.nio.file` para la persistencia en fichero
- `java.time` (`LocalDate`) para la gestión de fechas

## Próximas mejoras

- Guardado en base de datos SQLite
- Editar la descripción o la fecha de una tarea
- Filtrar entre tareas pendientes y completadas
- Ordenar tareas por fecha límite
- Prioridades