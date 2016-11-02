package com.example.root.bloodbank;

//package com.example.razin.gcminstall;


        import java.io.IOException;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager.NameNotFoundException;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.gcm.GoogleCloudMessaging;

public class getregId/* extends Activity*/ {

    Button btnGCMRegister;
    Button btnAppShare;
    GoogleCloudMessaging gcm;
    Context context;
    String regId;
    EditText txtName;
    EditText txtEmail;

    public static final String REG_ID = "regId";
    private static final String APP_VERSION = "appVersion";

    static final String TAG = "Register Activity";


    public getregId(Context context){
        this.context=context;
    }
    public String registerGCM() {

        gcm = GoogleCloudMessaging.getInstance(context);
       regId = getRegistrationId(context);

        if (TextUtils.isEmpty(regId)) {

            registerInBackground();

            Log.d("getregId",
                    "registerGCM - successfully registered with GCM server - regId: "
                            + regId);
        } else {
            Toast.makeText(context,
                    "RegId already available. RegId: " + regId,
                    Toast.LENGTH_LONG).show();
        }
        System.out.println(regId);
        return regId;
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(
                Home.class.getSimpleName(), Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        int registeredVersion = prefs.getInt(APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            Log.d("getregId",
                    "I never expected this! Going down, going down!" + e);
            throw new RuntimeException(e);
        }
    }

    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regId = gcm.register(Config.GOOGLE_PROJECT_ID);
                    Log.d("getregId", "registerInBackground - regId: "
                            + regId);
                    msg = "Device registered, registration ID=" + regId;

                    storeRegistrationId(context, regId);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    Log.d("getregId", "Error: " + msg);
                }
                Log.d("getregId", "AsyncTask completed: " + msg);
                //regId=msg;
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                Toast.makeText(context,
                        "Registered with GCM Server." + msg, Toast.LENGTH_LONG)
                        .show();
            }
        }.execute(null, null, null);
    }

    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = context.getSharedPreferences(
                Home.class.getSimpleName(), Context.MODE_PRIVATE);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putInt(APP_VERSION, appVersion);
        editor.commit();
    }
}

