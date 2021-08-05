package com.example.focuson;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class AlwaysOnButton extends Service {
    WindowManager mWindowManager;
    View mView;


    @Override
    public IBinder onBind(Intent intent) { //service class method
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CBI","cbicbi");

        LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mLayoutInflater.inflate(R.layout.service_alwayson_button,null);

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        |WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        |WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.START | Gravity.TOP;
        mWindowManager.addView(mView,params);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mWindowManager != null){
            if(mView != null){
                mWindowManager.removeView(mView);
                mView = null;
            }
            mWindowManager = null;
        }
    }
}
