# BREVUNI — MASTER

> Documento maestro del proyecto **Brevuni**.  
> Última actualización: **30/09/2026**.

---

## 1. Identidad del proyecto

**Nombre:** Brevuni

**Concepto:** aplicación Android para contar efectivo de forma rápida, cómoda, minimalista y moderna.

### Propuesta principal

Brevuni debe permitir contar dinero físico de forma extremadamente sencilla:

- Tocar una denominación para **sumar**.
- Mantener pulsado para **restar**.
- Ver el **total inmediatamente**.
- Poder **deshacer** una acción rápidamente.
- Separar visualmente **billetes y monedas**.
- Consultar un **historial** de conteos.
- Funcionar **offline**.
- No exigir registro obligatorio.
- Soportar **multidioma** y **multimoneda**.

El objetivo es abrir la aplicación y empezar a contar sin pasos innecesarios.

---

## 2. Navegación principal

La navegación queda confirmada con una barra inferior permanente de tres destinos:

1. **💰 Contar**
2. **📋 Historial**
3. **⋮ Más**

### Reglas

- La barra inferior debe mantenerse coherente en las pantallas actuales y futuras.
- Los iconos se mantienen consistentes entre idiomas.
- Los textos se traducen según el idioma seleccionado.
- No introducir navegación superior redundante cuando la barra inferior ya resuelva la navegación.

---

## 3. Pantalla Contar

Es la pantalla principal de Brevuni.

### Objetivo

Permitir introducir rápidamente las cantidades de cada denominación y obtener el total.

### Interacción

- **Toque:** suma una unidad.
- **Mantener pulsado:** resta una unidad.
- El total se actualiza inmediatamente.
- Debe existir una forma rápida de **deshacer** la última acción.

### Organización

Las denominaciones se separan claramente entre:

- **Billetes**
- **Monedas**

La interfaz debe priorizar rapidez, legibilidad y facilidad de uso con una sola mano.

### Resultado

El total debe permanecer claramente visible y actualizarse sin necesidad de pulsar un botón de cálculo.

---

## 4. Historial

El historial permitirá consultar conteos realizados anteriormente.

### Esqueleto funcional

- Lista de conteos anteriores.
- Fecha y hora.
- Importe total.
- Acceso al detalle de un conteo.
- Eliminación de un conteo.

La definición detallada de campos y acciones puede ampliarse posteriormente si resulta necesaria.

---

## 5. Más

El apartado **Más** contiene:

- ⚙️ **Configuración**
- 📤 **Compartir Brevuni**
- ⭐ **Valorar Brevuni**
- ℹ️ **Acerca de Brevuni**

### Acerca de Brevuni

Dentro de **Acerca de Brevuni** estarán:

- Versión de la aplicación.
- 🔒 **Política de privacidad**.
- Información básica de Brevuni.
- Licencias de código abierto, si procede.

La política de privacidad **no será un elemento independiente de primer nivel dentro de Más**.

---

## 6. Configuración

Configuración queda reservada a:

### Apariencia

Preferencias visuales de la aplicación.

Debe contemplar:

- modo claro,
- modo oscuro,
- configuración del sistema.

### Moneda

Permite seleccionar la moneda utilizada para el conteo.

### Conteo

Preferencias relacionadas con el funcionamiento del contador. La estructura exacta queda pendiente de definición.

### Idioma

Permite seleccionar el idioma de la aplicación.

La arquitectura debe permitir añadir idiomas posteriormente sin modificar la lógica principal.

---

## 7. Principios de UX

### Abrir y contar

La pantalla principal debe permitir comenzar a contar inmediatamente.

### Pocos pasos

Las operaciones habituales no deben requerir navegar por menús.

### Feedback inmediato

Cada acción debe reflejarse inmediatamente en la interfaz.

### Corrección sencilla

Un toque accidental debe poder corregirse rápidamente mediante resta o deshacer.

### Legibilidad

El total y las cantidades deben ser fáciles de identificar durante un uso rápido.

### Consistencia

Los mismos patrones visuales y de interacción deben mantenerse en toda la aplicación.

---

