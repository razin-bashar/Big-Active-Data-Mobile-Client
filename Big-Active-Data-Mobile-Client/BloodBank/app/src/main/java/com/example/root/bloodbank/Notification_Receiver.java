package com.example.root.bloodbank;

import android.content.Context;
import android.content.Intent;

import BADclientPack.GcmBroadcastReceiver;

/**
 * Created by root on 11/12/16.
 */
public class Notification_Receiver extends GcmBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent.putExtra("Service",Notification_Service.class.getName());
        super.onReceive(context, intent);
    }
}
