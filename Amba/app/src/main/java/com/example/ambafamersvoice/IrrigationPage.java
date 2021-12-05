package com.example.ambafamersvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

public class IrrigationPage extends AppCompatActivity {
    private static StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://amba-farmers-voice-d1a51.appspot.com/irrigation");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation_page);

        storageRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference prefix : listResult.getPrefixes()) {
                            System.out.println(prefix);
                        }

                        ScrollView sv = new ScrollView(IrrigationPage.this);
                        sv.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        LinearLayout ll = new LinearLayout(IrrigationPage.this);
                        ll.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                        ll.setOrientation(LinearLayout.VERTICAL);
                        sv.addView(ll);
                        final TextView textView = new TextView(IrrigationPage.this);
                        textView.setText("Irrigation Page");
                        textView.setTextSize(50);
                        ll.addView(textView);
                        if (listResult.getItems().size() == 0) {
                            final TextView textView1 = new TextView(IrrigationPage.this);
                            textView1.setText("There are no videos for this category... yet :)");
                            textView1.setTextSize(40);
                            ll.addView(textView1);
                        }
                        for (StorageReference item : listResult.getItems()) {
                            //  Create and add all of the hyperlinks
                            String input = item.toString().substring(5);

                            item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String VIDEO_SAMPLE=task.getResult().toString();
                                    Log.i("URL",VIDEO_SAMPLE);
                                    System.out.println(VIDEO_SAMPLE);
                                    final TextView textView = new TextView(IrrigationPage.this);
                                    textView.setClickable(true);
                                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                                    String text = "<a href='"+ VIDEO_SAMPLE +"'> "+ item.getName() +" </a>";
                                    textView.setText(Html.fromHtml(text));
                                    textView.setTextSize(27);
                                    ll.addView(textView);

                                    Button btn = new Button(IrrigationPage.this);
                                    btn.setText("Download Video Above");
                                    btn.setTextColor(Color.GREEN);
                                    btn.setBackgroundColor(Color.parseColor("#5844e4"));
                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            btn.startAnimation(AnimationUtils.loadAnimation(IrrigationPage.this,R.anim.shake));
                                            item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String url = uri.toString();
                                                    String fileName = item.getName().replace(".mp4","");
                                                    System.out.println(fileName);
                                                    downloadFile(IrrigationPage.this, fileName, "mp4", Environment.DIRECTORY_DOWNLOADS, url);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });
                                        }
                                    });
                                    ll.addView(btn);
                                }
                            });
                        }
                        IrrigationPage.this.setContentView(sv);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Uh-oh, an error occurred!
                        System.out.println(e);
                    }
                });
    }
    private Uri getMedia(String mediaName) {
        if (URLUtil.isValidUrl(mediaName)) {
            // media name is an external URL
            return Uri.parse(mediaName);
        } else { // media name is a raw resource embedded in the app
            return Uri.parse("android.resource://" + getPackageName() +
                    "/raw/" + mediaName);
        }
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