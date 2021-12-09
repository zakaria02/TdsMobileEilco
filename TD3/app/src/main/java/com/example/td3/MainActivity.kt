package com.example.td3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Le bouton lancé dè
        val rollButton = findViewById<Button>(R.id.rollButton)
        // le textview dont on va afficher la valeur du dè 1
        var dice1Text = findViewById<TextView>(R.id.de1)
        // le textview dont on va afficher la valeur du dè 2
        var dice2Text = findViewById<TextView>(R.id.de2)
        // le nombre de face choisi par l'utilisateur
        var facesNumber = findViewById<EditText>(R.id.rollMax)

        // ajouter l'événement qui va s'exécuter quand on clique sur le bouton
        rollButton.setOnClickListener {
            // stocker la valeur max choisi par l'utilisateur
            var maxFaces:String = facesNumber.text.toString()
            // Convertir la valeur en int
            var max:Int = 0
            // Vérification de l'input de l'utilisateur :
            // L'entrée ne doit pas être vide
            if(maxFaces.isNotEmpty()){
                // Ajouter un try catch pour gérer les exceptions (par exemple si l'utilisateur a écrit just "-" sans nombre)
                try {
                    max = maxFaces.toInt()
                }catch (e: Exception){
                    println("error : " + e.message)
                    max=0
                }
            }
            // Vérification de la valeur max
            if(max <= 0){
                // Si max inférieur ou égale à zéro, afficher ce message.
                Toast.makeText(applicationContext, "Le nombre de face doit être supèrieur à zéro", Toast.LENGTH_SHORT).show()
            }else{
                //Sinon continue le processs
                // la valeur du premier dè (entre 1 et le max choisi par l'utilisateur)
                val dice1 = (1..max).random()
                // affichage de la valeur du premier dè
                dice1Text.text = dice1.toString()
                // la valeur du deuxième dè (entre 1 et le max choisi par l'utilisateur)
                val dice2 = (1..max).random()
                // affichade de la valeur du deuxième dè
                dice2Text.text = dice2.toString()
            }
        }
    }
}