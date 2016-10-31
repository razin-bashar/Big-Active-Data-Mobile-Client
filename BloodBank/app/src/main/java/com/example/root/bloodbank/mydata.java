package com.example.root.bloodbank;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by root on 10/2/16.
 */
public class mydata {
    public static ArrayList<String> datalist=null;
    public static ArrayList<datainfo>datalistinfo=null;
    public Context ctx;
    public mydata(Context c){
        datalist=new ArrayList<>();
        datalistinfo=new ArrayList<>();
        ctx=c;
    }
    public void getdata() throws JSONException {

        SharedPreferences storage=ctx.getSharedPreferences("mydatabase", Context.MODE_PRIVATE);
        String num=storage.getString("counter","");
        if(num==null||num.isEmpty())return;
        int j=Integer.parseInt(num);
        Log.i("xxx",j+"");
        datalistinfo=new ArrayList<>();
        datalist=new ArrayList<>();
        int number=1;
        for(int i=1;i<=j;i++) {
            String data1=storage.getString("counter"+i,"");
          //  System.out.println("data1:"+data1);
            if (data1.isEmpty()) {
                Log.i("baa", "Registration not found.");

            }
            else {
              //  Log.i("ssss",data1);
            }
            if(data1.contains("error"))continue;
            JSONObject js=new JSONObject(data1);

            String channelName=js.getString("channelName");
            String channelExecutionTime=js.getString("channelExecutionTime");
            String userSubscriptionId=js.getString("userSubscriptionId");
            String status=js.getString("status");
            JSONArray arra=js.getJSONArray("results");
          //  "name": "Tasnim1", "number": 1717223936, "current": "2016-10-28T07:13:34.466Z", "address": "eskaton"
            for (int jj=0;jj<arra.length();jj++) {
                JSONObject data = arra.getJSONObject(jj);
                String name = data.getString("name");
                String number1 = data.getString("number");
                String address=data.getString("address");
                datainfo x=new datainfo();
                x.channelName=name;
                x.channelExecutionTime=number1;
                x.userSubscriptionId=address;
                //x.status=status;
               // x.message=message;
               // x.timestamp=timestamp;
                datalistinfo.add(x);
                datalist.add("Data"+number);
                number++;
            }
        }
    }
    public void delete(Context ct){
        SharedPreferences storage=ct.getSharedPreferences("mydatabase",Context.MODE_PRIVATE);
        String num=storage.getString("counter","");
        SharedPreferences.Editor editor=storage.edit();
        int j=Integer.parseInt(num);
        Log.i("xxx",j+"");
        editor.remove("counter");
        for(int i=1;i<j;i++){
            editor.remove("counter"+i);
        }
        editor.clear();
        editor.commit();
        datalist=new ArrayList<>();

    }
}
