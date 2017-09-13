package com.example.mymsi.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }
    private  static  final String TAG = "MyServiceTag";
    private LocalBinder localBinder = new LocalBinder();
    public class LocalBinder extends Binder{
            MyService getService(){
                return MyService.this;
            }
    }
    public int add(int x,int y){
        return x+y;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG,"onBind");
        return localBinder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }
}
