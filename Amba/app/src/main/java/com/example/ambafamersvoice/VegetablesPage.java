package com.example.ambafamersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ambafamersvoice.VegetableVideos.DrySeasonVegetables;

public class VegetablesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables_page);

        ImageButton dry_season_vegetables = (ImageButton)findViewById(R.id.btn_dry_season_vegetables);
        dry_season_vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VegetablesPage.this, DrySeasonVegetables.class));
            }
        });
    }
}