## 8. Multidioma

Brevuni debe diseñarse desde el principio para soportar varios idiomas.

Reglas:

- No introducir textos importantes directamente en código.
- Todas las cadenas deben estar preparadas para traducción.
- El cambio de idioma debe afectar a toda la interfaz.
- Los iconos principales deben mantenerse consistentes.
- Las traducciones largas no deben romper el diseño.

---

## 9. Multimoneda

La aplicación debe poder trabajar con diferentes monedas.

La moneda seleccionada debe utilizarse coherentemente en:

- pantalla de conteo,
- total,
- historial,
- configuración,
- futuras funciones relacionadas con importes.

La lógica monetaria debe estar separada de la presentación para facilitar futuras ampliaciones.

---

## 10. Funcionamiento offline

El conteo básico y las funciones locales no deben depender de Internet.

Las funciones que requieran servicios externos deben mantenerse separadas del núcleo de conteo.

---

## 11. Privacidad

Principios previstos:

- No exigir registro obligatorio.
- Priorizar el almacenamiento y procesamiento local para las funciones básicas.
- Mantener separadas las funciones que requieran servicios externos.
- Hacer accesible la política de privacidad desde **Más → Acerca de Brevuni**.

La política definitiva debe reflejar exactamente los datos que procese la versión final de la aplicación.

---

## 12. Diseño visual

El diseño visual está en fase de trabajo.

Objetivos generales:

- apariencia moderna,
- interfaz limpia,
- buena legibilidad,
- soporte claro/oscuro,
- navegación inferior consistente,
- jerarquía visual evidente,
- interacción rápida.

Las decisiones visuales definitivas se incorporarán a este documento cuando queden aprobadas.

---

## 13. Icono y Google Play

Se está trabajando en:

- icono de Brevuni,
- portada/material gráfico de Google Play,
- identidad visual general.

Las decisiones definitivas se añadirán cuando queden aprobadas.

---

## 14. Arquitectura funcional

```
BREVUNI
│
├── 💰 Contar
│   ├── Billetes
│   ├── Monedas
│   ├── Total
│   ├── Deshacer
│   └── Interacción suma/resta
│
├── 📋 Historial
│   ├── Lista de conteos
│   └── Detalle de conteo
│
└── ⋮ Más
    ├── ⚙️ Configuración
    │   ├── Apariencia
    │   ├── Moneda
    │   ├── Conteo
    │   └── Idioma
    │
    ├── 📤 Compartir Brevuni
    ├── ⭐ Valorar Brevuni
    └── ℹ️ Acerca de Brevuni
        └── 🔒 Política de privacidad
```

---

## 15. Esqueleto funcional — estado

**Estado: En curso**

### Pantallas y acciones definidas

- [x] Contar.
- [x] Historial — lista.
- [x] Historial — detalle.
- [x] Más.
- [x] Configuración.
- [x] Apariencia.
- [x] Moneda.
- [x] Conteo.
- [x] Idioma.
- [x] Acerca de Brevuni.
- [x] Política de privacidad ubicada dentro de Acerca de Brevuni.
- [x] Compartir Brevuni como acción del sistema.
- [x] Valorar Brevuni como acción hacia Google Play.

El esqueleto funcional queda definido a nivel de navegación y propósito. El detalle visual y el contenido exacto de cada pantalla se cerrarán antes o durante la implementación.

---

## 16. Primer MVP funcional

**Estado: En curso** — 30/09/2026

Se ha creado una primera implementación Android funcional y deliberadamente sencilla para poder probar el producto antes de aplicar el diseño visual definitivo.

Incluye:

- Pantalla Contar.
- Billetes y monedas.
- Toque para sumar.
- Pulsación larga para restar.
- Total inmediato.
- Deshacer.
- Guardar conteos en el historial durante la sesión.
- Historial básico.
- Navegación inferior.
- Apartado Más.
- Compartir mediante el sistema Android.
- Configuración y apartados previstos preparados como estructura del proyecto.
- Base Jetpack Compose / Material 3.
- Workflow de GitHub Actions para generar un APK debug.

### Criterio de esta primera versión

