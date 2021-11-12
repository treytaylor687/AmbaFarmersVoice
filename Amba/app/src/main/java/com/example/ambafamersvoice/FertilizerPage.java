package com.example.ambafamersvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ambafamersvoice.FertilizerVideos.HumanUrineFertilizer2;
import com.example.ambafamersvoice.FertilizerVideos.UrinePissFertilizer2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FertilizerPage extends AppCompatActivity {
    ImageView rImage;
    Button downButton0;
    Button downButton1;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer_page);
        downButton0 = findViewById(R.id.downloadbutton0);
        downButton1 = findViewById(R.id.downloadbutton1);

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

        downButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download0();
            }
        });

        downButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download1();
            }
        });
    }

    public void download0() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("How to Make Your Own Organic Fertilizer With Urine (Piss) Part 2.mp4");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(FertilizerPage.this, "How to Make Your Own Organic Fertilizer With Urine (Piss) Part 2", "mp4", Environment.DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void download1() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("How to make your own organic Fertilizer using Human Urine Episode 2.mp4");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(FertilizerPage.this, "How to make your own organic Fertilizer using Human Urine Episode 2", "mp4", Environment.DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);
        downloadManager.enqueue(request);
    }
}