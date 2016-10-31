package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.provider.Settings;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

/**
 * Created by Razin on 4/25/2016.
 */
public class Home extends Activity {
    public static String loginsession="no";

    public static int datacounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        SharedPreferences storage=getSharedPreferences("mydatabase", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=storage.edit();
        String count=storage.getString("counter","");
        if(count.isEmpty()) {
            editor.putString("counter", "0");
            editor.commit();
        }
        Button b1 = (Button) findViewById(R.id.register);
        Button b2 = (Button) findViewById(R.id.login);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
               // finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent1 = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent1);
                   // finish();
                
            }
        });
    }
        public void viewdata(View view){
            Intent intent = new Intent(getApplicationContext(), data.class);
            startActivity(intent);
           // finish();
        }
        public  void subscribe(View view)  {
            Intent intent=new Intent(getApplicationContext(),channellist.class);
            startActivity(intent);

        }
    public  void insert(View view){
        Intent intent=new Intent(getApplicationContext(),insertdata.class);
        startActivity(intent);
    }


}
