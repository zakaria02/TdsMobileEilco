package com.example.td4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(getLocalClassName())

        var loginButton = findViewById<Button>(R.id.loginButton)
        var usernameText = findViewById<EditText>(R.id.loginText)


        loginButton.setOnClickListener{
            val intent = Intent(this, NewsActivity::class.java)
            (applicationContext as NewsListApplication).login = usernameText.text.toString()
            intent.putExtra("login", usernameText.text.toString())
            startActivity(intent)
            finish()
        }
    }
}