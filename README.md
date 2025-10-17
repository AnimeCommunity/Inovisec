#  Inovisec — App Android

Aplicación móvil desarrollada en **Kotlin (Android Studio)** que permite simular el inicio de sesión de un usuario, conectándose a una **API REST construida con Express.js**, y visualizar un mapa de Google Maps con las coordenadas recibidas.  
El proyecto fue diseñado como parte de una **prueba técnica de desarrollo móvil.**

##  Características principales

- **Login funcional** con validación de campos y consumo de API REST.  
- **Integración con servidor Node/Express** para autenticación simulada.  
- **Visualización de coordenadas en Google Maps** mediante WebView.  
- **Interfaz moderna**, con colores personalizados (navy y aguamarina).  
- **Compatibilidad con Android 7.0 (API 24)** o superior.  

##  Tecnologías utilizadas

###  Frontend móvil
- **Kotlin**
- **Android Studio**
- **AppCompat / Material Components**
- **OkHttp** (para peticiones HTTP)
- **WebView** (para mostrar Google Maps)

###  Backend (API)
- **Node.js**
- **Express.js**

##  Estructura del proyecto

Inovisec/
│
├── app/
│   ├── java/
│   │   └── com/example/inovisec/
│   │       ├── MainActivity.kt          # Login y consumo de API
│   │       ├── MapActivity.kt           # Vista del mapa
│   │       └── ui/theme/                # Colores y tema general
│   │
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml        # Pantalla de login
│   │   │   └── activity_map.xml         # Pantalla del mapa
│   │   ├── values/
│   │   │   ├── colors.xml               # Paleta navy + aguamarina
│   │   │   ├── themes.xml               # Estilo visual global
│   │   │   └── arrays.xml               # Lista de tipos de vehículo
│   │   └── AndroidManifest.xml
│
└── server/
    └── index.js                         # API Express con ruta /login

##  API Backend (Express.js)

Archivo: index.js

const express = require("express");
const app = express();
app.use(express.json());

app.post("/login", (req, res) => {
  const { email, password } = req.body;

  if (email && password) {
    res.json({
      success: true,
      message: "Inicio de sesión exitoso",
      coords: { lat: 4.711, lng: -74.072 }
    });
  } else {
    res.json({
      success: false,
      message: "Credenciales inválidas"
    });
  }
});

app.listen(3000, () => console.log("Servidor corriendo en http://localhost:3000"));

## 📱 Conexión Android ↔ API

- Para emulador Android: http://10.0.2.2:3000/login
- Para dispositivo físico: http://192.168.1.6:3000/login

##  Paleta de colores personalizada

| Elemento              | Color HEX | Descripción              |
|------------------------|-----------|--------------------------|
| Fondo general          | #001F3F | Azul navy                |
| Fondo de inputs/marco  | #001633 | Navy oscuro              |
| Botones                | #1ABC9C | Verde aguamarina         |
| Texto principal        | #FFFFFF | Blanco puro              |

##  Funcionamiento básico

1. El usuario ingresa su correo y contraseña.
2. La app envía la información al backend.
3. Si la respuesta es exitosa, se obtienen las coordenadas.
4. Se abre el mapa centrado en las coordenadas (Bogotá).

##  Dependencias clave

implementation("com.squareup.okhttp3:okhttp:4.12.0")
implementation("androidx.appcompat:appcompat:1.7.0")
implementation("androidx.activity:activity-ktx:1.9.2")
implementation("androidx.core:core-ktx:1.13.1")
implementation("androidx.constraintlayout:constraintlayout:2.2.0")

##  Ejecución

1️⃣ Iniciar servidor backend:
cd server
node index.js

2️⃣ Ejecutar app en Android Studio:
Conecta el dispositivo o emulador y presiona ▶️ Run App.

##  Autor

**Santiago Pineda**  
Desarrollador de software especializado en desarrollo web, móvil y entornos interactivos.  
📍 Bogotá, Colombia  
💼 LinkedIn: [https://linkedin.com](https://www.linkedin.com/in/daniel-santiago-pineda-garnica-ab214894/)
