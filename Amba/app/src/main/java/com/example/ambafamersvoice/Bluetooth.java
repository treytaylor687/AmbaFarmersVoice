package com.example.ambafamersvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bluetooth extends AppCompatActivity {

    Button btn_on, btn_off;

    TextView status;

    private BluetoothAdapter bluetoothAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        btn_on = (Button) findViewById(R.id.on_btn);
        btn_off = (Button) findViewById(R.id.off_btn);
        status = (TextView) findViewById(R.id.status);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bluetoothAdapter.isEnabled()) {
                    Intent ak = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(ak, 0);
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
    }
}