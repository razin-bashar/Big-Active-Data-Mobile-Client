package com.example.root.bloodbank;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 10/27/16.
 */
public class channellist extends Activity{
    TextView group;
    TextView location;
    TextView time;
    String g;
    String l;
    String d;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list);
        group=(TextView)findViewById(R.id.editText6);
        location=(TextView)findViewById(R.id.editText7);
        time=(TextView)findViewById(R.id.editText8);
    }
   public void nearbyTweetChannel(View view)throws JSONException {
        JSONObject data=new JSONObject();
        JSONArray pram=new JSONArray();
       g=group.getText().toString();
        pram.put(g);
        data.put("dataverseName","channels");
        data.put("userId",Backgroundlogin.userid);
        data.put("accessToken",Backgroundlogin.accesstoken);
        data.put("channelName","nearbyTweetChannel");
        data.put("parameters",pram);
        post_http listsub=new post_http(Config.LIST_SUB,data.toString());
        listsub.execute();

    }
    public void nearbyDonationGroupLocation(View view)throws JSONException{
        JSONObject data=new JSONObject();
        JSONArray pram=new JSONArray();
        g=group.getText().toString();
        l=location.getText().toString();
        pram.put(g);
        pram.put(l);
        data.put("dataverseName","channels");
        data.put("userId",Backgroundlogin.userid);
        data.put("accessToken",Backgroundlogin.accesstoken);
        data.put("channelName","nearbyDonationGroupLocation");
        data.put("parameters",pram);
        post_http listsub=new post_http(Config.LIST_SUB,data.toString());
        listsub.execute();
    }

    public void nearbyDonationGroupLocTime(View view)throws JSONException{
        JSONObject data=new JSONObject();
        JSONArray pram=new JSONArray();
        g=group.getText().toString();
        l=location.getText().toString();
        d=time.getText().toString();
        pram.put(g);
        pram.put(l);
        pram.put(d);
        data.put("dataverseName","channels");
        data.put("userId",Backgroundlogin.userid);
        data.put("accessToken",Backgroundlogin.accesstoken);
        data.put("channelName","nearbyDonationGroupLocTime");
        data.put("parameters",pram);
        post_http listsub=new post_http(Config.LIST_SUB,data.toString());
        listsub.execute();
    }

}
