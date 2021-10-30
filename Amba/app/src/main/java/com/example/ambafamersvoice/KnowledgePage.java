package com.example.ambafamersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ambafamersvoice.KnowledgeVideos.BuildHutBush2;
import com.example.ambafamersvoice.KnowledgeVideos.CoronaVirusPrevention;
import com.example.ambafamersvoice.KnowledgeVideos.HandSanitizer;
import com.example.ambafamersvoice.KnowledgeVideos.MakeOwnFridge;
import com.example.ambafamersvoice.KnowledgeVideos.RocketStove;
import com.example.ambafamersvoice.KnowledgeVideos.TreatDiarrhea;

public class KnowledgePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_page);

        ImageButton rocket_stove = (ImageButton)findViewById(R.id.btn_rocket_stove);
        rocket_stove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KnowledgePage.this, RocketStove.class));
            }
        });

        ImageButton make_own_fridge = (ImageButton)findViewById(R.id.btn_make_own_fridge);
        make_own_fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KnowledgePage.this, MakeOwnFridge.class));
            }
        });

        ImageButton treat_diarrhea = (ImageButton)findViewById(R.id.btn_treat_diarrhea);
        treat_diarrhea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KnowledgePage.this, TreatDiarrhea.class));
            }
        });

        ImageButton build_hut_bush_2 = (ImageButton)findViewById(R.id.btn_build_hut_bush_2);
        build_hut_bush_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KnowledgePage.this, BuildHutBush2.class));
            }
        });

        ImageButton corona_virus_prevention = (ImageButton)findViewById(R.id.btn_corona_virus_prevention);
        corona_virus_prevention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KnowledgePage.this, CoronaVirusPrevention.class));
            }
        });

        ImageButton hand_sanitizer = (ImageButton)findViewById(R.id.btn_hand_sanitizer);
        hand_sanitizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KnowledgePage.this, HandSanitizer.class));
            }
        });
    }
}