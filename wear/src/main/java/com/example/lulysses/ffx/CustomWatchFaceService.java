package com.example.lulysses.ffx;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.text.format.Time;
import android.view.SurfaceHolder;

import java.util.concurrent.TimeUnit;

/**
 * Created by lulysses on 27/04/2015.
 */
public class CustomWatchFaceService extends CanvasWatchFaceService {

    @Override
    public Engine onCreateEngine()
    {
        return new SimpleEngine();
    }

    private class SimpleEngine extends Engine
    {

        static final int MSG_UPDATE_TIME = 0;
        /* a time object */
        Time mTime;

        /* device features */
        boolean mLowBitAmbient;

        /* graphic objects */
        Bitmap mBackgroundArray[];


        final Handler mUpdateTimeHandler  = new Handler()
        {
            @Override
            public void handleMessage(Message message)
            {
                switch(message.what)
                {
                    case MSG_UPDATE_TIME:
                        invalidate();
                        if(shouldTimerBeRunning())
                        {
                            long timeMs = System.currentTimeMillis();
                            long delayMs = INTERACTIVE_UPDATE_RATE_MS -
                                    (timeMs % INTERACTIVE_UPDATE_RATE_MS);
                            mUpdateTimeHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, delayMsg);
                        }
                        break;
                }
            }
        };


        @Override
        public void onCreate(SurfaceHolder holder)
        {
            super.onCreate(holder);


        }

        private void startTimerIfNecessary()
        {

        }



        @Override
        public void onVisibilityChanged(boolean visible)
        {
            super.onVisibilityChanged(visible);
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);
        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            invalidate();
        }


        @Override
        public void onDestroy() {
            super.onDestroy();
        }


    }
}
