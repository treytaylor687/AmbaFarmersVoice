package com.example.ambafamersvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import android.webkit.URLUtil;
import android.widget.TextView;

public class CerealsPage extends AppCompatActivity {

    private static final String VIDEO_SAMPLE =
            "https://firebasestorage.googleapis.com/v0/b/amba-farmers-voice-d1a51.appspot.com/o/rocket_stove.mp4?alt=media&token=77bf59cd-1898-4020-aa63-f2c8f29547a7";
    private VideoView mVideoView;
    private TextView mBufferingTextView;
    private int mCurrentPosition = 0;
    private static final String PLAYBACK_TIME = "play_time";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cereals_page);

        mBufferingTextView = findViewById(R.id.buffering_textview);
        mVideoView = findViewById(R.id.videoview);

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }

        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controller);

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

    private void initializePlayer() {
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        mBufferingTextView.setVisibility(VideoView.VISIBLE);
        mVideoView.setVideoURI(videoUri);

        mVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mBufferingTextView.setVisibility(VideoView.INVISIBLE);

                        if (mCurrentPosition > 0) {
                            mVideoView.seekTo(mCurrentPosition);
                        } else {
                            mVideoView.seekTo(1);
                        }

                        mVideoView.start();
                    }
                });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(CerealsPage.this, "Playback completed",
                                Toast.LENGTH_SHORT).show();
                        mVideoView.seekTo(1);
                    }
                });
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
    }

    private void releasePlayer() {
        mVideoView.stopPlayback();
    }

    @Override
    protected void onStart() {
        super.onStart();

        initializePlayer();
    }
    @Override
    protected void onStop() {
        super.onStop();

        releasePlayer();
    }
    @Override
    protected void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }

}