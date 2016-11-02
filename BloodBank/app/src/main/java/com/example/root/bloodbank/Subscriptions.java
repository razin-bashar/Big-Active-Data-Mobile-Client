package com.example.root.bloodbank;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import BADclientPack.BADclient;

/**
 * Created by root on 10/27/16.
 */
public class Subscriptions extends Activity{
    TextView group;
    TextView location;
    TextView time;
    String g;
    String l;
    String d;
    BADclient client;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list);
        group=(TextView)findViewById(R.id.editText6);
        location=(TextView)findViewById(R.id.editText7);
        time=(TextView)findViewById(R.id.editText8);
        client=new BADclient();
    }
   public void nearbyTweetChannel(View view) throws JSONException, ExecutionException, InterruptedException {
        JSONObject data=new JSONObject();
        JSONArray pram=new JSONArray();
        g=group.getText().toString();
        pram.put(g);
        data.put("dataverseName","channels");
        data.put("userId",Home.userid);
        data.put("accessToken",Home.accesstoken);
        data.put("channelName","nearbyTweetChannel");
        data.put("parameters",pram);
       // post_http listsub=new post_http(Config.LIST_SUB,data.toString());
        //listsub.execute();
       //JSONObject result=new JSONObject("nei");
       JSONObject result=client.Subscribe(Config.LIST_SUB,data);
       Thread.sleep(2000);
       Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
    }
    public void nearbyDonationGroupLocation(View view) throws JSONException, ExecutionException, InterruptedException {
        JSONObject data=new JSONObject();
        JSONArray pram=new JSONArray();
        g=group.getText().toString();
        l=location.getText().toString();
        pram.put(g);
        pram.put(l);
        data.put("dataverseName","channels");
        data.put("userId",Home.userid);
        data.put("accessToken",Home.accesstoken);
        data.put("channelName","nearbyDonationGroupLocation");
        data.put("parameters",pram);
        //post_http listsub=new post_http(Config.LIST_SUB,data.toString());
        //listsub.execute();
        //JSONObject result=new JSONObject("nei");
        JSONObject result=client.Subscribe(Config.LIST_SUB,data);
        Thread.sleep(2000);
        Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
    }

    public void nearbyDonationGroupLocTime(View view) throws JSONException, ExecutionException, InterruptedException {
        JSONObject data=new JSONObject();
        JSONArray pram=new JSONArray();
        g=group.getText().toString();
        l=location.getText().toString();
        d=time.getText().toString();
        pram.put(g);
        pram.put(l);
        pram.put(d);
        data.put("dataverseName","channels");
        data.put("userId",Home.userid);
        data.put("accessToken",Home.accesstoken);
        data.put("channelName","nearbyDonationGroupLocTime");
        data.put("parameters",pram);
      //  post_http listsub=new post_http(Config.LIST_SUB,data.toString());
       // listsub.execute();
        //JSONObject result=new JSONObject("nei");
        JSONObject result=client.Subscribe(Config.LIST_SUB,data);
        Thread.sleep(2000);
        Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
    }

}
