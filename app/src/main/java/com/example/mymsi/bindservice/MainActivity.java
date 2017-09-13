package com.example.mymsi.bindservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MyService myService = null;
    private  static  final String TAG = "MainActivityTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = (Button)findViewById(R.id.buttonStart);
        Button btnStop = (Button)findViewById(R.id.buttonStop);
        Button btnUse = (Button)findViewById(R.id.buttonUse);

        final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.v(TAG, "onServiceConnected");
                myService = ((MyService.LocalBinder) iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.v(TAG, "onServiceDisconnected");
            }
        };
        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
            }
        });
        btnUse.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(myService!=null){
                    Log.v(TAG,"Using Service:" + myService.add(10,2));
                }
            }
        });
    }

}
