package com.example.td4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setTitle(getLocalClassName())

        var okButton = findViewById<Button>(R.id.okButton)

        okButton.setOnClickListener{
            finish()
        }
    }
}