package com.example.root.bloodbank;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by root on 9/1/16.
 */
public class data_receive_service extends IntentService {
    public static String jsonn = "";
    public data_receive_service(){
        super("dataservice");

    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences loginfo=getSharedPreferences("loginsession", Context.MODE_PRIVATE);

        Bundle extras = intent.getExtras();
        System.out.println(extras.toString());
        try{
        JSONObject datainfo=new JSONObject();
        datainfo.put("dataverseName",extras.getString("dataverseName"));
        datainfo.put("userId",loginfo.getString("userid",""));
        datainfo.put("accessToken",loginfo.getString("logses",""));
        datainfo.put("userSubscriptionId",extras.getString("userSubscriptionId"));
        datainfo.put("channelName",extras.getString("channelName"));
        datainfo.put("channelExecutionTime",extras.getString("channelExecutionTime"));
            BADclient client=new BADclient();
            JSONObject result=client.GetResult(Config.GET_DATA,datainfo);
            storedata(result.toString());
        }catch (JSONException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    public void storedata(String s){
        if(s.contains("error"))return;
        SharedPreferences storage = getSharedPreferences("mydatabase",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = storage.edit();
        String counter =storage.getString("counter", "");
        int i;
        if(!counter.isEmpty()){
         i=Integer.parseInt(counter);
        i++;}
        else i=1;
        editor.remove("counter");
        editor.putString("counter",i+"");
        editor.putString("counter"+i, s);

        editor.commit();
        Log.i("hihi", "Saving data "+"counter"+i+":"+ storage.getString("counter"+i,""));
    }
}
