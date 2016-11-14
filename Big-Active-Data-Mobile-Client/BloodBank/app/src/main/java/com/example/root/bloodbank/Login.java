package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import android.app.Activity;
        import android.content.Context;
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
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.concurrent.ExecutionException;

        import BADclientPack.BADclient;

/**
 * Created by Razin on 4/26/2016.
 */
public class Login extends Activity {
    Button login;
    String pid;
    TextView userId;
    TextView pass;
    String userid;
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
        userId=(TextView)findViewById(R.id.editText4);
        pass=(TextView)findViewById(R.id.editText5);


    }
    public void dologin(View view) throws JSONException, InterruptedException, ExecutionException {

        userid=userId.getText().toString();
        password=pass.getText().toString();
        ID=getRegistrationId(getApplicationContext());

        JSONObject loginfo=new JSONObject();
        loginfo.put("userName",userid);
        loginfo.put("password",password);
        loginfo.put("dataverseName","channels");
        loginfo.put("platform","android");
        loginfo.put("gcmRegistrationToken",ID);
       // Backgroundlogin bk=new Backgroundlogin(this);
        //bk.execute(method, mail, password,ID);

       // BADclient client=new BADclient();
       // JSONObject result=new JSONObject("nei");

        JSONObject result=BADclient.Login(Config.LOGIN_URL,loginfo);

      //  Thread.sleep(2000);
        Home.userid=result.getString("userId");
        Home.accesstoken=result.getString("accessToken");
        SharedPreferences storage=getApplicationContext().getSharedPreferences("loginsession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=storage.edit();
        editor.putString("logses",Home.accesstoken);
        editor.putString("userid",Home.userid);
        editor.commit();

        Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();

    }
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(
                Home.class.getSimpleName(), Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i("getregid", "Registration not found.");
            return "";
        }
        return registrationId;
    }

}
