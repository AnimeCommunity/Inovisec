package com.example.inovisec

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        webView = findViewById(R.id.webViewMap)
        progressBar = findViewById(R.id.progressBar)

        val lat = intent.getDoubleExtra("lat", 4.7110)
        val lng = intent.getDoubleExtra("lng", -74.0721)
        val zoom = 14

        val mapsUrl = "https://www.google.com/maps?q=$lat,$lng&z=$zoom"
        println("MAPS URL: $mapsUrl")

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                // Bloquea cualquier intento de abrir "intent://" o "maps://"
                if (url != null && (url.startsWith("intent://") || url.startsWith("maps://"))) {
                    return true // no hagas nada, evita el error
                }
                view?.loadUrl(url ?: "")
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
            }
        }

        progressBar.visibility = View.VISIBLE
        webView.loadUrl(mapsUrl)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }
}
