package com.example.focuson;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

public class FocusOnLayout extends RelativeLayout {
    private MyKeyEventCallbackListener myKeyEventCallbackListener;

    public FocusOnLayout(@NonNull Context context) {
        super(context);
    }

    public FocusOnLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusOnLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FocusOnLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public interface MyKeyEventCallbackListener{
        void onKeyEvent(KeyEvent event);
    }

    public void setMyKeyEventCallbackListener(MyKeyEventCallbackListener callback){
        this.myKeyEventCallbackListener = callback;
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN)
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    if(myKeyEventCallbackListener != null)
                        myKeyEventCallbackListener.onKeyEvent(event);
                    break;
                default:
            }
        return super.dispatchKeyEvent(event);
    }


}
