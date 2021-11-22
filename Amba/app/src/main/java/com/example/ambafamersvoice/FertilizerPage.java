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
import android.widget.PopupWindow;

import com.example.ambafamersvoice.FertilizerVideos.HumanUrineFertilizer2;
import com.example.ambafamersvoice.FertilizerVideos.UrinePissFertilizer2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class FertilizerPage extends AppCompatActivity {
    ImageView rImage;
    Button downButton0;
    Button downButton1;
    Button shareButton0;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    boolean isDownloaded0 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer_page);
        downButton0 = findViewById(R.id.downloadbutton0);
        downButton1 = findViewById(R.id.downloadbutton1);
        shareButton0 = findViewById(R.id.shareButton0);

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

        shareButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share0();
            }
        });

    }

    public void share0() {
        if (isDownloaded0) {
            System.out.println("VIDEO IS DOWNLOADED AND READY TO SHARE");
        } else {
            System.out.println("ERROR: VIDEO IS NOT DOWNLOADED");
        }
    }


    public void download0() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("How to Make Your Own Organic Fertilizer With Urine (Piss) Part 2.mp4");
        // Check if the video isn't already downloaded then go ahead and download the video
        if (!isDownloadedAlready("How to Make Your Own Organic Fertilizer With Urine (Piss) Part 2.mp4")) {
            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String url = uri.toString();
                    downloadFile(FertilizerPage.this, "How to Make Your Own Organic Fertilizer With Urine (Piss) Part 2", "mp4", Environment.DIRECTORY_DOWNLOADS, url);
                    isDownloaded0 = true;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } else {
            System.out.println("The video is ALREADY downloaded. No need to download again");
        }
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

    public boolean isDownloadedAlready(String fileName) {
        File downloadFolder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File[] filesListed = downloadFolder.listFiles();
        assert filesListed != null;
        String removePeriod = fileName.replace(".", "");
        //System.out.println("STRING THAT HAS . REMOVED: " + removePeriod);

        for (File i : filesListed) {
            //System.out.println(i.getName() + " ================= comparing to: =================== " + removePeriod);
            if (fileName.equals(i.getName())) {
                //System.out.println("Already downloaded bra");
                return true;
            }
            //System.out.println("NAME OF FILE INSIDE OF DOWNLOADS: " +  i.getName());
        }
        return false;
    }

}
