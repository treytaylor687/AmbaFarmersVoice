package com.example.ambafamersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ambafamersvoice.CerealVideos.MakeOwnFridge;
import com.example.ambafamersvoice.CerealVideos.RocketStove;

public class CerealsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cereals_page);

        ImageButton rocket_stove = (ImageButton)findViewById(R.id.btn_rocket_stove);
        rocket_stove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CerealsPage.this, RocketStove.class));
            }
        });

        ImageButton make_own_fridge = (ImageButton)findViewById(R.id.btn_make_own_fridge);
        make_own_fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CerealsPage.this, MakeOwnFridge.class));
            }
        });

    }
}