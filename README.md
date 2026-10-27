# Proyecto Kotlin Multiplatform - Aplicación Móvil UCB

## Integrantes del Grupo
- Joel Delgado Churquina
- Dayana Maque Bautista
- Aron Fuentes

---

## Ejercicios Implementados

### 1. E25-2 · Room como fuente de verdad para configuración local
- **Descripción**: Implementación de almacenamiento local con Room utilizando una fuente de verdad reactiva.
- **Componentes**:
  - Entidad `ConfigItem` (`id`, `clave`, `valor`, `timestamp`).
  - DAO `ConfigDao` con inserción, consulta reactiva (`Flow<List<ConfigItem>>`) y eliminación (`deleteAll`, `deleteById`).
  - Siembra automática de 3 registros de ejemplo al primer arranque de la aplicación.
  - Pantalla en Jetpack Compose (`ConfigScreen`) y ViewModel (`ConfigViewModel`) con UI 100% reactiva al Flow.

### 2. E50-4 · WorkManager periódico + Room + Notificación al completar
- **Descripción**: Tareas en segundo plano con WorkManager sincronizadas con Room y notificaciones push locales.
- **Componentes**:
  - Entidad `TablaNovedad` y DAO `NovedadDao` para el historial de novedades.
  - `NovedadWorker` (Worker periódico) que simula la descarga de actualizaciones, guarda los registros en Room y compara con la última ejecución para decidir si se dispara una notificación interna.
  - Manejo robusto de errores en el Worker utilizando `Result.retry()` con control de intentos máximos (`runAttemptCount`).
  - Pantalla de historial reactiva (`NovedadScreen`) leída de Room mediante `Flow`.

---

### Comandos de Compilación (Gradle)
- Compilar APK de Android (Debug): `./gradlew :androidApp:assembleDebug`
- Ejecutar pruebas unitarias de Android: `./gradlew :shared:testAndroidHostTest`
