package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import android.app.Activity;
        import android.app.ActivityManager;
        import android.app.IntentService;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.ComponentName;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.SystemClock;
        import android.support.v4.app.NotificationCompat;
        import android.support.v4.content.WakefulBroadcastReceiver;
        import android.util.Log;

        import com.google.android.gms.gcm.GoogleCloudMessaging;

        import java.util.List;

public class GCMNotificationIntentService extends IntentService {

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    public static String datames;

    public GCMNotificationIntentService() {
        super("GcmIntentService");
    }

    public static final String TAG = "GCMNotificationIntentService";

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
                    .equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
                    .equals(messageType)) {
                sendNotification("Deleted messages on server: "
                        + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
                    .equals(messageType)) {

                for (int i = 0; i < 3; i++) {
                    Log.i(TAG, "Working... " + (i + 1) + "/5 @ " + SystemClock.elapsedRealtime());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }

                }
                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
                datames=intent.getExtras().getString("channelSubscriptionId");


                sendNotification("Message Received from Google GCM Server: "
                        + datames);
                Log.i(TAG, "Received: " + extras.toString());
            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg) {
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
                    data_receive_service.class.getName());
            WakefulBroadcastReceiver.startWakefulService(GcmBroadcastReceiver.conbr, (GcmBroadcastReceiver.in.setComponent(comp2)));
        }
        else {
            ComponentName comp2 = new ComponentName(GcmBroadcastReceiver.conbr.getPackageName(),
                    data_receive_service.class.getName());
            WakefulBroadcastReceiver.startWakefulService(GcmBroadcastReceiver.conbr, (GcmBroadcastReceiver.in.setComponent(comp2)));

                Intent intentu = new Intent(this, automatic_show_service.class);
                startService(intentu);

        }


    }
}
