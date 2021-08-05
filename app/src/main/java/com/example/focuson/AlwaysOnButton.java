package com.example.focuson;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class AlwaysOnButton extends Service {
    WindowManager mWindowManager;
    FocusOnLayout mView;

    @Override
    public IBinder onBind(Intent intent) { //service class method
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CBI","cbicbi");

        LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = (FocusOnLayout)mLayoutInflater.inflate(R.layout.service_alwayson_button,null);
        mView.setMyKeyEventCallbackListener(new FocusOnLayout.MyKeyEventCallbackListener() {
            @Override
            public void onKeyEvent(KeyEvent event) {
                Log.d("cbi","back:");
            }
        });
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        |WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE //이 윈도우 매니저가 띄우는 투명한 창은 터치를 받지 않음
                        |WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        |WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        |WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, //얘가있어야하네!
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
