[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/tc38IXJF)
# 📚 Trabajo Práctico: Sistema de Gestión de Biblioteca Digital (Java 21+)

## 📌 Objetivo General

Desarrollar un sistema de gestión de biblioteca digital que implemente los cinco principios SOLID, programación orientada a objetos, y conceptos avanzados de Java. El sistema deberá manejar diferentes tipos de recursos digitales, préstamos, reservas, y notificaciones en tiempo real.

## 👨‍🎓 Información del Alumno
- **Nombre y Apellido**: Maria Victoria Torres Burgos

## 📋 Requisitos Adicionales

### Documentación del Sistema
Como parte del trabajo práctico, deberás incluir en este README una guía de uso que explique:

## Cómo Funciona el Sistema

### Descripción General de la Arquitectura
El sistema está basado en una arquitectura **MVC (Modelo-Vista-Controlador)**, donde:

- **Modelo**: Incluye las clases que representan los datos del sistema, como `Usuario`, `RecursoDigital`, `Prestamo`, entre otras.
- **Vista**: La interfaz de usuario es completamente interactiva a través de la terminal.
- **Controlador**: Gestiona la lógica de negocio y maneja las interacciones entre los modelos y la vista. Cada funcionalidad del sistema está organizada en menús, como gestión de usuarios, recursos, préstamos, etc.

### Componentes Principales

- **Gestores**: Son clases que manejan la lógica de negocio, como `GestorUsuarios`, `GestorRecursos`, `GestorPrestamos`, `GestorReportes`, etc. Cada uno de estos gestores realiza operaciones específicas en su área (gestionar usuarios, recursos, generar reportes, etc.).

- **Modelo de Datos**: Las clases `Usuario`, `RecursoDigital`, `Prestamo`, `Reserva`, entre otras, representan la información que se maneja en el sistema.

- **Notificaciones**: El sistema envía notificaciones por **email** y **SMS** a los usuarios sobre cambios en sus reservas o préstamos. Esto es gestionado a través de servicios como `NotificacionesServiceEmail` y `NotificacionesServiceSMS`.

- **Reportes**: El sistema permite generar reportes de los **recursos más prestados**, **usuarios más activos**, y **estadísticas por categoría**, todo ello de forma concurrente usando `ExecutorService` para tareas en segundo plano.
   - Flujo de trabajo del sistema

### Flujo de Trabajo del Sistema

1. **Inicio**: El sistema comienza con un menú principal donde el usuario puede elegir gestionar usuarios, recursos, préstamos, reservas, generar reportes, o salir de la aplicación.
2. **Gestión de Usuarios**: Permite agregar, eliminar, buscar y listar usuarios registrados en el sistema.
3. **Gestión de Recursos**: Aquí se pueden agregar y eliminar recursos como libros, revistas, y audiolibros.
4. **Gestión de Préstamos y Reservas**: Los usuarios pueden realizar préstamos de recursos disponibles y hacer reservas si el recurso no está disponible en el momento.
5. **Generación de Reportes**: Los reportes se generan en segundo plano y muestran estadísticas sobre los recursos más prestados, los usuarios más activos, y la distribución de préstamos por categorías.

## Cómo Ponerlo en Funcionamiento

### Requisitos Previos

- **Java 8 o superior**: El sistema está desarrollado en Java, por lo que necesitas tener instalada la versión 8 o superior del JDK.
- **IDE recomendado**: Puedes usar cualquier IDE para Java como IntelliJ IDEA, Eclipse o NetBeans.

### Instrucciones de Instalación

1. Clonar el repositorio:
```bash
git clone git@github.com:um-programacion-ii/programacion-2-trabajo-practico-2-vickytorresburgos.git
```
2. Navegar al repositorio y compilar
```
cd SistemaGestionBiblioteca
javac src/*.java
```
3. Ejecutar el programa
```
java -cp src Main
```
O ejecutando la clase Main.java con tu IDE.

2. **Menú Principal**

   Una vez que la aplicación se ejecute, verás el menú principal en la consola. Aquí podrás interactuar con las diferentes funcionalidades del sistema, como la gestión de usuarios, recursos, préstamos, reservas, reportes, etc.

## Cómo Probar Cada Aspecto Desarrollado

### Ejemplos de Uso

#### Gestión de Usuarios

1. **Agregar un Usuario**:
    - Selecciona la opción **1** en el menú de usuarios.
    - Ingresa el nombre, email y teléfono del usuario, y elige el medio de notificación (Email/SMS).

2. **Buscar un Usuario**:
    - Selecciona la opción **3** en el menú de usuarios.
    - Ingresa el ID del usuario para obtener la información del usuario (nombre, email, teléfono).

