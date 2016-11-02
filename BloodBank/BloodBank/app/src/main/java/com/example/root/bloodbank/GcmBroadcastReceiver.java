package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import android.app.Activity;
        import android.content.ComponentName;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
 public static Context conbr;
    public static Intent in;
    public static SharedPreferences storage=null;

    @Override
    public void onReceive(Context context, Intent intent) {
        conbr=context;
        in=intent;
       ComponentName comp = new ComponentName(context.getPackageName(),
                GCMNotificationIntentService.class.getName());



       startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}