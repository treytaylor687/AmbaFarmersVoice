package com.example.ambafamersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ambafamersvoice.FertilizerVideos.HumanUrineFertilizer2;
import com.example.ambafamersvoice.FertilizerVideos.UrinePissFertilizer2;

public class FertilizerPage extends AppCompatActivity {
    ImageView rImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer_page);

        ImageButton urine_piss_fertilizer_2 = (ImageButton)findViewById(R.id.btn_urine_piss_fertilizer_2);
        urine_piss_fertilizer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FertilizerPage.this, UrinePissFertilizer2.class));
            }
        });

        ImageButton human_urine_fertilizer_2 = (ImageButton)findViewById(R.id.btn_human_urine_fertilizer_2);
        human_urine_fertilizer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FertilizerPage.this, HumanUrineFertilizer2.class));
            }
        });
    }
}