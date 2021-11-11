package com.example.ambafamersvoice;
//switch nullable import if not work
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;


public class Bluetooth extends AppCompatActivity {

    Button btn_on, btn_off, btn_discover, btn_paired;

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

    }
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent date) {
            switch (requestCode) {
                case 0:
                    if (requestCode == RESULT_OK) {
                        status.setText("on");
                        showToast("Bluetooth is on");
                    } else {
                        status.setText("off");
                        showToast("Bluetooth is off");
                    }
                    break;

            }
            super.onActivityResult(requestCode, resultCode, date);
        }



        private void showToast(String msg){
            Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
        }


}
