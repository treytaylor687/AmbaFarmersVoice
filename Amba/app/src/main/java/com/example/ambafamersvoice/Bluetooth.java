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

    //codes from android studio demo github
    // Name for the SDP record when creating server socket
//    private static final String NAME_SECURE = "BluetoothChatSecure";
//    private static final String NAME_INSECURE = "BluetoothChatInsecure";

    // Unique UUID for this application
//    private static final UUID MY_UUID_SECURE =
//            UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
//    private static final UUID MY_UUID_INSECURE =
//            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
//    private AcceptThread mSecureAcceptThread;
//    private AcceptThread mInsecureAcceptThread;
//    private ConnectedThread mConnectThread;
//    private ConnectedThread mConnectedThread;
    private int mState;
    private int mNewState;
    //initialize it in constructor later
    //private final BluetoothAdapter mAdapter;
    // Constants that indicate the current connection state
//    public static final int STATE_NONE = 0;       // we're doing nothing
//    public static final int STATE_LISTEN = 1;     // now listening for incoming connections
//    public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
//    public static final int STATE_CONNECTED = 3;  // now connected to a remote device
    //temporary adapter
    //private BluetoothAdapter mAdapter;


    Button btn_on, btn_off, btn_discover, btn_paired, btn_share;

    TextView status, paired;

    private BluetoothAdapter bluetoothAdapter;

//    private static final String TAG = "MY_APP_DEBUG_TAG";
//    private Handler handler; // handler that gets info from Bluetooth service
//    private final Handler mHandler;

//    /**
//     * Constructor. Prepares a new BluetoothChat session.
//     *
//     * @param context The UI Activity Context
//     * @param handler A Handler to send messages back to the UI Activity
//     */
//    public Bluetooth(Context context, Handler handler) {
//        mAdapter = BluetoothAdapter.getDefaultAdapter();
//        mState = STATE_NONE;
//        mNewState = mState;
//        mHandler = handler;
//    }

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

        //download part of video

//        public void download() {
//            storageReference = firebaseStorage.getInstance().getReference();
//            ref = storageReference.child("AmbaLogo.jpeg");
//            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    String url = uri.toString();
//                    downloadFile(FeedPage.this, "AmbaLogo", "jpeg", Environment.DIRECTORY_DOWNLOADS, url);
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//
//                }
//            });
//        }
//
//        public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
//            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//            Uri uri = Uri.parse(url);
//            DownloadManager.Request request = new DownloadManager.Request(uri);
//
//            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//            request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);
//            downloadManager.enqueue(request);
//        }

        //codes to check video
        VideoView videoView = (VideoView) findViewById(R.id.videoView);  //casting to VideoView is not Strictly required above API level 26
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.rocketvideo); //set the path of the video that we need to use in our VideoView
        videoView.start();  //start() method of the VideoView class will start the video to play
        MediaController mediaController = new MediaController(this);
//link mediaController to videoView
        mediaController.setAnchorView(videoView);
//allow mediaController to control our videoView
        videoView.setMediaController(mediaController);
    }

//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent date) {
//        switch (requestCode) {
//                case 0:
//                    if (requestCode == RESULT_OK) {
//                        status.setText("on");
//                        showToast("Bluetooth is on");
//                    } else {
//                        status.setText("off");
//                        showToast("Bluetooth is off");
//                    }
//                    break;
//
//            }
//            super.onActivityResult(requestCode, resultCode, date);
//    }



    private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }

