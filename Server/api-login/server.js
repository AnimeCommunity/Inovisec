const express = require("express");
const app = express();
app.use(express.json());

// Ruta de login
app.post("/login", (req, res) => {
  const { email, password } = req.body;

  if (email && password) {
    // Simula inicio de sesion exitoso
    res.json({
      success: true,
      message: "Inicio de sesión exitoso",
      coords: { lat: 4.711, lng: -74.072 } // Bogota
    });
  } else {
    res.json({
      success: false,
      message: "Credenciales inválidas"
    });
  }
});

// Puerto
const PORT = 3000;
app.listen(PORT, "0.0.0.0", () => console.log(`Servidor corriendo en http://0.0.0.0:${PORT}`));