#### Gestión de Recursos

1. **Agregar un Recurso**:
    - Selecciona la opción de agregar recurso en el menú correspondiente.
    - El sistema te pedirá ingresar detalles como el título y tipo de recurso (libro, revista, audiolibro).

2. **Listar Recursos**:
    - Selecciona la opción correspondiente para listar todos los recursos disponibles en el sistema.

#### Reportes

1. **Reporte de Recursos Más Prestados**:
    - Selecciona la opción de reporte y el sistema mostrará los 5 recursos más prestados.

2. **Reporte de Usuarios Más Activos**:
    - Selecciona la opción de reporte de usuarios más activos.

### Casos de Prueba

1. **Prueba de Gestión de Usuarios**:
    - Crear un usuario, buscarlo, eliminarlo, y luego intentar buscarlo de nuevo para asegurar que fue eliminado correctamente.

2. **Prueba de Gestión de Recursos**:
    - Agregar un recurso, luego realizar un préstamo y verificar que el recurso esté marcado como prestado.

3. **Prueba de Reportes**:
    - Generar un reporte de los recursos más prestados y verificar que los resultados sean correctos.

#### Flujo de Trabajo Completo

1. **Agregar un Usuario**: Agregar un usuario con su información.
2. **Agregar un Recurso**: Agregar un libro como recurso.
3. **Realizar un Préstamo**: Hacer un préstamo de ese libro a un usuario.
4. **Generar Reportes**: Generar un reporte de los recursos más prestados.

La guía debe ser clara, concisa y permitir a cualquier usuario entender y probar el sistema. Se valorará especialmente:
- La claridad de las instrucciones
- La completitud de la documentación
- La organización de la información
- La inclusión de ejemplos prácticos

### Prueba de Funcionalidades

#### 1. Gestión de Recursos
- **Agregar Libro**: 
  - Proceso para agregar un nuevo libro al sistema
  - Verificación de que el libro se agregó correctamente
  - Validación de los datos ingresados

- **Buscar Recurso**:
  - Proceso de búsqueda de recursos
  - Verificación de resultados de búsqueda
  - Manejo de casos donde no se encuentran resultados

- **Listar Recursos**:
  - Visualización de todos los recursos
  - Filtrado por diferentes criterios
  - Ordenamiento de resultados

#### 2. Gestión de Usuarios
- **Registrar Usuario**:
  - Proceso de registro de nuevos usuarios
  - Validación de datos del usuario
  - Verificación del registro exitoso

- **Buscar Usuario**:
  - Proceso de búsqueda de usuarios
  - Visualización de información del usuario
  - Manejo de usuarios no encontrados

#### 3. Préstamos
- **Realizar Préstamo**:
  - Proceso completo de préstamo
  - Verificación de disponibilidad
  - Actualización de estados

- **Devolver Recurso**:
  - Proceso de devolución
  - Actualización de estados
  - Liberación del recurso

#### 4. Reservas
- **Realizar Reserva**:
  - Proceso de reserva de recursos
  - Gestión de cola de reservas
  - Notificación de disponibilidad

#### 5. Reportes
- **Ver Reportes**:
  - Generación de diferentes tipos de reportes
  - Visualización de estadísticas
  - Exportación de datos

#### 6. Alertas
- **Verificar Alertas**:
  - Sistema de notificaciones
  - Diferentes tipos de alertas
  - Gestión de recordatorios

### Ejemplos de Prueba
1. **Flujo Completo de Préstamo**:
   - Registrar un usuario
   - Agregar un libro
   - Realizar un préstamo
   - Verificar el estado del recurso
   - Devolver el recurso
   - Verificar la actualización del estado

2. **Sistema de Reservas**:
   - Registrar dos usuarios
   - Agregar un libro
   - Realizar una reserva con cada usuario
   - Verificar la cola de reservas
   - Procesar las reservas

3. **Alertas y Notificaciones**:
   - Realizar un préstamo
   - Esperar a que se acerque la fecha de vencimiento
   - Verificar las alertas generadas
   - Probar la renovación del préstamo

## 🧩 Tecnologías y Herramientas

- Java 21+ (LTS)
- Git y GitHub
- GitHub Projects
- GitHub Issues
- GitHub Pull Requests

## 📘 Etapas del Trabajo

### Etapa 1: Diseño Base y Principios SOLID
- **SRP**: 
  - Crear clase `Usuario` con atributos básicos (nombre, ID, email)
  - Crear clase `RecursoDigital` como clase base abstracta
  - Implementar clase `GestorUsuarios` separada de `GestorRecursos`
  - Cada clase debe tener una única responsabilidad clara
  - Implementar clase `Consola` para manejar la interacción con el usuario

