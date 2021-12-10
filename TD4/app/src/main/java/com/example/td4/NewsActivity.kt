package com.example.td4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setTitle(getLocalClassName())

        var detailsButton = findViewById<Button>(R.id.detailsButton)
        var logoutButton = findViewById<Button>(R.id.logoutButton)
        var aboutButton = findViewById<Button>(R.id.aboutButton)
        var loginTextValue = findViewById<TextView>(R.id.loginTextValue)

        if (intent.hasExtra("login")) {
            val login = intent.getStringExtra("login")
            loginTextValue.text = "Bonjour $login"
        }

        detailsButton.setOnClickListener{
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        aboutButton.setOnClickListener{
            val url = "https://news.google.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}