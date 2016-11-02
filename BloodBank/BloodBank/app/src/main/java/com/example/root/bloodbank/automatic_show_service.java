package com.example.root.bloodbank;

import android.app.IntentService;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 10/2/16.
 */
 public class automatic_show_service extends IntentService {

    public static int serviceon;
    public automatic_show_service(){
        super("automaticshow"); serviceon=0;
    }
    @Override
    public void onCreate() {
       super.onCreate(); serviceon=0;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        data.stimulate();
    }
}
