package com.example.ambafamersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ambafamersvoice.HarvestVideos.PreserveFoodDocu2;

public class HarvestPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_page);

        ImageButton preserve_food_docu_2 = (ImageButton)findViewById(R.id.btn_preserve_food_docu_2);
        preserve_food_docu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HarvestPage.this, PreserveFoodDocu2.class));
            }
        });
    }
}