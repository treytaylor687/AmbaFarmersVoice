package com.example.ambafamersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class BeePage extends AppCompatActivity {
    private static final String VIDEO_SAMPLE =
            "https://firebasestorage.googleapis.com/v0/b/amba-farmers-voice-d1a51.appspot.com/o/rocket_stove.mp4?alt=media&token=77bf59cd-1898-4020-aa63-f2c8f29547a7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bee_page);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout);
        VideoView video = new VideoView(BeePage.this);
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        video.setVideoURI(videoUri);
        video.setLayoutParams(new FrameLayout.LayoutParams(550, 550));
        layout.addView(video);
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