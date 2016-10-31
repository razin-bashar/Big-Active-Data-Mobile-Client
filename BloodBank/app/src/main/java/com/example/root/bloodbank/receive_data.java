package com.example.root.bloodbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
public class receive_data extends AsyncTask<String,String,String>{
    ProgressDialog pDialog;
    Context context;
    String url;
    String jsonn = "";
    String userId;
    String accessToken;
    String channelName;
    String userSubscriptionId;
    String channelExecutionTime;
    String dataverseName;

    receive_data(Context ctx,String uid,String accesstk,String chname,String sub,String exetime,String dv) {
        context = ctx;
        this.url =Config.GET_DATA;
        userId=uid;
        accessToken=accesstk;
        channelName=chname;
        userSubscriptionId=sub;
        channelExecutionTime=exetime;
        dataverseName=dv;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Login is processing. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            JSONObject datainfo=new JSONObject();

           /* dataverseName = post_data['dataverseName']
            userId = post_data['userId']
            accessToken = post_data['accessToken']
            channelName = post_data['channelName']
            userSubscriptionId = post_data['userSubscriptionId']
            channelExecutionTime = post_data['channelExecutionTime']*/


            datainfo.put("dataverseName",dataverseName);
            datainfo.put("userId",userId);
            datainfo.put("accessToken",accessToken);
            datainfo.put("userSubscriptionId",userSubscriptionId);
            datainfo.put("channelName",channelName);
            datainfo.put("channelExecutionTime",channelExecutionTime);

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonn;
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context,"datacoming success",Toast.LENGTH_LONG).show();
    }
}
