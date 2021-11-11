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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.example.ambafamersvoice.FeedPage;

public class Download extends AppCompatActivity {

    Button down;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    public boolean isDownloaded = false;


    public void download(Context cx, StorageReference ref) {
        //storageReference = firebaseStorage.getInstance().getReference();
        //ref = storageReference.child("AmbaLogo.jpeg");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(cx,"AmbaLogo", "jpeg", Environment.DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                setIsDownloaded(false);
            }
        });

        //System.out.println("------- DOWLOADED? ---------" + getIsDownloaded());
    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);
        downloadManager.enqueue(request);
        setIsDownloaded(true);
    }

    public boolean getIsDownloaded() {
        return isDownloaded;
    }

    public void setIsDownloaded(boolean set) {
        isDownloaded = set;
    }
}
