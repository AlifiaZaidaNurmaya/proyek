package org.proyek.parkirassistant;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import org.proyek.parkirassistant.BookingActivity;
import org.proyek.parkirassistant.SharedPrefManager;

import java.io.Serializable;

import androidx.annotation.Nullable;

public class BroadcastCountdownService extends Service {

    private static final String TAG = "BroadcastService";

    public static final String COUNTDOWN_BR = "org.proyek.parkirassistant";
    Intent bi = new Intent(COUNTDOWN_BR);

    CountDownTimer cdt = null;

    @Override
    public void onCreate() {
        super.onCreate();


        Log.i(TAG, "Starting timer...");

//        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
//        long millIsLeft = sharedPreferences.getLong("mTimeLeftInMills",900000);
//        if(millIsLeft / 1000 == 0){
//            millIsLeft = 900000;
//        }
        cdt = new CountDownTimer(900000,1000) {
            @Override
            public void onTick(long millIsUntilFinished) {
                Log.i(TAG, "Countdown seconds remaining: " + millIsUntilFinished/ 1000);
                bi.putExtra("countdown",millIsUntilFinished);
                bi.putExtra("timeup",true);
                sendBroadcast(bi);

            }

            @Override
            public void onFinish() {
                bi.putExtra("timeup",false);
                sendBroadcast(bi);
                Log.i(TAG, "Timer finished");
            }
        };
    }

    @Override
    public void onDestroy() {
        cdt.cancel();
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
