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
        Log.i("dataser",extras.getString("channelSubscriptionId"));
        String uid=extras.getString("userId");
        String accesstk=loginfo.getString("logses","");//"8af40c4ef4d06e301a937b94c1eb20ab2ae2adde8187205c86c74fd0";//Backgroundlogin.accesstoken;
        System.out.println("hellloooooo"+accesstk);
        String chname=extras.getString("channelName");
        String sub=extras.getString("userSubscriptionId");
        String exetime=extras.getString("channelExecutionTime");
        String dv=extras.getString("dataverseName");
        String url=Config.GET_DATA;

        //receive_data rvdata=new receive_data(this,uid,accesstk,chname,sub,exetime,dv);
       // rvdata.execute();
        try{
            JSONObject datainfo=new JSONObject();

           /* dataverseName = post_data['dataverseName']
            userId = post_data['userId']
            accessToken = post_data['accessToken']
            channelName = post_data['channelName']
            userSubscriptionId = post_data['userSubscriptionId']
            channelExecutionTime = post_data['channelExecutionTime']*/


            datainfo.put("dataverseName",dv);
            datainfo.put("userId",uid);
            datainfo.put("accessToken",accesstk);
            datainfo.put("userSubscriptionId",sub);
            datainfo.put("channelName",chname);
            datainfo.put("channelExecutionTime",exetime);

            URL urll = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection) urll.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);
            httpCon.setFixedLengthStreamingMode(datainfo.toString().getBytes().length);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            OutputStream out = httpCon.getOutputStream();
            out.write(datainfo.toString().getBytes());
            out.close();


            InputStream is = httpCon.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            //is.close();
            jsonn = sb.toString();
            System.out.println(jsonn);

            storedata(jsonn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void storedata(String s){

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
