package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Looper;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Objects;
        import android.os.Handler;

/**
 * Created by Razin on 4/12/2016.
 */
public class data extends Activity implements AdapterView.OnItemClickListener {

    ListView l;
    public static ArrayAdapter<String>adapter;
    public static mydata md;
    public static Context context;
    public static int yesservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getApplicationContext();
        md=new mydata(getApplicationContext());
        System.out.println("onCreate");
        setContentView(R.layout.data);
        l= (ListView) findViewById(R.id.listView);
        show();





    }

    public void show(){
        try {
            md.getdata();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Collections.reverse(mydata.datalist);
        adapter=new ArrayAdapter<String>(this,R.layout.datalistview,mydata.datalist);
        // adapter.notifyDataSetChanged();

        l.setAdapter(adapter);
        l.setOnItemClickListener(this);

    }
    public static void stimulate(){
        new Handler(Looper.getMainLooper()).post(new Runnable() { // Tried new Handler(Looper.myLopper()) also
            @Override
            public void run() {
                System.out.println("addd");
                if(adapter!=null){
                    System.out.println("adapter not null");
                     adapter.clear();
                    try {
                        md.getdata();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Collections.reverse(mydata.datalist);
                    adapter.addAll(mydata.datalist);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getApplicationContext(),detail.class);
        intent.putExtra("dataindex",i+"");
        startActivity(intent);
       // finish();
    }
    public void delete(View view){
        md.delete(getApplicationContext());
        show();
    }


}
