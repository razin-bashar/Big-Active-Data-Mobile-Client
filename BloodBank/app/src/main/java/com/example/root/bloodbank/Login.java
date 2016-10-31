package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

       // import org.apache.http.NameValuePair;
       // import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

/**
 * Created by Razin on 4/26/2016.
 */
public class Login extends Activity {
    Button login;
    String pid;
    TextView email;
    TextView pass;
    String mail;
    String password;
    String ID;
    public static final String REG_ID = "regId";

    public static Context logincontext=null;


    // JSON parser class

    // single product url


    AsyncTask<Void, Void, String> client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login=(Button)findViewById(R.id.logincom);
        email=(TextView)findViewById(R.id.editText4);
        pass=(TextView)findViewById(R.id.editText5);


    }
    public void dologin(View view) throws JSONException, InterruptedException {

        String method="login";
        mail=email.getText().toString();
        password=pass.getText().toString();
        ID=getRegistrationId(getApplicationContext());
        Backgroundlogin bk=new Backgroundlogin(this);
        bk.execute(method, mail, password,ID);

    }
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(
                MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i("getregid", "Registration not found.");
            return "";
        }
        return registrationId;
    }

}
