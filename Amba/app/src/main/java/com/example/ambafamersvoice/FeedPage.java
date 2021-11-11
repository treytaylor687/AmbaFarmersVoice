package com.example.ambafamersvoice;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import com.example.ambafamersvoice.Download;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;



public class FeedPage extends AppCompatActivity {

    Button down;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    Download downloader = new Download();

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);
        down = findViewById(R.id.button);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storageReference = firebaseStorage.getInstance().getReference();
                ref = storageReference.child("AmbaLogo.jpeg");   
                downloader.download(FeedPage.this, ref);
            }
        });
    }
}
