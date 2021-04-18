package com.example.ambafarmersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Categories extends AppCompatActivity {

    private Button fruitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        fruitButton = (Button) findViewById(R.id.fruitButton);
        fruitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFruitCategory();
            }
        });
    }

    public void openFruitCategory() {
        Intent intent = new Intent(this, Fruit.class);
        startActivity(intent);
    }
}