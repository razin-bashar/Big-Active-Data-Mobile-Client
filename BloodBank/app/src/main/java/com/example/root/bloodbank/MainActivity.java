package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import android.app.Activity;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends Activity {

    ShareExternalServer appUtil;
    String regId;
    String name;
    String email;
    TextView lblMessage;
    String res;
    AsyncTask<Void, Void, String> shareRegidTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  appUtil = new ShareExternalServer();
        lblMessage = (TextView) findViewById(R.id.lblMessage);
        regId = getIntent().getStringExtra("regId");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        Log.d("MainActivity", "regId: " + regId);

        final Context context = this;
        shareRegidTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String result = appUtil.shareRegIdWithAppServer(context, regId,name,email,null,null);
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                shareRegidTask = null;
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

        };
        shareRegidTask.execute(null, null, null);*/
    }

}