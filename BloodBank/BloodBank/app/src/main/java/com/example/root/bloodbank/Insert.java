package com.example.root.bloodbank;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
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
 * Created by root on 10/30/16.
 */
public class Insert extends Activity{

    TextView name;
    TextView location;
    TextView group;
    TextView phone;
    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);
        name=(TextView)findViewById(R.id.editTextname);
        location=(TextView)findViewById(R.id.editTextaddress);
        phone=(TextView)findViewById(R.id.editTextphone);
        group=(TextView)findViewById(R.id.editTextblood);
        email=(TextView)findViewById(R.id.editTextemail);

    }
    @TargetApi(Build.VERSION_CODES.N)
    public void doinsert(View view) throws JSONException, ExecutionException, InterruptedException {
        JSONObject data=new JSONObject();
        JSONArray pram=new JSONArray();
        String name1=name.getText().toString();
        String location1=location.getText().toString();
        String phone1=phone.getText().toString();
        String email1=email.getText().toString();
        String g=group.getText().toString();

       // "name":"kabir3",
         //       "phone_number":01717223936,
          //      "address":"eskaton",
           //     "blood_group":"O+",
            //    "gcm_reg_id":7,
           //     "email_address":"x555@yahoo.com",
           //     "password":"2234",
           //     "time_of_join":datetime("2016-09-10T16:30:00"),
           //     "status":"active",
           //     "preferred_time":"Sunday"

       // Calendar cal = Calendar.getInstance();
       // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        //System.out.println( sdf.format(cal.getTime()) );
        String indata="[{\"name\":\""+name1+"\",\"phone_number\":"+phone1+",\"address\":\""+location1+"\",\"blood_group\":\""+g+"\",\"email_address\":\""+email1+"\",\"time_of_join\":current-datetime( )"+",\"status\":\"active\",\"preferred_time\":\"Sunday\"}]";
      // pram.put(indata);


        SharedPreferences loginfo=getSharedPreferences("loginsession", Context.MODE_PRIVATE);

        data.put("dataverseName","channels");
        data.put("userId",loginfo.getString("userid",""));
        data.put("accessToken",loginfo.getString("logses",""));
        data.put("datasetName","Donors");
        data.put("records",indata);


      //  post_http listsub=new post_http(Config.insert,data.toString());
      //  listsub.execute();
        BADclient client=new BADclient();

        JSONObject result=client.Insert(Config.insert,data);
        Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
    }
}