La prioridad es disponer de una aplicación funcional que podamos probar en un dispositivo. El diseño visual definitivo, la persistencia completa, multimoneda, multidioma y el resto del pulido se irán incorporando después de probar este MVP.

---

## 17. Reglas para futuras funcionalidades

Antes de añadir una nueva pantalla o función:

1. Debe aportar una utilidad clara.
2. No debe complicar innecesariamente el flujo principal.
3. Debe respetar la barra inferior.
4. Debe respetar el sistema de idiomas.
5. Debe respetar el sistema de monedas.
6. Debe funcionar correctamente en claro y oscuro.
7. Debe contemplar el funcionamiento offline cuando sea posible.
8. Debe mantener coherencia con el resto de Brevuni.

---

## 18. MVP 1.0

### Imprescindible

- Conteo de efectivo.
- Billetes y monedas.
- Sumar mediante toque.
- Restar mediante pulsación prolongada.
- Total inmediato.
- Deshacer.
- Historial.
- Multimoneda.
- Multidioma.
- Apariencia claro/oscuro/sistema.
- Funcionamiento offline.
- Navegación inferior.
- Configuración.
- Compartir.
- Valorar.
- Política de privacidad.
- Acerca de.

### Pendiente de decidir

- Monetización.
- Funciones adicionales no necesarias para el conteo.
- Elementos visuales definitivos todavía en diseño.

---

## 19. Checklist de lanzamiento

### Producto

- [ ] Pantalla Contar terminada.
- [ ] Historial terminado.
- [ ] Configuración terminada.
- [ ] Navegación inferior terminada.
- [ ] Multimoneda validada.
- [ ] Multidioma validado.
- [ ] Claro/oscuro validado.
- [ ] Funcionamiento offline validado.
- [ ] Deshacer validado.
- [ ] Suma/resta validada.

### Calidad

- [ ] Probar diferentes cantidades.
- [ ] Probar cero.
- [ ] Probar conteos grandes.
- [ ] Probar cambio de moneda.
- [ ] Probar cambio de idioma.
- [ ] Probar cambio de apariencia.
- [ ] Revisar accesibilidad y legibilidad.

### Google Play

- [ ] Nombre definitivo.
- [ ] Icono definitivo.
- [ ] Material gráfico definitivo.
- [ ] Descripción.
- [ ] Capturas.
- [ ] Política de privacidad.
- [ ] Ficha de Play revisada.
- [ ] Versión de producción preparada.

---

## 20. Registro de decisiones

| Fecha | Decisión | Estado |
|---|---|---|
| 30/09/2026 | Nombre del proyecto: **Brevuni** | Realizado |
| 30/09/2026 | Navegación confirmada: **Contar | Historial | Más** | Realizado |
| 30/09/2026 | Más contiene Configuración, Compartir Brevuni, Valorar Brevuni y Acerca de Brevuni | Realizado |
| 30/09/2026 | Política de privacidad pasa a estar dentro de **Acerca de Brevuni** y deja de ser una opción independiente de Más | Realizado |
| 30/09/2026 | Configuración contiene Apariencia, Moneda, Conteo e Idioma | Realizado |
| 30/09/2026 | Esqueleto funcional de pantallas definido | Realizado |
| 30/09/2026 | Diseño visual todavía en fase de trabajo | Pendiente |
| 30/09/2026 | Primera implementación funcional creada para probar el producto antes del diseño definitivo | En curso |
| 30/09/2026 | Primera versión basada en Jetpack Compose / Material 3 | Realizado |
| 30/09/2026 | Monetización todavía pendiente | Pendiente |

---

## 21. Regla del documento maestro

`BREVUNI_MASTER.md` es la referencia principal del proyecto.

Cuando una decisión quede aprobada:

1. Se incorpora al documento.
2. Se elimina o marca como obsoleta cualquier decisión contradictoria.
3. Se registra la fecha.
4. Las futuras implementaciones deben seguir el estado aprobado del documento.

**Estado actual:** documento maestro actualizado con el esqueleto funcional y la nueva ubicación de la política de privacidad.
