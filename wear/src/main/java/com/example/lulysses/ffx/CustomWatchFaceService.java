package com.example.lulysses.ffx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine
    {
        static final int MSG_UPDATE_TIME = 0;
        static final int INTERACTIVE_UPDATE_RATE_MS = 500;

        /* a time object */
        Time mTime;

        /* device features */
        boolean mLowBitAmbient;

        /* graphic objects */
        Bitmap mBackgroundBitmap[];
        Bitmap mScaledBackgroundBitmap[];


        @Override
        public void onCreate(SurfaceHolder holder)
        {
            super.onCreate(holder);

            /* load the background image */
            Resources resources = CustomWatchFaceService.this.getResources();
            Drawable backgroundDrawable = resources.getDrawable(R.drawable.bg);
            mBackgroundBitmap = ((BitmapDrawable) backgroundDrawable).getBitmap();



        }


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

        /* receiver to update the time zone */
        final BroadcastReceiver mTimeZoneReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mTime.clear(intent.getStringExtra("time-zone"));
                mTime.setToNow();
            }
        };

        @Override
        public void onPropertiesChanged(Bundle properties) {
            super.onPropertiesChanged(properties);
            /* get device features (burn-in, low-bit ambient) */
        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            /* the time changed */
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);
            /* the wearable switched between modes */
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            /* draw your watch face */
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            /* the watch face became visible or invisible */
        }



    }
}