- **OCP**: 
  - Diseñar interfaz `RecursoDigital` con métodos comunes
  - Implementar clases concretas `Libro`, `Revista`, `Audiolibro`
  - Usar herencia para extender funcionalidad sin modificar código existente
  - Ejemplo: agregar nuevo tipo de recurso sin cambiar clases existentes
  - Implementar menú de consola extensible para nuevos tipos de recursos

- **LSP**: 
  - Asegurar que todas las subclases de `RecursoDigital` puedan usarse donde se espera `RecursoDigital`
  - Implementar métodos comunes en la clase base
  - Validar que el comportamiento sea consistente en todas las subclases
  - Crear métodos de visualización en consola para todos los tipos de recursos

- **ISP**: 
  - Crear interfaz `Prestable` para recursos que se pueden prestar
  - Crear interfaz `Renovable` para recursos que permiten renovación
  - Implementar solo las interfaces necesarias en cada clase
  - Diseñar menús de consola específicos para cada tipo de operación

- **DIP**: 
  - Crear interfaz `ServicioNotificaciones`
  - Implementar `ServicioNotificacionesEmail` y `ServicioNotificacionesSMS`
  - Usar inyección de dependencias en las clases que necesitan notificaciones
  - Implementar visualización de notificaciones en consola

### Etapa 2: Gestión de Recursos y Colecciones
- Implementar colecciones:
  - Usar `ArrayList<RecursoDigital>` para almacenar recursos
  - Usar `Map<String, Usuario>` para gestionar usuarios
  - Implementar métodos de búsqueda básicos
  - Crear menú de consola para gestión de recursos

- Crear servicios de búsqueda:
  - Implementar búsqueda por título usando Streams
  - Implementar filtrado por categoría
  - Crear comparadores personalizados para ordenamiento
  - Diseñar interfaz de consola para búsquedas con filtros

- Sistema de categorización:
  - Crear enum `CategoriaRecurso`
  - Implementar método de asignación de categorías
  - Crear búsqueda por categoría
  - Mostrar categorías disponibles en consola

- Manejo de excepciones:
  - Crear `RecursoNoDisponibleException`
  - Crear `UsuarioNoEncontradoException`
  - Implementar manejo adecuado de excepciones en los servicios
  - Mostrar mensajes de error amigables en consola

### Etapa 3: Sistema de Préstamos y Reservas
- Implementar sistema de préstamos:
  - Crear clase `Prestamo` con atributos básicos
  - Implementar lógica de préstamo y devolución
  - Manejar estados de los recursos (disponible, prestado, reservado)
  - Diseñar menú de consola para préstamos

- Sistema de reservas:
  - Crear clase `Reserva` con atributos necesarios
  - Implementar cola de reservas usando `BlockingQueue`
  - Manejar prioridad de reservas
  - Mostrar estado de reservas en consola

- Notificaciones:
  - Implementar sistema básico de notificaciones
  - Crear diferentes tipos de notificaciones
  - Usar `ExecutorService` para enviar notificaciones
  - Mostrar notificaciones en consola

- Concurrencia:
  - Implementar sincronización en operaciones de préstamo
  - Usar `synchronized` donde sea necesario
  - Manejar condiciones de carrera
  - Mostrar estado de operaciones concurrentes en consola

### Etapa 4: Reportes y Análisis
- Generar reportes básicos:
  - Implementar reporte de recursos más prestados
  - Crear reporte de usuarios más activos
  - Generar estadísticas de uso por categoría
  - Diseñar visualización de reportes en consola

- Sistema de alertas:
  - Implementar alertas por vencimiento de préstamos:
    - Crear clase `AlertaVencimiento` que monitorea fechas de devolución
    - Implementar lógica de recordatorios (1 día antes, día del vencimiento)
    - Mostrar alertas en consola con formato destacado
    - Permitir renovación desde la alerta
  
  - Crear notificaciones de disponibilidad:
    - Implementar `AlertaDisponibilidad` para recursos reservados
    - Notificar cuando un recurso reservado está disponible
    - Mostrar lista de recursos disponibles en consola
    - Permitir préstamo inmediato desde la notificación
  
  - Manejar recordatorios automáticos:
    - Implementar sistema de recordatorios periódicos
    - Crear diferentes niveles de urgencia (info, warning, error)
    - Mostrar historial de alertas en consola
    - Permitir configuración de preferencias de notificación

- Concurrencia en reportes:
  - Implementar generación de reportes en segundo plano
  - Usar `ExecutorService` para tareas asíncronas
  - Manejar concurrencia en acceso a datos
  - Mostrar progreso de generación de reportes en consola

