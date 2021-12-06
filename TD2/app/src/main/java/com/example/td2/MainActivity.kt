package com.example.td2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonsButton = findViewById<Button>(R.id.buttonsLayout)
        val calendarButton = findViewById<Button>(R.id.calendarLayout)
        val constraintLayoutButton = findViewById<Button>(R.id.constraintLayout)
        val meteoButton = findViewById<Button>(R.id.meteoLayout)
        val passwordButton = findViewById<Button>(R.id.passwordLayout)
        val relativeLayoutButton = findViewById<Button>(R.id.relativeLayout)
        val repasButton = findViewById<Button>(R.id.repasLayout)

        buttonsButton.setOnClickListener {
            setContentView(R.layout.buttons)
        }

        passwordButton.setOnClickListener {
            setContentView(R.layout.password)
        }

        calendarButton.setOnClickListener {
            setContentView(R.layout.calendar)
        }

        relativeLayoutButton.setOnClickListener{
            setContentView(R.layout.relative_layout)
        }

        constraintLayoutButton.setOnClickListener {
            setContentView(R.layout.constraint_layout)
        }

        repasButton.setOnClickListener {
            setContentView(R.layout.repas)
        }

        meteoButton.setOnClickListener{
            setContentView(R.layout.meteo)
            val spinner: Spinner = findViewById(R.id.etatDuCiel)

            ArrayAdapter.createFromResource(
                this,
                R.array.etatDuCiel,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        }

    }
}
