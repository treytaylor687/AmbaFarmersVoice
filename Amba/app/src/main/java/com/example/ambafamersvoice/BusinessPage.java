package com.example.ambafamersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ambafamersvoice.BusinessVideos.NationalLockdownStrategy;

public class BusinessPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_page);

        ImageButton national_lockdown_strategy = (ImageButton)findViewById(R.id.btn_national_lockdown_strategy);
        national_lockdown_strategy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessPage.this, NationalLockdownStrategy.class));
            }
        });
    }
}