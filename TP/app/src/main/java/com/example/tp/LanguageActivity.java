package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LanguageActivity extends AppCompatActivity {

    Button englishButton;
    Button frenchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        englishButton = (Button) findViewById(R.id.englishButton);
        frenchButton = (Button) findViewById(R.id.frenchButton);
        Intent intent = new Intent(this, HomeActivity.class);

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("language", "en-US");
                startActivity(intent);
            }
        });

        frenchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("language", "fr-FR");
                startActivity(intent);
            }
        });
    }
}