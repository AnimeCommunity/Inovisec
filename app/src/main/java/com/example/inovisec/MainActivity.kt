package com.example.inovisec

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Campos vacíos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        val url = "http://192.168.1.6:3000/login" // tu API local
        val json = """{"email":"$email","password":"$password"}"""

        val body = json.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder().url(url).post(body).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string() ?: return
                println("Respuesta completa: $responseBody")

                val jsonResponse = JSONObject(responseBody)
                val success = jsonResponse.optBoolean("success", false)

                if (success) {
                    val coords = jsonResponse.getJSONObject("coords")
                    val lat = coords.getDouble("lat")
                    val lng = coords.getDouble("lng")
                    println("Lat: $lat, Lng: $lng")

                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "Login correcto → $lat , $lng",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@MainActivity, MapActivity::class.java)
                        intent.putExtra("lat", lat)
                        intent.putExtra("lng", lng)
                        startActivity(intent)
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "Credenciales inválidas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }
}