## 📋 Detalle de Implementación

### 1. Estructura Base
```java
// Interfaces principales
public interface RecursoDigital {
    String getIdentificador();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}

public interface Prestable {
    boolean estaDisponible();
    LocalDateTime getFechaDevolucion();
    void prestar(Usuario usuario);
}

public interface Notificable {
    void enviarNotificacion(String mensaje);
    List<Notificacion> getNotificacionesPendientes();
}

// Clase base abstracta
public abstract class RecursoBase implements RecursoDigital, Prestable {
    // Implementación común
}
```

### 2. Gestión de Biblioteca
```java
public class GestorBiblioteca {
    private final Map<String, RecursoDigital> recursos;
    private final List<Prestamo> prestamos;
    private final ExecutorService notificador;
    // Implementación de gestión
}
```

### 3. Sistema de Préstamos
```java
public class SistemaPrestamos {
    private final BlockingQueue<SolicitudPrestamo> colaSolicitudes;
    private final ExecutorService procesadorPrestamos;
    // Implementación de préstamos
}
```

## ✅ Entrega y Flujo de Trabajo con GitHub

1. **Configuración del Repositorio**
   - Proteger la rama `main`
   - Crear template de Issues y Pull Requests

2. **Project Kanban**
   - `To Do`
   - `In Progress`
   - `Code Review`
   - `Done`

3. **Milestones**
   - Etapa 1: Diseño Base
   - Etapa 2: Gestión de Recursos
   - Etapa 3: Sistema de Préstamos
   - Etapa 4: Reportes

4. **Issues y Pull Requests**
   - Crear Issues detallados para cada funcionalidad
   - Asociar cada Issue a un Milestone
   - Implementar en ramas feature
   - Revisar código antes de merge

## 📝 Ejemplo de Issue

### Título
Implementar sistema de préstamos concurrente

### Descripción
Crear el sistema de préstamos que utilice hilos y el patrón productor-consumidor para procesar solicitudes de préstamo en tiempo real.

#### Requisitos
- Implementar `BlockingQueue` para solicitudes de préstamo
- Crear procesador de solicitudes usando `ExecutorService`
- Implementar sistema de notificaciones
- Asegurar thread-safety en operaciones de préstamo

#### Criterios de Aceptación
- [ ] Sistema procesa préstamos concurrentemente
- [ ] Manejo adecuado de excepciones
- [ ] Documentación de diseño

### Labels
- `enhancement`
- `concurrency`

## ✅ Requisitos para la Entrega

- ✅ Implementación completa de todas las etapas
- ✅ Código bien documentado
- ✅ Todos los Issues cerrados
- ✅ Todos los Milestones completados
- ✅ Pull Requests revisados y aprobados
- ✅ Project actualizado

> ⏰ **Fecha de vencimiento**: 23/04/2025 a las 13:00 hs

## 📚 Recursos Adicionales

- Documentación oficial de Java 21
- Guías de estilo de código
- Ejemplos de implementación concurrente
- Patrones de diseño aplicados

## 📝 Consideraciones Éticas

### Uso de Inteligencia Artificial
El uso de herramientas de IA en este trabajo práctico debe seguir las siguientes pautas:

1. **Transparencia**
   - Documentar claramente qué partes del código fueron generadas con IA
   - Explicar las modificaciones realizadas al código generado
   - Mantener un registro de las herramientas utilizadas

2. **Aprendizaje**
   - La IA debe usarse como herramienta de aprendizaje, no como reemplazo
   - Comprender y ser capaz de explicar el código generado
   - Utilizar la IA para mejorar la comprensión de conceptos

3. **Integridad Académica**
   - El trabajo final debe reflejar tu aprendizaje y comprensión personal
   - No se permite la presentación de código generado sin comprensión
   - Debes poder explicar y defender cualquier parte del código

4. **Responsabilidad**
   - Verificar la corrección y seguridad del código generado
   - Asegurar que el código cumple con los requisitos del proyecto
   - Mantener la calidad y estándares de código establecidos

5. **Desarrollo Individual**
   - La IA puede usarse para facilitar tu proceso de aprendizaje
   - Documentar tu proceso de desarrollo y decisiones tomadas
   - Mantener un registro de tu progreso y aprendizaje

### Consecuencias del Uso Inadecuado
El uso inadecuado de IA puede resultar en:
- Calificación reducida o nula
- Sanciones académicas
- Pérdida de oportunidades de aprendizaje
- Impacto negativo en tu desarrollo profesional

## 📝 Licencia

Este trabajo es parte del curso de Programación Avanzada de Ingeniería en Informática. Uso educativo únicamente.