//    //codes from bluetooth api
//    private interface MessageConstants {
//        public static final int MESSAGE_READ = 0;
//        public static final int MESSAGE_WRITE = 1;
//        public static final int MESSAGE_TOAST = 2;
//
//        // ... (Add other message types here as needed.)
//    }
//
//    /**
//     * This thread runs while listening for incoming connections. It behaves
//     * like a server-side client. It runs until a connection is accepted
//     * (or until cancelled).
//     */
//    private class AcceptThread extends Thread {
//        // The local server socket
//        private final BluetoothServerSocket mmServerSocket;
//        private String mSocketType;
//
//        public AcceptThread(boolean secure) {
//            BluetoothServerSocket tmp = null;
//            mSocketType = secure ? "Secure" : "Insecure";
//
//            // Create a new listening server socket
//            try {
//                if (secure) {
//                    tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME_SECURE,
//                            MY_UUID_SECURE);
//                } else {
//                    tmp = mAdapter.listenUsingInsecureRfcommWithServiceRecord(
//                            NAME_INSECURE, MY_UUID_INSECURE);
//                }
//            } catch (IOException e) {
//                Log.e(TAG, "Socket Type: " + mSocketType + "listen() failed", e);
//            }
//            mmServerSocket = tmp;
//            mState = STATE_LISTEN;
//        }
//
//        public void run() {
//            Log.d(TAG, "Socket Type: " + mSocketType +
//                    "BEGIN mAcceptThread" + this);
//            setName("AcceptThread" + mSocketType);
//
//            BluetoothSocket socket;
//
//            // Listen to the server socket if we're not connected
//            while (mState != STATE_CONNECTED) {
//                try {
//                    // This is a blocking call and will only return on a
//                    // successful connection or an exception
//                    socket = mmServerSocket.accept();
//                } catch (IOException e) {
//                    Log.e(TAG, "Socket Type: " + mSocketType + "accept() failed", e);
//                    break;
//                }
//
//                 //If a connection was accepted
//                if (socket != null) {
//                    //constructor use later
//                    synchronized (Bluetooth.this) {
//                        switch (mState) {
//                            case STATE_LISTEN:
//                            case STATE_CONNECTING:
//                                // Situation normal. Start the connected thread.
//                                connected(socket, socket.getRemoteDevice(),
//                                        mSocketType);
//                                break;
//                            case STATE_NONE:
//                            case STATE_CONNECTED:
//                                // Either not ready or already connected. Terminate new socket.
//                                try {
//                                    socket.close();
//                                } catch (IOException e) {
//                                    Log.e(TAG, "Could not close unwanted socket", e);
//                                }
//                                break;
//                        }
//                    }
//                }
//            }
//            Log.i(TAG, "END mAcceptThread, socket Type: " + mSocketType);
//
//        }
//        public void cancel() {
//            Log.d(TAG, "Socket Type" + mSocketType + "cancel " + this);
//            try {
//                mmServerSocket.close();
//            } catch (IOException e) {
//                Log.e(TAG, "Socket Type" + mSocketType + "close() of server failed", e);
//            }
//        }
//    }
//
//    private class ConnectedThread extends Thread {
//        private final BluetoothSocket mmSocket;
//        private final InputStream mmInStream;
//        private final OutputStream mmOutStream;
//        private byte[] mmBuffer; // mmBuffer store for the stream
//
//        public ConnectedThread(BluetoothSocket socket) {
//            mmSocket = socket;
//            InputStream tmpIn = null;
//            OutputStream tmpOut = null;
//
//            // Get the input and output streams; using temp objects because
//            // member streams are final.
//            try {
//                tmpIn = socket.getInputStream();
//            } catch (IOException e) {
//                Log.e(TAG, "Error occurred when creating input stream", e);
//            }
//            try {
//                tmpOut = socket.getOutputStream();
//            } catch (IOException e) {
//                Log.e(TAG, "Error occurred when creating output stream", e);
//            }
//
//            mmInStream = tmpIn;
//            mmOutStream = tmpOut;
//        }
//
//        public void run() {
//            mmBuffer = new byte[1024];
//            int numBytes; // bytes returned from read()
//
//            // Keep listening to the InputStream until an exception occurs.
//            while (true) {
//                try {
//                    // Read from the InputStream.
//                    numBytes = mmInStream.read(mmBuffer);
//                    // Send the obtained bytes to the UI activity.
//                    Message readMsg = handler.obtainMessage(
//                            MessageConstants.MESSAGE_READ, numBytes, -1,
//                            mmBuffer);
//                    readMsg.sendToTarget();
//                } catch (IOException e) {
//                    Log.d(TAG, "Input stream was disconnected", e);
//                    break;
//                }
//            }
//        }
//
//        // Call this from the main activity to send data to the remote device.
//        public void write(byte[] bytes) {
//            try {
//                mmOutStream.write(bytes);
//
//                // Share the sent message with the UI activity.
//                Message writtenMsg = handler.obtainMessage(
//                        MessageConstants.MESSAGE_WRITE, -1, -1, mmBuffer);
//                writtenMsg.sendToTarget();
//            } catch (IOException e) {
//                Log.e(TAG, "Error occurred when sending data", e);
//
//                // Send a failure message back to the activity.
//                Message writeErrorMsg =
//                        handler.obtainMessage(MessageConstants.MESSAGE_TOAST);
//                Bundle bundle = new Bundle();
//                bundle.putString("toast",
//                        "Couldn't send data to the other device");
//                writeErrorMsg.setData(bundle);
//                handler.sendMessage(writeErrorMsg);
//            }
//        }
//
//        // Call this method from the main activity to shut down the connection.
//        public void cancel() {
//            try {
//                mmSocket.close();
//            } catch (IOException e) {
//                Log.e(TAG, "Could not close the connect socket", e);
//            }
//        }
//    }



}