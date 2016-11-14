package com.example.root.bloodbank;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.List;

import BADclientPack.Data_receive_service;
import BADclientPack.GCMNotificationIntentService;
import BADclientPack.GcmBroadcastReceiver;

/**
 * Created by root on 11/12/16.
 */
public class Notification_Service extends GCMNotificationIntentService {
    @Override
    protected void onHandleIntent(Intent intent) {
        intent.putExtra("Service",Get_result.class.getName());

        super.onHandleIntent(intent);
    }

    @Override
    public void buildnoti(String msg) {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> services = activityManager
                .getRunningTasks(Integer.MAX_VALUE);
        boolean isActivityFound = false;

        if (services.get(0).topActivity.getPackageName().toString()
                .equalsIgnoreCase(getApplicationContext().getPackageName().toString())) {
            isActivityFound = true;
        }
        if(!isActivityFound) {
            Log.d(TAG, "Preparing to send notification...: " + msg);
            mNotificationManager = (NotificationManager) this
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            Intent intent = new Intent(this, data.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    intent, 0);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                    this).setSmallIcon(R.drawable.gcm_cloud)
                    .setContentTitle("GCM Notification")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                    .setContentText(msg);

            mBuilder.setContentIntent(contentIntent);
            mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
            Log.d(TAG, "Notification sent successfully.");
            ComponentName comp2 = new ComponentName(GcmBroadcastReceiver.conbr.getPackageName(),
                    Get_result.class.getName());
            WakefulBroadcastReceiver.startWakefulService(GcmBroadcastReceiver.conbr, (GcmBroadcastReceiver.in.setComponent(comp2)));
        }
        else {
            ComponentName comp2 = new ComponentName(GcmBroadcastReceiver.conbr.getPackageName(),
                    Get_result.class.getName());
            WakefulBroadcastReceiver.startWakefulService(GcmBroadcastReceiver.conbr, (GcmBroadcastReceiver.in.setComponent(comp2)));

            Intent intentu = new Intent(this, automatic_show_service.class);
            startService(intentu);

        }
    }

}
