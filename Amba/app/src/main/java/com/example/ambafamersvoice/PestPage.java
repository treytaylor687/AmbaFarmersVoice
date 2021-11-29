package com.example.ambafamersvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

public class PestPage extends AppCompatActivity {
    private static StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://amba-farmers-voice-d1a51.appspot.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_page);

        storageRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference prefix : listResult.getPrefixes()) {
                            System.out.println(prefix);
                        }

                        ScrollView sv = new ScrollView(PestPage.this);
                        sv.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        LinearLayout ll = new LinearLayout(PestPage.this);
                        ll.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                        ll.setOrientation(LinearLayout.VERTICAL);
                        sv.addView(ll);

                        for (StorageReference item : listResult.getItems()) {
                            // All the items under listRef.
//                            System.out.println(item.getDownloadUrl().toString());
                            String input = item.toString().substring(5);
                            String VIDEO_SAMPLE =
                                    "https://firebasestorage.googleapis.com/v0/b/" + input + "?alt=media&token=77bf59cd-1898-4020-aa63-f2c8f29547a7";
                            VIDEO_SAMPLE = VIDEO_SAMPLE.replace("appspot.com","appspot.com/o");
//                            final TextView text = new TextView(PestPage.this);
//                            text.setText(VIDEO_SAMPLE);
//                            System.out.println(VIDEO_SAMPLE);
//                            String fileName = item.getName().split(".");

                            final TextView textView = new TextView(PestPage.this);
                            textView.setClickable(true);
                            textView.setMovementMethod(LinkMovementMethod.getInstance());
                            String text = "<a href='"+ VIDEO_SAMPLE +"'> "+ item.getName() +" </a>";
                            textView.setText(Html.fromHtml(text));
                            ll.addView(textView);

//                            VideoView video = new VideoView(PestPage.this);
//                            Uri videoUri = getMedia(VIDEO_SAMPLE);
//                            video.setVideoURI(videoUri);
//                            video.setLayoutParams(new FrameLayout.LayoutParams(200, 200));
//                            ll.addView(video);
//                            break;

//                            ll.addView(text);


                        }

                        PestPage.this.setContentView(sv);
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


}