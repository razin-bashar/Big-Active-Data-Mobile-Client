package com.example.root.bloodbank;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.TextView;

import com.google.android.gms.fitness.data.Application;

/**
 * Created by root on 9/3/16.
 */
public class detail extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Bundle x=getIntent().getExtras();
        String i=x.getString("dataindex");
        int index=Integer.parseInt(i);
        datainfo info=mydata.datalistinfo.get(index);
        TextView a= (TextView) findViewById(R.id.textView);
        TextView b= (TextView) findViewById(R.id.textView2);
        TextView c= (TextView) findViewById(R.id.textView3);

        a.setText(info.channelName);
        b.setText(info.channelExecutionTime);
        c.setText(info.userSubscriptionId);
       // d.setText(info.message);
       // e.setText(info.timestamp);
       // f.setText(info.status);


    }

}
