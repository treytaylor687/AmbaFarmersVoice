package com.example.ambafamersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ambafamersvoice.BusinessVideos.NationalLockdownStrategy;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BusinessPage extends AppCompatActivity {
    //Button down;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    Download downloader = new Download();
    Button down;

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
        down = findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                storageReference = firebaseStorage.getInstance().getReference();
                ref = storageReference.child("national_lockdown_strategy.mp4");
                downloader.download(BusinessPage.this, ref, "national_lockdown_strategy", "mp4");
            }
        });
    }
}

