package com.example.ambafamersvoice;
//switch nullable import if not work
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.content.Context;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;


public class Bluetooth extends AppCompatActivity {


    private int mState;
    private int mNewState;



    Button btn_on, btn_off, btn_discover, btn_paired, btn_share;

    TextView status, paired;

    private BluetoothAdapter bluetoothAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        btn_on = (Button) findViewById(R.id.on_btn);
        btn_off = (Button) findViewById(R.id.off_btn);
        status = (TextView) findViewById(R.id.status);
        paired = (TextView) findViewById(R.id.pairedDevice);
        btn_discover = (Button) findViewById(R.id.discover_btn);
        btn_paired = (Button) findViewById(R.id.paired_btn);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //for device without bluetooth letting them know u cant use it
        if (bluetoothAdapter == null) {
            status.setText("Not available");
        } else {
            status.setText("available");
        }

        btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bluetoothAdapter.isEnabled()) {
                    Intent ak = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(ak, 0);
                    //this is alternative code idk which one is better
                    //startActivityForResult(ak, REQUEST_ENABLE_BT);
                    status.setText("on");

                } else {
                    status.setText("on");
                }
            }
        });

        btn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothAdapter.disable();
                status.setText("off");
            }
        });

        btn_discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bluetoothAdapter.isDiscovering()) {
                    showToast("Making your device discoverable");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, 1);
                }
            }
        });

        btn_paired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter.isEnabled()) {
                    paired.setText("Paired device");
                    Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();

                    for (BluetoothDevice device : devices) {
                        paired.append("\n Device : " + device.getName() + ", " + device);
                    }

                } else {
                    showToast("Turn on bluetooth to get paired devices");
                }
            }
        });




//        //codes to check video
//        VideoView videoView = (VideoView) findViewById(R.id.videoView);  //casting to VideoView is not Strictly required above API level 26
//        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.rocketvideo); //set the path of the video that we need to use in our VideoView
//        videoView.start();  //start() method of the VideoView class will start the video to play
//        MediaController mediaController = new MediaController(this);
////link mediaController to videoView
//        mediaController.setAnchorView(videoView);
////allow mediaController to control our videoView
//        videoView.setMediaController(mediaController);
    }




    private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }





}